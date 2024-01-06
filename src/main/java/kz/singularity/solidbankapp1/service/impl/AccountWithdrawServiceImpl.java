package kz.singularity.solidbankapp1.service.impl;
import kz.singularity.solidbankapp1.dao.AccountDAO;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import static kz.singularity.solidbankapp1.model.AccountType.FIXED;


public class AccountWithdrawServiceImpl implements AccountWithdrawService {


    AccountDAO accountDAO;

    @Autowired
    public AccountWithdrawServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void deposit(double amount, AccountWithdraw account) {
        if(account.getAccountType() != FIXED && account.getBalance() >= amount && account.getBalance() > 0) {
            account.setBalance(account.getBalance() - amount);
            System.out.println(amount + " transferred from account " + account.getClientID() + " successfully");
        }
        else if (account.getBalance() < amount || account.getBalance() < 0){
            System.out.println("The withdrawal transaction has been declined due to insufficient money in your account.");
        }

    }
}
