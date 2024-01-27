package kz.singularity.solidbankapp1.account.service;

import kz.singularity.solidbankapp1.account.model.AccountType;

public interface AccountCreationService {
    void create(AccountType accountType, long bankID, String clientID, String accountID);
}
