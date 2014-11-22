package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class UsageReportsUI extends JFrame {

	private static final long serialVersionUID = -4705765678072542814L;
	private JTextPane transactionStatsPane;
	private JTextPane occupancyStatsPane;
	private JButton hourlyStatsButton;
	private JButton monthlyStatsButton;
	private JTextField hoursBack;
	private JTextField monthsBack;
	private JLabel hoursBackLabel;
	private JLabel monthsBackLabel;
	
	public UsageReportsUI() {
		initUI();
	}
	
    @Override
	public String toString() {
		return "UsageReportsUI";
	}

	private void initUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        setTitle("Usage Reports");
                
        transactionStatsPane =  new JTextPane();
        JScrollPane transactionStatsScroller = new JScrollPane (transactionStatsPane, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        appendTransactionStats("Transaction Stats:");
        
        occupancyStatsPane = new JTextPane();
        occupancyStatsPane.setEditable(false);
        appendOccupancyStats ("Project Statistics:\n");	
        
        hourlyStatsButton = new JButton ("Get Hourly Stats");
        monthlyStatsButton = new JButton ("Get Monthly Stats");
        
        hoursBackLabel = new JLabel();
        monthsBackLabel = new JLabel();
        
        hoursBack = new JTextField();
        monthsBack = new JTextField();
        
        
        
        
        
        JPanel pane = new JPanel(new GridLayout(3, 0));
        
        JScrollPane occupancyStatsScroller = new JScrollPane (occupancyStatsPane, 
           JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        pane.add(transactionStatsScroller);
        pane.add(occupancyStatsScroller);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                30, //top
                30, //left
                10, //bottom
                30) //right
                );

        getContentPane().add(pane, BorderLayout.CENTER);

        //setSize(400, 100);
        pack();
        
        setLocationRelativeTo(null);
        setVisible(true);
    }

	public void setMainMessageLabel(String message) {
		transactionStatsPane.setText(message);
	}

	public void appendOccupancyStats(String s) {
		try {
		      Document doc = occupancyStatsPane.getDocument();
		      doc.insertString(doc.getLength(), s, null);
		} catch(BadLocationException exc) {
		      exc.printStackTrace();
		}
	}

	public void appendTransactionStats(String s) {
	   try {
	      Document doc = transactionStatsPane.getDocument();
	      doc.insertString(doc.getLength(), s, null);
	   } catch(BadLocationException exc) {
		      exc.printStackTrace();
	   }
	}
	
}
