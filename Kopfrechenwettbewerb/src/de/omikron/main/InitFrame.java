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
	
	private JPanel sidePanel = new JPanel(null), titlePanel = new JPanel(null), topPanel = new JPanel(null);
	private JPanel gameSidePanel = new JPanel(null), settingsSidePanel = new JPanel(null), restartSidePanel = new JPanel(null);
	private JPanel gameTitlePanel = new JPanel(null), settingsTitlePanel = new JPanel(null), restartTitlePanel = new JPanel(null);

	private JLabel lblClose, lblTitle;
	private JLabel lblLogo;
	private JLabel lblSettingsLogo, lblPlayLogo, lblRestartLogo, lblSettings, lblPlay, lblRestart;
	private JLabel lblCopyright;
	private JLabel lblSettingsTitleLogo, lblPlayTitleLogo, lblRestartTitleLogo, lblSettingsTitle, lblPlayTitle, lblRestartTitle;
	
	private final Color brightPurple = new Color(140, 26, 255), darkPurple = new Color(64, 0, 128);
	private final Color menuePurple = new Color(77, 0, 153), menueOverPurple = new Color(102, 0, 204);
	private final Color white = new Color(252, 252, 252);
	
	private final ImageIcon sgpLogo = new ImageIcon("res/sgpSmall.png");
	private final ImageIcon settingsLogoSmall = new ImageIcon("res/settings_32px.png"), settingsLogo = new ImageIcon("res/settings_64px.png");
	private final ImageIcon playLogoSmall = new ImageIcon("res/start_32px.png"), playLogo = new ImageIcon("res/start_64px.png");
	private final ImageIcon restartLogoSmall = new ImageIcon("res/restart_32px.png"), restartLogo = new ImageIcon("res/restart_64px.png");
	
	private boolean gameSide, settingsSide, restartSide;
	
	public InitFrame() {
		super("Kopfrechenwettbewerb");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setUndecorated(true);
		setContentPane(contentPane);
	}
	
	protected void init() {
		
		lblSettingsTitleLogo = new JLabel(settingsLogo);
		lblSettingsTitleLogo.setBounds(20, 20, 64, 64);
		settingsTitlePanel.add(lblSettingsTitleLogo);
		
		lblPlayTitleLogo = new JLabel(playLogo);
		lblPlayTitleLogo.setBounds(20, 20, 64, 64);
		gameTitlePanel.add(lblPlayTitleLogo);
		
		lblRestartTitleLogo = new JLabel(restartLogo);
		lblRestartTitleLogo.setBounds(20, 20, 64, 64);
		restartTitlePanel.add(lblRestartTitleLogo);
		
		lblRestartLogo = new JLabel(restartLogoSmall);
		lblRestartLogo.setBounds(10, 0, 32, 70);
		lblRestartLogo.setVerticalAlignment(SwingConstants.CENTER);
		restartSidePanel.add(lblRestartLogo);
		
		lblRestart = new JLabel("Neu starten");
		lblRestart.setFont(lblRestart.getFont().deriveFont(20f));
		lblRestart.setBounds(50, 0, 225, 70);
		lblRestart.setHorizontalAlignment(SwingConstants.LEFT);
		lblRestart.setVerticalAlignment(SwingConstants.CENTER);
		lblRestart.setForeground(white);
		restartSidePanel.add(lblRestart);
		
		lblPlayLogo = new JLabel(playLogoSmall);
		lblPlayLogo.setBounds(10, 0, 32, 70);
		lblPlayLogo.setVerticalAlignment(SwingConstants.CENTER);
		gameSidePanel.add(lblPlayLogo);
		
		lblPlay = new JLabel("Starten");
		lblPlay.setFont(lblPlay.getFont().deriveFont(20f));
		lblPlay.setBounds(50, 0, 250, 70);
		lblPlay.setVerticalAlignment(SwingConstants.CENTER);
		lblPlay.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlay.setForeground(white);
		gameSidePanel.add(lblPlay);
		
		lblSettings = new JLabel("Einstellungen");
		lblSettings.setFont(lblSettings.getFont().deriveFont(20f));
		lblSettings.setBounds(50, 0, 250, 70);
		lblSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblSettings.setVerticalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(white);
		settingsSidePanel.add(lblSettings);
		
		lblSettingsLogo = new JLabel(settingsLogoSmall);
		lblSettingsLogo.setBounds(10, 0, 32, 70);
		lblSettingsLogo.setVerticalAlignment(SwingConstants.CENTER);
		settingsSidePanel.add(lblSettingsLogo);
		
		lblCopyright = new JLabel('\u00A9' + " by Niklas Pfister");
		lblCopyright.setFont(lblCopyright.getFont().deriveFont(12f));
		lblCopyright.setForeground(white);
		lblCopyright.setBounds(0, 630, 225, 20);
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		sidePanel.add(lblCopyright);
		
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
		
		restartSidePanel.setBounds(0, 530, 225, 70);
		restartSidePanel.setBackground(menuePurple);
		restartSidePanel.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override public void mousePressed(MouseEvent e) {  }
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isRestartSide()) {
					restartSidePanel.setBackground(menuePurple);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				restartSidePanel.setBackground(menueOverPurple);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isRestartSide()) {
					restartSidePanel.setBackground(menueOverPurple);
					settingsSidePanel.setBackground(menuePurple);
					gameSidePanel.setBackground(menuePurple);
					setRestartSide();
				}
			}
		});
		sidePanel.add(restartSidePanel);
		
		settingsSidePanel.setBounds(0, 250, 225, 70);
		settingsSidePanel.setBackground(menueOverPurple);
		settingsSidePanel.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override public void mousePressed(MouseEvent e) {  }
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isSettingsSide()) {
					settingsSidePanel.setBackground(menuePurple);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				settingsSidePanel.setBackground(menueOverPurple);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isSettingsSide()) {
					restartSidePanel.setBackground(menuePurple);
					gameSidePanel.setBackground(menuePurple);
					settingsSidePanel.setBackground(menueOverPurple);
					setSettingsSide();
				}
			}
		});
		sidePanel.add(settingsSidePanel);
		
		gameSidePanel.setBounds(0, 320, 225, 70);
		gameSidePanel.setBackground(menuePurple);
		gameSidePanel.addMouseListener(new MouseListener() {
			@Override public void mousePressed(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override 
			public void mouseEntered(MouseEvent e) {
				gameSidePanel.setBackground(menueOverPurple);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isGameSide()) {
					gameSidePanel.setBackground(menuePurple);
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isGameSide()) {
					gameSidePanel.setBackground(menueOverPurple);
					settingsSidePanel.setBackground(menuePurple);
					restartSidePanel.setBackground(menuePurple);
					setGameSide();
				}
			}
		});
		sidePanel.add(gameSidePanel);
		
		sidePanel.setBackground(darkPurple);
		sidePanel.setBounds(0, 0, 225, 650);
		contentPane.add(sidePanel);
		
		gameTitlePanel.setBounds(0, 0, 775, 150);
		gameTitlePanel.setOpaque(false);
		gameTitlePanel.setVisible(false);
		titlePanel.add(gameTitlePanel);
		
		settingsTitlePanel.setBounds(0, 0, 775, 150);
		settingsTitlePanel.setOpaque(false);
		settingsTitlePanel.setVisible(false);
		titlePanel.add(settingsTitlePanel);
		
		restartTitlePanel.setBounds(0, 0, 775, 150);
		restartTitlePanel.setOpaque(false);
		restartTitlePanel.setVisible(false);
		titlePanel.add(restartTitlePanel);
		
		titlePanel.setBackground(brightPurple);
		titlePanel.setBounds(225, 50, 775, 150);
		contentPane.add(titlePanel);
		
		topPanel.setBackground(white);
		topPanel.setBounds(225, 0, 775, 50);
		contentPane.add(topPanel);
		
		contentPane.setBackground(white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setSettingsSide();
		
		setVisible(true);
	}
	
	private boolean isSettingsSide() {
		return settingsSide;
	}
	
	private boolean isGameSide() {
		return gameSide;
	}
	
	private boolean isRestartSide() {
		return restartSide;
	}
	
	private void setSettingsSide() {
		settingsSide = true;
		settingsTitlePanel.setVisible(true);
		gameTitlePanel.setVisible(false);
		restartTitlePanel.setVisible(false);
		gameSide = false;
		restartSide = false;
	}
	
	private void setGameSide() {
		gameSide = true;
		gameTitlePanel.setVisible(true);
		settingsTitlePanel.setVisible(false);
		restartTitlePanel.setVisible(false);
		settingsSide = false;
		restartSide = false;
	}
	
	private void setRestartSide() {
		restartSide = true;
		restartTitlePanel.setVisible(true);
		settingsTitlePanel.setVisible(false);
		gameTitlePanel.setVisible(false);
		settingsSide = false;
		gameSide = false;
	}
}
