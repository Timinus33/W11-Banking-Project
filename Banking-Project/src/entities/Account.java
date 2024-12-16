package entities;

import java.util.ArrayList;

import static utilities.Login.findUser;
import static utilities.Utils.*;

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

    public void deposit(String amount, double depositLimit) {
        if (parseDepositValues(amount)) {
            double value = Double.parseDouble(amount);
            if (value <= depositLimit) {
                this.balance += value;
                System.out.println("Success\n");
            } else {
                System.out.println("Failed to deposit! The amount is over the limit!\n");
            }
        } else {
            System.out.println("Invalid amount entered!\n");
        }
    }

    public void withdraw(String amount, double withdrawLimit) {
        if (parseDepositValues(amount)) {
            double value = Double.parseDouble(amount);
            if (value <= this.balance && value <= withdrawLimit) {
                this.balance -= value;
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
        if (account != null) {
            if (this.equals(account)) {
                System.out.println("You cannot transfer money to yourself! Please try again.\n");
            } else if (parseDepositValues(amount)) {
                if (Double.parseDouble(amount) > getBalance()) {
                    System.out.println("Error! Insufficient funds on your bank account. Please try again.\n");
                } else {
                    System.out.println("Transferring " + amount + " to " + account.getUsername() + "...");
                    withdraw(amount, Double.MAX_VALUE);
                    account.deposit(amount, Double.MAX_VALUE);
                }
            } else {
                System.out.println("Transaction failed! Reason: invalid amount of money.\n");
            }
        } else {
            System.out.println("Transaction failed! Reason: recipient id not found\n");
        }
    }

    public boolean authenticateRequest() {
        System.out.print("Please enter your pin code: ");
        String pinCode = scanner.nextLine();
        if (pinCode.equals(currentUser.getPinCode())) {
            return true;
        } else {
            System.out.println("Incorrect pin code! Please try again.\n");
            return false;
        }
    }
}
