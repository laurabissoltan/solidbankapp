package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class AccountBasicCLI {
    @Autowired
    CreateAccountOperationUI createAccountOperationUI;
    @Autowired
    BankCore bankCore;
    @Autowired
    AccountListingService accountListing;

    public void createAccountRequest(String clientID) {
        AccountType accountType = this.createAccountOperationUI.requestAccountType();
        if(accountType != null) {
            bankCore.createNewAccount(accountType, clientID);
            System.out.println("Bank account created successfully");
        }
    }

    public void getAccounts(String clientID) {
        System.out.println(accountListing.getClientAccounts(clientID));

    }
}
