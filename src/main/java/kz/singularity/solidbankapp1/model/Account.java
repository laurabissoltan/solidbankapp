package kz.singularity.solidbankapp1.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Account")
public class Account {

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Id private String id;
    @Column(name = "CLIENT_ID")
    private String clientID;
    private double balance;
    private boolean withdrawAllowed;
}
