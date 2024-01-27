package kz.singularity.solidbankapp1.account.cliui;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.service.AccountCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
