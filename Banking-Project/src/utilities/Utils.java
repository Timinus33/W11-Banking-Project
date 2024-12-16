package utilities;

import entities.Account;

import java.util.Scanner;

import static utilities.Login.findUser;
import static utilities.Login.login;
import static utilities.Signup.signUp;


public class Utils {
    public static final Scanner scanner = new Scanner(System.in);
    public static Account currentUser;

    public static void displayLoginMenu() {
        System.out.println("""
                Main Menu
                Please select an option:
                1. Signup
                2. Login
                3. Exit""");
        processLoginMenu();
    }

    public static void displayUserMenu() {
        System.out.println("""
                User Menu
                Please select an option:
                1. Show balance
                2. Deposit
                3. Withdraw
                4. Transfer
                5. Reset password
                6. Logout""");
        processUserMenu(scanner.nextLine());
    }

    private static void processLoginMenu() {
        String option;
        option = scanner.nextLine();
        if (option.length() == 1 && option.chars().allMatch(Character::isDigit)) {
            int optionValue = Integer.parseInt(option);
            switch (optionValue) {
                case 1:
                    signUp();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("No such option exist!\n");
                    displayLoginMenu();
            }
        } else {
            System.out.println("No such option exist!\n");
            displayLoginMenu();
        }
    }

    private static void processUserMenu(String option) {
        if (option.length() == 1 && option.chars().allMatch(Character::isDigit)) {
            int optionValue = Integer.parseInt(option);
            switch (optionValue) {
                case 1:
                    if (currentUser.authenticateRequest()) {
                        System.out.printf("Account balance: %.0f\n\n", currentUser.getBalance());
                    }
                    displayUserMenu();
                    break;
                case 2:
                    if (currentUser.authenticateRequest()) {
                        System.out.print("How much money do you want to deposit (max. " + currentUser.getDepositLimit() + "): ");
                        currentUser.deposit(scanner.nextLine(), currentUser.getDepositLimit());
                    }
                    displayUserMenu();
                    break;
                case 3:
                    if (currentUser.authenticateRequest()) {
                        System.out.print("How much money do you want to withdraw (max. " + currentUser.getWithdrawLimit() + "): ");
                        currentUser.withdraw(scanner.nextLine(), currentUser.getWithdrawLimit());
                    }
                    displayUserMenu();
                    break;
                case 4:
                    if (currentUser.authenticateRequest()) {
                        System.out.print("Please enter the recipient id: ");
                        String receiverId = scanner.nextLine();
                        System.out.print("Please enter the amount you want to send: ");
                        String amount = scanner.nextLine();
                        currentUser.transfer(receiverId, amount);
                    }
                    displayUserMenu();
                    break;
                case 5:
                    if (currentUser.authenticateRequest()) {
                        System.out.print("Please enter your current password: ");
                        String currentPassword = scanner.nextLine();
                        System.out.print("Please enter the new password: ");
                        String newPassword = scanner.nextLine();
                        currentUser.resetPassword(currentPassword, newPassword);
                    }
                    displayUserMenu();
                    break;
                case 6:
                    currentUser = null;
                    displayLoginMenu();
                    break;
                default:
                    System.out.println("No such option exist!");
                    displayUserMenu();
            }
        } else {
            System.out.println("No such option exist!\n");
            displayUserMenu();
        }
    }

    public static boolean parseDepositValues(String amount) {
        if (amount != null && !amount.isBlank()) {
            return amount.matches("(\\d+.)?\\d+");
        } else {
            return false;
        }
    }
}
