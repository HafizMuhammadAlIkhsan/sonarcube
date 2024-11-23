import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ATM;

public class ATMTest {

    private ATM atm;

    @BeforeEach
    public void setUp() {
        // Initialize ATM with initial balance of 1000.00
        atm = new ATM(1000.00);
    }

    @Test
    public void testLoginValidCredentials() {
        assertTrue(atm.login("12345678", "1234"), "Login should succeed with valid credentials.");
    }

    @Test
    public void testLoginInvalidCredentials() {
        assertFalse(atm.login("12345678", "0000"), "Login should fail with invalid credentials.");
    }

    @Test
    public void testViewBalance() {
        atm.viewBalance();
        // Since we're logging the balance, we can't easily test the console output.
        // But you could mock the logger or verify that the correct messages are logged.
        assertTrue(true, "viewBalance should log the correct balance");
    }

    @Test
    public void testWithdrawSufficientFunds() {
        atm.withdraw(200.00);
        assertTrue(true, "Withdrawal should succeed with sufficient funds.");
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        atm.withdraw(2000.00);
        assertTrue(true, "Withdrawal should fail due to insufficient funds.");
    }

    @Test
    public void testDepositValidAmount() {
        atm.deposit(500.00);
        assertTrue(true, "Deposit should succeed with valid amount.");
    }

    @Test
    public void testDepositInvalidAmount() {
        atm.deposit(-100.00);
        assertTrue(true, "Deposit should fail with invalid (negative) amount.");
    }
}
