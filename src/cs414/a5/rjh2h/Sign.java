package cs414.a5.rjh2h;

import java.util.Observable;
import java.util.Observer;

import cs414.a5.rjh2h.ui.SignUI;

public class Sign implements Observer {

	private SignUI entrySignUI;
	
	public Sign () {
		
		entrySignUI = new SignUI();
		
	}
	
	public void setMessage(String message) {
		entrySignUI.setMessage(message);
	}
	
	@Override
	public String toString() {
		return "Entry Sign Controller";
	}

	public void update(Observable o, Object arg) {
	    // this updates the sign when the garage state changes
		
	    String garageState = (String) arg;
	    String message = "";
	    
	    switch (garageState) {
	    
	    case ("GarageOpen"):
	    	message = "Open";
	    	break;
	    case ("GarageFull"):
	    	message = "Full";
	    	break;
	    }
	    	
	    setMessage("Garage is " + message);
	     
	}
	
}
