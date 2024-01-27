package kz.singularity.solidbankapp1.account.cliui;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.service.AccountListingService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<Account> accounts = accountListing.getClientAccounts(clientID);

        if(accounts.isEmpty()) {
            System.out.println("[]");
        }
        else {
            for (Account account: accounts) {
                System.out.println(account);
            }
        }
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
