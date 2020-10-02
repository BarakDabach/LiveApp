package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import Java_Classes.Main;
import SQL.sqlDriver;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateResidnetPersonalDetailsWindow {

	public JFrame frame;
	private JTextField firstNameEntry;
	private JTextField lastNameEntry;
	private JTextField phoneNumberEntry;
	private JPasswordField passwordEntry;
	private JTextField emailEntry;
	public sqlDriver conec = new sqlDriver();
	public static String userPhoneNumber;
	private static  String numRegex = "[0-9]+";
	private static String lettersRegex = "[a-zA-Z]+";
	private static PreparedStatement preStatment;
	private static Connection con = Login_Page.con; 
	private static ResultSet rs ;
	private JButton updateDetailsBtn;
	public UpdateResidnetPersonalDetailsWindow(String phoneNumber) {
		userPhoneNumber = phoneNumber;
		initialize();
		getUserDetails();
	}
	
	
	
	public void getUserDetails() {
		try {
			preStatment = con.prepareStatement("select f_Name , l_name , email, phone,password from Resident  where phone = ?");
			preStatment.setString(1,userPhoneNumber);

			
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				firstNameEntry.setText(rs.getString(1));
				lastNameEntry.setText(rs.getString(2));
				emailEntry.setText(rs.getString(3));
				phoneNumberEntry.setText(rs.getString(4));
				passwordEntry.setText(rs.getString(5));
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(34,36,39));
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 556, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(firstNameEntry.getText().length() == 0 || lastNameEntry.getText().length() == 0 
						|| emailEntry.getText().length() == 0 || phoneNumberEntry.getText().length() == 0 ) {
					
					updateDetailsBtn.setEnabled(false);
					
				}
				
				else {
					updateDetailsBtn.setEnabled(true);
				}
			}
		});
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 204, 102)));
		panel.setBackground(new Color(34,36,39));
		panel.setBounds(0, 0, 556, 640);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel closeIcon = new JLabel("");
		closeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		closeIcon.setBounds(512, 13, 32, 32);
		panel.add(closeIcon);
		closeIcon.setHorizontalAlignment(SwingConstants.CENTER);
		closeIcon.setIconTextGap(0);
		closeIcon.setIcon(new ImageIcon(UpdateResidnetPersonalDetailsWindow.class.getResource("/Media/close.png")));
		
		JLabel label_2 = new JLabel("First Name");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_2.setBackground(new Color(34, 36, 39));
		label_2.setBounds(232, 156, 92, 25);
		panel.add(label_2);
		
		firstNameEntry = new JTextField();
		firstNameEntry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleOnlyLettersCheck(firstNameEntry);
			}
		});
		firstNameEntry.setToolTipText("");
		firstNameEntry.setSelectionColor(new Color(102, 51, 255));
		firstNameEntry.setForeground(Color.WHITE);
		firstNameEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		firstNameEntry.setColumns(10);
		firstNameEntry.setCaretColor(new Color(147, 112, 219));
		firstNameEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		firstNameEntry.setBackground(new Color(34, 36, 39));
		firstNameEntry.setAlignmentX(1.0f);
		firstNameEntry.setBounds(154, 186, 249, 31);
		panel.add(firstNameEntry);
		
		JLabel label_3 = new JLabel("Last Name");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_3.setBackground(new Color(34, 36, 39));
		label_3.setBounds(233, 230, 91, 25);
		panel.add(label_3);
		
		lastNameEntry = new JTextField();
		lastNameEntry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleOnlyLettersCheck(lastNameEntry);
			}
		});
		lastNameEntry.setToolTipText("");
		lastNameEntry.setSelectionColor(new Color(102, 51, 255));
		lastNameEntry.setForeground(Color.WHITE);
		lastNameEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		lastNameEntry.setColumns(10);
		lastNameEntry.setCaretColor(new Color(147, 112, 219));
		lastNameEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		lastNameEntry.setBackground(new Color(34, 36, 39));
		lastNameEntry.setAlignmentX(1.0f);
		lastNameEntry.setBounds(154, 254, 249, 31);
		panel.add(lastNameEntry);
		
		JLabel label_4 = new JLabel("Phone Number");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_4.setBackground(new Color(34, 36, 39));
		label_4.setBounds(214, 301, 129, 25);
		panel.add(label_4);
		
		phoneNumberEntry = new JTextField();
		phoneNumberEntry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleOnlyDigitsCheck(phoneNumberEntry);
			}
		});
		phoneNumberEntry.setToolTipText("");
		phoneNumberEntry.setSelectionColor(new Color(102, 51, 255));
		phoneNumberEntry.setForeground(Color.WHITE);
		phoneNumberEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		phoneNumberEntry.setColumns(10);
		phoneNumberEntry.setCaretColor(new Color(123, 104, 238));
		phoneNumberEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		phoneNumberEntry.setBackground(new Color(34, 36, 39));
		phoneNumberEntry.setAlignmentX(1.0f);
		phoneNumberEntry.setBounds(154, 333, 249, 31);
		panel.add(phoneNumberEntry);
		
		JLabel label_5 = new JLabel("Password");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_5.setBackground(new Color(34, 36, 39));
		label_5.setBounds(238, 379, 80, 25);
		panel.add(label_5);
		
		passwordEntry = new JPasswordField();
		passwordEntry.setSelectionColor(new Color(147, 112, 219));
		passwordEntry.setForeground(Color.WHITE);
		passwordEntry.setFont(new Font("Tahoma", Font.PLAIN, 19));
		passwordEntry.setCaretColor(new Color(147, 112, 219));
		passwordEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		passwordEntry.setBackground(new Color(34, 36, 39));
		passwordEntry.setBounds(154, 417, 249, 31);
		panel.add(passwordEntry);
		
		JLabel label_6 = new JLabel("Email");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_6.setBackground(new Color(34, 36, 39));
		label_6.setBounds(256, 463, 44, 25);
		panel.add(label_6);
		
		emailEntry = new JTextField();
		emailEntry.setToolTipText("");
		emailEntry.setSelectionColor(new Color(102, 51, 255));
		emailEntry.setForeground(Color.WHITE);
		emailEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		emailEntry.setColumns(10);
		emailEntry.setCaretColor(new Color(147, 112, 219));
		emailEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		emailEntry.setBackground(new Color(34, 36, 39));
		emailEntry.setAlignmentX(1.0f);
		emailEntry.setBounds(154, 493, 249, 31);
		panel.add(emailEntry);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 255, 153));
		panel_1.setBounds(4, 67, 548, 64);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUpdatePersonalInformation = new JLabel("Update Personal Information");
		lblUpdatePersonalInformation.setBounds(101, 13, 331, 33);
		panel_1.add(lblUpdatePersonalInformation);
		lblUpdatePersonalInformation.setToolTipText("");
		lblUpdatePersonalInformation.setForeground(Color.WHITE);
		lblUpdatePersonalInformation.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblUpdatePersonalInformation.setBackground(new Color(34, 36, 39));
		
		updateDetailsBtn = new JButton("Update Personal Detalis");
		updateDetailsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					preStatment = con.prepareStatement("UPDATE  Resident SET f_Name = ? , l_name = ? , email = ? , phone = ?   where phone = ?");
					preStatment.setString(1, firstNameEntry.getText());
					preStatment.setString(2, lastNameEntry.getText());
					preStatment.setString(3, emailEntry.getText());
					preStatment.setString(4, phoneNumberEntry.getText());
					preStatment.setString(5, userPhoneNumber);
					preStatment.executeUpdate();
					
					Resident_Window.userPhoneNumber = phoneNumberEntry.getText();
					userPhoneNumber = phoneNumberEntry.getText();
					frame.dispose();
					
				}catch(SQLException e1) {
					
				}
			}
		});
		
		
		updateDetailsBtn.setForeground(Color.WHITE);
		updateDetailsBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		updateDetailsBtn.setFocusPainted(false);
		updateDetailsBtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 204, 153)));
		updateDetailsBtn.setBackground(new Color(34, 36, 39));
		updateDetailsBtn.setBounds(154, 569, 249, 42);
		panel.add(updateDetailsBtn);
	}
	
	protected void handleOnlyLettersCheck(JTextField textField) {
		if(textField.getText().matches(lettersRegex) == false && textField.getText().equals("") == false ) {									
			textField.setText(textField.getText().substring(0,textField.getText().length()-1));
			
			}
	}
	
	protected void handleOnlyDigitsCheck(JTextField textField) {
		if(textField.getText().matches(numRegex) == false && textField.getText().equals("") == false || textField.getText().length() >10 == true ) {									
			textField.setText(textField.getText().substring(0,textField.getText().length()-1));
			
			}
	}
	protected boolean isEmailValid(JTextField textField) {
		if(textField.getText().contains("@") == false) {									
			return false;
			
			}
		
		return true;
	}
}
