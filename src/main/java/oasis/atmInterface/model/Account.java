package oasis.atmInterface.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Account {

    private String accountId;
    private String userId;
    private String pin;
    private double balance;

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}