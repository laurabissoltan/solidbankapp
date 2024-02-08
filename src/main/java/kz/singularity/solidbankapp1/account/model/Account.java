package kz.singularity.solidbankapp1.account.model;

import jakarta.persistence.*;
import kz.singularity.solidbankapp1.transaction.model.Transaction;
import lombok.*;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
public class Account {
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Id private String id;
    private String clientID;
    private double balance;
    private boolean withdrawAllowed;

    public Account(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        this.accountType = accountType;
        this.id = id;
        this.clientID = clientID;
        this.balance = balance;
        this.withdrawAllowed = withdrawAllowed;
    }

}
