package cs414.a5.rjh2h;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class Gate extends Observable {
	
	private boolean isOpen;
	private Timer carWaitTimer;
	public final static int WAIT_TIME = 2000;

	
	public Gate() {
		super();
		closeGate();
	}
	
	@Override
	public String toString() {
		return "Gate";
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void openGate() {
		this.isOpen = true;
		setChanged();
		notifyObservers("GateOpen");
	}
	
	public void closeGate() {
		this.isOpen = false;
		setChanged();
		notifyObservers("GateClosed");
	}
	
	public void openGateForCar () {
		// opens the gate, waits for the car, closes the gate
		openGate();
		waitForCar();
	}
	
	private void waitForCar() {
		
		// check the car sensor and wait while a car is there
		// since we don't have an actual car sensor, we wait
		// a set amount of time
		
		carWaitTimer = new Timer(WAIT_TIME, new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
			    closeGate();
			    carWaitTimer.stop();
		    }    
		});
		
		carWaitTimer.start();
		
	}
	
	
	
	
	
	
	
	
	
	

}
