package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.SignUI;

public class SignUITest extends SignUI {

	private static final long serialVersionUID = 3065290320564797754L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSignUI() {
		SignUI signUITest = new SignUI();
		assertEquals("SignUI", signUITest.toString());
	}

}
