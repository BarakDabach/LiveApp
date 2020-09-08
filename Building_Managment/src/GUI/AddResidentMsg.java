package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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

public class AddResidentMsg {

	public JFrame ResidentAddFrame;
	public static JLabel ResidentAddlbl;


	/**
	 * Create the application.
	 */
	public AddResidentMsg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ResidentAddFrame = new JFrame();
		ResidentAddFrame.setType(Type.POPUP);
		ResidentAddFrame.setTitle("Resident Added");
		ResidentAddFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		ResidentAddFrame.setUndecorated(false);
		ResidentAddFrame.getContentPane().setBackground(new Color(255, 255, 255));
		ResidentAddFrame.setBounds(100, 100, 450, 193);
		ResidentAddFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ResidentAddFrame.setLocation(500, 500);
		ResidentAddFrame.getContentPane().setLayout(null);
		
		ResidentAddlbl = new JLabel("Resident Added Succesfully");
		ResidentAddlbl.setForeground(new Color(0, 0, 0));
		ResidentAddlbl.setIconTextGap(30);
		ResidentAddlbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		ResidentAddlbl.setBounds(new Rectangle(90, 49, 264, 36));
		ResidentAddlbl.setHorizontalAlignment(SwingConstants.CENTER);
		ResidentAddFrame.getContentPane().add(ResidentAddlbl);
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setForeground(new Color(0, 0, 0));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResidentAddFrame.dispose();
				addResident.addResidentFrame.dispose();
				try {
					Admin_Window.enterResidetnsName();;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGotIt.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnGotIt.setFocusPainted(false);
		btnGotIt.setBorder(null);
		btnGotIt.setBackground(new Color(255, 255, 255));
		btnGotIt.setBounds(0, 100, 432, 50);
		
		btnGotIt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				btnLogin.setBackground(new Color(46,139,87));
				btnGotIt.setBackground(new Color(50,205,50));
				btnGotIt.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnGotIt.setBackground(new Color(255,255,255));
				btnGotIt.setForeground(Color.black);
			}
		
		});
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		separator.setBackground(Color.GRAY);
		separator.setBounds(2, 98, 428, 2);
		ResidentAddFrame.getContentPane().add(separator);
		ResidentAddFrame.getContentPane().add(btnGotIt);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Message");
		lblDetailsAreIncorrect.setForeground(new Color(255, 140, 0));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setBounds(115, 13, 210, 36);
		ResidentAddFrame.getContentPane().add(lblDetailsAreIncorrect);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AddDefect.class.getResource("/Media/defectsuccescIcon.png")));
		label.setBounds(33, 20, 60, 60);
		ResidentAddFrame.getContentPane().add(label);
		ResidentAddFrame.setLocationRelativeTo(null);
	}
}
