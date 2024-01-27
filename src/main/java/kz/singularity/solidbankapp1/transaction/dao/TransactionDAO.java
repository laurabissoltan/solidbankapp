package kz.singularity.solidbankapp1.transaction.dao;


import kz.singularity.solidbankapp1.transaction.model.Transaction;
import java.util.List;


public interface TransactionDAO {

    public List<Transaction> getTransaction();

    public void addTransaction(Transaction transaction);

    List<Transaction> getTransactions(String accountID);

    void deleteTransactions(String accountID);

    //  public Transaction getTransactionByAccountID(String accountID);

}
