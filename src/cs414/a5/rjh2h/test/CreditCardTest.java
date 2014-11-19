package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.CreditCard;

public class CreditCardTest extends CreditCard {
	
	private CreditCard testCard;
	private CreditCard testCard2;
	
	@Before
	public void setUp() throws Exception {
		testCard = new CreditCard();
		testCard2 = new CreditCard("1234 1234 1234 1234", 10, 2014, 123 );

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreditCard1() {
		assertEquals("CreditCard [1111 1111 1111 1111]", testCard.toString());
	}
	
	@Test
	public void testCreditCard2() {
		assertEquals("CreditCard [1234 1234 1234 1234]", testCard2.toString());
	}
	
	@Test
	public void testCreditCard3() {
		CreditCard testCard3 = new CreditCard(null, 0, 0, 0);
		assertEquals("CreditCard [1111 1111 1111 1111]", testCard3.toString());
	}
	
	@Test
	public void testGetCardNumber() {
		assertEquals("1234 1234 1234 1234", testCard2.getCardNumber());
	}
	
	@Test
	public void testGetExpMonth() {
		assertEquals(10, testCard2.getExpMonth());
	}
	
	@Test
	public void testGetExpYear() {
		assertEquals(2014, testCard2.getExpYear());
	}
	
	@Test
	public void testGetCvv() {
		assertEquals(123, testCard2.getCvv());
	}

	@Test
	public void testGetTotalCharged() {
		assertEquals(new BigDecimal(0), testCard2.getTotalCharged());
	}

	@Test
	public void testChargeCard1() {
		BigDecimal chargeAmount = new BigDecimal(100);
		testCard2.chargeCard(chargeAmount);
		assertEquals(chargeAmount, testCard2.getTotalCharged());
	}

	@Test
	public void testChargeCard2() {
		testCard2.chargeCard(null);
		assertEquals(new BigDecimal(0), testCard2.getTotalCharged());
	}




}
