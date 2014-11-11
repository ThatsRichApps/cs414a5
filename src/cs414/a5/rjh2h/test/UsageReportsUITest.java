package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.ui.UsageReportsUI;

public class UsageReportsUITest extends UsageReportsUI {

	private static final long serialVersionUID = 3792384268219444647L;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UsageReportsUI usageReportsUITest = new UsageReportsUI();
		assertEquals("UsageReportsUI", usageReportsUITest.toString());
	}

}
