package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.AccountDeposit;
import kz.singularity.solidbankapp1.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



@AllArgsConstructor
public class TransactionDepositCLI {

    @Autowired
    TransactionDeposit transactionDeposit;
    @Autowired
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    @Autowired
    AccountListingService accountListing;
    public void depositMoney(String clientID) {

        String accountID = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        AccountDeposit accountDeposit = (AccountDeposit) accountListing.getClientAccount(clientID, accountID);
        if(accountDeposit != null) {
            Double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            transactionDeposit.execute(accountDeposit, amount);
        }
        else {
            System.out.println("Bank account with the given account ID was not found. Please try again");
        }
    }
}
