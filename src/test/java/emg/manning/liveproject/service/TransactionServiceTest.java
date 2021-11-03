package emg.manning.liveproject.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionServiceTest {

    private final TransactionService transactionService = new TransactionService();

    @Test
    void findAllByAccountNumber() {
        final String accountNumber = "";
        assertEquals(5, transactionService.findAllByAccountNumber(accountNumber).size());
    }
}