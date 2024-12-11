package entities;

import static utilities.AccountUtilities.validateName;
import static utilities.AccountUtilities.validateUsername;

public class Account extends User {
    private String firstName;
    private String lastName;

    private double balance;
    private double depositLimit;
    private double withdrawLimit;

    public Account(String username, String password, String firstName, String lastName, String balance, String depositLimit, String withdrawLimit) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = Double.parseDouble(balance);
        this.depositLimit = Double.parseDouble(depositLimit);
        this.withdrawLimit = Double.parseDouble(withdrawLimit);
    }

    @Override
    public String toString() {
        return "entities.Account{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", depositLimit=" + depositLimit +
                ", withdrawLimit=" + withdrawLimit +
                "} " + super.toString();
    }
}
