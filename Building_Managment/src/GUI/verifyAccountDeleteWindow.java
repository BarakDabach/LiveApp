package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

import Java_Classes.Main;

import javax.swing.JPanel;
import java.awt.Cursor;

public class verifyAccountDeleteWindow {

	public JFrame alertFrame;
	protected String userphoneNumber;
	private static Connection con = Login_Page.con; 
	private static PreparedStatement preStatment;
	/**
	 * Create the application.
	 */
	public verifyAccountDeleteWindow(PreparedStatement preStatment,String userPhoneNumber) {
		this.preStatment = preStatment;
		this.userphoneNumber = userPhoneNumber;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alertFrame = new JFrame();
		alertFrame.setType(Type.POPUP);
		alertFrame.setTitle("Defect Added");
		alertFrame.setLocationRelativeTo(null);
		alertFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		alertFrame.setUndecorated(true);
		alertFrame.getContentPane().setBackground(new Color(34,36,39));
		alertFrame.setBounds(100, 100, 450, 193);
		alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alertFrame.setLocation(500, 500);
		alertFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		panel.setBackground(new Color(34,36,39));
		panel.setBounds(0, 0, 448, 193);
		alertFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Are you sure you want to delete your Account ?");
		lblDetailsAreIncorrect.setBounds(12, 44, 424, 36);
		panel.add(lblDetailsAreIncorrect);
		lblDetailsAreIncorrect.setForeground(new Color(255, 255, 255));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel deleteLabel = new JLabel("");
		deleteLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					preStatment = con.prepareStatement("delete from Resident where phone = ?");
					preStatment.setString(1,userphoneNumber);
					preStatment.executeUpdate();
					Resident_Window.frmUserWindow.dispose();
					alertFrame.dispose();
					Main.isLoggedIn = false;
					
				}
				
				catch(Exception e1) {
					e1.getMessage();
				}
				
			}
		});
		deleteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		deleteLabel.setBounds(127, 110, 71, 70);
		panel.add(deleteLabel);
		deleteLabel.setIcon(new ImageIcon(verifyAccountDeleteWindow.class.getResource("/Media/uploadSuccess.png")));
		
		JLabel cancelLabel = new JLabel("");
		cancelLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				alertFrame.dispose();
				
			}
		});
		cancelLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelLabel.setIcon(new ImageIcon(verifyAccountDeleteWindow.class.getResource("/Media/x-button.png")));
		cancelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cancelLabel.setBounds(245, 110, 71, 70);
		panel.add(cancelLabel);
		alertFrame.setLocationRelativeTo(null);
	}
}
