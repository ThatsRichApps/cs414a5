package cs414.a5.rjh2h.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote {

	void update(Object observable) throws RemoteException;
	
}
