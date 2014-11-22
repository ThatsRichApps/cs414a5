package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.CreditPayment;

public class CreditPaymentTest extends CreditPayment {

	private static final long serialVersionUID = 1L;
	private CreditPayment creditPaymentTest;
	
	@Before
	public void setUp() throws Exception {
		BigDecimal amount = new BigDecimal("23.00");
		creditPaymentTest = new CreditPayment(amount, "1234 1234 1234 1234");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreditPayment1() {
		assertEquals("CreditPayment", creditPaymentTest.toString());
	}

	@Test
	public void testCreditPayment2() {
		String testCard = "0000 0000 0000 0000";
		int expMonth = 12;
		int expYear = 2014;		
		
		creditPaymentTest.setCardNumber(testCard);
		creditPaymentTest.setExpMonth(expMonth);
		creditPaymentTest.setExpYear(expYear);
		
		assertEquals(false, creditPaymentTest.initiatePayment());
	}

	@Test
	public void testCreditPayment3() {
		int expMonth = 10;
		int expYear = 2014;		
		
		creditPaymentTest.setExpMonth(expMonth);
		creditPaymentTest.setExpYear(expYear);
		
		assertEquals(false, creditPaymentTest.initiatePayment());
	}

	@Test
	public void testCreditPayment4() {
		int expMonth = 12;
		int expYear = 2014;		
		
		creditPaymentTest.setExpMonth(expMonth);
		creditPaymentTest.setExpYear(expYear);
		
		assertEquals(true, creditPaymentTest.initiatePayment());
	}

	
	
	
}
