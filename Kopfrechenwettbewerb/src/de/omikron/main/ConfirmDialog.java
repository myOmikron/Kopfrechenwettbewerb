package de.omikron.main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ConfirmDialog extends JDialog {
	
	private final Color darkRed = new Color(128, 0, 0), lightRed = new Color(204, 0, 0);
	private final Color white = new Color(252, 252, 252);
	
	private JPanel sidePanel = new JPanel(null), midPanel = new JPanel(null), titlePanel = new JPanel(null);
	
	private JLabel lblTitle, lblWarning;
	private JTextArea taMid;	
	private JLabel lblWarningLogo, lblSgpLogo;
	private JLabel lblConfirm;
	
	private JPanel contentPane = new MotionPanel(this);
	
	private final ImageIcon warningsLogo = new ImageIcon("res/error_50px.png");
	private final ImageIcon sgpLogoSmall = new ImageIcon("res/sgp_55px.png");

	public ConfirmDialog(JFrame frame, boolean modal, String s) {
		super(frame, modal);
		setUndecorated(true);
		setResizable(false);
		setBounds(300, 300, 400, 200);
		setContentPane(contentPane);
		getRootPane().setBorder(BorderFactory.createLineBorder(darkRed));
		
		lblSgpLogo = new JLabel(sgpLogoSmall);
		lblSgpLogo.setBounds(10, 10, 55, 55);
		sidePanel.add(lblSgpLogo);
		
		lblWarning = new JLabel("Warnung");
		lblWarning.setForeground(white);
		lblWarning.setVerticalAlignment(SwingConstants.CENTER);
		lblWarning.setBounds(70, 0, 255, 70);
		lblWarning.setFont(lblWarning.getFont().deriveFont(24f));
		titlePanel.add(lblWarning);
		
		lblWarningLogo = new JLabel(warningsLogo);
		lblWarningLogo.setBounds(10, 0, 50, 70);
		lblWarningLogo.setVerticalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblWarningLogo);
		
		lblTitle = new JLabel();
		lblTitle.setText("Kopfrechnenwettbewerb");
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
		
		setWarning(s);
		
		setVisible(true);
	}
	
	private void setWarning(String warning) {
		taMid = new JTextArea(warning);
		taMid.setEditable(false);
		taMid.setOpaque(false);
		taMid.setBounds(10, 10, 325, 60);
		taMid.setForeground(darkRed);
		taMid.setFont(taMid.getFont().deriveFont(16f));
		midPanel.add(taMid);
		
		lblConfirm = new JLabel("OKAY");
		lblConfirm.setForeground(white);
		lblConfirm.setOpaque(true);
		lblConfirm.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override
			public void mousePressed(MouseEvent e) {
				exit();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblConfirm.setBackground(lightRed);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblConfirm.setBackground(darkRed);
			}
		});
		lblConfirm.setBounds(120, 70, 80, 20);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setBackground(darkRed);
		midPanel.add(lblConfirm);
	}
	
	private void exit() {
		dispose();
	}
}
