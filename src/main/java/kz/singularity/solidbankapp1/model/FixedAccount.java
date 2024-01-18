package kz.singularity.solidbankapp1.model;

import jakarta.persistence.Entity;

@Entity
public class FixedAccount extends AccountDeposit {
    public FixedAccount(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, balance, withdrawAllowed);
    }

    public FixedAccount() {

    }
}
