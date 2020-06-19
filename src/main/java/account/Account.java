package account;

import java.math.BigDecimal;

/**
 * You can change this class any way you want (i.e. add any methods you want),
 * except for the fields declaration and constructor. Note: the account doesn't
 * need to be persisted anywhere, stored into database etc. - it's purely an
 * in-memory object that needs to hold the current balance of the account
 */

public class Account {
    /**
     * Represents UNIQUE account id.
     */
    private String accountId;

    /**
     * Represents current balance of the account
     */
    private BigDecimal balance;

    public Account(String accountId, BigDecimal balance) {
	this.accountId = accountId;
	this.balance = balance;
    }

    public String getAccountId() {
	return accountId;
    }

    public BigDecimal getBalance() {
	return balance;
    }

    public void debit(BigDecimal amount) {
	balance = balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
	balance = balance.add(amount);
    }
}
