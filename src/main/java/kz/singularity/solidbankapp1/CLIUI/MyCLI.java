package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;



@AllArgsConstructor
@Getter
public class MyCLI implements CreateAccountOperationUI, CLIUI, WithdrawDepositOperationCLIUI {

    @Autowired
    private Scanner scanner;

    @Override
    public double requestClientAmount() {
        System.out.println("Type amount of money:");
        return Double.parseDouble(this.scanner.nextLine());
    }
    @Override
    public String requestClientAccountNumber() {
        System.out.println("Type account ID:");
        return this.scanner.nextLine();
    }

    @Override
    public AccountType requestAccountType() {
        return AccountType.valueOf(scanner.nextLine());
    }
}
