package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.common.UsageReports;

public class UsageReportsTest extends UsageReports {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUsageReports() {
		UsageReports usageReportsTest = new UsageReports();
		assertEquals("UsageReports", usageReportsTest.toString());
	}

}
