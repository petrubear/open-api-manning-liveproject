package emg.manning.liveproject.controller;

import emg.manning.liveproject.repository.TransactionRepository;
import emg.manning.liveproject.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
class TransactionComponentTest {
    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTransactionsByAccount() {
        given().standaloneSetup(new TransactionController(new TransactionService(transactionRepository)))
            .when().get("/transactions/0")
            .then().statusCode(200);
    }
}
