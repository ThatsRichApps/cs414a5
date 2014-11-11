package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ParkingGarage;

public class ParkingGarageTest extends ParkingGarage {

	private ParkingGarage garageTest;
	
	@Before
	public void setUp() throws Exception {
		garageTest = new ParkingGarage();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParkingGarage1() {
		assertEquals("ParkingGarage", garageTest.toString());
	}

}
