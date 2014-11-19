package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.Sign;

public class SignTest extends Sign {

	private Sign testSign;
	
	
	@Before
	public void setUp() throws Exception {
	
		testSign = new Sign();
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSign1() {
		assertEquals("Entry Sign Controller", testSign.toString());
	}

	@Test
	public void testSign2() {
		
		testSign.setMessage("Garage is OPEN!");
		assertEquals("Entry Sign Controller", testSign.toString());

	}

}
