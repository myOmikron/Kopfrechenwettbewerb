package de.omikron.main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WarningFrame extends JFrame {

	private JPanel contentPane = new MotionPanel(this);
	
	private JLabel lblWarning;
	
	public WarningFrame() {
		setUndecorated(true);
		setResizable(false);
		setBounds(300, 300, 400, 200);
		setContentPane(contentPane);
		
		setVisible(true);
	}
	
	public void setWarning(String warning) {
		lblWarning = new JLabel(warning);
		lblWarning.setBounds(0, 0, 400, 200);
		lblWarning.setFont(lblWarning.getFont().deriveFont(22f));
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setVerticalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWarning);
	}
	
}
