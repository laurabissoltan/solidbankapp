package kz.singularity.solidbankapp1.service.impl;

import kz.singularity.solidbankapp1.dao.AccountDAO;
import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountDeposit;
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
    public void deposit(double amount, Account account) {
        account.setBalance(account.getBalance() + amount);
        accountDAO.updateAccount(account);
        System.out.println(amount + " transferred to account " + account.getId() + " successfully");
    }
}

