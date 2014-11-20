package cs414.a5.rjh2h.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import cs414.a5.rjh2h.common.DataStorage;
import cs414.a5.rjh2h.ui.UsageReportsUI;

public class UsageReports implements ActionListener {
	
	private UsageReportsUI usageReportsUI;
	
	public UsageReports() {
	}
	
	public UsageReports(DataStorage dataStorage) {
		usageReportsUI = new UsageReportsUI();
		
		// I only had time here in iteration one to create a basic report of
		// total min and max occupancy : more types of usage summaries would
		// be added modularly to this class
		
		// go through the data and show some summaries
		HashMap<Date, Integer> occupancyData = dataStorage.getOccupancyData();
		
		int min = Integer.MAX_VALUE;
	    int max = Integer.MIN_VALUE;
		
		for (Date timeStamp : occupancyData.keySet()) {
			Integer occupancy = occupancyData.get(timeStamp);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(timeStamp);
			
			// eventually roll up stats by year, month, day
			//int year = calendar.get(Calendar.YEAR);
		    //int month = calendar.get(Calendar.MONTH);
		    //int day = calendar.get(Calendar.DAY_OF_MONTH);
		    //int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		    if (occupancy < min) {
		    	min = occupancy;
		    }
		    
		    if (occupancy > max) {
		    	max = occupancy;
		    }
		    
		}
		
		if (min == Integer.MAX_VALUE) {
			min = 0;
		}
		if (max == Integer.MIN_VALUE) {
			max = 0;
		}
		
		usageReportsUI.setMainMessageLabel("Min occupancy: " + min + " Max occupancy: " + max);
		
	}
	
	@Override
	public String toString() {
		return "UsageReports";
	}

	public void actionPerformed(ActionEvent event) {
	
	}

}
