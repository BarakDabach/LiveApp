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
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.border.MatteBorder;
import javax.swing.JPanel;

public class AddMsg {

	public JFrame alertFrame;
	public static JLabel errordetlbl;


	/**
	 * Create the application.
	 */
	public AddMsg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alertFrame = new JFrame();
		alertFrame.setType(Type.POPUP);
		alertFrame.setTitle("Message Error");
		alertFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		alertFrame.setUndecorated(true);
		alertFrame.getContentPane().setBackground(new Color(34,36,39));
		alertFrame.setBounds(100, 100, 450, 193);
		alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alertFrame.setLocation(500, 500);
		alertFrame.getContentPane().setLayout(null);
		
		errordetlbl = new JLabel("Your Message Sent Successfully");
		errordetlbl.setForeground(new Color(255, 255, 255));
		errordetlbl.setIconTextGap(30);
		errordetlbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		errordetlbl.setBounds(new Rectangle(90, 13, 272, 36));
		errordetlbl.setHorizontalAlignment(SwingConstants.CENTER);
		alertFrame.getContentPane().add(errordetlbl);
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setForeground(new Color(255, 255, 255));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertFrame.dispose();
			}
		});
		btnGotIt.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnGotIt.setFocusPainted(false);
		btnGotIt.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 153)));
		btnGotIt.setBackground(new Color(34,36,39));
		btnGotIt.setBounds(2, 143, 448, 50);
		
		btnGotIt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				btnLogin.setBackground(new Color(46,139,87));
				btnGotIt.setBackground(new Color(153,255,153));
				btnGotIt.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnGotIt.setBackground(new Color(34,36,39));
				btnGotIt.setForeground(Color.white);
			}
		
		});
		alertFrame.getContentPane().add(btnGotIt);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 153)));
		panel.setBackground(new Color(34,36,39));
		panel.setBounds(2, 0, 448, 193);
		alertFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(194, 62, 60, 60);
		panel.add(label);
		label.setIcon(new ImageIcon(AddMsg.class.getResource("/Media/uploadSuccess.png")));
		alertFrame.setLocationRelativeTo(null);
	}
}
