package cs414.a5.rjh2h.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import cs414.a5.rjh2h.common.*;
import cs414.a5.rjh2h.ui.*;

public class GarageImpl extends UnicastRemoteObject implements Garage, RemoteSubject, ActionListener {
	
	// Parking Garage occupancy is observable.  Also, the garage observes the entryKiosk
	// in order to know when cars are entering
	
	private static final long serialVersionUID = 1L;
	private boolean isOpen;
	private int currentOccupancy;
	private GarageUI garageUI;
	private SystemPreferences systemPreferences;
	@SuppressWarnings("unused")
	private UsageReports usageReports;
	private DataStorage dataStorage;
	
	private ArrayList<RemoteObserver> observers = new ArrayList<RemoteObserver>();
	
	//private static GarageImpl garage;
	
	public GarageImpl() throws java.rmi.RemoteException {
		super();
		
		// create the main gui here
		garageUI = new GarageUI();
		systemPreferences = new SystemPreferences(this);
		dataStorage = new DataStorage();

		garageUI.addSysAdminActionListener(this);
		garageUI.addShowUsageActionListener(this);
		
		this.setOpen(true);
		
		this.currentOccupancy = 0;
		
	}
	
	
	@Override
	public String toString() {
		return "ParkingGarage";
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		notifyObservers();
	}

	
	public DataStorage getDataStorage() {
		return dataStorage;
	}

	public void setDataStorage(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}

	public SystemPreferences getSystemPreferences() {
		return systemPreferences;
	}

	public void setSystemPreferences(SystemPreferences systemPreferences) {
		this.systemPreferences = systemPreferences;
	}
	
	public void updateOccupancy(String entryOrExit) {
		// successful entry or exit, update occupancy and observers
		
		System.out.println("occupancy update for: " + entryOrExit);
		
		if (entryOrExit.equals("entry")) {
			System.out.println("occupancy = " + currentOccupancy);
			currentOccupancy++;
		} else if (entryOrExit.equals("exit")) {
			currentOccupancy--;
			if (currentOccupancy < 0 ) {currentOccupancy = 0;};
		}
		
		garageUI.setMessage("Current Occupancy: " + currentOccupancy);
		garageUI.setVisible(true);

		Date timeNow = new Date();
		dataStorage.updateOccupancyData(timeNow, currentOccupancy);
				
		// If the garage reaches max occupancy, close it down by 
		// notifying the sign and the entryKiosk
		if (currentOccupancy >= systemPreferences.getMaxOccupancy()) {
			this.isOpen = false;		
			notifyObservers();
		} else if (this.isOpen == false) {
			this.isOpen = true;		
			notifyObservers();
		}
			
	}
	
	public BigDecimal getRate() {
		
		return getSystemPreferences().getRate();
		
	}
	
	public BigDecimal getMaxFee() {
		
		return getSystemPreferences().getMaxFee();
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		String eventName = event.getActionCommand();
		
		switch (eventName) {
		
		case "SysAdmin":
			systemPreferences.showAdminUI();
			break;
		case "ShowUsage":
			usageReports = new UsageReports(dataStorage);
			break;
		
		}
	}
	

	public void addVirtualTicket(Ticket ticket) {
		
		dataStorage.addVirtualTicket(ticket);
			
	}
	
	public void addPhysicalTicket(Ticket ticket) {
		
		dataStorage.addPhysicalTicket(ticket);
		
	}
	
	public Ticket getTicketNumber (int ticketNumber) {
		
		System.out.println("looking up ticket in dataStorage");
		return dataStorage.getTicketByNumber(ticketNumber);
		
	}
	
	public Ticket getTicketForLicensePlate(String licensePlate) {
		
		return dataStorage.getTicketForLicensePlate(licensePlate);
		
	}
	
	public void saveTransaction(Transaction transaction) {
		
		dataStorage.saveTransaction(transaction);
	
	}

	@Override
	public void addObserver(RemoteObserver o) throws RemoteException {
		observers.add(o);
	}


	@Override
	public void remoteObserver(RemoteObserver o) throws RemoteException {
		observers.remove(o);
	}
	
	private void notifyObservers() {
		Iterator<RemoteObserver> i = observers.iterator();
		while ( i.hasNext() ) {
			RemoteObserver o = (RemoteObserver) i.next();
			try {
				o.update( this );
			} catch (RemoteException re) {
				// do something
			}
		}	
	}
	
	public String getStatus() throws RemoteException {
		if (isOpen) {
			return ("GarageOpen");
		} else {
			return ("GarageFull");
		}
	}

	@Override
	public void addTicket(Ticket currentTicket) throws RemoteException {
		// merge these to one...
		System.out.println("adding ticket to dataStorage");
		dataStorage.addPhysicalTicket(currentTicket);
		dataStorage.addVirtualTicket(currentTicket);
	}
	
	
	
	
	
}

