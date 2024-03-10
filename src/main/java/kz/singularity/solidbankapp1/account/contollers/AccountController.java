package kz.singularity.solidbankapp1.account.contollers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.singularity.solidbankapp1.account.cliui.BankCore;
import kz.singularity.solidbankapp1.account.dao.AccountDAO;
import kz.singularity.solidbankapp1.account.dao.AccountRepository;
import kz.singularity.solidbankapp1.security.model.User;
import kz.singularity.solidbankapp1.security.service.UserService;
import kz.singularity.solidbankapp1.transaction.cliui.TransactionDeposit;
import kz.singularity.solidbankapp1.transaction.cliui.TransactionWithdraw;
import kz.singularity.solidbankapp1.transaction.dao.TransactionDAO;
import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.transaction.model.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {

    private AccountDAO accountDAO;
    private BankCore bankCore;
    private TransactionDeposit transactionDeposit;
    private TransactionDAO transactionDAO;
    private TransactionWithdraw transactionWithdraw;
    private UserService userService;
    private final AccountRepository accountRepository;

    @Operation(summary = "Get all client accounts",
    description = "Retrieves a list of all accounts associated with the authenticated client.")
    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts() {
        User user = userService.getCurrentUser();
        List<Account> accounts = accountDAO.getClientAccounts(String.valueOf(user.getId()));
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @Operation(summary = "Create the new account",
            description = "This operation allows an authenticated client to create a new bank account of the specified type.")
    @PostMapping()
    public ResponseEntity<Object> createAccount(@RequestParam AccountType accountType) {
        try {
            User user = userService.getCurrentUser();
            bankCore.createNewAccount(accountType, String.valueOf(user.getId()));

            String message = "Bank account created successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Failed to create a new account";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get client accounts by account id")
    @GetMapping("/{account_id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("account_id") String accountID) {
        Account account = accountDAO.getClientAccountById(accountID);
        if (account != null) {
            if (userService.isAuthorized(account.getClientID())) {
                return new ResponseEntity<>(account, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete the account associated with the given account id")
    @DeleteMapping("/{account_id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("account_id") String accountID) {
        Account account = accountDAO.getClientAccountById(accountID);
        if (userService.isAuthorized(account.getClientID())) {
            try {
                transactionDAO.deleteTransactions(accountID);
                accountDAO.deleteAccount(accountID);
                String message = "Account " + accountID + " deleted";
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                String errorMessage = "Failed to delete account " + accountID;
                return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            String errorMessage = "Unauthorized operation: Account does not belong to the authenticated user " + accountID;
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Deposit money into the client's account")
    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<Object> depositMoney(@PathVariable("account_id") String accountID,
                                               @RequestParam double amount) {
        Account account = accountDAO.getClientAccountById(accountID);
        if(userService.isAuthorized(account.getClientID())) {
            try {
                Account accountToDeposit = accountDAO.getClientAccountById(accountID);
                transactionDeposit.execute(accountToDeposit, amount);
                String message = amount + " was transferred to the account " + accountID + " successfully";
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                String errorMessage = "Failed to transfer to the account " + accountID;
                return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            String errorMessage = "Unauthorized operation: Account does not belong to the authenticated user " + accountID;
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Withdraw money from the client's account")
    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<Object> withdrawMoney(@PathVariable("account_id") String accountID,
                                               @RequestParam double amount) {
        Account accountToWithdraw = accountDAO.getClientAccountById(accountID);

        if(!userService.isAuthorized(accountToWithdraw.getClientID())) {
            return new ResponseEntity<>("Unauthorized operation: Account does not belong to the authenticated user " + accountID, HttpStatus.UNAUTHORIZED);
        }
        if(!accountToWithdraw.isWithdrawAllowed()) {
            return new ResponseEntity<>("Not allowed to withdraw from a FIXED account", HttpStatus.FORBIDDEN);
        }
        if(accountToWithdraw.getBalance() < amount) {
            return new ResponseEntity<>("The withdrawal transaction has been declined due to insufficient funds", HttpStatus.FORBIDDEN);
        }
        try {
            transactionWithdraw.execute(accountToWithdraw, amount);
            String message = amount + " was transferred from the account " + accountID + " successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch(Exception e) {
            String errorMessage = "Failed to transfer from the account " + accountID;
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get client transactions by account id")
    @GetMapping("/{account_id}/transactions")
    public ResponseEntity<Object> getTransactions(@PathVariable("account_id") String accountID) {
        Account account = accountDAO.getClientAccountById(accountID);
        if(userService.isAuthorized(account.getClientID())){
            List<Transaction> transactions = transactionDAO.getTransactions(accountID);
            if (transactions.isEmpty()) {
                String message = "No transactions for account " + accountID;
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(transactions, HttpStatus.OK);
            }
        } else {
            String errorMessage = "Unauthorized operation: Account does not belong to the authenticated user " + accountID;
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Transfer money from account")
    @PostMapping ("/{account_id}/transfer")
    public ResponseEntity<Object> transferMoney(@PathVariable("account_id") String accountID,
                                                @RequestParam("destination_account_id") String destinationAccountId,
                                                @RequestParam("amount") double amount) {
        Account account = accountDAO.getClientAccountById(accountID);
        if(!account.isWithdrawAllowed()) {
            return new ResponseEntity<>("Not allowed to transfer money from a FIXED account", HttpStatus.FORBIDDEN);
        } else if (account.getBalance() >= amount) {
            withdrawMoney(accountID, amount);
            depositMoney(destinationAccountId, amount);
            String message = "The amount " + amount + " was transferred from account " + accountID + " to the " + destinationAccountId + " successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The operation has been declined due to insufficient funds", HttpStatus.FORBIDDEN);
        }
    }


}
