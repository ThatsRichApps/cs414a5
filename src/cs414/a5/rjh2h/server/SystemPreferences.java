package cs414.a5.rjh2h.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cs414.a5.rjh2h.ui.SysAdminUI;

public class SystemPreferences implements ActionListener {

	private SysAdminUI sysAdminUI;
	
	private Map<String, String> systemPrefs = new HashMap<String, String>();
	@SuppressWarnings("unused")
	private GarageImpl garage;
	
	public SystemPreferences(){
	}
	
	public SystemPreferences(GarageImpl garage) {
		
		this.garage = garage;
		
		//systemPrefs = (Map<String, String>) garage.getSystemPreferences();
		
		systemPrefs.put("rate", "2.00");
		systemPrefs.put("maximum occupancy", "5");
		systemPrefs.put("max fee", "48.00");
			
	}
	
	@Override
	public String toString() {
		return "SystemPreferences";
	}

	public void showAdminUI() {
		if (sysAdminUI == null) {
			sysAdminUI = new SysAdminUI(systemPrefs);
			sysAdminUI.addActionListeners(this);
		}
	}

	public BigDecimal getRate() {
		return (new BigDecimal(systemPrefs.get("rate")));
	}

	public BigDecimal getMaxFee() {
		return (new BigDecimal(systemPrefs.get("max fee")));
	}

	public int getMaxOccupancy() {
		return Integer.parseInt(systemPrefs.get("maximum occupancy"));
	}

	public void setRate(BigDecimal rate) {
		systemPrefs.put("rate", rate.toString());
	}

	public void setMaxFee(BigDecimal maxFee) {
		systemPrefs.put("max fee", maxFee.toString());
	}

	public void setMaxOccupancy(int maxOccupancy) {
		systemPrefs.put("maximum occupancy", Integer.toString(maxOccupancy));
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		String eventName = event.getActionCommand();
		
		//System.out.println("event:" + eventName);
		
		switch (eventName) {
			
			case "comboBoxChanged":
				// get the current pref name
				String prefName = sysAdminUI.getPreferenceListBoxSelection();
				// get preference value
				String value = systemPrefs.get(prefName).toString();
				// set the value
				sysAdminUI.setPreferenceField(value);
				break;
				
			case "Set Preference":
				// get the current pref name
				String preference = sysAdminUI.getPreferenceListBoxSelection();
				// get preference value
				String valueField = sysAdminUI.getPreferenceField(); 
				
				systemPrefs.put(preference, valueField);
				break;
		
		}
		
	}
	
}
