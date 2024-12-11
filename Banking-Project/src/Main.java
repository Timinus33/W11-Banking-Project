import entities.Account;

import java.util.HashSet;
import java.util.Scanner;

import static utilities.AccountUtilities.*;

public class Main {
    private static final HashSet<Account> accounts = new HashSet<>();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayLoginMenu();

        scanner.close();
    }

    private static void displayLoginMenu() {
        System.out.println("""
                Please select an option:
                1. Signup
                2. Login
                3. Exit""");
        processLoginMenu();
    }

    private static void processLoginMenu() {
        String option;
        do {
            option = scanner.nextLine();
            if (option.length() == 1 && option.chars().allMatch(Character::isDigit)) {
                int optionValue = Integer.parseInt(option);
                switch (optionValue) {
                    case 1 -> signUp();
                    case 2 -> System.out.println("In progress...");
                    case 3 -> {
                        return;
                    }
                }
                break;
            } else {
                System.out.print("No such option exist! Please enter another option: ");
            }
        } while (true);
    }

    private static void signUp() {
        System.out.print("Please enter your username: ");
        String username;
        do {
            username = scanner.nextLine().toLowerCase();
        } while (!validateUsername(accounts, username));

        System.out.print("Please enter your password: ");
        String password;
        do {
            password = scanner.nextLine();
        } while (!validatePassword(password));

        System.out.print("Please enter your first name: ");
        String firstName;
        do {
            firstName = scanner.nextLine();
        } while (!validateName(firstName, true));

        System.out.print("Please enter your last name: ");
        String lastName;
        do {
            lastName = scanner.nextLine();
        } while (!validateName(lastName, false));

        System.out.print("Please enter the initial deposit amount: ");
        String initialDepositAmount;
        do {
            initialDepositAmount = scanner.nextLine();
        } while (!validateBalanceLimit(initialDepositAmount));

        System.out.print("Please enter the deposit limit: ");
        String depositLimit;
        do {
            depositLimit = scanner.nextLine();
        } while (!validateBalanceLimit(depositLimit));

        System.out.print("Please enter the withdraw limit: ");
        String withdrawLimit;
        do {
            withdrawLimit = scanner.nextLine();
        } while (!validateBalanceLimit(withdrawLimit));

        accounts.add(signUp(username, password, firstName, lastName, initialDepositAmount, depositLimit, withdrawLimit));
    }

    private static Account signUp(String username, String password, String firstName, String lastName, String initialDepositAmount, String depositLimit, String withdrawLimit) {
        return new Account(username, password, firstName, lastName, initialDepositAmount, depositLimit, withdrawLimit);
    }
}
