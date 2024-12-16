package entities;

public class PlatinumAccount extends Account {
    private static final String DEPOSIT_LIMIT = "30_000.0";
    private static final String WITHDRAW_LIMIT = "20_000.0";

    public PlatinumAccount(String username, String password, String pinCode, String firstName, String lastName, String balance) {
        super(username, password, pinCode, firstName, lastName, balance, DEPOSIT_LIMIT, WITHDRAW_LIMIT);
    }
}
