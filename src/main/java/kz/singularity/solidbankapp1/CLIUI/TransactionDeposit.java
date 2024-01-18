package kz.singularity.solidbankapp1.CLIUI;

import jakarta.persistence.*;
import kz.singularity.solidbankapp1.dao.TransactionDAO;
import kz.singularity.solidbankapp1.model.*;
import kz.singularity.solidbankapp1.service.AccountDepositService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@AllArgsConstructor
@Component
public class TransactionDeposit {

    @Autowired
    AccountDepositService accountDepositService;
    @Autowired
    TransactionDAO transactionDAO;


    public void execute(AccountWithdraw accountWithdraw, double amount) {

        accountDepositService.deposit(amount, accountWithdraw);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccount(accountWithdraw);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setProcessed(true);

        transactionDAO.addTransaction(transaction);

    }
}
