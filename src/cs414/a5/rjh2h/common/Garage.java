package cs414.a5.rjh2h.common;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Garage extends Remote {

	public String getStatus() throws RemoteException;

	public void addObserver(RemoteObserver o) throws RemoteException;
	public void addTicket(Ticket currentTicket) throws RemoteException;

	public void updateOccupancy(String string) throws RemoteException;

	public boolean isOpen() throws RemoteException;

	public Ticket getTicketNumber(int ticketNumber) throws RemoteException;

	public Ticket getTicketForLicensePlate(String licensePlate) throws RemoteException;

	public void saveTransaction(Transaction currentTransaction) throws RemoteException;

	public BigDecimal getRate() throws RemoteException;

	public BigDecimal getMaxFee() throws RemoteException;
	
}
