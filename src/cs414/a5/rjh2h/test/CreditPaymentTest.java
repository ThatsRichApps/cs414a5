package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.CreditCard;
import cs414.a5.rjh2h.common.CreditPayment;

public class CreditPaymentTest extends CreditPayment {

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
		CreditCard testCard = new CreditCard();
		creditPaymentTest.setCreditCard(testCard);
		assertEquals(testCard, creditPaymentTest.getCreditCard());
	}
	
}
