package de.omikron.main;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WarningFrame extends JFrame {

	private final Color darkRed = new Color(128, 0, 0), lightRed = new Color(255, 0, 0);
	private final Color white = new Color(252, 252, 252);
	
	private JPanel sidePanel = new JPanel(null), midPanel = new JPanel(null), titlePanel = new JPanel(null);
	
	private JLabel lblTitle;
	
	private JPanel contentPane = new MotionPanel(this);
	
	private JLabel lblWarning;
	
	public WarningFrame() {
		setUndecorated(true);
		setResizable(false);
		setBounds(300, 300, 400, 200);
		setContentPane(contentPane);
		
		lblTitle = new JLabel();
		lblTitle.setText("Kopfrechenwettbewerb");
		lblTitle.setFont(lblTitle.getFont().deriveFont(18f));
		lblTitle.setBounds(160, 0, 240, 30);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(lightRed);
		contentPane.add(lblTitle);
		
		sidePanel.setBackground(darkRed);
		sidePanel.setOpaque(true);
		sidePanel.setBounds(0, 0, 150, 200);
		contentPane.add(sidePanel);
		
		midPanel.setBackground(white);
		midPanel.setOpaque(true);
		midPanel.setBounds(150, 100, 250, 100);
		contentPane.add(midPanel);
		
		titlePanel.setBackground(lightRed);
		titlePanel.setBounds(150, 30, 250, 70);
		contentPane.add(titlePanel);
		
		contentPane.setLayout(null);
		
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
