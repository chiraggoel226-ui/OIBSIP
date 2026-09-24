package oasis.atmInterface;

import oasis.atmInterface.model.Account;
import oasis.atmInterface.model.Atm;
import oasis.atmInterface.model.Bank;

public class Main {

    public static void main(String[] args) {

        // Create Bank
        Bank bank = new Bank();

        // Create Accounts
        Account account1 = new Account(
                "ACC001",
                "chirag",
                "1234",
                10000
        );

        Account account2 = new Account(
                "ACC002",
                "rahul",
                "5678",
                15000
        );

        // Add accounts to Bank
        bank.addAccount(account1);
        bank.addAccount(account2);

        // Create ATM
        Atm atm = new Atm(bank);

        // Start ATM
        atm.start();
    }
}