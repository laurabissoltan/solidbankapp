package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findAll();
}
