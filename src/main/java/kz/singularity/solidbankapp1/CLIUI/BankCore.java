package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.service.AccountCreationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public class BankCore {
    private static long id = 1;
    private long lastAccountNumber = 1;

    @Autowired
    AccountCreationService accountCreation;

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
