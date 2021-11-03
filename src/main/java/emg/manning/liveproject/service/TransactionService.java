package emg.manning.liveproject.service;


import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public List<Transaction> findAllByAccountNumber(final Integer accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}
