package kz.singularity.solidbankapp1.account.model;

import jakarta.persistence.Entity;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.model.AccountWithdraw;

@Entity
public class SavingAccount extends AccountWithdraw {

    public SavingAccount(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, balance, withdrawAllowed);
    }

    public SavingAccount() {

    }
}
