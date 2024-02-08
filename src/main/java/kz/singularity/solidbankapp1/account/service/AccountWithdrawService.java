package kz.singularity.solidbankapp1.account.service;

import kz.singularity.solidbankapp1.account.model.Account;

public interface AccountWithdrawService {
    void withdraw(double amount, Account account);
}
