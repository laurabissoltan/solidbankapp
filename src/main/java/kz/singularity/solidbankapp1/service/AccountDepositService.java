package kz.singularity.solidbankapp1.service;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountDeposit;

public interface AccountDepositService {
    void deposit(double amount, Account accountDeposit);
}
