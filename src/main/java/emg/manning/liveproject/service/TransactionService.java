package emg.manning.liveproject.service;


import emg.manning.liveproject.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        return List.of(
            Transaction.builder()
                .type("credit")
                .date(new Date())
                .accountNumber(accountNumber)
                .currency("USD")
                .amount(10.0)
                .merchantName("Amazon")
                .merchantLogo("amazon.png")
                .build());
    }
}
