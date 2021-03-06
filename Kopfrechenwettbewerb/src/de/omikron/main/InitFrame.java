package de.omikron.main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class InitFrame extends JFrame {

	private JPanel contentPane = new MotionPanel(this);
	
	private JPanel sidePanel = new JPanel(null), titlePanel = new JPanel(null), topPanel = new JPanel(null), midPanel = new JPanel(null);
	private JPanel gameSidePanel = new JPanel(null), settingsSidePanel = new JPanel(null), restartSidePanel = new JPanel(null);
	private JPanel gameTitlePanel = new JPanel(null), settingsTitlePanel = new JPanel(null), restartTitlePanel = new JPanel(null);
	private JPanel gameMidPanel = new JPanel(null), settingsMidPanel = new JPanel(null), restartMidPanel = new JPanel(null);
	private JPanel gameMidBottomPanel = new JPanel(null), gameMidCenterPanel = new JPanel(null);
	private JPanel settingsMidAddPanel = new JPanel(null), settingsMidUpdatePanel = new JPanel(null), settingsMidRemovePanel = new JPanel(null);
	private ArrayList<JPanel> gameMidList = new ArrayList<>();
	
	private JLabel lblClose, lblTitle;
	private JLabel lblLogo;
	private JLabel lblSettingsLogo, lblPlayLogo, lblRestartLogo, lblSettings, lblPlay, lblRestart;
	private JLabel lblCopyright;
	private JLabel lblSettingsTitleLogo, lblPlayTitleLogo, lblRestartTitleLogo, lblSettingsTitle, lblPlayTitle, lblRestartTitle;
	private JLabel lblSettingsMidGameLength, lblSettingsMidGameUser, lblSettingsMidGameClass, lblSettingsMidGameName;
	private JLabel lblSettingsMidAddLogo, lblSettingsMidUpdateLogo, lblSettingsMidRemoveLogo;
	private JLabel lblSettingsMidAdd, lblSettingsMidUpdate, lblSettingsMidRemove;
	private JLabel lblRestartInfo, lblRestartOkay;
	private JLabel lblStartTime;
	private ArrayList<JLabel> lblGameUserList = new ArrayList<>(), lblGameUserPlotList = new ArrayList<>();
	private ArrayList<JLabel> lblGameUserAdd = new ArrayList<>(), lblGameUserRemove = new ArrayList<>();
	private JLabel lblSeperator;
	
	private JTextField tfSettingsMidClass, tfSettingsMidName;
	
	private JList<String> listSettingsMidTable;
	private DefaultListModel<String> model;
	
	private JSlider sliSettingsMidGameLength;
	
	private JProgressBar pbarTime;
	
	private final Color brightPurple = new Color(140, 26, 255), darkPurple = new Color(64, 0, 128);
	private final Color menuePurple = new Color(77, 0, 153), menueOverPurple = new Color(102, 0, 204);
	private final Color notEditablePurple = new Color(204, 179, 255);
	private final Color white = new Color(252, 252, 252);
	
	private final ImageIcon sgpLogo = new ImageIcon("res/sgpSmall.png");
	private final ImageIcon settingsLogoSmall = new ImageIcon("res/settings_32px.png"), settingsLogo = new ImageIcon("res/settings_64px.png");
	private final ImageIcon playLogoSmall = new ImageIcon("res/start_32px.png"), playLogo = new ImageIcon("res/start_64px.png");
	private final ImageIcon restartLogoSmall = new ImageIcon("res/restart_32px.png"), restartLogo = new ImageIcon("res/restart_64px.png");
	private final ImageIcon addLogoSmall = new ImageIcon("res/add_16px.png");
	private final ImageIcon removeLogoSmall = new ImageIcon("res/remove_16px.png");
	private final ImageIcon updateLogoSmall = new ImageIcon("res/update_16px.png");
	
	private boolean gameSide, settingsSide, restartSide;
	private boolean enabledUpdate, enabledDelete, enabledStartTimer;
	private boolean gameIsZebra;
	
	private Backend backend;
	
	public InitFrame(Backend backend) {
		super("Kopfrechenwettbewerb");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setUndecorated(true);
		setContentPane(contentPane);
		getRootPane().setBorder(BorderFactory.createLineBorder(darkPurple));
		
		this.backend = backend;
	}
	
	protected void init() {
		
		lblSeperator = new JLabel();
		lblSeperator.setOpaque(true);
		lblSeperator.setBackground(darkPurple);
		lblSeperator.setBounds(15, 0, 745, 1);
		gameMidBottomPanel.add(lblSeperator);
		
		pbarTime = new JProgressBar(0, VarSettings.getGameLength());
		pbarTime.setBounds(390, 30, 350, 20);
		pbarTime.setStringPainted(true);
		gameMidBottomPanel.add(pbarTime);
		
		lblStartTime = new JLabel("Start");
		lblStartTime.setForeground(white);
		lblStartTime.setBackground(menuePurple);
		lblStartTime.setOpaque(true);
		lblStartTime.setBounds(290, 30, 80, 20);
		lblStartTime.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override
			public void mouseExited(MouseEvent e) {
				if(isEnabledStartTimer()) {
					lblStartTime.setBackground(menuePurple);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isEnabledStartTimer()) {
					lblStartTime.setBackground(menueOverPurple);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(isEnabledStartTimer()) {
					startTime();
				}
			}
		});
		lblStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		gameMidBottomPanel.add(lblStartTime);
		
		lblRestartOkay = new JLabel("Okay");
		lblRestartOkay.setBounds(310, 60, 150, 30);
		lblRestartOkay.setFont(lblRestartOkay.getFont().deriveFont(16f));
		lblRestartOkay.setForeground(white);
		lblRestartOkay.setBackground(menuePurple);
		lblRestartOkay.setOpaque(true);
		lblRestartOkay.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestartOkay.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRestartOkay.setBackground(menueOverPurple);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblRestartOkay.setBackground(menuePurple);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				backend.reset();
			}
		});
		restartMidPanel.add(lblRestartOkay);
		
		lblRestartInfo = new JLabel("Alle Daten l�schen und neustarten?");
		lblRestartInfo.setBounds(20, 20, 735, 40);
		lblRestartInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestartInfo.setFont(lblRestartInfo.getFont().deriveFont(24f));
		lblRestartInfo.setForeground(darkPurple);
		restartMidPanel.add(lblRestartInfo);
	
		lblSettingsMidRemove = new JLabel("Entfernen");
		lblSettingsMidRemove.setBounds(25, 0, 80, 25);
		lblSettingsMidRemove.setForeground(white);
		lblSettingsMidRemove.setVerticalAlignment(SwingConstants.CENTER);
		settingsMidRemovePanel.add(lblSettingsMidRemove);
		
		lblSettingsMidRemoveLogo = new JLabel(removeLogoSmall);
		lblSettingsMidRemoveLogo.setBounds(5, 0, 16, 25);
		lblSettingsMidRemoveLogo.setVerticalAlignment(SwingConstants.CENTER);
		settingsMidRemovePanel.add(lblSettingsMidRemoveLogo);
		
		lblSettingsMidUpdate = new JLabel("Aktualisieren");
		lblSettingsMidUpdate.setForeground(white);
		lblSettingsMidUpdate.setBounds(25, 0, 80, 25);
		lblSettingsMidUpdate.setVerticalAlignment(SwingConstants.CENTER);
		settingsMidUpdatePanel.add(lblSettingsMidUpdate);
		
		lblSettingsMidUpdateLogo = new JLabel(updateLogoSmall);
		lblSettingsMidUpdateLogo.setBounds(5, 0, 16, 25);
		lblSettingsMidUpdateLogo.setVerticalAlignment(SwingConstants.CENTER);
		settingsMidUpdatePanel.add(lblSettingsMidUpdateLogo);
		
		lblSettingsMidAdd = new JLabel("Hinzuf" + '\u00fc' + "gen");
		lblSettingsMidAdd.setBounds(25, 0, 80, 25);
		lblSettingsMidAdd.setForeground(white);
		lblSettingsMidAdd.setVerticalAlignment(SwingConstants.CENTER);
		settingsMidAddPanel.add(lblSettingsMidAdd);
		
		lblSettingsMidAddLogo = new JLabel(addLogoSmall);
		lblSettingsMidAddLogo.setBounds(5, 0, 16, 25);
		lblSettingsMidAddLogo.setVerticalAlignment(SwingConstants.CENTER);
		settingsMidAddPanel.add(lblSettingsMidAddLogo);
		
		model = new DefaultListModel<>();
		listSettingsMidTable = new JList<>(model);
		listSettingsMidTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSettingsMidTable.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!listSettingsMidTable.isSelectionEmpty()) {
					setEnabledUpdate(true);
					setEnabledDelete(true);
					settingsMidRemovePanel.setBackground(menuePurple);
					settingsMidUpdatePanel.setBackground(menuePurple);
					tfSettingsMidClass.setText(backend.getSchuelerlist().get(listSettingsMidTable.getSelectedIndex()).getKlasse().getName());
					tfSettingsMidName.setText(backend.getSchuelerlist().get(listSettingsMidTable.getSelectedIndex()).getName());
				}
			}
		});
		listSettingsMidTable.setFont(listSettingsMidTable.getFont().deriveFont(16f));
		listSettingsMidTable.setBounds(365, 20, 390, 435);
		listSettingsMidTable.setBorder(new LineBorder(darkPurple));
		listSettingsMidTable.setForeground(darkPurple);
		settingsMidPanel.add(listSettingsMidTable);
		
		tfSettingsMidName = new JTextField();
		tfSettingsMidName.setForeground(darkPurple);
		tfSettingsMidName.setBounds(80, 75, 160, 20);
		settingsMidPanel.add(tfSettingsMidName);
		
		tfSettingsMidClass = new JTextField();
		tfSettingsMidClass.setForeground(darkPurple);
		tfSettingsMidClass.setBounds(20, 75, 50, 20);
		settingsMidPanel.add(tfSettingsMidClass);
		
		lblSettingsMidGameName = new JLabel("Name");
		lblSettingsMidGameName.setFont(lblSettingsMidGameName.getFont().deriveFont(12f));
		lblSettingsMidGameName.setForeground(darkPurple);
		lblSettingsMidGameName.setBounds(80, 50, 80, 20);
		settingsMidPanel.add(lblSettingsMidGameName);
		
		lblSettingsMidGameClass = new JLabel("Klasse");
		lblSettingsMidGameClass.setBounds(20, 50, 80, 20);
		lblSettingsMidGameClass.setFont(lblSettingsMidGameClass.getFont().deriveFont(12f));
		lblSettingsMidGameClass.setForeground(darkPurple);
		settingsMidPanel.add(lblSettingsMidGameClass);
		
		lblSettingsMidGameUser = new JLabel("Teilnehmerverwaltung");
		lblSettingsMidGameUser.setBounds(20, 20, 200, 30);
		lblSettingsMidGameUser.setFont(lblSettingsMidGameUser.getFont().deriveFont(14f));
		lblSettingsMidGameUser.setForeground(darkPurple);
		settingsMidPanel.add(lblSettingsMidGameUser);
		
		sliSettingsMidGameLength = new JSlider(SwingConstants.HORIZONTAL);
		sliSettingsMidGameLength.setMinimum(10);
		sliSettingsMidGameLength.setMaximum(25);
		sliSettingsMidGameLength.setPaintTicks(true);
		sliSettingsMidGameLength.setMajorTickSpacing(5);
		sliSettingsMidGameLength.setPaintLabels(true);
		sliSettingsMidGameLength.setOpaque(false);
		sliSettingsMidGameLength.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				VarSettings.setGameLength(sliSettingsMidGameLength.getValue());
				pbarTime.setMaximum(VarSettings.getGameLength());
			}
		});
		sliSettingsMidGameLength.setSnapToTicks(true);
		sliSettingsMidGameLength.setValue(15);
		sliSettingsMidGameLength.setBounds(20, 25, 245, 50);
		gameMidBottomPanel.add(sliSettingsMidGameLength);
		
		lblSettingsMidGameLength = new JLabel("Gew" + '\u00fc' + "nschte Rundenzeit eingeben:");
		lblSettingsMidGameLength.setBounds(20, 5, 300, 20);
		lblSettingsMidGameLength.setFont(lblSettingsMidGameLength.getFont().deriveFont(14f));
		lblSettingsMidGameLength.setForeground(darkPurple);
		gameMidBottomPanel.add(lblSettingsMidGameLength);
		
		lblSettingsTitle = new JLabel("Einstellungen");
		lblSettingsTitle.setFont(lblSettingsTitle.getFont().deriveFont(36f));
		lblSettingsTitle.setForeground(white);
		lblSettingsTitle.setBounds(110, 20, 250, 50);
		settingsTitlePanel.add(lblSettingsTitle);
		
		lblSettingsTitleLogo = new JLabel(settingsLogo);
		lblSettingsTitleLogo.setBounds(20, 20, 64, 64);
		settingsTitlePanel.add(lblSettingsTitleLogo);
		
		lblPlayTitle = new JLabel("Spielen");
		lblPlayTitle.setFont(lblPlayTitle.getFont().deriveFont(36f));
		lblPlayTitle.setForeground(white);
		lblPlayTitle.setBounds(110, 20, 200, 50);
		gameTitlePanel.add(lblPlayTitle);
		
		lblPlayTitleLogo = new JLabel(playLogo);
		lblPlayTitleLogo.setBounds(20, 20, 64, 64);
		gameTitlePanel.add(lblPlayTitleLogo);
		
		lblRestartTitle = new JLabel("Neu starten");
		lblRestartTitle.setBounds(110, 20, 200, 50);
		lblRestartTitle.setFont(lblRestartTitle.getFont().deriveFont(36f));
		lblRestartTitle.setForeground(white);
		restartTitlePanel.add(lblRestartTitle);
		
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
		lblClose.setBounds(745, 5, 30, 30);
		lblClose.setFont(lblClose.getFont().deriveFont(32f));
		lblClose.setForeground(darkPurple);
		lblClose.addMouseListener(new MouseListener() {
			@Override public void mouseEntered(MouseEvent e) {  }
			@Override public void mouseExited(MouseEvent e) {  }
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		lblClose.setVisible(true);
		topPanel.add(lblClose);

		lblTitle = new JLabel("Kopfrechnenwettbewerb");
		lblTitle.setFont(lblTitle.getFont().deriveFont(16f));
		lblTitle.setBounds(20, 10, 300, 30);
		lblTitle.setForeground(darkPurple);
		topPanel.add(lblTitle);
		
		restartSidePanel.setBounds(0, 530, 225, 70);
		restartSidePanel.setBackground(menuePurple);
		restartSidePanel.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override public void mouseClicked(MouseEvent e) {  }
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
			public void mousePressed(MouseEvent e) {
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
			@Override public void mouseClicked(MouseEvent e) {  }
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
			public void mousePressed(MouseEvent e) {
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
			@Override public void mouseClicked(MouseEvent e) {  }
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
			public void mousePressed(MouseEvent e) {
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
		
		gameTitlePanel.setBounds(0, 0, 775, 125);
		gameTitlePanel.setOpaque(false);
		gameTitlePanel.setVisible(false);
		titlePanel.add(gameTitlePanel);
		
		settingsTitlePanel.setBounds(0, 0, 775, 125);
		settingsTitlePanel.setOpaque(false);
		settingsTitlePanel.setVisible(false);
		titlePanel.add(settingsTitlePanel);
		
		restartTitlePanel.setBounds(0, 0, 775, 125);
		restartTitlePanel.setOpaque(false);
		restartTitlePanel.setVisible(false);
		titlePanel.add(restartTitlePanel);
		
		settingsMidRemovePanel.setBounds(250, 135, 105, 25);
		settingsMidRemovePanel.setBackground(notEditablePurple);
		settingsMidRemovePanel.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isEnabledDelete()) {
					settingsMidRemovePanel.setBackground(menueOverPurple);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(isEnabledDelete()) {
					settingsMidRemovePanel.setBackground(menuePurple);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(isEnabledDelete()) {
					backend.rmSchueler(listSettingsMidTable.getSelectedIndex());
					
					tfSettingsMidClass.setText("");
					tfSettingsMidName.setText("");
					setEnabledUpdate(false);
					setEnabledDelete(false);
					settingsMidUpdatePanel.setBackground(notEditablePurple);
					settingsMidRemovePanel.setBackground(notEditablePurple);
					tfSettingsMidClass.requestFocus();
				}
			}
		});
		settingsMidPanel.add(settingsMidRemovePanel);
		
		settingsMidUpdatePanel.setBounds(250, 105, 105, 25);
		settingsMidUpdatePanel.setBackground(notEditablePurple);
		settingsMidUpdatePanel.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override
			public void mouseEntered(MouseEvent e) {
				if(isEnabledUpdate()) {
					settingsMidUpdatePanel.setBackground(menueOverPurple);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(isEnabledUpdate()) {
					settingsMidUpdatePanel.setBackground(menuePurple);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(Controller.isNotEmpty(tfSettingsMidClass.getText()) &&
						Controller.isNotEmpty(tfSettingsMidName.getText()) &&
						Controller.checkClassInput(tfSettingsMidClass.getText())) {
					backend.editSchueler(tfSettingsMidName.getText(), tfSettingsMidClass.getText(), listSettingsMidTable.getSelectedIndex());
					tfSettingsMidClass.setText("");
					tfSettingsMidName.setText("");
					setEnabledUpdate(false);
					setEnabledDelete(false);
					settingsMidUpdatePanel.setBackground(notEditablePurple);
					settingsMidRemovePanel.setBackground(notEditablePurple);
					tfSettingsMidClass.requestFocus();
				}
				listSettingsMidTable.clearSelection();
			}
		});
		settingsMidPanel.add(settingsMidUpdatePanel);
		
		settingsMidAddPanel.setBounds(250, 75, 105, 25);
		settingsMidAddPanel.setBackground(menuePurple);
		settingsMidAddPanel.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override
			public void mouseEntered(MouseEvent e) {
				settingsMidAddPanel.setBackground(menueOverPurple);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				settingsMidAddPanel.setBackground(menuePurple);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				String name = tfSettingsMidName.getText();
				String klasse = tfSettingsMidClass.getText();
				if(Controller.isNotEmpty(name) &&
						Controller.isNotEmpty(klasse) &&
						Controller.checkClassInput(klasse)) {
					backend.addSchueler(name, klasse);
					listSettingsMidTable.clearSelection();
					tfSettingsMidClass.setText("");
					tfSettingsMidName.setText("");
					tfSettingsMidClass.requestFocus();
					setEnabledUpdate(false);
					setEnabledDelete(false);
					settingsMidUpdatePanel.setBackground(notEditablePurple);
				} else {
					setConfirmDialog("�berpr�fen Sie Ihre Eingaben!");
				}
			}
		});
		settingsMidPanel.add(settingsMidAddPanel);
		
		settingsMidPanel.setBounds(0, 0, 775, 475);
		settingsMidPanel.setOpaque(false);
		settingsMidPanel.setVisible(false);
		midPanel.add(settingsMidPanel);
		
		restartMidPanel.setBounds(0, 0, 775, 475);
		restartMidPanel.setOpaque(false);
		restartMidPanel.setVisible(false);
		midPanel.add(restartMidPanel);
		
		gameMidCenterPanel.setBounds(0, 0, 775, 390);
		gameMidCenterPanel.setOpaque(false);
		gameMidCenterPanel.setVisible(false);
		gameMidPanel.add(gameMidCenterPanel);
		
		gameMidBottomPanel.setBounds(0, 390, 775, 85);
		gameMidBottomPanel.setOpaque(false);
		gameMidBottomPanel.setVisible(false);
		gameMidPanel.add(gameMidBottomPanel);
		
		gameMidPanel.setBounds(0, 0, 775, 475);
		gameMidPanel.setOpaque(false);
		gameMidPanel.setVisible(false);
		midPanel.add(gameMidPanel);
		
		midPanel.setBackground(white);
		midPanel.setBounds(225, 175, 775, 475);
		contentPane.add(midPanel);
		
		titlePanel.setBackground(brightPurple);
		titlePanel.setBounds(225, 50, 775, 125);
		contentPane.add(titlePanel);
		
		topPanel.setBackground(white);
		topPanel.setBounds(225, 0, 775, 50);
		contentPane.add(topPanel);
		
		contentPane.setSize(1000, 650);
		contentPane.setBackground(white);
		contentPane.setLayout(null);

		setSettingsSide();
		setEnabledDelete(false);
		setEnabledUpdate(false);
		setEnabledStartTimer(true);
		setVisible(true);
	}
	
	private void startTime() {
		sliSettingsMidGameLength.setEnabled(false);
		setEnabledStartTimer(false);
		lblStartTime.setBackground(notEditablePurple);
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(VarSettings.getGameLength() > pbarTime.getValue()) {
					pbarTime.setValue(pbarTime.getValue()+1);
				} else {
					playSound();
					sliSettingsMidGameLength.setEnabled(true);
					setEnabledStartTimer(true);
					lblStartTime.setBackground(menuePurple);
					pbarTime.setValue(0);
					t.cancel();
				}
			}
		},1000, 1000);		

		
	}
	
	public void reset() {
		dispose();
	}
	
	public void updateList(Schueler newSchueler, int i) {
		if(i >= model.getSize()) {
			model.addElement(newSchueler.getKlasse().getName() + "         " + newSchueler.getName());
			
			JPanel gameListPanel = new JPanel(null);
			gameListPanel.setOpaque(true);
			gameListPanel.setBounds(0, i*28, 775, 27);
			if(gameIsZebra) {
				gameListPanel.setBackground(notEditablePurple);
				gameIsZebra = false;
			} else {
				gameListPanel.setBackground(white);
				gameIsZebra = true;
			}
			gameMidCenterPanel.add(gameListPanel);
			gameMidList.add(gameListPanel);
			
			JLabel lblGameUserName = new JLabel(newSchueler.getKlasse().getName() + "  " + newSchueler.getName());
			lblGameUserName.setForeground(darkPurple);
			lblGameUserName.setBounds(10, 4, 150, 20);
			gameListPanel.add(lblGameUserName);
			lblGameUserList.add(lblGameUserName);
			
			JLabel lblGameUserAdd = new JLabel("+");
			lblGameUserAdd.setFont(lblGameUserAdd.getFont().deriveFont(18f));
			lblGameUserAdd.setForeground(white);
			lblGameUserAdd.setBackground(menuePurple);
			lblGameUserAdd.setOpaque(true);
			lblGameUserAdd.setVerticalAlignment(SwingConstants.CENTER);
			lblGameUserAdd.setHorizontalAlignment(SwingConstants.CENTER);
			lblGameUserAdd.addMouseListener(new MouseListener() {
				@Override public void mouseReleased(MouseEvent e) {  }
				@Override public void mouseClicked(MouseEvent e) {  }
				@Override
				public void mouseExited(MouseEvent e) {
					lblGameUserAdd.setBackground(menuePurple);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					lblGameUserAdd.setBackground(menueOverPurple);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					if(newSchueler.getPoints() < 20) {
						backend.addPoint(newSchueler);
						JLabel lblPlotLength = lblGameUserPlotList.get(i);
						lblPlotLength.setSize(lblPlotLength.getSize().width+25, lblPlotLength.getSize().height);
						lblPlotLength.setText(String.valueOf(newSchueler.getPoints()));
						lblGameUserPlotList.set(i, lblPlotLength);
					} else {
						setConfirmDialog("Die maximale Punktezahl ist auf 20 limitiert!");
					}
				}
			});
			lblGameUserAdd.setBounds(185, 4, 20, 20);
			gameListPanel.add(lblGameUserAdd);
			this.lblGameUserAdd.add(lblGameUserAdd);
			
			JLabel lblGameUserRemove = new JLabel("-");
			lblGameUserRemove.setFont(lblGameUserRemove.getFont().deriveFont(18f));
			lblGameUserRemove.setForeground(white);
			lblGameUserRemove.setBackground(menuePurple);
			lblGameUserRemove.setOpaque(true);
			lblGameUserRemove.setVerticalAlignment(SwingConstants.CENTER);
			lblGameUserRemove.setHorizontalAlignment(SwingConstants.CENTER);
			lblGameUserRemove.addMouseListener(new MouseListener() {
				@Override public void mouseReleased(MouseEvent e) {  }
				@Override public void mouseClicked(MouseEvent e) {  }
				@Override
				public void mouseExited(MouseEvent e) {
					lblGameUserRemove.setBackground(menuePurple);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					lblGameUserRemove.setBackground(menueOverPurple);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					boolean isSuccessfull = backend.removePoint(newSchueler);
					if(isSuccessfull) {
						JLabel lblPlotLength = lblGameUserPlotList.get(i);
						lblPlotLength.setSize(lblPlotLength.getSize().width-25, lblPlotLength.getSize().height);
						lblPlotLength.setText(String.valueOf(newSchueler.getPoints()));
						lblGameUserPlotList.set(i, lblPlotLength);
					} else {
						setConfirmDialog("Die minimale Punktezahl ist auf 0 begrenzt!");
					}
				}
			});
			lblGameUserRemove.setBounds(210, 4, 20, 20);
			gameListPanel.add(lblGameUserRemove);
			this.lblGameUserRemove.add(lblGameUserRemove);
			
			JLabel lblGameUserPlot = new JLabel(String.valueOf(newSchueler.getPoints()));
			lblGameUserPlot.setBounds(240, 4, 20, 20);
			lblGameUserPlot.setForeground(white);
			lblGameUserPlot.setFont(lblGameUserPlot.getFont().deriveFont(18f));
			lblGameUserPlot.setBackground(menuePurple);
			lblGameUserPlot.setOpaque(true);
			lblGameUserPlot.setHorizontalAlignment(SwingConstants.CENTER);
			gameListPanel.add(lblGameUserPlot);
			this.lblGameUserPlotList.add(lblGameUserPlot);
			
		} else {
			model.set(i, newSchueler.getKlasse().getName() + "         " + newSchueler.getName());
			
			lblGameUserList.get(i).setText(newSchueler.getKlasse().getName() + " " + newSchueler.getName());
		}
	}
	
	public void updateDeleteOperation(int index) {
		model.remove(index);
		
		gameMidCenterPanel.remove(gameMidList.get(index));
		gameMidList.get(index).removeAll();

		gameMidList.remove(index);
		lblGameUserAdd.remove(index);
		lblGameUserRemove.remove(index);
		lblGameUserList.remove(index);
		lblGameUserPlotList.remove(index);
		
		for(int i = index; i < model.size(); i++) {
			updateListDeleteOperation(i);
			
		}
	}
	
	private void updateListDeleteOperation(int index) {
		gameMidList.get(index).setBounds(0, index*28, gameMidList.get(index).getBounds().width, gameMidList.get(index).getBounds().height);
		MouseListener[] mlGameUserAdd = lblGameUserAdd.get(index).getMouseListeners();
		lblGameUserAdd.get(index).removeMouseListener(mlGameUserAdd[0]);
		MouseListener[] mlGameUserRemove = lblGameUserRemove.get(index).getMouseListeners();
		lblGameUserRemove.get(index).removeMouseListener(mlGameUserRemove[0]);
		
		lblGameUserAdd.get(index).addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override
			public void mousePressed(MouseEvent e) {
				if(backend.getSchuelerlist().get(index).getPoints() < 20) {
					backend.addPoint(backend.getSchuelerlist().get(index));
					JLabel lblPlotLength = lblGameUserPlotList.get(index);
					lblPlotLength.setSize(lblPlotLength.getSize().width+25, lblPlotLength.getSize().height);
					lblPlotLength.setText(String.valueOf(backend.getSchuelerlist().get(index).getPoints()));
					lblGameUserPlotList.set(index, lblPlotLength);
				} else {
					setConfirmDialog("Die maximale Punktezahl ist auf 20 limitiert!");
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGameUserAdd.get(index).setBackground(menueOverPurple);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblGameUserAdd.get(index).setBackground(menuePurple);
			}
			
		});
		
		lblGameUserRemove.get(index).addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override public void mouseClicked(MouseEvent e) {  }
			@Override
			public void mouseExited(MouseEvent e) {
				lblGameUserRemove.get(index).setBackground(menuePurple);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGameUserRemove.get(index).setBackground(menueOverPurple);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				boolean isSuccessfull = backend.removePoint(backend.getSchuelerlist().get(index));
				if(isSuccessfull) {
					JLabel lblPlotLength = lblGameUserPlotList.get(index);
					lblPlotLength.setSize(lblPlotLength.getSize().width-25, lblPlotLength.getSize().height);
					lblPlotLength.setText(String.valueOf(backend.getSchuelerlist().get(index).getPoints()));
					lblGameUserPlotList.set(index, lblPlotLength);
				} else {
					setConfirmDialog("Die minimale Punktezahl ist auf 0 begrenzt!");
				}
			}
		});
		
		if(index%2 == 0) {
			gameMidList.get(index).setBackground(white);
		} else {
			gameMidList.get(index).setBackground(notEditablePurple);
		}
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
		settingsMidPanel.setVisible(true);
		gameMidCenterPanel.setVisible(false);
		gameMidBottomPanel.setVisible(false);
		gameMidPanel.setVisible(false);
		restartMidPanel.setVisible(false);
		gameSide = false;
		restartSide = false;
	}
	
	private void setGameSide() {
		gameSide = true;
		gameTitlePanel.setVisible(true);
		settingsTitlePanel.setVisible(false);
		restartTitlePanel.setVisible(false);
		gameMidPanel.setVisible(true);
		gameMidCenterPanel.setVisible(true);
		gameMidBottomPanel.setVisible(true);
		settingsMidPanel.setVisible(false);
		restartMidPanel.setVisible(false);
		settingsSide = false;
		restartSide = false;
	}
	
	private void setRestartSide() {
		restartSide = true;
		restartTitlePanel.setVisible(true);
		settingsTitlePanel.setVisible(false);
		gameTitlePanel.setVisible(false);
		gameMidCenterPanel.setVisible(false);
		gameMidBottomPanel.setVisible(false);
		restartMidPanel.setVisible(true);
		settingsMidPanel.setVisible(false);
		gameMidPanel.setVisible(false);
		settingsSide = false;
		gameSide = false;
	}

	private boolean isEnabledUpdate() {
		return enabledUpdate;
	}

	private void setEnabledUpdate(boolean enabledUpdate) {
		this.enabledUpdate = enabledUpdate;
	}

	private boolean isEnabledDelete() {
		return enabledDelete;
	}

	private void setEnabledDelete(boolean enabledDelete) {
		this.enabledDelete = enabledDelete;
	}
	
	private void setEnabledStartTimer(boolean enabledStartTimer) {
		this.enabledStartTimer = enabledStartTimer;
	}
	
	private boolean isEnabledStartTimer() {
		return enabledStartTimer;
	}
	
	@SuppressWarnings("unused")
	private void setConfirmDialog(String s) {
		new ConfirmDialog(this, true, s);
	}
	
	@SuppressWarnings("resource")
	private static void playSound() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("res/ready.wav")));
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
