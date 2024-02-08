package kz.singularity.solidbankapp1.transaction.dao;

import kz.singularity.solidbankapp1.account.dao.AccountDAO;
import kz.singularity.solidbankapp1.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class TransactionDAOImpl implements TransactionDAO {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionDAOImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(String accountID) {
        return transactionRepository.getTransactionByAccount_Id(accountID);
    }

    @Override
    public void deleteTransactions(String accountID) {
        List<Transaction> transactions = getTransactions(accountID);
        transactionRepository.deleteAll(transactions);
    }


}
