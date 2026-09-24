package oasis.atmInterface.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Atm {

    private Bank bank;
    private Scanner scanner;

    private Account currentAccount;

    private List<Transaction> transactions;

    public Atm(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
        this.transactions = new ArrayList<>();
    }

    public void start() {

        System.out.println("================================");
        System.out.println("        WELCOME TO ATM");
        System.out.println("================================");

        if (!login()) {
            System.out.println("Access Denied.");
            return;
        }

        showMenu();
    }

    private boolean login() {

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        Account account = bank.findByUserId(userId);

        if (account == null) {
            System.out.println("User not found.");
            return false;
        }

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            if (account.getPin().equals(pin)) {

                currentAccount = account;

                System.out.println("\nLogin successful!");
                System.out.println(
                        "Welcome, " + account.getUserId()
                );

                return true;
            }

            attempts++;

            System.out.println("Incorrect PIN.");

            if (attempts < 3) {
                System.out.println(
                        "Attempts remaining: " + (3 - attempts)
                );
            }
        }

        return false;
    }

    private void showMenu() {

        while (true) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    System.out.println(
                            "Thank you for using ATM."
                    );
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void withdraw() {

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (!currentAccount.withdraw(amount)) {
            System.out.println("Insufficient Funds.");
            return;
        }

        transactions.add(
                new Transaction(
                        "WITHDRAW",
                        amount,
                        "Cash withdrawn"
                )
        );

        System.out.println("Withdrawal successful.");

        System.out.println(
                "Remaining Balance: ₹" +
                        currentAccount.getBalance()
        );
    }

    private void deposit() {

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        currentAccount.deposit(amount);

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        "Cash deposited"
                )
        );

        System.out.println("Deposit successful.");

        System.out.println(
                "New Balance: ₹" +
                        currentAccount.getBalance()
        );
    }

    private void transfer() {

        System.out.print("Enter recipient Account ID: ");
        String recipientId = scanner.next();

        Account recipient =
                bank.findByAccountId(recipientId);

        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        if (recipient == currentAccount) {
            System.out.println(
                    "Cannot transfer to your own account."
            );
            return;
        }

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (!currentAccount.withdraw(amount)) {
            System.out.println("Insufficient Funds.");
            return;
        }

        recipient.deposit(amount);

        transactions.add(
                new Transaction(
                        "TRANSFER",
                        amount,
                        "Transfer to " +
                                recipient.getAccountId()
                )
        );

        System.out.println("Transfer successful.");

        System.out.println(
                "Remaining Balance: ₹" +
                        currentAccount.getBalance()
        );
    }

    private void showTransactionHistory() {

        System.out.println(
                "\n====== TRANSACTION HISTORY ======"
        );

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}