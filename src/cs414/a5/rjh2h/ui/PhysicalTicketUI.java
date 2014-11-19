package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cs414.a5.rjh2h.common.Ticket;

public class PhysicalTicketUI extends JFrame {

	private static final long serialVersionUID = -7528681233196221855L;
	
	public PhysicalTicketUI() {
	}
	
	
	
	public PhysicalTicketUI(Ticket thisTicket) {
	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Physical Ticket");

        String message = "Ticket Number:" + thisTicket.getTicketNumber();          
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM yyyy HH:mm z");

		String timeIn = dateFormatter.format(thisTicket.getTimeIn());
        
        String message2 = "Time: " + timeIn;          
        JLabel messageLabel2 = new JLabel(message2, SwingConstants.CENTER);
        
        JPanel pane = new JPanel(new GridLayout(5, 0));
        
        String message3 = "Rate per Hour: " + thisTicket.getRate();          
        JLabel messageLabel3 = new JLabel(message3, SwingConstants.CENTER);
        
        
        
        
        pane.add(messageLabel);
        pane.add(messageLabel2);
        pane.add(messageLabel3);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                                        30, //top
                                        30, //left
                                        10, //bottom
                                        30) //right
                                        );

        getContentPane().add(pane, BorderLayout.CENTER);
	    
	    pack();
	    setLocation (5,460);
	    setVisible(true);
	}



	@Override
	public String toString() {
		return "PhysicalTicketUI";
	}

}
