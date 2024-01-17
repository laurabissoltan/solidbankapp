package kz.singularity.solidbankapp1.service;

import kz.singularity.solidbankapp1.model.AccountWithdraw;

public interface AccountDepositService {
    void deposit(double amount, AccountWithdraw accountDeposit);
}
