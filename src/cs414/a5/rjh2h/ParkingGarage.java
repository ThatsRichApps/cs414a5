package cs414.a5.rjh2h;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import cs414.a5.rjh2h.ui.GarageUI;

public class ParkingGarage extends Observable implements Observer, ActionListener {
	
	// Parking Garage occupancy is observable.  Also, the garage observes the entryKiosk
	// in order to know when cars are entering
	
	private boolean isOpen;
	private int currentOccupancy;
	private GarageUI garageUI;
	private SystemPreferences systemPreferences;
	@SuppressWarnings("unused")
	private UsageReports usageReports;
	private DataStorage dataStorage;
	
	//private HashMap<String, Ticket> virtualTicketMap = new HashMap<String, Ticket>();
	//private HashMap<Integer, Ticket> physicalTicketMap = new HashMap<Integer, Ticket>();

	public ParkingGarage() {
		super();
		
		// create the main gui here
		garageUI = new GarageUI();
		systemPreferences = new SystemPreferences(this);
		dataStorage = new DataStorage();

		garageUI.addSysAdminActionListener(this);
		garageUI.addShowUsageActionListener(this);
		
		Sign entrySign = new Sign();
		this.addObserver(entrySign);
		
		EntryKiosk entryKiosk = new EntryKiosk(this);
		this.addObserver(entryKiosk);
		
		@SuppressWarnings("unused")
		ExitKiosk exitKiosk = new ExitKiosk(this);
		
		currentOccupancy = 0;
		
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
		setChanged();
		notifyObservers("GarageOpen");
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

	public void update(Observable o, Object arg) {
	    // update garage.occupancy and the garage.isOpen status as needed 
		// the garage observes both the entry and exit in order to 
		// keep occupancy correct
		if ((String)arg == "entry") {
			currentOccupancy++;
		} else if ((String)arg == "exit") {
			currentOccupancy--;
			if (currentOccupancy < 0 ) {currentOccupancy = 0;};
		}
		
		// update the occupancy of the garage ui view
		garageUI.setMessage("Current Occupancy:" + currentOccupancy);

		Date timeNow = new Date();
		dataStorage.updateOccupancyData(timeNow, currentOccupancy);
		
		// If the garage reaches max occupancy, close it down by 
		// notifying the sign and the entryKiosk
		if (currentOccupancy >= systemPreferences.getMaxOccupancy()) {
			this.isOpen = false;		
			this.setChanged();
			this.notifyObservers("GarageFull");
		} else if (this.isOpen == false) {
			this.isOpen = true;		
			this.setChanged();
			this.notifyObservers("GarageOpen");
		}
		
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
		
		return dataStorage.getTicketByNumber(ticketNumber);
		
	}
	
	public Ticket getTicketForLicensePlate(String licensePlate) {
		
		return dataStorage.getTicketForLicensePlate(licensePlate);
		
	}
	
	public void saveTransaction(Transaction transaction) {
		
		dataStorage.saveTransaction(transaction);
	
	}
	
	
	
}

