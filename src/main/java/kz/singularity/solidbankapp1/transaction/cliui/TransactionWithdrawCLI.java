package kz.singularity.solidbankapp1.transaction.cliui;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountWithdraw;
import kz.singularity.solidbankapp1.account.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kz.singularity.solidbankapp1.account.model.AccountType.*;

@AllArgsConstructor
@Component
public class TransactionWithdrawCLI {
    @Autowired
    TransactionWithdraw transactionWithdraw;
    @Autowired
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    @Autowired
    AccountListingService accountListing;

    public void withdrawMoney(String clientID) {
        String accountID = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        Account accountWithdraw = accountListing.getClientAccount(clientID, accountID);

        if(accountWithdraw == null) {
            System.out.println("Bank account with the given account ID was not found. Please try again");
        }
        else if (accountWithdraw.getAccountType() == FIXED) {
            System.out.println("It is not allowed to withdraw money from a FIXED account");
        }
        else {
            Double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            transactionWithdraw.execute((AccountWithdraw) accountWithdraw, amount);
        }
    }
}

