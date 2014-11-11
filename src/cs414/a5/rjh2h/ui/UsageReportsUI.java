package cs414.a5.rjh2h.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class UsageReportsUI extends JFrame {

	private static final long serialVersionUID = -4705765678072542814L;
	private JLabel mainMessageLabel;
	
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
                
        mainMessageLabel =  new JLabel("Usage Reports", SwingConstants.CENTER);
        	
        add(mainMessageLabel);
       
        setSize(400, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

	public void setMainMessageLabel(String message) {
		mainMessageLabel.setText(message);
	}
	
}
