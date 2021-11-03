package emg.manning.liveproject.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Transaction {
    private String type;
    private LocalDate date;
    private String accountNumber;
    private String currency;
    private double amount;
    private String merchantName;
    private String merchantLogo;
}
