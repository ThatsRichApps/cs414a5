package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.SystemAccount;

public class SystemAccountTest extends SystemAccount {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSystemAccount() {
		SystemAccount systemAccountTest = new SystemAccount();
		assertEquals("SystemAccount", systemAccountTest.toString());
	}

}
