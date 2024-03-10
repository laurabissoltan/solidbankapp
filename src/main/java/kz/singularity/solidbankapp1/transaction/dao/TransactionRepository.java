package kz.singularity.solidbankapp1.transaction.dao;

import kz.singularity.solidbankapp1.transaction.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findAll();
    List<Transaction> getTransactionByAccount_Id(String accountID);

}
