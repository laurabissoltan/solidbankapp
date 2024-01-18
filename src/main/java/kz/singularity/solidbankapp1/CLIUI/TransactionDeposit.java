package kz.singularity.solidbankapp1.CLIUI;

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

    public void execute(Account accountDeposit, double amount) {

        accountDepositService.deposit(amount, accountDeposit);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccount(accountDeposit);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setProcessed(true);

        transactionDAO.addTransaction(transaction);

    }

}
