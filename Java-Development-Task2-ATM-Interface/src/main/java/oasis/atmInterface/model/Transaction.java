package oasis.atmInterface.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Transaction {

    private String type;
    private double amount;
    private String description;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return "Type: " + type +
                " | Amount: ₹" + amount +
                " | " + description +
                " | Time: " + dateTime;
    }
}