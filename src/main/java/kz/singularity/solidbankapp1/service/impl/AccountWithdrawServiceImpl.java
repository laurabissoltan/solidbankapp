package kz.singularity.solidbankapp1.service.impl;
import kz.singularity.solidbankapp1.dao.AccountDAO;
import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.service.AccountWithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import static kz.singularity.solidbankapp1.model.AccountType.FIXED;


@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {


    AccountDAO accountDAO;

    @Autowired
    public AccountWithdrawServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void withdraw(double amount, AccountWithdraw account) {
        if(!account.getAccountType().equals(AccountType.FIXED) && account.getBalance() >= amount && account.getBalance() > 0) {
            account.setBalance(account.getBalance() - amount);
            accountDAO.updateAccount(account);
            System.out.println(amount + " transferred from account " + account.getId() + " successfully");
        } else {
            System.out.println("The withdrawal transaction has been declined due to insufficient money in your account.");
        }
    }
}
