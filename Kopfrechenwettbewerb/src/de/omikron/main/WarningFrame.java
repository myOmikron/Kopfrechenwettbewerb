package de.omikron.main;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class WarningFrame extends JFrame {

	private final Color darkRed = new Color(128, 0, 0), lightRed = new Color(255, 0, 0);
	private final Color white = new Color(252, 252, 252);
	
	private JPanel sidePanel = new JPanel(null), midPanel = new JPanel(null), titlePanel = new JPanel(null);
	
	private JLabel lblTitle, lblWarning;
	private JTextArea taMid;
	
	private JPanel contentPane = new MotionPanel(this);
	
	public WarningFrame() {
		setUndecorated(true);
		setResizable(false);
		setBounds(300, 300, 400, 200);
		setContentPane(contentPane);
		
		lblWarning = new JLabel("Warnung");
		lblWarning.setForeground(white);
		lblWarning.setVerticalAlignment(SwingConstants.CENTER);
		lblWarning.setBounds(10, 0, 325, 70);
		lblWarning.setFont(lblWarning.getFont().deriveFont(24f));
		titlePanel.add(lblWarning);
		
		lblTitle = new JLabel();
		lblTitle.setText("Kopfrechenwettbewerb");
		lblTitle.setFont(lblTitle.getFont().deriveFont(18f));
		lblTitle.setBounds(85, 0, 285, 30);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(darkRed);
		contentPane.add(lblTitle);
		
		sidePanel.setBackground(darkRed);
		sidePanel.setOpaque(true);
		sidePanel.setBounds(0, 0, 75, 200);
		contentPane.add(sidePanel);
		
		midPanel.setBackground(white);
		midPanel.setOpaque(true);
		midPanel.setBounds(75, 100, 325, 100);
		contentPane.add(midPanel);
		
		titlePanel.setBackground(lightRed);
		titlePanel.setBounds(75, 30, 325, 70);
		contentPane.add(titlePanel);
		
		contentPane.setLayout(null);
		
		setVisible(true);
	}
	
	public void setWarning(String warning) {
		taMid = new JTextArea(warning);
		taMid.setEditable(false);
		taMid.setBounds(10, 10, 185, 60);
		taMid.setForeground(darkRed);
		taMid.setFont(taMid.getFont().deriveFont(16f));
		midPanel.add(taMid);
	}
	
}
