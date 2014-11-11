package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.GarageUI;

public class GarageUITest extends GarageUI {

	private static final long serialVersionUID = 7843327271145601342L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGarageUI() {
		GarageUI garageUITest = new GarageUI();
		assertEquals("GarageUI", garageUITest.toString());
	}

}
