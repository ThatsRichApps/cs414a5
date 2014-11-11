package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.LoginUI;

public class LoginUITest extends LoginUI {

		private static final long serialVersionUID = -8620098540902002410L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginUI() {
		LoginUI loginUITest = new LoginUI();
		assertEquals("LoginUI", loginUITest.toString());
	}

}
