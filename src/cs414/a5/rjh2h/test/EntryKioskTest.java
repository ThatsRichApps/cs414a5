package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.entry.EntryKiosk;
import cs414.a5.rjh2h.server.GarageImpl;

public class EntryKioskTest extends EntryKiosk {

	public EntryKioskTest() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEntryKiosk1() {
	
		GarageImpl garageTest;
		EntryKiosk entryKiosk;
		
		try {
			garageTest = new GarageImpl();
			entryKiosk = new EntryKiosk(garageTest);
			assertEquals("EntryKiosk", entryKiosk.toString());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}


}
