package emg.manning.liveproject.service;

import emg.manning.liveproject.controller.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
class TransactionComponentTest {
    @Test
    void getTransactionsByAccount() {
        given().standaloneSetup(new TransactionController(new TransactionService()))
            .param("accountNumber", 0)
            .when().get("/transactions/0")
            .then().statusCode(200);
    }
}
