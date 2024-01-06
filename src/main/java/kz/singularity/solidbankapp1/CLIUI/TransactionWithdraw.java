package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.dao.TransactionDAO;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.model.Transaction;
import kz.singularity.solidbankapp1.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class TransactionWithdraw {
    @Autowired
    AccountWithdrawService accountWithdrawService;
    @Autowired
    TransactionDAO transactionDAO;

    public void execute(AccountWithdraw account, double amount) {
        accountWithdrawService.deposit(amount, account);
        Transaction transaction =  new Transaction();
        transactionDAO.addTransaction(transaction);
    }
}
