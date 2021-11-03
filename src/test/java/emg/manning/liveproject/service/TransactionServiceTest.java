package emg.manning.liveproject.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionServiceTest {

    private final TransactionService transactionService = new TransactionService();

    @Test
    void findAllByAccountNumber() {
        final Integer accountNumber = 0;
        assertThat(transactionService.findAllByAccountNumber(accountNumber).size()).isPositive();
    }
}