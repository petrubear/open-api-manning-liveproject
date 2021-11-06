package emg.manning.liveproject.repository.client.impl;

import com.acme.banking.model.OBReadTransaction6;
import com.acme.banking.model.OBTransaction6;
import emg.manning.liveproject.adapter.OBTransactionAdapter;
import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.client.TransactionApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class RESTTransactionsAPIClient implements TransactionApiClient {
    private final OBTransactionAdapter adapter = new OBTransactionAdapter();
    private final WebClient webClient;

    public RESTTransactionsAPIClient(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) throws Exception {
        final var obTransactions = retrieveTransactionsInfo(accountNumber);
        return obTransactions.stream().map(adapter::adapt).collect(Collectors.toList());
    }

    private List<OBTransaction6> retrieveTransactionsInfo(final Integer accountNumber) throws Exception {

        try {
            var result = webClient.get()
                .uri("accounts/" + accountNumber + "/transactions")
                .retrieve()
                .bodyToMono(OBReadTransaction6.class)
                .block();

            if (result != null) {
                return result.getData().getTransaction();
            }

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
        return List.of();
    }
}
