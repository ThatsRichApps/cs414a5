package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.ExitKioskUI;

public class ExitKioskUITest extends ExitKioskUI {

	private static final long serialVersionUID = -7668356110169785209L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExitKioskUI() {
		ExitKioskUI exitKioskUI = new ExitKioskUI();
		assertEquals("ExitKioskUI", exitKioskUI.toString());
	}

}
