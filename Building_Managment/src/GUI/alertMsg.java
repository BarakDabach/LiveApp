package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class alertMsg {

	public JFrame alertFrame;
	public static JLabel errordetlbl;


	/**
	 * Create the application.
	 */
	public alertMsg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alertFrame = new JFrame();
		alertFrame.setType(Type.POPUP);
		alertFrame.setTitle("Login Error");
		alertFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		alertFrame.setUndecorated(true);
		alertFrame.getContentPane().setBackground(new Color(34, 36, 39));
		alertFrame.setBounds(100, 100, 450, 193);
		alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alertFrame.setUndecorated(true);
		alertFrame.setLocation(500, 500);
		alertFrame.getContentPane().setLayout(null);
		
		errordetlbl = new JLabel("Your Details Must Be The Same");
		errordetlbl.setBackground(Color.WHITE);
		errordetlbl.setForeground(Color.WHITE);
		errordetlbl.setIconTextGap(30);
		errordetlbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		errordetlbl.setBounds(new Rectangle(96, 49, 264, 36));
		errordetlbl.setHorizontalAlignment(SwingConstants.CENTER);
		alertFrame.getContentPane().add(errordetlbl);
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setBounds(88, 527, 198, 41);
		btnGotIt.setForeground(new Color(255, 255, 255));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertFrame.dispose();
			}
		});
		btnGotIt.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnGotIt.setFocusPainted(false);
		btnGotIt.setBorder(null);
		btnGotIt.setBackground(new Color(255, 255, 255));
		btnGotIt.setBounds(12, 113, 432, 50);
		
		btnGotIt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				btnLogin.setBackground(new Color(46,139,87));
				btnGotIt.setBackground(new Color(51, 204, 153));
//				btnGotIt.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnGotIt.setBackground(new Color(34,36,39));
			}
		
		});
		
		
		btnGotIt.setFocusPainted(false);
		btnGotIt.setBackground(new Color(34, 36, 39));
		btnGotIt.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnGotIt.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(127, 255, 212)));
		
		
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		separator.setBackground(Color.GRAY);
		separator.setBounds(14, 98, 428, 2);
		alertFrame.getContentPane().add(separator);
		alertFrame.getContentPane().add(btnGotIt);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Problem With Details");
		lblDetailsAreIncorrect.setForeground(new Color(102, 0, 204));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setBounds(123, 13, 210, 36);
		alertFrame.getContentPane().add(lblDetailsAreIncorrect);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(alertMsg.class.getResource("/Media/userDetailsIcon.png")));
		label.setBounds(16, 34, 60, 60);
		alertFrame.getContentPane().add(label);
		alertFrame.setLocationRelativeTo(null);
	}
}
