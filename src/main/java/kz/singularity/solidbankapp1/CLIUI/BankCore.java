package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.service.AccountCreationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class BankCore {
    private static long id = 1;
    private long lastAccountNumber = 1;

    AccountCreationService accountCreation;

    @Autowired
    public BankCore(AccountCreationService accountCreation) {
        this.accountCreation = accountCreation;
    }

    public void createNewAccount(AccountType accountType, String clientID) {
        accountCreation.create(accountType, id , clientID, String.valueOf(lastAccountNumber));
        incrementLastAccountNumber();
    }

    public void incrementLastAccountNumber() {
        lastAccountNumber++;
    }
}
