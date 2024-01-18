package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository <Account, String> {
    List<Account> findByClientID(String clientID);

    List<Account> findByClientIDAndAccountType(String ClientID, AccountType AccountType);

    Account findByClientIDAndId(String clientID, String AccountID);

}
