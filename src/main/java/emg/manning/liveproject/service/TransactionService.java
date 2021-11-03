package emg.manning.liveproject.service;


import emg.manning.liveproject.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        return List.of(Transaction.builder().accountNumber(accountNumber).build(),
            Transaction.builder().accountNumber(accountNumber).build(),
            Transaction.builder().accountNumber(accountNumber).build(),
            Transaction.builder().accountNumber(accountNumber).build(),
            Transaction.builder().accountNumber(accountNumber).build());
    }
}
