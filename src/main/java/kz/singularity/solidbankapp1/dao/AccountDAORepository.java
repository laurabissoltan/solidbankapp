package kz.singularity.solidbankapp1.dao;

import kz.singularity.solidbankapp1.model.Account;
import kz.singularity.solidbankapp1.model.AccountType;
import kz.singularity.solidbankapp1.model.AccountWithdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAORepository implements AccountDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDAORepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> getClientAccounts(String clientID) {
        String sql = "SELECT * FROM ACCOUNT WHERE CLIENT_ID = ?";

        return jdbcTemplate.query(sql, new Object[]{clientID}, (resultSet, rowNum) -> {
            Account account = new Account();
            account.setAccountType(AccountType.valueOf(resultSet.getString("ACCOUNT_TYPE")));
            account.setId(resultSet.getString("id"));
            account.setClientID(resultSet.getString("CLIENT_ID"));
            account.setBalance(resultSet.getDouble("balance"));
            account.setWithdrawAllowed(resultSet.getBoolean("withdraw_allowed"));
            return account;
        });
    }

    @Override
    public void createNewAccount(Account account) {
        String sql = "INSERT INTO Account (ACCOUNT_TYPE, id, client_ID, balance, withdraw_Allowed) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, account.getAccountType().name(), account.getId(), account.getClientID(),
                account.getBalance(), account.isWithdrawAllowed());
    }

    @Override
    public void updateAccount(Account account) {
        String sql = """
                UPDATE ACCOUNT SET BALANCE = ?, WITHDRAW_ALLOWED = ? WHERE CLIENT_ID= ? AND ID=?""";
        jdbcTemplate.update(sql, account.getBalance(), account.isWithdrawAllowed(), account.getClientID(), account.getId());
    }

    @Override
    public List<Account> getClientAccountsByType(String clientID, AccountType accountType) {
        String sql = "SELECT * FROM Account WHERE CLIENT_ID = ? AND ACCOUNT_TYPE = ?";
        return jdbcTemplate.query(
                sql,
                new Object[]{clientID, accountType},
                (resultSet, rowNum) -> {
                    Account account = new Account();
                    account.setAccountType(AccountType.valueOf(resultSet.getString("ACCOUNT_TYPE")));
                    account.setId(resultSet.getString("id"));
                    account.setClientID(resultSet.getString("CLIENT_ID"));
                    account.setBalance(resultSet.getDouble("balance"));
                    account.setWithdrawAllowed(resultSet.getBoolean("withdraw_allowed"));
                    return account;
                }
        );
    }

    @Override
    public AccountWithdraw getClientWithdrawAccount(String clientID, String accountID) {
        String sql = "SELECT * FROM Account WHERE CLIENT_ID = ? AND ID = ?";

        List<AccountWithdraw> result = jdbcTemplate.query(
                sql,
                new Object[]{clientID, accountID},
                (resultSet, rowNum) -> {
                    AccountWithdraw accountWithdraw = new AccountWithdraw();
                    accountWithdraw.setAccountType(AccountType.valueOf(resultSet.getString("ACCOUNT_TYPE")));
                    accountWithdraw.setId(resultSet.getString("id"));
                    accountWithdraw.setClientID(resultSet.getString("CLIENT_ID"));
                    accountWithdraw.setBalance(resultSet.getDouble("balance"));
                    accountWithdraw.setWithdrawAllowed(resultSet.getBoolean("withdraw_allowed"));
                    return accountWithdraw;
                }
        );

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Account getClientAccount(String clientID, String accountID) {
        String sql = "SELECT * FROM Account WHERE CLIENT_ID = ? AND ID = ?";

        List<Account> result = jdbcTemplate.query(
                sql,
                new Object[]{clientID, accountID},
                (resultSet, rowNum) -> {
                    Account account = new Account();
                    account.setAccountType(AccountType.valueOf(resultSet.getString("ACCOUNT_TYPE")));
                    account.setId(resultSet.getString("id"));
                    account.setClientID(resultSet.getString("CLIENT_ID"));
                    account.setBalance(resultSet.getDouble("balance"));
                    account.setWithdrawAllowed(resultSet.getBoolean("withdraw_allowed"));
                    return account;
                }
        );

        return result.isEmpty() ? null : result.get(0);
    }
}
