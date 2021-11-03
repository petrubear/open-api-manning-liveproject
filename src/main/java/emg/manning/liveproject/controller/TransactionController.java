package emg.manning.liveproject.controller;

import emg.manning.liveproject.dto.TransactionDto;
import emg.manning.liveproject.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{accountNumber}")
    public List<TransactionDto> getTransactions(@PathVariable final Integer accountNumber) {
        return transactionService.findAllByAccountNumber(accountNumber)
            .stream().map(TransactionDto::from).collect(Collectors.toList());
    }
}
