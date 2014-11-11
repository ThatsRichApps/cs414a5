package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.RegisterUI;

public class RegisterUITest extends RegisterUI {

	private static final long serialVersionUID = -4450357241893467209L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterUI() {
		RegisterUI registerUITest = new RegisterUI();
		assertEquals("RegisterUI", registerUITest.toString());
	}

}
