package kz.singularity.solidbankapp1.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;



public class AccountDeposit extends Account {
    public AccountDeposit(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, balance, withdrawAllowed);
    }

    public AccountDeposit() {
    }
}
