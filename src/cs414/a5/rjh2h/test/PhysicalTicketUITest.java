package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.PhysicalTicketUI;

public class PhysicalTicketUITest extends PhysicalTicketUI {

	private static final long serialVersionUID = -4573213815040008030L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPhysicalTicketUI() {
		PhysicalTicketUI physicalTicketTest = new PhysicalTicketUI();
		assertEquals("PhysicalTicketUI", physicalTicketTest.toString());
	}

}
