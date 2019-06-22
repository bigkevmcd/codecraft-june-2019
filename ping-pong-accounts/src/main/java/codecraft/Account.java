package codecraft;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static codecraft.TransactionType.*;

public class Account {
    private List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return sumTransactions(this.transactions);
    }

    private double sumTransactions(List<Transaction> transactions) {
        double total = 0;
        for (Transaction transaction : transactions) {
            switch (transaction.getType()) {
                case DEPOSIT:
                    total += transaction.getAmount();
                    break;
                case WITHDRAWAL:
                    total -= transaction.getAmount();
                    break;
                case FINE:
                    total -= transaction.getAmount();
                    break;
                case INTEREST:
                    total += transaction.getAmount();
            }
        }
        return total;
    }

    public void recordTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void withdraw(long amount, String description) {
        var transaction = new Transaction(amount, description, WITHDRAWAL, LocalDate.now());
        recordTransaction(transaction);

        if (getBalance() < 0) {
            var fine = new Transaction(10, "overdraft fee", FINE, LocalDate.now());
            recordTransaction(fine);
        }

    }

    public void deposit(long amount, String description){
        var transaction = new Transaction(amount, description, DEPOSIT, LocalDate.now());
        recordTransaction(transaction);
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void applyInterestForMonth(int year, int month) {
        var thisMonth = this.transactions
                .stream()
                .filter(t -> {
                    return (t.getDate().getMonthValue() == month) && t.getDate().getYear() == year;
                })
                .collect(Collectors.toList());

        double total = sumTransactions(thisMonth);

        var interest = total * 0.025;

        var transaction = new Transaction(interest, "monthly interest", INTEREST, LocalDate.now());
        recordTransaction(transaction);
    }
}
