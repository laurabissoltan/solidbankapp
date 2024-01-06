package kz.singularity.solidbankapp1.service;

import kz.singularity.solidbankapp1.model.AccountWithdraw;

public interface AccountWithdrawService {
    void deposit(double amount, AccountWithdraw account);
}
