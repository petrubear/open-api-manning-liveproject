package emg.manning.liveproject.repository;

import emg.manning.liveproject.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByAccountNumber(final Integer accountNumber);
}
