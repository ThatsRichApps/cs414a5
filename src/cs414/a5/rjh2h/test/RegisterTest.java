package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.Garage;
import cs414.a5.rjh2h.exit.Register;

public class RegisterTest extends Register {

	private static final long serialVersionUID = 1L;

	public RegisterTest(Garage garage) {
		super(garage);
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegister() {
		Register registerTest = new Register();
		assertEquals("Register", registerTest.toString());
	}

}
