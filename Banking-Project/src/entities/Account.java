package entities;

import java.util.ArrayList;

import static utilities.Login.findUser;
import static utilities.Utils.parseDepositValues;

public class Account extends User {
    public static final ArrayList<Account> accounts = new ArrayList<>();

    private final String firstName;
    private final String lastName;

    private double balance;
    private final double depositLimit;
    private final double withdrawLimit;

    public Account(String username, String password, String pinCode, String firstName, String lastName, String balance, String depositLimit, String withdrawLimit) {
        super(username, password, pinCode);
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = Double.parseDouble(balance);
        this.depositLimit = Double.parseDouble(depositLimit);
        this.withdrawLimit = Double.parseDouble(withdrawLimit);
    }

    public void deposit(String amount) {
        if (parseDepositValues(amount)) {
            double value = Double.parseDouble(amount);
            if (value <= this.depositLimit) {
                this.balance += value;
                System.out.println(value + " added!\n");
            } else {
                System.out.println("Failed to deposit! The amount is over the limit!\n");
            }
        } else {
            System.out.println("Invalid amount entered!\n");
        }
    }

    public void withdraw(String amount) {
        if (parseDepositValues(amount)) {
            double value = Double.parseDouble(amount);
            if (value > this.depositLimit) {
                this.balance -= value;
                System.out.println(value + " subtracted!\n");
            } else {
                System.out.println("Failed to withdraw! The amount is over the limit!\n");
            }
        } else {
            System.out.println("Invalid amount entered!\n");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", depositLimit=" + depositLimit +
                ", withdrawLimit=" + withdrawLimit +
                "} " + super.toString();
    }

    public double getBalance() {
        return balance;
    }

    public double getDepositLimit() {
        return depositLimit;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void transfer(String id, String amount) {
        Account account = findUser(id, true);
        if (parseDepositValues(amount)) {
            if (account != null) {
                System.out.println("Transferring " + amount + " to " + account.getUsername());
                withdraw(amount);
                account.deposit(amount);
                System.out.println("Successfully transfered!");
            }
        }
    }
}
