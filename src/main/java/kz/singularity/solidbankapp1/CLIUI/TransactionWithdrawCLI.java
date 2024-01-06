package kz.singularity.solidbankapp1.CLIUI;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.service.AccountListingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import static kz.singularity.solidbankapp1.model.AccountType.*;

@AllArgsConstructor
public class TransactionWithdrawCLI {
    @Autowired
    TransactionWithdraw transactionWithdraw;
    @Autowired
    WithdrawDepositOperationCLIUI withdrawDepositOperationCLIUI;
    @Autowired
    AccountListingService accountListing;

    public void withdrawMoney(String clientID) {
        String accountID = withdrawDepositOperationCLIUI.requestClientAccountNumber();
        AccountWithdraw accountWithdraw = accountListing.getClientWithdrawAccount(clientID, accountID);
        if(accountWithdraw != null) {
            Double amount = withdrawDepositOperationCLIUI.requestClientAmount();
            transactionWithdraw.execute(accountWithdraw, amount);
        }
        else {
            Account account = accountListing.getClientAccount(clientID, accountID);
            if(account != null && account.getAccountType() == FIXED) {
                System.out.println("It is not allowed to withdraw money from a FIXED account");
            }
            else {
                System.out.println("Bank account with the given account ID was not found. Please try again");
            }
        }
    }
}
