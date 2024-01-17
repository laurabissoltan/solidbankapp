package kz.singularity.solidbankapp1;
import kz.singularity.solidbankapp1.CLIUI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@EntityScan
@EnableJpaRepositories("kz.singularity.solidbankapp1")
@SpringBootApplication
@Configuration
public class SolidBankApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext context;

    @Autowired
    MyCLI myCLI;
    @Autowired
    AccountBasicCLI accountBasicCLI;
    @Autowired
    TransactionDepositCLI transactionDepositCLI;
    @Autowired
    TransactionWithdrawCLI transactionWithdrawCLI;
    public static void main(String[] args) {
        SpringApplication.run(SolidBankApplication.class);
    }

    @Override
    public void run(String... arg0) throws Exception {
        boolean running = true;
        String clientID = "1";



        String helpMessage = "1 - show accounts\n2 - create account\n3 - deposit\n4 - withdraw\n5 - transfer\n6 - this message\n7 - exit\n";
        System.out.printf("Welcome to CLI bank service\n");
        System.out.printf("Enter operation number: \n");
        System.out.printf(helpMessage);
        while(running){
            switch(myCLI.getScanner().nextLine()){

                case "1":
                    accountBasicCLI.getAccounts(clientID);
                    break;

                case "2":
                    System.out.println("Please choose account type:\n[CHECKING, FIXED, SAVING]");
                    accountBasicCLI.createAccountRequest(clientID);
                    break;

                case "3":
                    transactionDepositCLI.depositMoney(clientID);
                    break;

                case "4":
                    transactionWithdrawCLI.withdrawMoney(clientID);
                    break;

                case "6":
                    System.out.printf(helpMessage);
                    break;

                case "7":
                    System.out.printf("Application Closed\n");
                    running = false;
                    break;

                default:
                    System.out.printf("Command not recognized!\n");
                    break;
            }
        }
        myCLI.getScanner().close();
    }

}