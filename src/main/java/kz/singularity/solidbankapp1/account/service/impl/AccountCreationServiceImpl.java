package kz.singularity.solidbankapp1.account.service.impl;

import kz.singularity.solidbankapp1.account.dao.AccountDAO;
import kz.singularity.solidbankapp1.account.model.AccountType;
import kz.singularity.solidbankapp1.account.model.CheckingAccount;
import kz.singularity.solidbankapp1.account.model.FixedAccount;
import kz.singularity.solidbankapp1.account.model.SavingAccount;
import kz.singularity.solidbankapp1.account.service.AccountCreationService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AccountCreationServiceImpl implements AccountCreationService {

    AccountDAO accountDAO;
    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void create(AccountType accountType, long bankID, String clientID, String accountID) {

        String accountIDD = String.format("%03d%06d", bankID, Long.parseLong(accountID));

        switch(accountType) {
            case CHECKING -> {accountDAO.createNewAccount(new CheckingAccount(accountType, accountIDD, clientID, 0.0, true ));
            }
            case FIXED -> {accountDAO.createNewAccount(new FixedAccount(accountType, accountIDD, clientID, 0.0, false ));
            }
            case SAVING -> {accountDAO.createNewAccount(new SavingAccount(accountType, accountIDD, clientID, 0.0, true ));
            }
            default -> System.out.println("Unexpected value: " + accountType);
        }
    }
}
