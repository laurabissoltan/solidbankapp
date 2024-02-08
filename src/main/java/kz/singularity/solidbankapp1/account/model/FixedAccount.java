package kz.singularity.solidbankapp1.account.model;

import jakarta.persistence.Entity;
import kz.singularity.solidbankapp1.account.model.AccountDeposit;
import kz.singularity.solidbankapp1.account.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class FixedAccount extends AccountDeposit {
    public FixedAccount(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, balance, withdrawAllowed);
    }

    public FixedAccount() {

    }
}
