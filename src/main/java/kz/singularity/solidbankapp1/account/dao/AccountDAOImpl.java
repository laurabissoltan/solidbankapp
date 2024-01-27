package kz.singularity.solidbankapp1.account.dao;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.model.AccountWithdraw;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class AccountDAOImpl implements AccountDAO {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getClientAccounts(String clientID) {
        return accountRepository.findByClientID(clientID);
    }

    @Override
    public List<Account> getClientAccountts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public void createNewAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        return accountRepository.findByClientIDAndAccountType(clientID, accountType);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        Account account = accountRepository.findByClientIDAndId(clientID, accountID);
        if(account.isWithdrawAllowed()) {
            return (AccountWithdraw) accountRepository.findByClientIDAndId(clientID, accountID);
        }
        else {
            return null;
        }
    }

    @Override
    public Account getClientAccount(String clientID, String accountID) {
        Account foundAccount = accountRepository.findByClientIDAndId(clientID, accountID);
        return foundAccount;
    }

    @Override
    public void deleteAccount(String accountID) {
        accountRepository.deleteById(accountID);
    }

    @Override
    public Account getClientAccountById(String accountID) {
        return accountRepository.findById(accountID).orElse(null);
    }




}
