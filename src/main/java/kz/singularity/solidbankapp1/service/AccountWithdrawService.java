package kz.singularity.solidbankapp1.service;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountWithdraw;

public interface AccountWithdrawService {
    void withdraw(double amount, Account account);
}
