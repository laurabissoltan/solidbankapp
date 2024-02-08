package kz.singularity.solidbankapp1.transaction.cliui;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TransactionDepositCLI {

    @Autowired
    TransactionDeposit transactionDeposit;
    @Autowired
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    @Autowired
    AccountListingService accountListing;
    public void depositMoney(String clientID) {
        String accountID = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        Account accountDeposit = accountListing.getClientAccount(clientID, accountID);
        if(accountDeposit != null) {
            Double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            transactionDeposit.execute(accountDeposit, amount);
        }
        else {
            System.out.println("Bank account with the given account ID was not found. Please try again");
        }
    }
}
