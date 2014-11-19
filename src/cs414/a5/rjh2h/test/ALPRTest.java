package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.ALPR;

public class ALPRTest extends ALPR {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testALPR1() {

		ALPR testALPR = new ALPR();
		assertEquals(10, testALPR.getLicensePlate().length());
		
	}

	@Test
	public void testALPR2() {

		ALPR testALPR = new ALPR("AK-AAA-123");
		assertEquals("AK-AAA-123", testALPR.getLicensePlate());
		
	}
	
	@Test
	public void testALPR3() {

		ALPR testALPR = new ALPR(null);
		assertEquals(10, testALPR.getLicensePlate().length());
			
	}
	
	
}
