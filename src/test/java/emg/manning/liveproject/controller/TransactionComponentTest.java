package emg.manning.liveproject.controller;

import emg.manning.liveproject.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
class TransactionComponentTest {
    @Autowired
    private TransactionService transactionService;

    @Test
    void getTransactionsByAccount() {
        given().standaloneSetup(new TransactionController(transactionService))
            .when().get("/transactions/123456")
            .then().statusCode(200);
    }
}
