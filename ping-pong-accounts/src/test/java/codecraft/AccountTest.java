package codecraft;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private Account account;

    @Before
    public void createAccount() {
        this.account = new Account();
    }


    @Test
    public void emptyAccountCheckBalance() {
        assertBalance(0);
    }

    @Test
    public void depositAmount() {
        account.deposit(10, "cash");
        assertBalance(10);
    }

    @Test
    public void withrawAmount() {
        account.deposit(40, "cash");
        account.withdraw(20, "cash");

        assertBalance(20);
    }

    @Test
    public void withdrawalAndDeposit() {
        account.deposit(20, "cash");
        account.withdraw(20, "cash");
        account.deposit(20, "cash");

        assertBalance(20);
    }

    @Test
    public void getListOfTransactions() {
        account.deposit(10, "cash");
        account.withdraw(10, "cash");

        assertEquals(2, account.getTransactions().size());
    }

    @Test
    public void fineForOverDraft() {
        account.withdraw(10, "cash");

        assertBalance(-20);
        assertEquals(2, account.getTransactions().size());
        assertEquals(TransactionType.FINE, account.getTransactions().get(1).getType());
    }

    @Test
    public void interest() {
        var today = LocalDate.now();
        account.deposit(50, "wages");

        account.applyInterestForMonth(today.getYear(), today.getMonthValue());

        assertEquals(2, account.getTransactions().size());
        assertBalance(51.25);
    }

    protected void assertBalance(double amount) {
        assertEquals(amount, account.getBalance(), 0.001);
    }
}
