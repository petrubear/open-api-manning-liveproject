package emg.manning.liveproject.service;


import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.MerchantDetailsRepository;
import emg.manning.liveproject.repository.TransactionRepository;
import emg.manning.liveproject.repository.client.TransactionApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @CircuitBreaker(name = "transactionService", fallbackMethod = "findOnCacheByAccountNumber")
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) throws Exception {
        var transactions = transactionApiClient.findAllByAccountNumber(accountNumber);
        transactions.forEach(t -> merchantDetailsRepository.findLogoByMerchantId(t.getMerchantName()).ifPresent(t::setMerchantLogo));
        return transactions;
    }

    public List<Transaction> findOnCacheByAccountNumber(final Integer accountNumber, final Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}
