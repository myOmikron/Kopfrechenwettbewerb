package de.omikron.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class InitFrame extends JFrame {

	private JPanel contentPane = new MotionPanel(this);
	
	private JPanel sidePanel = new JPanel(null), midPanel = new JPanel(null), topPanel = new JPanel(null);
	private JPanel gamePanel = new JPanel(null), settingsPanel = new JPanel(null);

	private JLabel lblClose, lblTitle;
	private JLabel lblLogo;
	private JLabel lblSettingsLogo, lblPlayLogo, lblSettings, lblPlay;
	
	private final Color brightPurple = new Color(140, 26, 255), darkPurple = new Color(64, 0, 128);
	private final Color menuePurple = new Color(77, 0, 153), menueOverPurple = new Color(102, 0, 204);
	private final Color white = new Color(252, 252, 252);
	
	private final ImageIcon sgpLogo = new ImageIcon("res/sgpSmall.png");
	private final ImageIcon settingsLogo = new ImageIcon("res/settings_32px.png");
	private final ImageIcon playLogo = new ImageIcon("res/start_32px.png");
	
	public InitFrame() {
		super("Kopfrechenwettbewerb");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setUndecorated(true);
		setContentPane(contentPane);
		
		lblPlayLogo = new JLabel(playLogo);
		lblPlayLogo.setBounds(10, 0, 32, 70);
		lblPlayLogo.setVerticalAlignment(SwingConstants.CENTER);
		gamePanel.add(lblPlayLogo);
		
		lblPlay = new JLabel("Starten");
		lblPlay.setFont(lblPlay.getFont().deriveFont(20f));
		lblPlay.setBounds(0, 0, 250, 70);
		lblPlay.setVerticalAlignment(SwingConstants.CENTER);
		lblPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlay.setForeground(white);
		gamePanel.add(lblPlay);
		
		lblSettings = new JLabel("Einstellungen");
		lblSettings.setFont(lblSettings.getFont().deriveFont(20f));
		lblSettings.setBounds(0, 0, 250, 70);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setVerticalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(white);
		settingsPanel.add(lblSettings);
		
		lblSettingsLogo = new JLabel(settingsLogo);
		lblSettingsLogo.setBounds(10, 0, 32, 70);
		lblSettingsLogo.setVerticalAlignment(SwingConstants.CENTER);
		settingsPanel.add(lblSettingsLogo);
		
		lblLogo = new JLabel(sgpLogo);
		lblLogo.setBounds(25, 25, 175, 175);
		sidePanel.add(lblLogo);
		
		lblClose = new JLabel("X");
		lblClose.setBounds(750, 5, 30, 30);
		lblClose.setFont(lblClose.getFont().deriveFont(32f));
		lblClose.setForeground(darkPurple);
		lblClose.addMouseListener(new MouseListener() {
			@Override public void mouseEntered(MouseEvent e) {  }
			@Override public void mouseExited(MouseEvent e) {  }
			@Override public void mousePressed(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblClose.setVisible(true);
		topPanel.add(lblClose);

		lblTitle = new JLabel("Kopfrechenwettbewerb");
		lblTitle.setFont(lblTitle.getFont().deriveFont(16f));
		lblTitle.setBounds(20, 10, 300, 30);
		lblTitle.setForeground(darkPurple);
		topPanel.add(lblTitle);
		
		settingsPanel.setBounds(0, 250, 225, 70);
		settingsPanel.setBackground(menueOverPurple);
		sidePanel.add(settingsPanel);
		
		gamePanel.setBounds(0, 320, 225, 70);
		gamePanel.setBackground(menuePurple);
		sidePanel.add(gamePanel);
		
		sidePanel.setBackground(darkPurple);
		sidePanel.setBounds(0, 0, 225, 650);
		contentPane.add(sidePanel);
		
		midPanel.setBackground(brightPurple);
		midPanel.setBounds(225, 50, 775, 150);
		contentPane.add(midPanel);
		
		topPanel.setBackground(white);
		topPanel.setBounds(225, 0, 775, 50);
		contentPane.add(topPanel);
		
		contentPane.setBackground(white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
	}
}
