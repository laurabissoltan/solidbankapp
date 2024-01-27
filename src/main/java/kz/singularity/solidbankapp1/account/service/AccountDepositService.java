package kz.singularity.solidbankapp1.account.service;

import kz.singularity.solidbankapp1.account.model.Account;

public interface AccountDepositService {
    void deposit(double amount, Account accountDeposit);
}
