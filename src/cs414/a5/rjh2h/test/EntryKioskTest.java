package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.entry.EntryKiosk;
import cs414.a5.rjh2h.server.GarageImpl;

public class EntryKioskTest extends EntryKiosk {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEntryKiosk1() {
	
		GarageImpl garageTest = new GarageImpl();
		
		EntryKiosk entryKiosk = new EntryKiosk(garageTest);
		
		assertEquals("EntryKiosk", entryKiosk.toString());
	
	}

}