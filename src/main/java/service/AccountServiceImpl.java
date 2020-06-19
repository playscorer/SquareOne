package service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import account.Account;
import account.TransferException;

public class AccountServiceImpl implements AccountService {

    private Lock lock;

    public AccountServiceImpl() {
	lock = new ReentrantLock();
    }

    public void transfer(Account from, Account to, BigDecimal amountToTransfer) throws TransferException {
	if (amountToTransfer.signum() < 0) {
	    throw new TransferException("The amount to transfer must be positive!");
	}

	try {
	    // the thread tries to acquire the lock during 100ms otherwise it
	    // throws an exception
	    // it is a strategy that can be changed depending on the
	    // requirements
	    boolean allowed = lock.tryLock(100, TimeUnit.MILLISECONDS);
	    if (allowed) {
		if (amountToTransfer.compareTo(from.getBalance()) > 0) {
		    throw new TransferException("There is not enough money in the account!");
		}
		from.debit(amountToTransfer);
		to.credit(amountToTransfer);
	    } else {
		throw new TransferException("Transfer could not be done!");
	    }
	} catch (InterruptedException e) {
	    throw new TransferException(e);
	} finally {
	    lock.unlock();
	}
    }

}
