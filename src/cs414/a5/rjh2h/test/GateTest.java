package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.Gate;

public class GateTest {
	
	private Gate testGate;

	@Before
	public void setUp() throws Exception {
		
		testGate = new Gate();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGate1() {
		assertEquals("Gate",testGate.toString());	
	}

	@Test
	public void testIsOpen() {
		assertFalse(testGate.isOpen());
	}

	@Test
	public void testOpenGate() {
		testGate.openGate();
		assertTrue(testGate.isOpen());
	}

	@Test
	public void testCloseGate() {
		testGate.closeGate();
		assertFalse(testGate.isOpen());
	}

	@Test
	public void testOpenGateForCar() {
		testGate.closeGate();
		testGate.openGateForCar();
		assertTrue(testGate.isOpen());
	}

}
