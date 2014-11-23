package cs414.a5.rjh2h.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DataStorage {
	
	private Map<String, Ticket> virtualTickets = new HashMap<String, Ticket>();
	private Map<Integer, Ticket> physicalTickets = new HashMap<Integer, Ticket>();
	private Map<Date, Integer> occupancyData = new TreeMap<Date, Integer>();
	private Map<Integer, Transaction> transactionRecords = new HashMap<Integer, Transaction>();
	private int transactionID = 0;
	
	private Map<String, SystemAccount> systemAccounts = new HashMap<String, SystemAccount>();
	private Map<String, BillingAccount> billingAccounts = new HashMap<String, BillingAccount>();
	
	public DataStorage() {
		
		// initialize a few system accounts
		
		systemAccounts.put("rich", new SystemAccount("rich", "pass1", "admin"));	
		
	}
	
	@Override
	public String toString() {
		return "DataStorage";
	}
	
	public Map<Date, Integer> getOccupancyData() {
		return occupancyData;
	}

	public Map<String, Ticket> getVirtualTicketMap() {
		return virtualTickets;
	}

	public Map<Integer, Ticket> getPhysicalTicketMap() {
		return physicalTickets;
	}

	public Map<String, SystemAccount> getSystemAccountList() {
		return systemAccounts;
	}

	public void setSystemAccountList(HashMap<String, SystemAccount> systemAccountList) {
		this.systemAccounts = systemAccountList;
	}

	public void setVirtualTicketMap(HashMap<String, Ticket> virtualTicketMap) {
		this.virtualTickets = virtualTicketMap;
	}

	public void setPhysicalTicketMap(HashMap<Integer, Ticket> physicalTicketMap) {
		this.physicalTickets = physicalTicketMap;
	}
	
	public void addVirtualTicket(Ticket ticket) {
		String key = ticket.getVehicle().getLicensePlate();
		virtualTickets.put(key, ticket);
	}
	
	public void addPhysicalTicket(Ticket ticket) {
		int key = ticket.getTicketNumber();
		physicalTickets.put(key, ticket);
	}

	public void addSystemAccount(SystemAccount systemAccount) {
		String key = systemAccount.getUsername();
		systemAccounts.put(key, systemAccount);
	}
	
	public SystemAccount getSystemAccount(String username) {
		return systemAccounts.get(username);
	}
	
	public boolean validateSystemAccount (String username, String password) {
		
		if ((username == null) || (password == null)) {
			return false;
		}
		
		SystemAccount sysAccount = systemAccounts.get(username);
		
		if (sysAccount == null) return false;
		
		
		if (sysAccount.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	public Ticket getTicketByNumber (int ticketNumber) {
		// lookup the ticket by number 
		Ticket ticket = physicalTickets.get(ticketNumber);
		// ticket can only be used once, so remove it
		physicalTickets.remove(ticketNumber);
		return ticket;
	}

	public Ticket getTicketForLicensePlate (String licensePlate) {
		Ticket ticket = virtualTickets.get(licensePlate);
		// ticket can only be used once, so remove it
		virtualTickets.remove(licensePlate);
		return ticket;
	}
	
	public void saveTransaction (Transaction transaction) {
		// increment the transaction id and use it as a key
		transactionID++;
		transactionRecords.put(transactionID, transaction);
	}
	
	public void updateOccupancyData (Date timestamp, int occupancy) {
		
		//System.out.println("Occupancy: " + timestamp + " : " + occupancy);
		
		occupancyData.put(timestamp,  occupancy);
		
	}

	public BillingAccount getBillingAccount(String licensePlate) {
		
		BillingAccount billing;
		
		try {
			billing = billingAccounts.get(licensePlate);
		} catch (NullPointerException np) {
			billing = null;
		}
			
		return billing;
	
	}
	
	public void addBillingAccount (BillingAccount newAccount) {
		
		String licensePlate = newAccount.getLicensePlate();
		billingAccounts.put(licensePlate, newAccount);
		
	}
	

}
