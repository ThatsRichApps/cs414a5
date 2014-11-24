package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectCriteriaUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton hourlyStatsButton;
	private JButton dailyStatsButton;
	private JButton monthlyStatsButton;
	private JLabel unitsBackLabel;
	private JTextField unitsBackField;
	private JButton refreshTransactionStatsButton;
	
	public SelectCriteriaUI() {
		initUI();
	}
	
    @Override
	public String toString() {
		return "SelectCriteriaUI";
	}

	private void initUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setTitle("Select Reports Criteria");
                
        hourlyStatsButton = new JButton ("Get Hourly Stats");
        hourlyStatsButton.setActionCommand("Hourly");
        
        dailyStatsButton = new JButton ("Get Daily Stats");
        dailyStatsButton.setActionCommand("Daily");
 
        monthlyStatsButton = new JButton ("Get Monthly Stats");
        monthlyStatsButton.setActionCommand("Monthly");

        unitsBackLabel = new JLabel("Time back from today (days or months)");
        unitsBackField = new JTextField("12");
        
        refreshTransactionStatsButton = new JButton ("Transaction Stats");
        refreshTransactionStatsButton.setActionCommand("RefreshTransStats");

        
        JPanel pane = new JPanel(new GridLayout(4, 1));
        
        pane.add(refreshTransactionStatsButton);
        pane.add(hourlyStatsButton);
        pane.add(dailyStatsButton);
        pane.add(monthlyStatsButton);
        pane.add(unitsBackLabel);
        pane.add(unitsBackField);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                30, //top
                30, //left
                10, //bottom
                30) //right
                );
        
        getContentPane().add(pane, BorderLayout.CENTER);

        setSize(400, 400);
        pack();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
	
	public void addActionListeners (ActionListener listener) {
		hourlyStatsButton.addActionListener(listener);
		dailyStatsButton.addActionListener(listener);
		monthlyStatsButton.addActionListener(listener);
		refreshTransactionStatsButton.addActionListener(listener);
	}
	
	public String getUnitsBackField () {
		return unitsBackField.getText();
	}
	
	
}
