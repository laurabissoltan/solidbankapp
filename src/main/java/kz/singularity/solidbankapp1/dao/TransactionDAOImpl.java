package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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

  //  @Transactional
    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
