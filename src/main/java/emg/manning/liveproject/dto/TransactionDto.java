package emg.manning.liveproject.dto;

import emg.manning.liveproject.model.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransactionDto {
    private Long id;
    private String type;
    private Date date;
    private Integer accountNumber;
    private String currency;
    private Double amount;
    private String merchantName;
    private String merchantLogo;

    public static TransactionDto from(Transaction transaction) {
        return TransactionDto.builder()
            .id(transaction.getId())
            .type(transaction.getType())
            .date(transaction.getDate())
            .accountNumber(transaction.getAccountNumber())
            .currency(transaction.getCurrency())
            .amount(transaction.getAmount())
            .merchantName(transaction.getMerchantName())
            .merchantLogo(transaction.getMerchantLogo())
            .build();
    }
}
