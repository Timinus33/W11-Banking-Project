import entities.Account;

import static entities.Account.accounts;
import static utilities.Utils.*;

public class Main {

    public static void main(String[] args) {
        Account account = new Account("ben", "111111", "0000", "ben", "afleck", "5000", "2000", "1000");
        Account account2 = new Account("tom", "222222", "1111", "mihai", "tataru", "10000", "5000", "3000");
        currentUser = account;
        accounts.add(new Account("ben21", "111111", "0000", "Ben", "Afleck", "5000", "2000", "1000"));
        accounts.add(new Account("tom47", "222222", "1111", "Tom", "Cruise", "10000", "5000", "3000"));
        displayLoginMenu();
//        displayUserMenu();
        scanner.close();
    }

}
