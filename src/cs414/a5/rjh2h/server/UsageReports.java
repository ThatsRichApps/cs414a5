package cs414.a5.rjh2h.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import cs414.a5.rjh2h.common.DataStorage;
import cs414.a5.rjh2h.ui.UsageReportsUI;

public class UsageReports implements ActionListener {
	
	private UsageReportsUI usageReportsUI;
	private DataStorage dataStorage;
	
	public UsageReports() {
	}
	
	public UsageReports(DataStorage dataStorage) {
		usageReportsUI = new UsageReportsUI();
		
		this.dataStorage = dataStorage;
		

		usageReportsUI.setMainMessageLabel("Transactions:\n");
		
		Calendar calendar = Calendar.getInstance();
	    //calendar.add(Calendar.DAY_OF_YEAR, -10);   //Go to date, x days ago 
	    calendar.add(Calendar.MONTH, -20);   //Go to date, x months ago 
    	
	    Date startDate = calendar.getTime(); 
    	
		showMonthlyData(startDate);
		
		showTransactionStats();
		
	}
	
	@Override
	public String toString() {
		return "UsageReports";
	}

	public void actionPerformed(ActionEvent event) {
	
	}
	
	private void showTransactionStats() {
		
		usageReportsUI.appendTransactionStats("Transaction Type : Number Transactions : Total Amount\n");
		
	}
	
	private void showHourlyData (Date startDate) {
		
		Map<Date, Integer> occupancyData = dataStorage.getOccupancyData();
		
		StringBuilder sb = new StringBuilder();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh a");
		
		sb.append("Date, Hour : Average Occupancy\n");
		
		Map<String, Integer> hourlySums = new TreeMap<String, Integer>();
		Map<String, Integer> hourlyCount = new TreeMap<String, Integer>();
		
		for (Map.Entry<Date,Integer> entry : occupancyData.entrySet()) {
		
			Date key = entry.getKey();
			Integer value = entry.getValue();

			if (key.before(startDate)) { continue; };
			
			String newkey = df.format(key);

			//System.out.println(newkey + " => " + value);
		
			if (hourlySums.get(newkey) == null) {
				hourlySums.put(newkey, value);
				hourlyCount.put(newkey, 1);
			} else {
				hourlySums.put(newkey, hourlySums.get(newkey) + value);
				hourlyCount.put(newkey, hourlyCount.get(newkey) + 1);
			}
			
		}

		for (Map.Entry<String,Integer> entry : hourlySums.entrySet()) {
		
			String dateString = entry.getKey();
			int sum = entry.getValue();
			
			int count = hourlyCount.get(dateString);
			
			int average = (int) Math.round((double)sum / count);
			
			sb.append(dateString + " : " + average + "\n");
			
		}
		
		String s = sb.toString();
		usageReportsUI.appendOccupancyStats(s);
		
	}
	
	
private void showMonthlyData (Date startDate) {
		
		Map<Date, Integer> occupancyData = dataStorage.getOccupancyData();
		
		StringBuilder sb = new StringBuilder();
		DateFormat df = new SimpleDateFormat("yyyy- MM");
		
		sb.append("Month/Year : Average Occupancy\n");
		
		Map<String, Integer> monthlySums = new TreeMap<String, Integer>();
		Map<String, Integer> monthlyCount = new TreeMap<String, Integer>();
		
		for (Map.Entry<Date,Integer> entry : occupancyData.entrySet()) {
		
			Date key = entry.getKey();
			Integer value = entry.getValue();

			if (key.before(startDate)) { continue; };
			
			String newkey = df.format(key);

			//System.out.println(newkey + " => " + value);
		
			if (monthlySums.get(newkey) == null) {
				monthlySums.put(newkey, value);
				monthlyCount.put(newkey, 1);
			} else {
				monthlySums.put(newkey, monthlySums.get(newkey) + value);
				monthlyCount.put(newkey, monthlyCount.get(newkey) + 1);
			}
			
		}

		for (Map.Entry<String,Integer> entry : monthlySums.entrySet()) {
		
			String dateString = entry.getKey();
			int sum = entry.getValue();
			
			int count = monthlyCount.get(dateString);
			
			int average = (int) Math.round((double)sum / count);
			
			sb.append(dateString + " : " + average + "\n");
			
		}
		
		String s = sb.toString();
		usageReportsUI.appendOccupancyStats(s);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
