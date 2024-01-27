package kz.singularity.solidbankapp1.account.service.impl;

import kz.singularity.solidbankapp1.account.dao.AccountDAO;
import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.model.AccountWithdraw;
import kz.singularity.solidbankapp1.account.service.AccountListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountListingServiceImpl implements AccountListingService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account getClientAccount(String clientID, String accountID) {
        return accountDAO.getClientAccount(clientID, accountID);
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        return accountDAO.getClientWithdrawAccount(clientID, accountID);
    }

    @Override
    public List<Account> getClientAccounts(String clientID) {



        return accountDAO.getClientAccounts(clientID);
    }

    @Override
    public List<Account> getClientAccountByType(String clientID, AccountType accountType) {
        return accountDAO.getClientAccountsByType(clientID, accountType);
    }


}
