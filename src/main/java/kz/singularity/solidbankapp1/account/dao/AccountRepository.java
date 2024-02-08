package kz.singularity.solidbankapp1.account.dao;

import kz.singularity.solidbankapp1.account.model.Account;
import kz.singularity.solidbankapp1.account.model.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository <Account, String> {
    List<Account> findByClientID(String clientID);

    List<Account> findByClientIDAndAccountType(String ClientID, AccountType AccountType);

    Account findByClientIDAndId(String clientID, String AccountID);


}
