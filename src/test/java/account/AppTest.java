package account;

import java.math.BigDecimal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import service.AccountService;
import service.AccountServiceImpl;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName) {
	super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
	return new TestSuite(AppTest.class);
    }

    public void testTransfer() {
	// Arrange
	AccountService svc = new AccountServiceImpl();
	Account acc1 = new Account("1", new BigDecimal(100));
	Account acc2 = new Account("2", new BigDecimal(100));

	// Act
	try {
	    svc.transfer(acc1, acc2, new BigDecimal(50));
	} catch (TransferException e) {
	    fail(e.getMessage());
	}

	// Assert
	assertEquals(new BigDecimal(50), acc1.getBalance());
	assertEquals(new BigDecimal(150), acc2.getBalance());
    }

    public void testTransfer2() {
	// Arrange
	AccountService svc = new AccountServiceImpl();
	Account acc1 = new Account("1", new BigDecimal(50));
	Account acc2 = new Account("2", new BigDecimal(100));

	// Act
	try {
	    svc.transfer(acc1, acc2, new BigDecimal(70));
	    fail("Expected Exception");
	} catch (TransferException e) {
	}

	// Assert
	assertTrue(true);
    }

    public void testTransfer3() {
	// Arrange
	AccountService svc = new AccountServiceImpl();
	Account acc1 = new Account("1", new BigDecimal(50));
	Account acc2 = new Account("2", new BigDecimal(100));

	// Act
	try {
	    svc.transfer(acc1, acc2, new BigDecimal(-10));
	    fail("Expected Exception");
	} catch (TransferException e) {
	}

	// Assert
	assertTrue(true);
    }
}
