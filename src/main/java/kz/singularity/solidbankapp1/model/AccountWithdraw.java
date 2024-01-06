package kz.singularity.solidbankapp1.model;

public class AccountWithdraw extends AccountDeposit {

    public AccountWithdraw(AccountType accountType, String id, String clientID, double balance, boolean withdrawAllowed) {
        super(accountType, id, clientID, balance, withdrawAllowed);
    }
}
