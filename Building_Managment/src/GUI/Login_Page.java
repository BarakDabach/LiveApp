package GUI;
import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import Java_Classes.Building;
import Java_Classes.Main;
import Java_Classes.Resident;
import SQL.sqlDriver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;

import org.w3c.dom.css.RGBColor;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Window.Type;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;

public class Login_Page {

	public static  JFrame frmLoginPage;
	private JLabel lblPhoneNumber;
	public static JTextField phoneEntry;
	private JLabel lblLogin;
	public static JTextField PasswordEntry;
	private JLabel lblPassword;
	public JButton btnLogin;
	private static sqlDriver driver;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	public static Connection con ;
	public static String windowType = "";
	public static String userName;
	public static String num_of_msg = "You Have ";
	public static int buildingIDSQL;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblWeWillHelp;
	private JLabel lblNewLabel;
	private JLabel lblToBeIn;
	//function that checks if a string contains only numbers
	
	public static void handleLoginCheck() throws SQLException {




			
			String numRegex = "[0-9]+";
			String lettersRegex = "[a-zA-Z]+";
			
			
			if(phoneEntry.getText().equalsIgnoreCase(PasswordEntry.getText())) {
				
				if(phoneEntry.getText().matches(numRegex)) {
					rs = driver.sendQuery("select phone,f_Name,l_Name,buildingID from Resident where phone = "+PasswordEntry.getText());
					
					while(rs.next()) {
						buildingIDSQL = rs.getInt("buildingID");
						if (rs.getString("phone").equalsIgnoreCase(PasswordEntry.getText() )) {
							userName = rs.getString("f_Name")+" "+rs.getString("l_Name");
							rs = driver.sendQuery("select count(receive) from Message where receive ="+PasswordEntry.getText());
							while(rs.next()) {
								
								num_of_msg += Integer.toString(rs.getInt(1)) + " Messages";
								
								
							}
							frmLoginPage.dispose();
							windowType = "User";
							
							return;
						}
						
						
					
					}
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("User Is Not Exists");
					alert.alertFrame.setVisible(true);
				}
				
				else if(phoneEntry.getText().matches(lettersRegex))  {
					
					
					preStatment = con.prepareStatement("select userName from Admin where userName = ?");
					preStatment.setString(1,phoneEntry.getText());
					rs = preStatment.executeQuery();
					
					while(rs.next()) {
						if (rs.getString("userName").equalsIgnoreCase(PasswordEntry.getText())) {
							frmLoginPage.dispose();
							windowType = "Admin";
							return;
						}
					
					}
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("Admin Is Not Exists");
					alert.alertFrame.setVisible(true);
				}
				
				
				else {
					
					
				}
				
				
			}	
				
			
			
			
			else {
				
				alertMsg alert = new alertMsg();
				alert.alertFrame.setVisible(true);
				
			}
			
		
		}
	
	
	
	
		
	

	public static JTextField getPhoneEntry() {
		return phoneEntry;
	}




	public static JTextField getPasswordEntry() {
		return PasswordEntry;
	}




	/**
	 * Create the application.
	 */
	public Login_Page() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		sqlDriver conec = new sqlDriver();
		con =conec.connect();
		
		frmLoginPage = new JFrame();
		frmLoginPage.setUndecorated(true);
		frmLoginPage.setType(Type.UTILITY);
		frmLoginPage.getContentPane().setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		
		frmLoginPage.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.getContentPane().setBackground(new Color(34, 36, 39));
		frmLoginPage.setBounds(100, 100, 668, 680);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBackground(new Color(34, 36, 39));
		lblPhoneNumber.setForeground(new Color(255, 255, 255));
		lblPhoneNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPhoneNumber.setBounds(433, 307, 143, 25);
		frmLoginPage.getContentPane().add(lblPhoneNumber);
		
		phoneEntry = new JTextField();
		phoneEntry.setForeground(new Color(255, 255, 255));
		phoneEntry.setAlignmentX(Component.RIGHT_ALIGNMENT);
		phoneEntry.setSelectionColor(new Color(102, 51, 255));
		phoneEntry.setToolTipText("");
		phoneEntry.setHorizontalAlignment(SwingConstants.CENTER);
		phoneEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		phoneEntry.setBackground(new Color(34, 36, 39));
		phoneEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		phoneEntry.setBounds(395, 338, 198, 31);
		frmLoginPage.getContentPane().add(phoneEntry);
		phoneEntry.setColumns(10);
		
		lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblLogin.setBackground(new Color(34, 36, 39));
		lblLogin.setBounds(459, 195, 73, 33);
		frmLoginPage.getContentPane().add(lblLogin);
		
		
		
		btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					handleLoginCheck();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				btnLogin.setBackground(new Color(46,139,87));
				btnLogin.setBackground(new Color(51, 204, 153));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(34,36,39));
				
			}
		
		});
		
		btnLogin.setFocusPainted(false);
		btnLogin.setBackground(new Color(34, 36, 39));
		btnLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnLogin.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(127, 255, 212)));
		btnLogin.setBounds(395, 528, 198, 41);
		frmLoginPage.getContentPane().add(btnLogin);
		
		PasswordEntry = new JTextField();
		PasswordEntry.setForeground(new Color(255, 255, 255));
		PasswordEntry.setAlignmentX(Component.RIGHT_ALIGNMENT);
		PasswordEntry.setSelectionColor(new Color(102, 51, 255));
		PasswordEntry.setToolTipText("");
		PasswordEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		PasswordEntry.setHorizontalAlignment(SwingConstants.CENTER);
		PasswordEntry.setBackground(new Color(34, 36, 39));
		PasswordEntry.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(51, 204, 153)));
		PasswordEntry.setBounds(395, 423, 198, 31);
		frmLoginPage.getContentPane().add(PasswordEntry);
		PasswordEntry.setColumns(10);
		lblPassword = new JLabel("Password");
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(439, 401, 112, 25);
		frmLoginPage.getContentPane().add(lblPassword);
		
		JLabel closeIcon = new JLabel("");
		closeIcon.setHorizontalAlignment(SwingConstants.CENTER);
		closeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeIcon.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/close.png")));
		closeIcon.setAlignmentX(1.0f);
		closeIcon.setBounds(624, 17, 24, 24);
		frmLoginPage.getContentPane().add(closeIcon);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 0, 204));
		panel.setBounds(333, 241, 310, 2);
		frmLoginPage.getContentPane().add(panel);
		
		JLabel signUpLabel = new JLabel("Don't have an account? Sign Up");
		signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signUpLabel.setForeground(new Color(51, 254, 153));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signUpLabel.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frmLoginPage.dispose();
				SignUpWindow sign = new SignUpWindow();
			}
		});
		signUpLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		signUpLabel.setForeground(new Color(255, 255, 255));
		signUpLabel.setBounds(382, 582, 247, 25);
		frmLoginPage.getContentPane().add(signUpLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51,53,56));
		panel_1.setBounds(0, 0, 305, 705);
		frmLoginPage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(22, 87, 271, 280);
		panel_1.add(label_3);
		label_3.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/logo_transparent.png")));
		
		JLabel lblLoginPage = new JLabel("LiveApp");
		lblLoginPage.setBounds(109, 285, 108, 34);
		panel_1.add(lblLoginPage);
		lblLoginPage.setForeground(new Color(102, 153, 255));
		lblLoginPage.setBackground(new Color(0, 0, 0));
		lblLoginPage.setFont(new Font("Yu Gothic UI", Font.BOLD, 28));
		
		lblWeWillHelp = new JLabel("We will help you Manage and Maintain");
		lblWeWillHelp.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWeWillHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeWillHelp.setVerticalTextPosition(SwingConstants.TOP);
		lblWeWillHelp.setVerifyInputWhenFocusTarget(false);
		lblWeWillHelp.setRequestFocusEnabled(false);
		lblWeWillHelp.setForeground(Color.WHITE);
		lblWeWillHelp.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblWeWillHelp.setBackground(new Color(34, 36, 39));
		lblWeWillHelp.setBounds(22, 350, 271, 27);
		panel_1.add(lblWeWillHelp);
		
		lblNewLabel = new JLabel("your Buidling Like with supposed ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setVerifyInputWhenFocusTarget(false);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(32, 382, 249, 27);
		panel_1.add(lblNewLabel);
		
		lblToBeIn = new JLabel("to be in the 21th Century");
		lblToBeIn.setVerticalTextPosition(SwingConstants.TOP);
		lblToBeIn.setVerticalAlignment(SwingConstants.BOTTOM);
		lblToBeIn.setVerifyInputWhenFocusTarget(false);
		lblToBeIn.setRequestFocusEnabled(false);
		lblToBeIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblToBeIn.setForeground(Color.WHITE);
		lblToBeIn.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblToBeIn.setBackground(new Color(34, 36, 39));
		lblToBeIn.setBounds(22, 419, 271, 16);
		panel_1.add(lblToBeIn);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/user-login.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(415, 60, 128, 128);
		frmLoginPage.getContentPane().add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/minimize.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setAlignmentX(1.0f);
		label_1.setBounds(586, 17, 24, 24);
		frmLoginPage.getContentPane().add(label_1);
		frmLoginPage.setLocationRelativeTo(null);
		
	
		
	}
}
