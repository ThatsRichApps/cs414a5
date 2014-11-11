package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.DataStorage;

public class DataStorageTest extends DataStorage {

	private DataStorage dataStorage;
	
	@Before
	public void setUp() throws Exception {

		dataStorage = new DataStorage();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDataStorage1() {
		assertEquals("DataStorage", dataStorage.toString());
	}

	
	
	
	
	
	
}
