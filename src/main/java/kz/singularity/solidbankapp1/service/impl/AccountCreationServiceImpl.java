package kz.singularity.solidbankapp1.service.impl;

import kz.singularity.solidbankapp1.dao.AccountDAO;
import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.model.CheckingAccount;
import kz.singularity.solidbankapp1.model.FixedAccount;
import kz.singularity.solidbankapp1.model.SavingAccount;
import kz.singularity.solidbankapp1.service.AccountCreationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class AccountCreationServiceImpl implements AccountCreationService {

    @Autowired
    AccountDAO accountDAO;

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
