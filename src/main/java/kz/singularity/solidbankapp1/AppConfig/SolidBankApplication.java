package kz.singularity.solidbankapp1.AppConfig;
import kz.singularity.solidbankapp1.CLIUI.*;
import kz.singularity.solidbankapp1.dao.MemoryAccountDAO;
import kz.singularity.solidbankapp1.dao.MemoryTransactionDAO;
import kz.singularity.solidbankapp1.CLIUI.TransactionDeposit;
import kz.singularity.solidbankapp1.CLIUI.TransactionWithdraw;
import kz.singularity.solidbankapp1.service.impl.AccountCreationServiceImpl;
import kz.singularity.solidbankapp1.service.impl.AccountDepositServiceImpl;
import kz.singularity.solidbankapp1.service.impl.AccountListingServiceImpl;
import kz.singularity.solidbankapp1.service.impl.AccountWithdrawServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SolidBankApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(SolidBankApplication.class);
    }

    @Override
    public void run(String... arg0) throws Exception {
        boolean running = true;
        String clientID = "1";

        MyCLI myCLI = context.getBean(MyCLI.class);
        AccountBasicCLI accountBasicCLI = context.getBean(AccountBasicCLI.class);
        TransactionDepositCLI transactionDepositCLI = context.getBean(TransactionDepositCLI.class);
        TransactionWithdrawCLI transactionWithdrawCLI = context.getBean(TransactionWithdrawCLI.class);

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

    @Bean
    public AccountBasicCLI accountBasicCLI() {
        return new AccountBasicCLI(myCLI(), bankCore(), accountListingServiceImpl());
    }

    @Bean
    public MyCLI myCLI() {
        return new MyCLI(scanner());
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public BankCore bankCore() {
        return new BankCore(accountCreationServiceImpl());
    }

    @Bean
    public AccountCreationServiceImpl accountCreationServiceImpl() {
        return new AccountCreationServiceImpl(memoryAccountDAO());
    }

    @Bean
    public MemoryAccountDAO memoryAccountDAO() {
        return new MemoryAccountDAO();
    }

    @Bean
    public AccountListingServiceImpl accountListingServiceImpl() {
        return new AccountListingServiceImpl();
    }

    @Bean
    public TransactionWithdrawCLI transactionWithdrawCLI() {
        return new TransactionWithdrawCLI(transactionWithdraw(), myCLI(), accountListingServiceImpl());
    }

    @Bean
    public TransactionWithdraw transactionWithdraw() {
        return new TransactionWithdraw(accountWithdrawServiceImpl(), memoryTransactionDAO());
    }

    @Bean
    public MemoryTransactionDAO memoryTransactionDAO() {
        return new MemoryTransactionDAO();
    }

    @Bean
    public AccountWithdrawServiceImpl accountWithdrawServiceImpl() {
        return new AccountWithdrawServiceImpl(memoryAccountDAO());
    }
    @Bean
    public TransactionDepositCLI transactionDepositCLI() {
        return new TransactionDepositCLI(transactionDeposit(), myCLI(), accountListingServiceImpl());
    }

    @Bean
    public TransactionDeposit transactionDeposit() {
        return new TransactionDeposit(accountDepositServiceImpl(), memoryTransactionDAO());
    }

    @Bean
    public AccountDepositServiceImpl accountDepositServiceImpl() {
        return new AccountDepositServiceImpl(memoryAccountDAO());
    }




}