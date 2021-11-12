package emg.manning.liveproject.repository.client.impl;

import com.acme.banking.model.OBReadTransaction6;
import com.acme.banking.model.OBTransaction6;
import com.fasterxml.jackson.databind.JsonNode;
import emg.manning.liveproject.adapter.OBTransactionAdapter;
import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.client.TransactionApiClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class RESTTransactionsAPIClient implements TransactionApiClient {
    private final OBTransactionAdapter adapter = new OBTransactionAdapter();
    private final WebClient webClient;
    @Value("${testnet.client}")
    private String testNetClient;
    @Value("${testnet.secret}")
    private String testNetSecret;

    public RESTTransactionsAPIClient(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        final var obTransactions = retrieveTransactionsInfo(accountNumber);
        return obTransactions.stream().map(adapter::adapt).collect(Collectors.toList());
    }

    private List<OBTransaction6> retrieveTransactionsInfo(final Integer accountNumber) {
        try {
            val credentials = testNetClient + ":" + testNetSecret;
            val encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
            val accesToken = webClient.post()
                .uri("oauth/token")
                .header("Authorization", "Basic " + encodedCredentials)
                .header("Content-Type:application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> response.get("access_token").textValue())
                .block();

            val response = webClient.get()
                .uri("accounts/" + accountNumber + "/transactions")
                .header("Authorization", "Bearer " + accesToken)
                .header("Accept", "application/json")
                .retrieve()
                .bodyToMono(OBReadTransaction6.class)
                .block();

            if (response != null) {
                return response.getData().getTransaction();
            }

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        }
        return List.of();
    }
}
