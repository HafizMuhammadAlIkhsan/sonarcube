package com.example;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

interface ATMOperations {
    void viewBalance();
    void withdraw(double amount);
    void deposit(double amount);
}

abstract class AbstractATM implements ATMOperations {
    protected double balance;
    protected static final Logger logger = Logger.getLogger(AbstractATM.class.getName());

    protected AbstractATM(double initialBalance) {
        this.balance = initialBalance;
    }

    public abstract boolean login(String accountNumber, String pin);

    @Override
    public void viewBalance() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Your current balance is: $%.2f%n", balance));
        }
    }
}

public class ATM extends AbstractATM {
    private static final String ACCOUNT_NUMBER = "12345678";
    private static final String PIN = "1234";

    public ATM(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public boolean login(String accountNumber, String pin) {
        return ACCOUNT_NUMBER.equals(accountNumber) && PIN.equals(pin);
    }

    @Override
    public void withdraw(double amount) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Attempting withdrawal of $%.2f", amount));
        }

        if (amount > balance) {
            logger.warning("Insufficient funds.");
        } else {
            balance -= amount;
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Withdrawal successful. New balance: $%.2f", balance));
            }
            viewBalance();
        }
    }

    @Override
    public void deposit(double amount) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Attempting deposit of $%.2f", amount));
        }

        if (amount <= 0) {
            logger.warning("Deposit amount must be greater than zero.");
        } else {
            balance += amount;
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Deposit successful. New balance: $%.2f", balance));
            }
            viewBalance();
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(1000.00);

        logger.info("Enter Account Number: ");
        String accountNumberInput = scanner.nextLine();
        logger.info("Enter PIN: ");
        String pinInput = scanner.nextLine();

        if (!atm.login(accountNumberInput, pinInput)) {
            logger.warning("Invalid account number or PIN. Exiting...");
            return;
        }

        logger.info("Login successful!");

        boolean running = true;
        while (running) {
            logger.info("\nATM Menu:");
            logger.info("1. View Balance");
            logger.info("2. Withdraw");
            logger.info("3. Deposit");
            logger.info("4. Exit");
            logger.info("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    atm.viewBalance();
                    break;
                case 2:
                    logger.info("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    logger.info("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    running = false;
                    logger.info("Exiting...");
                    break;
                default:
                    logger.warning("Invalid choice. Please choose again.");
            }
        }
        scanner.close();
    }

}
