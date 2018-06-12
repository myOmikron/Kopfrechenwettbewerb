package de.omikron.main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class InitFrame extends JFrame {

	private JPanel contentPane = new MotionPanel(this);
	
	private JPanel sidePanel = new JPanel(null), titlePanel = new JPanel(null), topPanel = new JPanel(null), midPanel = new JPanel(null);
	private JPanel gameSidePanel = new JPanel(null), settingsSidePanel = new JPanel(null), restartSidePanel = new JPanel(null);
	private JPanel gameTitlePanel = new JPanel(null), settingsTitlePanel = new JPanel(null), restartTitlePanel = new JPanel(null);
	private JPanel gameMidPanel = new JPanel(null), settingsMidPanel = new JPanel(null), restartMidPanel = new JPanel(null);
	private JPanel settingsMidAddPanel = new JPanel(null), settingsMidUpdatePanel = new JPanel(null), settingsMidRemovePanel = new JPanel(null);

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
	
	private JTextField tfSettingsMidClass, tfSettingsMidName;
	
	private JList<String> listSettingsMidTable;
	private DefaultListModel<String> model;
	
	private JSlider sliSettingsMidGameLength;
	
	private JProgressBar pbarTime;
	
	private final Color brightPurple = new Color(140, 26, 255), darkPurple = new Color(64, 0, 128);
	private final Color menuePurple = new Color(77, 0, 153), menueOverPurple = new Color(102, 0, 204);
	private final Color notEditablePurple = new Color(191, 128, 255);
	private final Color white = new Color(252, 252, 252);
	
	private final ImageIcon sgpLogo = new ImageIcon("res/sgpSmall.png");
	private final ImageIcon settingsLogoSmall = new ImageIcon("res/settings_32px.png"), settingsLogo = new ImageIcon("res/settings_64px.png");
	private final ImageIcon playLogoSmall = new ImageIcon("res/start_32px.png"), playLogo = new ImageIcon("res/start_64px.png");
	private final ImageIcon restartLogoSmall = new ImageIcon("res/restart_32px.png"), restartLogo = new ImageIcon("res/restart_64px.png");
	private final ImageIcon addLogoSmall = new ImageIcon("res/add_16px.png");
	private final ImageIcon removeLogoSmall = new ImageIcon("res/remove_16px.png");
	private final ImageIcon updateLogoSmall = new ImageIcon("res/update_16px.png");
	
	private boolean gameSide, settingsSide, restartSide;
	private boolean enabledUpdate, enabledDelete;
	
	private Backend backend;
	
	public InitFrame(Backend backend) {
		super("Kopfrechenwettbewerb");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setUndecorated(true);
		setContentPane(contentPane);
		
		this.backend = backend;
	}
	
	protected void init() {
		pbarTime = new JProgressBar(0, VarSettings.getGameLength());
		pbarTime.setBounds(120, 400, 500, 20);
		gameMidPanel.add(pbarTime);
		
		lblStartTime = new JLabel("Start");
		lblStartTime.setForeground(white);
		lblStartTime.setBackground(menuePurple);
		lblStartTime.setOpaque(true);
		lblStartTime.setBounds(20, 400, 80, 20);
		lblStartTime.addMouseListener(new MouseListener() {
			@Override public void mouseReleased(MouseEvent e) {  }
			@Override public void mousePressed(MouseEvent e) {  }
			@Override
			public void mouseExited(MouseEvent e) {
				lblStartTime.setBackground(menuePurple);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblStartTime.setBackground(menueOverPurple);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		lblStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		gameMidPanel.add(lblStartTime);
		
		lblRestartOkay = new JLabel("Okay");
		lblRestartOkay.setBounds(310, 60, 150, 30);
		lblRestartOkay.setFont(lblRestartOkay.getFont().deriveFont(16f));
		lblRestartOkay.setForeground(white);
		lblRestartOkay.setBackground(menuePurple);
		lblRestartOkay.setOpaque(true);
		lblRestartOkay.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestartOkay.addMouseListener(new MouseListener() {
			@Override public void mousePressed(MouseEvent e) {  }
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
			public void mouseClicked(MouseEvent e) {
				backend.reset();
				VarSettings.setGameLength(15);
				sliSettingsMidGameLength.setValue(15);
				setSettingsSide();
			}
		});
		restartMidPanel.add(lblRestartOkay);
		
		lblRestartInfo = new JLabel("Alle Daten löschen und neustarten?");
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
		listSettingsMidTable.setBounds(365, 20, 390, 410);
		listSettingsMidTable.setBorder(new LineBorder(darkPurple));
		listSettingsMidTable.setForeground(darkPurple);
		settingsMidPanel.add(listSettingsMidTable);
		
		tfSettingsMidName = new JTextField();
		tfSettingsMidName.setForeground(darkPurple);
		tfSettingsMidName.setBounds(80, 155, 160, 20);
		settingsMidPanel.add(tfSettingsMidName);
		
		tfSettingsMidClass = new JTextField();
		tfSettingsMidClass.setForeground(darkPurple);
		tfSettingsMidClass.setBounds(20, 155, 50, 20);
		settingsMidPanel.add(tfSettingsMidClass);
		
		lblSettingsMidGameName = new JLabel("Name");
		lblSettingsMidGameName.setFont(lblSettingsMidGameName.getFont().deriveFont(12f));
		lblSettingsMidGameName.setForeground(darkPurple);
		lblSettingsMidGameName.setBounds(80, 130, 80, 20);
		settingsMidPanel.add(lblSettingsMidGameName);
		
		lblSettingsMidGameClass = new JLabel("Klasse");
		lblSettingsMidGameClass.setBounds(20, 130, 80, 20);
		lblSettingsMidGameClass.setFont(lblSettingsMidGameClass.getFont().deriveFont(12f));
		lblSettingsMidGameClass.setForeground(darkPurple);
		settingsMidPanel.add(lblSettingsMidGameClass);
		
		lblSettingsMidGameUser = new JLabel("Teilnehmerverwaltung");
		lblSettingsMidGameUser.setBounds(20, 100, 200, 30);
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
			}
		});
		sliSettingsMidGameLength.setSnapToTicks(true);
		sliSettingsMidGameLength.setValue(15);
		sliSettingsMidGameLength.setBounds(20, 45, 225, 50);
		settingsMidPanel.add(sliSettingsMidGameLength);
		
		lblSettingsMidGameLength = new JLabel("Gew" + '\u00fc' + "nschte Rundenzeit eingeben:");
		lblSettingsMidGameLength.setBounds(20, 20, 300, 20);
		lblSettingsMidGameLength.setFont(lblSettingsMidGameLength.getFont().deriveFont(14f));
		lblSettingsMidGameLength.setForeground(darkPurple);
		settingsMidPanel.add(lblSettingsMidGameLength);
		
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
		
		settingsMidRemovePanel.setBounds(250, 215, 105, 25);
		settingsMidRemovePanel.setBackground(notEditablePurple);
		settingsMidRemovePanel.addMouseListener(new MouseListener() {
			@Override public void mousePressed(MouseEvent e) {  }
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
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		settingsMidPanel.add(settingsMidRemovePanel);
		
		settingsMidUpdatePanel.setBounds(250, 185, 105, 25);
		settingsMidUpdatePanel.setBackground(notEditablePurple);
		settingsMidUpdatePanel.addMouseListener(new MouseListener() {
			@Override public void mousePressed(MouseEvent e) {  }
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
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		settingsMidPanel.add(settingsMidUpdatePanel);
		
		settingsMidAddPanel.setBounds(250, 155, 105, 25);
		settingsMidAddPanel.setBackground(menuePurple);
		settingsMidAddPanel.addMouseListener(new MouseListener() {
			@Override public void mousePressed(MouseEvent e) {  }
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
			public void mouseClicked(MouseEvent e) {
				String name = tfSettingsMidName.getText();
				String klasse = tfSettingsMidClass.getText();
				if(Controller.isNotEmpty(name) &&
						Controller.isNotEmpty(klasse) &&
						Controller.checkClassInput(klasse)) {
					backend.addSchueler(name, klasse);
					tfSettingsMidClass.setText("");
					tfSettingsMidName.setText("");
					tfSettingsMidClass.requestFocus();
				}
			}
		});
		settingsMidPanel.add(settingsMidAddPanel);
		
		settingsMidPanel.setBounds(0, 0, 775, 500);
		settingsMidPanel.setOpaque(false);
		settingsMidPanel.setVisible(false);
		midPanel.add(settingsMidPanel);
		
		restartMidPanel.setBounds(0, 0, 775, 500);
		restartMidPanel.setOpaque(false);
		restartMidPanel.setVisible(false);
		midPanel.add(restartMidPanel);
		
		gameMidPanel.setBounds(0, 0, 775, 500);
		gameMidPanel.setOpaque(false);
		gameMidPanel.setVisible(false);
		midPanel.add(gameMidPanel);
		
		midPanel.setBackground(white);
		midPanel.setBounds(225, 200, 775, 500);
		contentPane.add(midPanel);
		
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
		setEnabledDelete(false);
		setEnabledUpdate(false);
		setVisible(true);
	}
	
	public void resetList() {
		model.removeAllElements();
		lblGameUserList = new ArrayList<>();
		lblGameUserAdd = new ArrayList<>();
		lblGameUserRemove = new ArrayList<>();
		lblGameUserPlotList = new ArrayList<>();
	}
	
	public void updateList(Schueler newSchueler, int i) {
		if(i >= model.getSize()) {
			model.addElement(newSchueler.getKlasse().getName() + "         " + newSchueler.getName());
			
			JLabel lblGameUserName = new JLabel(newSchueler.getKlasse().getName() + "  " + newSchueler.getName());
			lblGameUserName.setForeground(darkPurple);
			lblGameUserName.setBounds(20, 20+(25*i), 150, 20);
			gameMidPanel.add(lblGameUserName);
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
				@Override public void mousePressed(MouseEvent e) {  }
				@Override
				public void mouseExited(MouseEvent e) {
					lblGameUserAdd.setBackground(menuePurple);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					lblGameUserAdd.setBackground(menueOverPurple);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if(newSchueler.getPoints() < 100) {
						backend.addPoint(newSchueler);
						JLabel lblPlotLength = lblGameUserPlotList.get(i);
						lblPlotLength.setSize(lblPlotLength.getSize().width+5, lblPlotLength.getSize().height);
						lblPlotLength.setText(String.valueOf(newSchueler.getPoints()));
						lblGameUserPlotList.set(i, lblPlotLength);
					} else {
						WarningFrame wf = new WarningFrame();
						wf.setWarning("Mehr als 100 Punkte sind nicht erlaubt!");
					}
				}
			});
			lblGameUserAdd.setBounds(180, 20+(i*25), 20, 20);
			gameMidPanel.add(lblGameUserAdd);
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
				@Override public void mousePressed(MouseEvent e) {  }
				@Override
				public void mouseExited(MouseEvent e) {
					lblGameUserRemove.setBackground(menuePurple);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					lblGameUserRemove.setBackground(menueOverPurple);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					boolean isSuccessfull = backend.removePoint(newSchueler);
					if(isSuccessfull) {
						JLabel lblPlotLength = lblGameUserPlotList.get(i);
						lblPlotLength.setSize(lblPlotLength.getSize().width-5, lblPlotLength.getSize().height);
						lblPlotLength.setText(String.valueOf(newSchueler.getPoints()));
						lblGameUserPlotList.set(i, lblPlotLength);
					} else {
						WarningFrame wf = new WarningFrame();
						wf.setWarning("Weniger als 0 Punkte sind nicht erlaubt!");
					}
				}
			});
			lblGameUserRemove.setBounds(210, 20+(25*i), 20, 20);
			gameMidPanel.add(lblGameUserRemove);
			this.lblGameUserRemove.add(lblGameUserRemove);
			
			JLabel lblGameUserPlot = new JLabel(String.valueOf(newSchueler.getPoints()));
			lblGameUserPlot.setBounds(240, 20+(i*25), 20, 20);
			lblGameUserPlot.setForeground(white);
			lblGameUserPlot.setFont(lblGameUserPlot.getFont().deriveFont(18f));
			lblGameUserPlot.setBackground(menuePurple);
			lblGameUserPlot.setOpaque(true);
			lblGameUserPlot.setHorizontalAlignment(SwingConstants.CENTER);
			gameMidPanel.add(lblGameUserPlot);
			this.lblGameUserPlotList.add(lblGameUserPlot);
			
		} else {
			model.set(i, newSchueler.getKlasse().getName() + "         " + newSchueler.getName());
			
			
		}
	}
	
	public void updateGame() {
		
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
}
