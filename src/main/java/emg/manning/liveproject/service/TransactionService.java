package emg.manning.liveproject.service;


import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.MerchantDetailsRepository;
import emg.manning.liveproject.repository.TransactionRepository;
import emg.manning.liveproject.repository.client.TransactionApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionService {
    private final MerchantDetailsRepository merchantDetailsRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionApiClient transactionApiClient;

    public TransactionService(final MerchantDetailsRepository merchantDetailsRepository,
                              TransactionRepository transactionRepository, TransactionApiClient transactionApiClient) {
        this.merchantDetailsRepository = merchantDetailsRepository;
        this.transactionRepository = transactionRepository;
        this.transactionApiClient = transactionApiClient;
    }


    @CircuitBreaker(name = "transactionService", fallbackMethod = "findAllByAccountNumberFallback")
    @PostFilter("hasAuthority(filterObject.accountNumber)")
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @PostFilter("hasAuthority(filterObject.accountNumber)")
    public List<Transaction> findAllByAccountNumberFallback(final Integer accountNumber, final Throwable throwable) throws Exception {
        log.error(throwable.getMessage(), throwable);
        return pollByAccountNumber(accountNumber);
    }


    public List<Transaction> pollByAccountNumber(final Integer accountNumber) {
        List<Transaction> serviceTransactions = null;
        try {
            serviceTransactions = transactionApiClient.findAllByAccountNumber(accountNumber);
            serviceTransactions.forEach(t -> merchantDetailsRepository.findLogoByMerchantId(t.getMerchantName()).ifPresent(t::setMerchantLogo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return List.of();
        }
        val databaseTransactions = transactionRepository.findByAccountNumber(accountNumber);
        val transactionsDiff = serviceTransactions.stream().filter(t -> !databaseTransactions.contains(t)).collect(Collectors.toList());

        transactionRepository.saveAll(transactionsDiff);

        return serviceTransactions;
    }


    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    public void poll() {
        log.info("Polling for transactions");
        val accounts = transactionRepository.findAllAccountNumbers();
        accounts.forEach(this::pollByAccountNumber);
    }
}
