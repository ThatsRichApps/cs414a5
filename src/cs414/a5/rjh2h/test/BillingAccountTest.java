package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.BillingAccount;
import cs414.a5.rjh2h.CreditCard;

public class BillingAccountTest extends BillingAccount {

	private BillingAccount billingAccountTest;
	
	@Before
	public void setUp() throws Exception {
		String licensePlate = "CO-BBB-222";
		billingAccountTest = new BillingAccount(licensePlate);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBillingAccount1() {
		assertEquals("BillingAccount", billingAccountTest.toString());
	}

	@Test
	public void testBillingAccount2() {
		billingAccountTest.setFirstName("John");
		assertEquals("John", billingAccountTest.getFirstName());
	}

	@Test
	public void testBillingAccount3() {
		billingAccountTest.setLastName("Smith");
		assertEquals("Smith", billingAccountTest.getLastName());
	}

	@Test
	public void testBillingAccount4() {
		billingAccountTest.setAddress("PO Box 5, Frisco, CO 80443");
		assertEquals("PO Box 5, Frisco, CO 80443", billingAccountTest.getAddress());
	}

	@Test
	public void testBillingAccount5() {
		assertEquals("CO-BBB-222", billingAccountTest.getLicensePlate());
	}

	@Test
	public void testBillingAccount6() {
		CreditCard creditCard = new CreditCard(); 
		billingAccountTest.setCreditCard(creditCard);
		assertEquals(creditCard, billingAccountTest.getCreditCard());
	}

	
	
	
	
	
}
