package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.service.AccountListingService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AccountBasicCLI {

    CreateAccountOperationUI createAccountOperationUI;

    BankCore bankCore;

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
    @Autowired
    public void setCreateAccountOperationUI(CreateAccountOperationUI createAccountOperationUI) {
        this.createAccountOperationUI = createAccountOperationUI;
    }
    @Autowired
    public void setBankCore(BankCore bankCore) {
        this.bankCore = bankCore;
    }
    @Autowired
    public void setAccountListing(AccountListingService accountListing) {
        this.accountListing = accountListing;
    }
}
