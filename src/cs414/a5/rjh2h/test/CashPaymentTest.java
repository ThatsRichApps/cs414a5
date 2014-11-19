package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.CashPayment;

public class CashPaymentTest extends CashPayment {
	
	private CashPayment cashPayment;
	
	@Before
	public void setUp() throws Exception {
		BigDecimal amount = new BigDecimal("23.00");
		cashPayment = new CashPayment(amount);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCashPayment1() {
		assertEquals("CashPayment", cashPayment.toString());
	}
	
	@Test
	public void testCashPayment2() {
		assertEquals(new BigDecimal("23.00"), cashPayment.getAmountDue());
	}
	
	@Test
	public void testCashPayment3() {
		BigDecimal cashTendered = new BigDecimal("40.00");
		cashPayment.setCashTendered(cashTendered);
		assertEquals(new BigDecimal("40.00"), cashPayment.getCashTendered());
	}
	
	@Test
	public void testCashPayment4() {
		BigDecimal cashTendered = new BigDecimal("40.00");
		cashPayment.setCashTendered(cashTendered);
		assertEquals(new BigDecimal("17.00"), cashPayment.getChange());
	}
	
}
