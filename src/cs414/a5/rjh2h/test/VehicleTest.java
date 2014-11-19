package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.Vehicle;

public class VehicleTest extends Vehicle {

	private Vehicle testVehicle1;
	private Vehicle testVehicle2;
	
	@Before
	public void setUp() throws Exception {
		testVehicle1 = new Vehicle();
		testVehicle2 = new Vehicle("VA-ABC-123");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testVehicle1() {
		assertEquals("Vehicle", testVehicle1.toString());
	}
	
	@Test
	public void testVehicle2() {
		assertEquals("Vehicle", testVehicle2.toString());
	}
	
	@Test
	public void testVehicle3() {
		Vehicle testNull = new Vehicle(null);
		assertEquals("Vehicle", testNull.toString());
	}
	
	@Test
	public void testGetLicense1() {
		assertEquals(10, testVehicle1.getLicensePlate().length());
	}

	@Test
	public void testGetLicense2() {
		assertEquals("VA-ABC-123", testVehicle2.getLicensePlate());
	}
	
}

