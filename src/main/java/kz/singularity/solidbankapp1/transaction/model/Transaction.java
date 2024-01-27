package kz.singularity.solidbankapp1.transaction.model;

import jakarta.persistence.*;
import kz.singularity.solidbankapp1.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Column(name = "transaction_status", nullable = false)
    private boolean isProcessed;

}
