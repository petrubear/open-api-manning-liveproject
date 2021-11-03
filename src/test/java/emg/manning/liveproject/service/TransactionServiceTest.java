package emg.manning.liveproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TransactionServiceTest {

//    @Mock
//    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;


    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.openMocks(this);
    }

    @Test
//    @Sql(scripts = {"classpath:init_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findAllByAccountNumber() {
//        when(transactionRepository.findByAccountNumber(anyInt())).thenReturn(transactions());
//        var transactionService = new TransactionService(transactionRepository);
        final var accountNumber = 123456;
        assertThat(transactionService.findAllByAccountNumber(accountNumber).size()).isPositive();
    }

    /*private List<Transaction> transactions() {
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
    }*/
}