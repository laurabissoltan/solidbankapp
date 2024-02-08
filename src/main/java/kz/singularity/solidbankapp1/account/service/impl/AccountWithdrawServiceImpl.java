package kz.singularity.solidbankapp1.account.service.impl;
import kz.singularity.solidbankapp1.account.dao.AccountDAO;
import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.service.AccountWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {


    AccountDAO accountDAO;

    @Autowired
    public AccountWithdrawServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void withdraw(double amount, Account account) {
        if(!account.getAccountType().equals(AccountType.FIXED) && account.getBalance() >= amount && account.getBalance() > 0) {
            account.setBalance(account.getBalance() - amount);
            accountDAO.updateAccount(account);
      //      System.out.println(amount + " transferred from account " + account.getId() + " successfully");
        }
        else if (account.getBalance() <= amount ){
    //        System.out.println("The withdrawal transaction has been declined due to insufficient money in your account.");
        }

    }
}
