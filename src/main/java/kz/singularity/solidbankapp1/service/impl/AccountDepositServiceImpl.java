package kz.singularity.solidbankapp1.service.impl;

import kz.singularity.solidbankapp1.dao.AccountDAO;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.service.AccountDepositService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountDepositServiceImpl implements AccountDepositService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public void deposit(double amount, AccountWithdraw accountWithdraw) {
        accountWithdraw.setBalance(accountWithdraw.getBalance() + amount);
        accountDAO.updateAccount(accountWithdraw);
        System.out.println(amount + " transferred to account " + accountWithdraw.getId() + " successfully");
    }
}

