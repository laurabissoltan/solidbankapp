package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountDeposit;
import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import kz.singularity.solidbankapp1.service.AccountListingService;
import kz.singularity.solidbankapp1.service.impl.AccountCreationServiceImpl;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class MemoryAccountDAO implements AccountDAO{

    List <Account> accountList = new ArrayList<>();

    @Override
    public List<Account> getClientAccounts(String clientID) {
        return this.accountList;
    }

    @Override
    public void createNewAccount(Account account) {
        accountList.add(account);
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        List<Account> clientAccountByType = new ArrayList<>();
        for(Account account: accountList) {
            if(account.getClientID().equals(clientID) && account.getAccountType().equals(accountType)) {
                clientAccountByType.add(account);
            }
        }
        return  clientAccountByType;
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        for(Account account: accountList) {
            if(account.getClientID().equals(clientID) && account.getId().equals(accountID) && account instanceof AccountWithdraw) {
                return (AccountWithdraw) account;
            }
        }
        return null;
    }

    @Override
    public Account getClientAccount(String clientID, String accountID) {
        for(Account account: accountList) {
            if(account.getClientID().equals(clientID) && account.getId().equals(accountID)){
                return account;
            }
        }
        return null;
    }

/*    @Override
    public AccountDeposit getClientDepositAccount(String clientID, String accountID) {
        for(Account account: accountList) {
            if(account.getClientID().equals(clientID) && account.getId().equals(accountID) && account instanceof AccountWithdraw) {
                return (AccountDeposit) account;
            }
        }
        return null;
    }*/
}
