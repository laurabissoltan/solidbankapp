package kz.singularity.solidbankapp1.account.dao;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.model.AccountWithdraw;

import java.util.List;

public interface AccountDAO {
 //   List<Account> getClientAccounts(String clientID);

    List<Account> getClientAccounts(String clientID);

    List<Account> getClientAccountts();

    void createNewAccount(Account account);
    void updateAccount(Account account);
    List<Account> getClientAccountsByType(String clientID, AccountType accountType);
    AccountWithdraw getClientWithdrawAccount(String clientID, String accountID);
    Account getClientAccount(String clientID, String accountID);

    void deleteAccount(String accountID);

    Account getClientAccountById(String accountID);

}
