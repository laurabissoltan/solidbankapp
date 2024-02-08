package kz.singularity.solidbankapp1.transaction.cliui;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.transaction.dao.TransactionDAO;
import kz.singularity.solidbankapp1.account.service.AccountDepositService;
import kz.singularity.solidbankapp1.transaction.model.Transaction;
import kz.singularity.solidbankapp1.transaction.model.TransactionType;
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
