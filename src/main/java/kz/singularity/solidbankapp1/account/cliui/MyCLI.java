package kz.singularity.solidbankapp1.account.cliui;

import kz.singularity.solidbankapp1.transaction.cliui.WithdrawDepositOperationCLIUI;
import kz.singularity.solidbankapp1.account.model.AccountType;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyCLI implements CreateAccountOperationUI, CLIUI, WithdrawDepositOperationCLIUI {

    private Scanner scanner;

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }


    public MyCLI() {
        this.scanner = new Scanner(System.in);
    }

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
        String userInput = scanner.nextLine().toUpperCase();
        //     return AccountType.valueOf(scanner.nextLine());
        try {
            return AccountType.valueOf(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: invalid account type. Please try again");
            return null;
        }
    }
}

