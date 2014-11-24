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
import cs414.a5.rjh2h.common.Transaction;
import cs414.a5.rjh2h.ui.SelectCriteriaUI;
import cs414.a5.rjh2h.ui.UsageReportsUI;

public class UsageReports implements ActionListener {
	
	private UsageReportsUI usageReportsUI;
	private DataStorage dataStorage;
	private SelectCriteriaUI selectCriteriaUI;
	
	public UsageReports() {
	}
	
	public UsageReports(DataStorage dataStorage) {
		
		selectCriteriaUI = new SelectCriteriaUI();
		selectCriteriaUI.addActionListeners(this);
		this.dataStorage = dataStorage;
			
	}
	
	@Override
	public String toString() {
		return "UsageReports";
	}

	public void actionPerformed(ActionEvent event) {
		
		String eventName = event.getActionCommand();

		// lazy initialization of reports ui
		if (usageReportsUI == null) {
			usageReportsUI = new UsageReportsUI();
		}
		
		switch (eventName) {

		case "RefreshTransStats":
			usageReportsUI.clearTransactionStats();
			showTransactionStats();
			break;
		case "Hourly":
			
		    String daysBackStr = selectCriteriaUI.getUnitsBackField();
		    int daysBack;
		    try {
		    	daysBack = Integer.parseInt(daysBackStr);
		    
		    }
		    catch (NumberFormatException nfe)
		    {
		       // invalid data
		    	daysBack = 0;
		    }
		    
		    // also check for it in range...
		    if (daysBack > 365) daysBack = 0;
		    
		    if (daysBack != 0) {
		    	Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, -daysBack);   //Go to date, x days ago 
		    	Date startDate = calendar.getTime(); 
	    		usageReportsUI.clearOccupancyStats();
		    	showHourlyData(startDate);
		    }
			
			break;
		
		case "Daily":
			
		    String daysStr = selectCriteriaUI.getUnitsBackField();
		    int days;
		    try {
		    	days = Integer.parseInt(daysStr);
		    
		    }
		    catch (NumberFormatException nfe)
		    {
		       // invalid data
		    	days = 0;
		    }
		    
		    // also check for it in range...
		    if (days > 365) days = 0;
		    
		    if (days != 0) {
		    	Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_YEAR, -days);   //Go to date, x days ago 
		    	Date startDate = calendar.getTime(); 
	    		usageReportsUI.clearOccupancyStats();
		    	showDailyData(startDate);
		    }
			
			break;
		case "Monthly":
			String monthsBackStr = selectCriteriaUI.getUnitsBackField();
			
		    int monthsBack;
		    try {
		    	monthsBack = Integer.parseInt(monthsBackStr);
		    }
		    catch (NumberFormatException nfe)
		    {
		       // invalid data
		    	monthsBack = 0;
		    }
		    if (monthsBack != 0) {
		    	Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -monthsBack);   //Go to date, x months ago 
		    	Date startDate = calendar.getTime(); 
	    		usageReportsUI.clearOccupancyStats();
		    	showMonthlyData(startDate);
		    }
			break;
		}
		
	}
	
	private void showTransactionStats() {
		
		Map<Integer, Transaction> transactionRecords = dataStorage.getTransactionData();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Transaction Type : Number Transactions : Total Amount\n");

		
		Map<String, Double> paymentSums = new TreeMap<String, Double>();
		Map<String, Integer> paymentCount = new TreeMap<String, Integer>();

		
		for (Map.Entry<Integer,Transaction> entry : transactionRecords.entrySet()) {
		
			Transaction transaction = entry.getValue();
			double amount = transaction.getAmount().doubleValue();
			
			String paymentType = transaction.getPayment().toString();
			if (paymentSums.get(paymentType) == null) {
				paymentSums.put(paymentType, amount);
				paymentCount.put(paymentType, 1);
			} else {
				paymentSums.put(paymentType, paymentSums.get(paymentType) + amount);
				paymentCount.put(paymentType, paymentCount.get(paymentType) + 1);
			}
			
		}
		
		for (Map.Entry<String,Integer> entry : paymentCount.entrySet()) {
			
			String paymentString = entry.getKey();
			int count = entry.getValue();
			
			double sum = paymentSums.get(paymentString);
			
			sb.append(paymentString + " : " + count + " : " +  sum + "\n");
			
		}
		
		String s = sb.toString();
		usageReportsUI.appendTransactionStats(s);
		
	}
	
	private void showHourlyData (Date startDate) {
		
		Map<Date, Integer> occupancyData = dataStorage.getOccupancyData();
		
		StringBuilder sb = new StringBuilder();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH");
		
		sb.append("Date, Hour : Average Occupancy\n");
		
		Map<String, Integer> hourlySums = new TreeMap<String, Integer>();
		Map<String, Integer> hourlyCount = new TreeMap<String, Integer>();
		
		for (Map.Entry<Date,Integer> entry : occupancyData.entrySet()) {
		
			Date key = entry.getKey();
			Integer value = entry.getValue();

			if (key.before(startDate)) { continue; };
			
			String newkey = df.format(key);

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

private void showDailyData (Date startDate) {
		
		Map<Date, Integer> occupancyData = dataStorage.getOccupancyData();
		
		StringBuilder sb = new StringBuilder();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		sb.append("Date : Average Occupancy\n");
		
		Map<String, Integer> dailySums = new TreeMap<String, Integer>();
		Map<String, Integer> dailyCount = new TreeMap<String, Integer>();
		
		for (Map.Entry<Date,Integer> entry : occupancyData.entrySet()) {
		
			Date key = entry.getKey();
			Integer value = entry.getValue();

			if (key.before(startDate)) { continue; };
			
			String newkey = df.format(key);

			if (dailySums.get(newkey) == null) {
				dailySums.put(newkey, value);
				dailyCount.put(newkey, 1);
			} else {
				dailySums.put(newkey, dailySums.get(newkey) + value);
				dailyCount.put(newkey, dailyCount.get(newkey) + 1);
			}
			
		}

		for (Map.Entry<String,Integer> entry : dailySums.entrySet()) {
		
			String dateString = entry.getKey();
			int sum = entry.getValue();
			
			int count = dailyCount.get(dateString);
			
			int average = (int) Math.round((double)sum / count);
			
			sb.append(dateString + " : " + average + "\n");
			
		}
		
		String s = sb.toString();
		usageReportsUI.appendOccupancyStats(s);
		
}
	
private void showMonthlyData (Date startDate) {
		
		Map<Date, Integer> occupancyData = dataStorage.getOccupancyData();
		
		StringBuilder sb = new StringBuilder();
		DateFormat df = new SimpleDateFormat("yyyy MM - MMMMM");
		
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
