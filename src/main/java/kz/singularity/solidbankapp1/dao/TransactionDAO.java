package kz.singularity.solidbankapp1.dao;


import kz.singularity.solidbankapp1.model.Transaction;
import java.util.List;


public interface TransactionDAO {

    public List<Transaction> getTransaction();



    public void addTransaction(Transaction transaction);

}
