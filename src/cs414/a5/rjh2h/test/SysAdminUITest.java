package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.SysAdminUI;

public class SysAdminUITest extends SysAdminUI {

	private static final long serialVersionUID = -5001546638337952771L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSysAdminUI() {
		Map<String, String> systemPrefs = new HashMap<String, String>();
		systemPrefs.put("rate", "3.00");
		SysAdminUI sysAdminUITest = new SysAdminUI(systemPrefs);
		assertEquals("SysAdminUI", sysAdminUITest.toString());
	}

}
