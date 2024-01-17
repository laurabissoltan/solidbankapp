package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.dao.TransactionDAO;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.model.Transaction;
import kz.singularity.solidbankapp1.model.TransactionType;
import kz.singularity.solidbankapp1.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;


@AllArgsConstructor
@Component
public class TransactionWithdraw {
    @Autowired
    AccountWithdrawService accountWithdrawService;
    @Autowired
    TransactionDAO transactionDAO;

    public void execute(AccountWithdraw accountWithdraw, double amount) {

        accountWithdrawService.withdraw(amount, accountWithdraw);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setAccount(accountWithdraw);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());

        transactionDAO.addTransaction(transaction);

    }
}
