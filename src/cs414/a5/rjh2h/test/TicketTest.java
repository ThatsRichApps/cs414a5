package cs414.a5.rjh2h.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs414.a5.rjh2h.Ticket;
import cs414.a5.rjh2h.Vehicle;

public class TicketTest extends Ticket {
	
	private Ticket testTicket1;
	private Ticket testTicket2;
	
	@Before
	public void setUp() throws Exception {
		testTicket1 = new Ticket();
	
		BigDecimal rate = new BigDecimal("2.00");
		int ticketNumber = 123;
		
		testTicket2 = new Ticket(ticketNumber, rate);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTicket1() {
		assertEquals("Ticket [999999]", testTicket1.toString());
	}

	@Test
	public void testTicket2() {
		assertEquals("Ticket [123]", testTicket2.toString());
	}

	@Test
	public void testGetTicketNumber() {
		assertEquals(123, testTicket2.getTicketNumber());
	}

	@Test
	public void testGetTimeIn() {
		Date timeNow = new Date();
		Ticket testTicket3 = new Ticket();
		Date timeIn = testTicket3.getTimeIn();
		double timeDifference = timeIn.getTime() - timeNow.getTime();
		assertTrue(timeDifference < 100);
	}

	@Test
	public void testGetRate() {
		BigDecimal rate = new BigDecimal("2.00");
		assertEquals(rate, testTicket2.getRate());
	}

	@Test
	public void testSetRate() {
		BigDecimal newRate = new BigDecimal("3.25"); 
		testTicket2.setRate(newRate);
		assertEquals(newRate, testTicket2.getRate());
	}
	
	@Test
	public void testGetVehicle() {
		Vehicle vehicle = testTicket2.getVehicle();
		assertEquals("Vehicle", vehicle.toString());
	}
	
	
}
