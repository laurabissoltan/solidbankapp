package kz.singularity.solidbankapp1.account.model;

import jakarta.persistence.Entity;
import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
//@NoArgsConstructor
public class AccountWithdraw extends Account {
    public AccountWithdraw(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, balance, withdrawAllowed);
    }


    public AccountWithdraw() {
    }
}
