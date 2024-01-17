package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountDeposit;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

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
        AccountWithdraw accountWithdraw = accountListing.getClientWithdrawAccount(clientID, accountID);
        if(accountWithdraw != null) {
            Double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            transactionDeposit.execute(accountWithdraw, amount);
        }
        else {
            System.out.println("Bank account with the given account ID was not found. Please try again");
        }
    }
}
