import entities.Account;

import static entities.Account.accounts;
import static utilities.Utils.*;

public class Main {

    public static void main(String[] args) {
        Account account = new Account("mike", "111111", "0000", "mihai", "tataru", "100", "20", "10");
        currentUser = account;
        accounts.add(account);
        displayLoginMenu();
//        displayUserMenu();
        scanner.close();
    }

}
