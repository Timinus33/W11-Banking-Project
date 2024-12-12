package utilities;

import entities.Account;

import java.util.HashMap;

import static entities.Account.accounts;
import static utilities.Utils.*;

public class Login {
    static void login() {
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        currentUser = findUser(accounts, username, password);
        if (currentUser != null) {
            displayUserMenu();
        } else {
            displayLoginMenu();
        }
    }

    public static Account findUser(HashMap<String, Account> accounts, String username, String password) {
        if (accounts.containsKey(username)) {
            if (accounts.get(username).getPassword().equals(password)) {
                System.out.println("Successfully logged in!\n");
                return accounts.get(username);
            } else {
                System.out.println("wrong password\n");
            }
        } else {
            System.out.println("username not found\n");
        }
        return null;
    }
}
