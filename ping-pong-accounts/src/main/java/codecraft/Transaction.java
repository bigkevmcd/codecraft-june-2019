package codecraft;

import java.time.LocalDate;

public class Transaction {

    private double amount;
    private String description;
    private TransactionType type;
    private LocalDate date;

    public Transaction(double amount, String description, TransactionType type, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }
}
