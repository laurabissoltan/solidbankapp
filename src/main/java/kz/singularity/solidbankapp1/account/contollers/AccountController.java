package kz.singularity.solidbankapp1.account.contollers;

import kz.singularity.solidbankapp1.account.cliui.BankCore;
import kz.singularity.solidbankapp1.account.dao.AccountDAO;
import kz.singularity.solidbankapp1.account.model.AccountWithdraw;
import kz.singularity.solidbankapp1.transaction.cliui.TransactionDeposit;
import kz.singularity.solidbankapp1.transaction.cliui.TransactionWithdraw;
import kz.singularity.solidbankapp1.transaction.dao.TransactionDAO;
import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.transaction.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountDAO accountDAO;
    private BankCore bankCore;
    private TransactionDeposit transactionDeposit;
    private TransactionDAO transactionDAO;
    private TransactionWithdraw transactionWithdraw;

    public AccountController(AccountDAO accountDAO, BankCore bankCore, TransactionDeposit transactionDeposit, TransactionDAO transactionDAO, TransactionWithdraw transactionWithdraw) {
        this.accountDAO = accountDAO;
        this.bankCore = bankCore;
        this.transactionDeposit = transactionDeposit;
        this.transactionDAO = transactionDAO;
        this.transactionWithdraw = transactionWithdraw;
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountDAO.getClientAccountts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> createAccount(@RequestParam AccountType accountType) {
        try {
            bankCore.createNewAccount(accountType, "1");
            String message = "Bank account created successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            String errorMessage = "Failed to create a new account";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{account_id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("account_id") String accountID) {
        Account account = accountDAO.getClientAccountById(accountID);

        if(account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{account_id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("account_id") String accountID) {
        try {
            transactionDAO.deleteTransactions(accountID);
            accountDAO.deleteAccount(accountID);
            String message = "Account " + accountID + " deleted";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Failed to delete account " + accountID;
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{account_id}/deposit")
    public ResponseEntity<Object> depositMoney(@PathVariable("account_id") String accountID,
                                               @RequestParam double amount) {
        try {
            Account accountToDeposit = accountDAO.getClientAccountById(accountID);
            transactionDeposit.execute(accountToDeposit, amount);
            String message = amount + " was transferred to the account " + accountID + " successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        catch (Exception e) {
            String errorMessage = "Failed to transfer to the account " + accountID;
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{account_id}/withdraw")
    public ResponseEntity<Object> withdrawMoney(@PathVariable("account_id") String accountID,
                                               @RequestParam double amount) {
        try {
            Account accountToWithdraw = accountDAO.getClientAccountById(accountID);
            if(accountToWithdraw.isWithdrawAllowed()) {
                if(accountToWithdraw.getBalance() >= amount) {
                    transactionWithdraw.execute(accountToWithdraw, amount);
                    String message = amount + " was transferred from the account " + accountID + " successfully";
                    return new ResponseEntity<>(message, HttpStatus.OK);
                } else {
                    String message = "The withdrawal transaction has been declined due to insufficient money in your account";
                    return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
                }
            } else {
                String message = "Not allowed to transfer money from a FIXED account";
                return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
            }
        }
        catch (Exception e) {
            String errorMessage = "Failed to transfer from the account " + accountID;
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{account_id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("account_id") String accountID) {
        List<Transaction> transactions = transactionDAO.getTransactions(accountID);
        if(transactions.isEmpty()) {
            System.out.print("No transactions for account " + accountID +": ");
            return new ResponseEntity<>(transactions, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
    }
}
