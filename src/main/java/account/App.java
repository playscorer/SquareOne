package account;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import service.AccountService;
import service.AccountServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
	final AccountService svc = new AccountServiceImpl();
	final Account from = new Account("1", new BigDecimal("100"));
	final Account to = new Account("1", new BigDecimal("20"));

	ExecutorService executor = Executors.newFixedThreadPool(5);
	for (int i = 1; i < 10; i++) {
	    executor.execute(new Runnable() {

		public void run() {
		    try {
			svc.transfer(from, to, new BigDecimal("20"));
			System.out.println("from balance : " + from.getBalance());
			System.out.println("to balance : " + to.getBalance());
		    } catch (TransferException e) {
			System.out.println(e.getMessage());
		    }
		}

	    });
	    System.out.println();
	}
	executor.shutdown();
    }
}
