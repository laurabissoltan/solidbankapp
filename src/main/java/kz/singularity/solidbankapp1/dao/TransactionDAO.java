package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Transaction;

import java.util.List;

public interface TransactionDAO {
    List<Transaction> getTransaction();
    void addTransaction(Transaction transaction);
}
