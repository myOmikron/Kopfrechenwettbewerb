package de.omikron.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class InitFrame extends JFrame {

	private JPanel contentPane = new MotionPanel(this);

	
	public InitFrame() {
		super("Kopfrechenwettbewerb");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setUndecorated(true);
		setContentPane(contentPane);
		
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		
		
		pack();
		setVisible(true);
	}
}
