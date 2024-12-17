package utilities;

import entities.Account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static entities.Account.accounts;
import static utilities.Utils.*;

public class Login {
    private static int loginAttemptsRemaining = 3;
    private static boolean isLoginAvailable = true;
    private static LocalDateTime remainingTime;

    static void login() {
        if (remainingTime == null || LocalDateTime.now().isEqual(remainingTime) || LocalDateTime.now().isAfter(remainingTime)) {
            System.out.print("Please enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();
            currentUser = findUserByName(username, password);
            if (currentUser != null) {
                displayUserMenu();
            } else {
                displayLoginMenu();
            }
        } else {
            System.out.println("Remaining time left: " + remainingTime);
        }
    }

    private static Account findUserByName(String username, String password) {
        Account account = findUser(username, false);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                loginAttemptsRemaining = 3;
                System.out.println("Successfully logged in!\n");
                return account;
            } else {
                System.out.println("wrong password\n");
            }
        } else {
            loginAttemptsRemaining--;
            if (loginAttemptsRemaining == 0) {
                blockUserToLogin();
            }
            System.out.println("username not found\n");
        }
        return null;
    }

    public static Account findUser(String id, boolean searchById) {
        if (searchById) {
            for (Account account : accounts) {
                if (account.getUserId().equals(id)) {
                    return account;
                }
            }
        } else {
            for (Account account : accounts) {
                if (account.getUsername().equals(id)) {
                    return account;
                }
            }
        }
        return null;
    }

    public static void blockUserToLogin() {
        isLoginAvailable = false;
        remainingTime = LocalDateTime.now().plusDays(1);
        System.out.println("Login process blocked! Try again at " + remainingTime.format(DateTimeFormatter.ofPattern("dd\\MM\\yyyy HH:mm")));
    }

    public static boolean isIsLoginAvailable() {
        return isLoginAvailable;
    }

    public static LocalDateTime getRemainingTime() {
        return remainingTime;
    }
}
