package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.AccountPayment;

public class AccountPaymentTest extends AccountPayment {

	private static final long serialVersionUID = 1L;
	private AccountPayment accountPaymentTest;

	@Before
	public void setUp() throws Exception {
		BigDecimal amount = new BigDecimal("100.00");
		accountPaymentTest = new AccountPayment(amount);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAccountPayment1() {
		assertEquals("AccountPayment", accountPaymentTest.toString());
	}

	@Test
	public void testAccountPayment2() {
		accountPaymentTest.setLicensePlate("CO-AAA-111");
		assertEquals("CO-AAA-111", accountPaymentTest.getLicensePlate());
	}
	
}
