package utilities;

import entities.Account;

import java.util.HashSet;

public class AccountUtilities {

    public static boolean validateUsername(HashSet<Account> accounts, String username) {
        if (username != null && !username.isBlank()) {
            if (accounts.stream().findAny().filter(account -> account.getUsername().equals(username)).isPresent()) {
                System.out.print("This username already exists! Please enter another name: ");
                return false;
            } else {
                System.out.println("username -> " + username);
                return true;
            }
        } else {
            System.out.print("Username cannot be neither null nor empty! Please enter a valid username: ");
            return false;
        }
    }

    public static boolean validatePassword(String password) {
        if (password != null && password.length() >= 6) {
            System.out.println("password -> " + password);
            return true;
        } else {
            System.out.print("Password is either null or less than 6 characters long! Please enter another password: ");
            return false;
        }

    }

    public static boolean validateName(String name, boolean isFirstName) {
        String s = isFirstName ? "First" : "Last";
        if (name != null && !name.isBlank() && name.chars().allMatch(Character::isLetter)) {
            System.out.println(s + "name -> " + name);
            return true;
        } else {
            System.out.print(s + " name cannot be null nor empty and should contain only letters! Please enter another name: ");
            return false;
        }
    }

    public static boolean validateBalanceLimit(String amount) {
        if (parseDepositValues(amount)) {
            double value = Double.parseDouble(amount);
            System.out.println("Successfully added: " + value);
            return true;
        } else {
            System.out.print("Invalid amount entered! Please enter another amount: ");
            return false;
        }
    }

    private static boolean parseDepositValues(String amount) {
        if (amount != null && !amount.isBlank()) {
            return amount.matches("(\\d+.)?\\d+");
        } else {
            return false;
        }
    }
}
