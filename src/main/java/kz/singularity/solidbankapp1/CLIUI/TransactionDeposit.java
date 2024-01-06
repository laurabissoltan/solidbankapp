package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.dao.TransactionDAO;
import kz.singularity.solidbankapp1.model.AccountDeposit;
import kz.singularity.solidbankapp1.model.Transaction;
import kz.singularity.solidbankapp1.service.AccountDepositService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@AllArgsConstructor
public class TransactionDeposit {

    @Autowired
    AccountDepositService accountDepositService;
    @Autowired
    TransactionDAO transactionDAO;


    public void execute(AccountDeposit accountDeposit, double amount) {
        accountDepositService.deposit(amount, accountDeposit);
        Transaction transaction = new Transaction();
        transactionDAO.addTransaction(transaction);
    }
}
