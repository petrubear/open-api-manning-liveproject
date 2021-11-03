package emg.manning.liveproject.service;

import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllByAccountNumber() {
        when(transactionRepository.findByAccountNumber(anyInt())).thenReturn(transactions());
        final var accountNumber = 0;
        var transactionService = new TransactionService(transactionRepository);
        assertThat(transactionService.findAllByAccountNumber(accountNumber).size()).isPositive();
    }

    private List<Transaction> transactions() {
        return List.of(
            Transaction
                .builder()
                .type("credit")
                .date(new Date())
                .accountNumber(0)
                .currency("USD")
                .amount(10.0)
                .merchantName("Amazon")
                .merchantLogo("amazon.png")
                .build()
        );
    }
}