package kz.singularity.solidbankapp1.service.impl;

import kz.singularity.solidbankapp1.dao.AccountDAO;
import kz.singularity.solidbankapp1.model.AccountDeposit;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.service.AccountDepositService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import static kz.singularity.solidbankapp1.model.AccountType.FIXED;

@AllArgsConstructor
public class AccountDepositServiceImpl implements AccountDepositService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public void deposit(double amount, AccountDeposit account) {
        account.setBalance(account.getBalance() + amount);
        System.out.println(amount + " transferred to account " + account.getClientID() + " successfully");
    }
}

