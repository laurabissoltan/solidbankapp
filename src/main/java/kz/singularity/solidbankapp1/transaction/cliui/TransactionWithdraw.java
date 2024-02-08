package kz.singularity.solidbankapp1.transaction.cliui;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.transaction.dao.TransactionDAO;
import kz.singularity.solidbankapp1.account.model.AccountWithdraw;
import kz.singularity.solidbankapp1.transaction.model.Transaction;
import kz.singularity.solidbankapp1.transaction.model.TransactionType;
import kz.singularity.solidbankapp1.account.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@AllArgsConstructor
@Component
public class TransactionWithdraw {
    @Autowired
    AccountWithdrawService accountWithdrawService;
    @Autowired
    TransactionDAO transactionDAO;

    public void execute(Account accountWithdraw, double amount) {

        if (accountWithdraw.isWithdrawAllowed()) {

            accountWithdrawService.withdraw(amount, accountWithdraw);

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.WITHDRAWAL);
            transaction.setAccount(accountWithdraw);
            transaction.setAmount(amount);
            transaction.setTransactionDate(new Date());
            if (accountWithdraw.getBalance() >= amount) {
                transaction.setProcessed(true);
            }

            transactionDAO.addTransaction(transaction);

        }
    }

}
