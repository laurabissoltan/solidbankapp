package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Transaction;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class MemoryTransactionDAO implements TransactionDAO{

    List<Transaction> transactions = new ArrayList<Transaction>();
    @Override
    public List<Transaction> getTransaction() {
        return this.transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
