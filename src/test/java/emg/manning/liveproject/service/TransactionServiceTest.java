package emg.manning.liveproject.service;

import emg.manning.liveproject.BetterBankingApplication;
import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.MerchantDetailsRepository;
import emg.manning.liveproject.repository.client.TransactionApiClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {BetterBankingApplication.class})
class TransactionServiceTest {

    @Mock
    private TransactionApiClient transactionApiClient;
    @Mock
    private MerchantDetailsRepository merchantDetailsRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testTransactionCount() {
        Mockito.when(transactionApiClient.findAllByAccountNumber(any())).thenReturn(List.of(new Transaction()));

        assertThat(transactionService.findAllByAccountNumber(123456).size()).isPositive();
    }
}