package emg.manning.liveproject;

import emg.manning.liveproject.model.Transaction;
import emg.manning.liveproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;

@SpringBootApplication
public class BetterBankingApplication {

    @Value("${testnet.service.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(baseUrl).build();
    }


    @Profile("dev")
    @Bean
    public ApplicationRunner dataLoader(TransactionRepository transactionRepository) {
        return args -> {
            transactionRepository.deleteAll();
            transactionRepository.save(Transaction.builder().date(new Date())
                .merchantName("Costco")
                .amount(10.0)
                .accountNumber(1234567)
                .currency("USD").type("debit").merchantLogo("costco-logo.png").build());
            transactionRepository.save(Transaction.builder().date(new Date())
                .merchantName("Globex")
                .amount(123.0)
                .accountNumber(1234567)
                .currency("USD").type("credit").merchantLogo("globex-logo.png").build());
            transactionRepository.save(Transaction.builder().date(new Date())
                .merchantName("Acme")
                .amount(1090.0)
                .accountNumber(1234568)
                .currency("USD").type("debit").merchantLogo("acme-logo.png").build());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BetterBankingApplication.class, args);
    }
}
