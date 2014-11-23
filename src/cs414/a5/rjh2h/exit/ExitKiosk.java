package cs414.a5.rjh2h.exit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import cs414.a5.rjh2h.common.BillingAccount;
import cs414.a5.rjh2h.common.Garage;
import cs414.a5.rjh2h.common.Gate;
import cs414.a5.rjh2h.common.Ticket;
import cs414.a5.rjh2h.common.Transaction;
import cs414.a5.rjh2h.ui.ExitKioskUI;

public class ExitKiosk implements ActionListener, Observer, Serializable {

	private static final long serialVersionUID = 1L;
	private ExitKioskUI exitUI;
	private Garage garage;
	private Register register;
	private Gate exitGate;
	private Ticket currentTicket;
	private Transaction currentTransaction;
	
	public static void main(String[] args) {
		Garage garage = null;
		try {
			garage = (Garage) 
					Naming.lookup("rmi://" + args[0] + ":" + args[1]  + "/GarageService");

			@SuppressWarnings("unused")
			ExitKiosk exitKiosk = new ExitKiosk(garage);
		
		} catch (MalformedURLException murle) {
			System.out.println("MalformedURLException");
			System.out.println(murle);
			System.exit(-1);
		} catch (RemoteException re) {
			System.out.println("RemoteException - Make sure server is started"); 
			System.out.println(re);
			System.exit(-1);
		} catch (NotBoundException nbe) {
			System.out.println("NotBoundException");
			System.out.println(nbe);
			System.exit(-1);
		}
		
		
	}
	
	public ExitKiosk() throws RemoteException {
		super();
	}	
	
	public ExitKiosk(Garage garage) throws RemoteException {
		// set the garage as the observer to track car exit
		this.garage = garage;
		
		// create the exit kiosk UI and listen for actions
		exitUI = new ExitKioskUI();
		
		// Add the ExitKiosk (this) as the Action Listener for UI Actions
		exitUI.addActionListeners(this);
		
		// create a gate and observe it's status
		exitGate = new Gate();	
		exitGate.addObserver(this);
		exitGate.closeGate();
		
		// create a cash register for taking payments
		register = new Register(garage);
	
		// pass the exitGate so that the cashier can open it too
		register.setExitKiosk(this);
		
		currentTicket = null;
		currentTransaction = null;
		
				
	}

	@Override
	public String toString() {
		return "ExitKiosk";
	}

	public void actionPerformed(ActionEvent event) {
		
		String eventName = event.getActionCommand();
		
		switch (eventName) {

		case "TicketField":
			lookupTicket();
			break;
		case "LicenseField":
			lookupLicense();
			break;
		case "LostTicket":
			lostTicket();
			break;
		case "PayCash":
			exitUI.enableFindTicketButtons(false);
			exitUI.enablePaymentFields(false);
			currentTransaction.createCashPayment();
			register.validatePayment(currentTransaction);
			break;
		case "PayOnAccount":
			exitUI.enableFindTicketButtons(false);
			exitUI.enablePaymentFields(false);
			
			// get the license plate of the currenet vehicle
			String licensePlate = currentTicket.getVehicle().getLicensePlate();
			
			// lookup billing account by licensePlate
			BillingAccount billingAccount;
			try {
				billingAccount = garage.getBillingAccount(licensePlate);
			} catch (RemoteException e) {
				billingAccount = null;
			}
			
			currentTransaction.createAccountPayment(billingAccount);
			register.validatePayment(currentTransaction);
			break;
		case "PayCreditCard":
			exitUI.enableFindTicketButtons(false);
			exitUI.enablePaymentFields(false);
			String creditCardNumber = exitUI.getCreditCardNumber();
			int expMonth = exitUI.getExpMonth();
			int expYear = exitUI.getExpYear();
			currentTransaction.createCreditPayment(creditCardNumber, expMonth, expYear);
			register.validatePayment(currentTransaction);
			break;
		case "CanNotPay":
			exitUI.enableFindTicketButtons(false);
			exitUI.enablePaymentFields(false);
			currentTransaction.createAccountPayment(null);
			register.validatePayment(currentTransaction);
			break;
		}
				
	}

	private void lookupTicket() {
		
		int ticketNumber = exitUI.getTicketNumber();
		
		try {
			currentTicket = garage.getTicketNumber(ticketNumber);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (currentTicket == null) {
			// handle ticket not found
			exitUI.setMessage("Ticket Not Found");
			exitUI.setPaymentMessage("");
		} else {
			currentTransaction = new Transaction(currentTicket);
			String message = "Fee by Ticket: " + ticketNumber;
			exitUI.setMessage(message);
			exitUI.setPaymentMessage("You owe: $" + currentTransaction.getAmount());
			register.setCurrentTransaction(currentTransaction);
		
			// now enable the payment buttons and disable find ticket buttons
			exitUI.enableFindTicketButtons(false);
			exitUI.enablePaymentFields(true);
		}
		
	}
	
	private void lookupLicense() {
		String licensePlate = exitUI.getLicensePlate();
		try {
			currentTicket = garage.getTicketForLicensePlate(licensePlate);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (currentTicket == null) {
			// handle ticket not found
			exitUI.setMessage("Ticket Not Found");
			exitUI.setPaymentMessage("");
		} else {
			currentTransaction = new Transaction(currentTicket);
			String message = "Fee by License: " + licensePlate;
			exitUI.setMessage(message);
			exitUI.setPaymentMessage("You owe: $" + currentTransaction.getAmount());
			register.setCurrentTransaction(currentTransaction);

			// now enable the payment buttons and disable find ticket buttons
			exitUI.enableFindTicketButtons(false);
			exitUI.enablePaymentFields(true);
		}
	}
	
	private void lostTicket() {
		currentTicket = new Ticket();  // create a dummy ticket
		try {
			currentTransaction = new Transaction(garage.getMaxFee());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message = "Lost Ticket, Max Fee ";
		exitUI.setMessage(message);
		exitUI.setPaymentMessage("You owe: $" + currentTransaction.getAmount());
		register.setCurrentTransaction(currentTransaction);

		// now enable the payment buttons and disable find ticket buttons
		exitUI.enableFindTicketButtons(false);
		exitUI.enablePaymentFields(true);
	}
	
	public void retryPayment() {
		exitUI.enablePaymentFields(true);
	}
	
	public void openGate() {
		try {
			garage.updateOccupancy("exit");
			garage.saveTransaction(currentTransaction);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//register.resetUI();
		
		currentTicket = null;
		currentTransaction = null;
		exitUI.setMessage("");
		exitUI.setPaymentMessage("");
		exitUI.enableFindTicketButtons(true);
		exitUI.enablePaymentFields(false);
		exitGate.openGateForCar();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		// Entry observes the gate with a standard java observable / observer 
		
		// get the state
		String status = "";
		
		if (o.equals(exitGate)) {
			status = exitGate.getStatus();
		} 	
		
		switch (status) {
			case ("GateOpen"):
				exitUI.setGateStatus(true);
				break;
			case ("GateClosed"):	
				exitUI.setGateStatus(false);
				break;
		}
	}

	
}
