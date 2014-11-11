package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ExitKiosk;
import cs414.a5.rjh2h.ParkingGarage;

public class ExitKioskTest extends ExitKiosk {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExitKiosk1() {

		ParkingGarage garageTest = new ParkingGarage();
		
		ExitKiosk exitKiosk = new ExitKiosk(garageTest);
	
		assertEquals("ExitKiosk", exitKiosk.toString());
	
	}

}
