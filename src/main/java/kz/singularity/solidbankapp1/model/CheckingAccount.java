package kz.singularity.solidbankapp1.model;

import jakarta.persistence.Entity;


public class CheckingAccount extends AccountWithdraw{
    public CheckingAccount(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, balance, withdrawAllowed);
    }

}
