package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.Transaction;
import kz.singularity.solidbankapp1.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionDAORepository implements TransactionDAO{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionDAORepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> getTransaction() {
        String sql = "SELECT * FROM Transaction";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Account account = Account.builder().id(resultSet.getString("account_id")).build();
            Transaction transaction = Transaction.builder()
                    .id(resultSet.getInt("id"))
                    .account(account)
                    .transactionType(TransactionType.valueOf(resultSet.getString("transaction_type")))
                    .amount(resultSet.getDouble("amount"))
                    .transactionDate(resultSet.getTimestamp("transaction_date"))
                    .isProcessed(resultSet.getBoolean("transaction_status"))
                    .build();

            return transaction;
        });
    /*    String sql = "SELECT * FROM Transaction";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class));*/
    }

    @Override
    public void addTransaction(Transaction transaction) {
        System.out.println("Transaction ID: " + transaction.getId());
        System.out.println("Account ID: " + transaction.getAccount().getId());
        System.out.println("Transaction Type: " + transaction.getTransactionType());
        System.out.println("Amount: " + transaction.getAmount());
        System.out.println("Transaction Date: " + transaction.getTransactionDate());
        System.out.println("Is Processed: " + transaction.isProcessed());
        String sql = "INSERT INTO Transaction (ACCOUNT_ID, TRANSACTION_TYPE, AMOUNT, TRANSACTION_DATE, TRANSACTION_STATUS) VALUES (?, ?, ?, ?, ?)";



        jdbcTemplate.update(
                sql,
                transaction.getAccount().getId(),
                transaction.getTransactionType().toString(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.isProcessed()
        );
    }
}
