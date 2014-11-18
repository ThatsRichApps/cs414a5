package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.server.GarageImpl;

public class ParkingGarageTest extends GarageImpl {

	private GarageImpl garageTest;
	
	@Before
	public void setUp() throws Exception {
		garageTest = new GarageImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParkingGarage1() {
		assertEquals("ParkingGarage", garageTest.toString());
	}

}
