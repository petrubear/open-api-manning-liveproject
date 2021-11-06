package emg.manning.liveproject.repository.client;

import emg.manning.liveproject.model.Transaction;

import java.util.List;

public interface TransactionApiClient {
    List<Transaction> findAllByAccountNumber(final Integer accountNumber);
}
