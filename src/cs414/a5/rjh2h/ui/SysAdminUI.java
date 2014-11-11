package cs414.a5.rjh2h.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SysAdminUI extends JFrame {

	private static final long serialVersionUID = -7241484193835854441L;
	private JLabel messageLabel;
	private JComboBox<String> preferenceListBox;
	private JFormattedTextField setPreferenceField;
	private JButton setPreferenceButton;
	private String[] listOfPreferences;
	
	public SysAdminUI() {
	}
	
	public SysAdminUI(Map<String, String> systemPrefs) {
		listOfPreferences = systemPrefs.keySet().toArray(new String[0]);
		preferenceListBox = new JComboBox<>(listOfPreferences);
		initUI();
	}
	
    @Override
	public String toString() {
		return "SysAdminUI";
	}

	private void initUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("System Administration");
        
        messageLabel =  new JLabel("System Adminstration", SwingConstants.CENTER);
   	      
        setPreferenceField = new JFormattedTextField();
        
        
        setPreferenceButton = new JButton("Set Preference");        
        
        JPanel pane = new JPanel(new GridLayout(4, 2));
        
        pane.add(messageLabel);
        pane.add(preferenceListBox);
        pane.add(setPreferenceField);
        pane.add(setPreferenceButton);
        
        pane.setBorder(BorderFactory.createEmptyBorder(
                                        30, //top
                                        30, //left
                                        10, //bottom
                                        30) //right
                                        );

        getContentPane().add(pane, BorderLayout.CENTER);
                
	    pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

	public void setMessage(String message) {
		messageLabel.setText(message);
	}

	public String[] getListOfPreferences() {
		return listOfPreferences;
	}
	
	public void addActionListeners(ActionListener listener) {
		preferenceListBox.addActionListener(listener);
		setPreferenceButton.addActionListener(listener);
	}

	public String getPreferenceListBoxSelection() {
		return preferenceListBox.getSelectedItem().toString();
	}

	public String getPreferenceField() {
		return setPreferenceField.getText();
	}

	public void setPreferenceField(String preferenceField) {
		this.setPreferenceField.setText(preferenceField);
	}
	
}
