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
import com.sun.prism.Image;

import Java_Classes.Building;
import Java_Classes.Main;
import Java_Classes.Resident;
import SQL.sqlDriver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import org.w3c.dom.css.RGBColor;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Window.Type;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.CaretEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Login_Page {
	private static sqlDriver driver = new sqlDriver();
	public static  JFrame frmLoginPage;
	private JLabel lblPhoneNumber;
	private JLabel lblLogin;
	public static JTextField PasswordEntry;
	private JLabel lblPassword;
	public JButton loginButton;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	public static Connection con ;
	public static String windowType = "";
	public static String userName;
	public static String num_of_msg = "You Have ";
	public static int buildingIDSQL;
	public static boolean admin;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblWeWillHelp;
	private JLabel lblNewLabel;
	private JLabel lblToBeIn;
	private JPanel loginPanel;
	private JPanel signUpPanel;
	private JLabel label_2;
	private JPanel stage1Indicator;
	private JLabel label_5;
	private JTextField signUpPhoneTextField;
	private JButton nextBtnPersonalInf;
	private JLabel lblSign;
	private JLabel lblPaswword;
	private JPasswordField signUpPasswordTextField;
	private JLabel lblEmail;
	private JTextField signUpEmailTextField;
	private JLabel lblCity;
	private JComboBox cityComboBox;
	private JComboBox streetComboBox;
	private JLabel lblStreet;
	private JComboBox buildingNumberComboBox;
	private JLabel lblBuildingNumber;
	private JPanel stage2Indicator;
	private JPanel stage3Indicator;
	private JTextField firstNameEntry;
	private JTextField lastNameEntry;
	private JPanel buildingInformationPanel;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JButton buildingInfoNextbrn;
	private JLabel lblBuildingInformation;
	private JButton buildingInfoBackbtn;
	private JPanel finishPanel;
	private JPanel personalInformationPanel;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JButton signUpBtn;
	private JLabel lblVerifyYourDetails;
	private JLabel lblFirstName_1;
	private JButton backVerfBtn;
	private JLabel fnameFinishLbl;
	private JLabel lnameFinishLbl;
	private JLabel lblLastName_1;
	private JLabel cityFinishLbl;
	private JLabel lblCity_1;
	private JLabel streetFinishLbl;
	private JLabel lblStreet_1;
	private JLabel buildingNumberFinishLbl;
	private JLabel lblBuildingNumber_1;
	private JLabel lblAppartmentNumber;
	private JLabel appNumFinishLbl;
	public sqlDriver conec = new sqlDriver();
	public static String userPhoneNumber;
	private static  String numRegex = "[0-9]+";
	private static String lettersRegex = "[a-zA-Z]+";
	private JLabel emaillbl;
	private JLabel emailFinishLbl;
	private JLabel lblPhone;
	private JLabel phoneNumFinishLbl;
	public static JTextField phoneEntry;
	private JLabel imagePathLbl;
	private InputStream imageStream;
	private static JComboBox appratmentNumberComboBox;
	
	
	
	public static void handleLoginCheck() throws SQLException {

			
			
			sqlDriver driver = new sqlDriver();
			driver.connect();
			
			
				
			if(phoneEntry.getText().equals("")  == false && PasswordEntry.getText().equals("") == false ) {
				
				preStatment = con.prepareStatement("select phone,f_Name,l_Name,buildingID,admin from Resident where phone = ? and password = ?",
						ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
				
				preStatment.setString(1, phoneEntry.getText());
				preStatment.setString(2, PasswordEntry.getText());
			
				rs = preStatment.executeQuery();
				
				
				if(rs.first() == false) {
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("User Not Exist");
					alert.alertFrame.setVisible(true);
					return;
				}
				else {
					preStatment = con.prepareStatement("select phone,f_Name,l_Name,buildingID,admin from Resident where phone = ? and password = ?",
							ResultSet.TYPE_SCROLL_SENSITIVE, 
	                        ResultSet.CONCUR_UPDATABLE);
					
					preStatment.setString(1, phoneEntry.getText());
					preStatment.setString(2, PasswordEntry.getText());
				
					rs = preStatment.executeQuery();
					while(rs.next()) {
						
						buildingIDSQL = rs.getInt("buildingID");
						
							userName = rs.getString("f_Name")+" "+rs.getString("l_Name");
							admin = rs.getBoolean("admin");
							
							
							preStatment = con.prepareStatement("select count(receive) from Message where receive = ?");
							preStatment.setString(1, phoneEntry.getText());
							
							rs = preStatment.executeQuery(); 
							while(rs.next()) {
								
								num_of_msg += Integer.toString(rs.getInt(1)) + " Messages";
								
								
							}
							
							userPhoneNumber = phoneEntry.getText();
							//check if the user is Admin
							if(admin) {
								frmLoginPage.dispose();
								windowType = "Admin";
							}
							else {
								frmLoginPage.dispose();
								windowType = "User";
							}
							
						return;
						
						
					
				}
				
			}
				
				
				
			}	
				
			
			
			
			else {
				
				alertMsg alert = new alertMsg();
				alert.errordetlbl.setText("All Fields Must Be fill");
				alert.alertFrame.setVisible(true);
				
			}
			
		driver.disconnect();
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
	
		
		con =conec.connect();
		driver.connect();
		frmLoginPage = new JFrame();
		frmLoginPage.setLocationRelativeTo(null);
		frmLoginPage.getContentPane().setLocation(143, 0);
		JLabel loginLabel = new JLabel("Login");
		JLabel signUpLabel = new JLabel("Sign up");
		frmLoginPage.setUndecorated(true);
		frmLoginPage.getContentPane().setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		
		frmLoginPage.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/logo_transparent.png")).getImage());
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.getContentPane().setBackground(new Color(34, 36, 39));
		frmLoginPage.setBounds(100, 100, 668, 809);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		loginPanel = new JPanel();
		loginPanel.setVisible(true);
		
		
		signUpPanel = new JPanel();
		signUpPanel.setVisible(false);
		
		
		signUpPanel.setLayout(null);
		signUpPanel.setBackground(new Color(34, 36, 39));
		signUpPanel.setBounds(306, 130, 362, 677);
		frmLoginPage.getContentPane().add(signUpPanel);
		
		appratmentNumberComboBox = new JComboBox();
		appratmentNumberComboBox.setForeground(new Color(255, 255, 255));
		appratmentNumberComboBox.setUI(new BasicComboBoxUI());
		appratmentNumberComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		appratmentNumberComboBox.setFocusable(false);
		appratmentNumberComboBox.setFocusTraversalKeysEnabled(false);
		appratmentNumberComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		
		appratmentNumberComboBox.setAlignmentX(1.0f);
		appratmentNumberComboBox.setBounds(109, 396, 134, 22);
		appratmentNumberComboBox.setBackground(new Color(34, 36, 39));
		buildingInformationPanel = new JPanel();
		buildingInformationPanel.setBounds(10, 17, 340, 647);
		signUpPanel.add(buildingInformationPanel);
		buildingInformationPanel.setLayout(null);
		buildingInformationPanel.setBackground(new Color(34, 36, 39));
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(51, 204, 153));
		panel_4.setBounds(12, 75, 100, 4);
		buildingInformationPanel.add(panel_4);
		
		panel_5 = new JPanel();
		panel_5.setBackground(new Color(51, 204, 153));
		panel_5.setBounds(117, 75, 100, 4);
		buildingInformationPanel.add(panel_5);
		
		panel_6 = new JPanel();
		panel_6.setBackground(new Color(102, 0, 204));
		panel_6.setBounds(223, 75, 100, 4);
		buildingInformationPanel.add(panel_6);
		
		buildingInfoNextbrn = new JButton("Next");
		
		buildingInfoNextbrn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildingInformationPanel.setVisible(false);
				
				setFinishLblText();
				finishPanel.setVisible(true);
				
			}
		});
		buildingInfoNextbrn.setForeground(Color.WHITE);
		buildingInfoNextbrn.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		buildingInfoNextbrn.setFocusPainted(false);
		buildingInfoNextbrn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
		buildingInfoNextbrn.setBackground(new Color(102,0,153));
		buildingInfoNextbrn.setBounds(182, 593, 134, 41);
		buildingInformationPanel.add(buildingInfoNextbrn);
		
		lblBuildingInformation = new JLabel("Building Information");
		lblBuildingInformation.setToolTipText("");
		lblBuildingInformation.setForeground(Color.WHITE);
		lblBuildingInformation.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblBuildingInformation.setBackground(new Color(34, 36, 39));
		lblBuildingInformation.setBounds(58, 13, 242, 33);
		buildingInformationPanel.add(lblBuildingInformation);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(156, 116, 40, 25);
		buildingInformationPanel.add(lblCity);
		lblCity.setForeground(Color.WHITE);
		lblCity.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblCity.setBackground(new Color(34, 36, 39));
		
		cityComboBox = new JComboBox();
		cityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStreets(streetComboBox);
				getBuildingNumber(buildingNumberComboBox);
				getAppartmentNumber(appratmentNumberComboBox);
			}
		});
		cityComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				
			}
		});
		cityComboBox.setForeground(new Color(255, 255, 255));
		cityComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		cityComboBox.setBounds(77, 144, 198, 22);
		buildingInformationPanel.add(cityComboBox);
		cityComboBox.setFocusTraversalKeysEnabled(false);
		cityComboBox.setFocusable(false);
		cityComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		cityComboBox.setBackground(new Color(34, 36, 39));
		cityComboBox.setUI(new BasicComboBoxUI());
		
		lblStreet = new JLabel("Street");
		lblStreet.setBounds(146, 183, 60, 25);
		buildingInformationPanel.add(lblStreet);
		lblStreet.setForeground(Color.WHITE);
		lblStreet.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblStreet.setBackground(new Color(34, 36, 39));
		
		streetComboBox = new JComboBox();
		streetComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getBuildingNumber(buildingNumberComboBox);
				getAppartmentNumber(appratmentNumberComboBox);
			}
		});
		streetComboBox.setForeground(new Color(255, 255, 255));
		streetComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		streetComboBox.setBounds(77, 221, 198, 22);
		buildingInformationPanel.add(streetComboBox);
		streetComboBox.setFocusable(false);
		streetComboBox.setFocusTraversalKeysEnabled(false);
		streetComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		streetComboBox.setBackground(new Color(34, 36, 39));
		streetComboBox.setUI(new BasicComboBoxUI());
		
		lblBuildingNumber = new JLabel("Building Number");
		lblBuildingNumber.setBounds(100, 273, 153, 25);
		buildingInformationPanel.add(lblBuildingNumber);
		lblBuildingNumber.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBuildingNumber.setForeground(Color.WHITE);
		lblBuildingNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblBuildingNumber.setBackground(new Color(34, 36, 39));
		
		buildingNumberComboBox = new JComboBox();
		buildingNumberComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAppartmentNumber(appratmentNumberComboBox);
			}
		});
		buildingNumberComboBox.setForeground(new Color(255, 255, 255));
		buildingNumberComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		buildingNumberComboBox.setBounds(77, 311, 198, 22);
		buildingInformationPanel.add(buildingNumberComboBox);
		buildingNumberComboBox.setFocusTraversalKeysEnabled(false);
		buildingNumberComboBox.setFocusable(false);
		buildingNumberComboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buildingNumberComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		buildingNumberComboBox.setBackground(new Color(34, 36, 39));
		buildingNumberComboBox.setUI(new BasicComboBoxUI());
		
		buildingInfoBackbtn = new JButton("Back");
		buildingInfoBackbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buildingInformationPanel.setVisible(false);
				personalInformationPanel.setVisible(true);
			}
		});
		buildingInfoBackbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buildingInfoBackbtn.setBorder(new MatteBorder(4,4,4,4, (Color) new Color(102, 0, 153)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buildingInfoBackbtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
			}
		});
		buildingInfoBackbtn.setForeground(Color.WHITE);
		buildingInfoBackbtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		buildingInfoBackbtn.setFocusPainted(false);
		buildingInfoBackbtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
		buildingInfoBackbtn.setBackground(new Color(34,36,39));
		buildingInfoBackbtn.setBounds(24, 593, 134, 41);
		buildingInformationPanel.add(buildingInfoBackbtn);
		buildingInformationPanel.add(appratmentNumberComboBox);
		
		JLabel lblAppartmentNumber_1 = new JLabel("Appartment Number");
		lblAppartmentNumber_1.setForeground(Color.WHITE);
		lblAppartmentNumber_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblAppartmentNumber_1.setBackground(new Color(34, 36, 39));
		lblAppartmentNumber_1.setAlignmentX(1.0f);
		lblAppartmentNumber_1.setBounds(83, 357, 186, 25);
		buildingInformationPanel.add(lblAppartmentNumber_1);
		buildingInformationPanel.setVisible(false);
		
		finishPanel = new JPanel();
		finishPanel.setBounds(10, 17, 340, 647);
		signUpPanel.add(finishPanel);
		finishPanel.setLayout(null);
		finishPanel.setBackground(new Color(34, 36, 39));
		
		panel_7 = new JPanel();
		panel_7.setBackground(new Color(51, 204, 153));
		panel_7.setBounds(12, 75, 100, 4);
		finishPanel.add(panel_7);
		
		panel_8 = new JPanel();
		panel_8.setBackground(new Color(51, 204, 153));
		panel_8.setBounds(117, 75, 100, 4);
		finishPanel.add(panel_8);
		
		panel_9 = new JPanel();
		panel_9.setBackground(new Color(51, 204, 153));
		panel_9.setBounds(223, 75, 100, 4);
		finishPanel.add(panel_9);
		
		signUpBtn = new JButton("Sign Up");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				signUpHandle();
			}
		});
		signUpBtn.setForeground(Color.WHITE);
		signUpBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		signUpBtn.setFocusPainted(false);
		signUpBtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
		signUpBtn.setBackground(new Color(102, 0, 153));
		signUpBtn.setBounds(182, 593, 134, 41);
		finishPanel.add(signUpBtn);
		
		lblVerifyYourDetails = new JLabel("Verify Your Details");
		lblVerifyYourDetails.setToolTipText("");
		lblVerifyYourDetails.setForeground(Color.WHITE);
		lblVerifyYourDetails.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblVerifyYourDetails.setBackground(new Color(34, 36, 39));
		lblVerifyYourDetails.setBounds(59, 13, 217, 33);
		finishPanel.add(lblVerifyYourDetails);
		
		lblFirstName_1 = new JLabel("First Name: ");
		lblFirstName_1.setForeground(Color.WHITE);
		lblFirstName_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblFirstName_1.setBackground(new Color(34, 36, 39));
		lblFirstName_1.setBounds(12, 120, 100, 25);
		finishPanel.add(lblFirstName_1);
		
		backVerfBtn = new JButton("Back");
		backVerfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishPanel.setVisible(false);
				buildingInformationPanel.setVisible(true);
			}
		});
		backVerfBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backVerfBtn.setBorder(new MatteBorder(4,4,4,4, (Color) new Color(102, 0, 153)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backVerfBtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
			}
		});
		backVerfBtn.setForeground(Color.WHITE);
		backVerfBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		backVerfBtn.setFocusPainted(false);
		backVerfBtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
		backVerfBtn.setBackground(new Color(34,36,39));
		backVerfBtn.setBounds(24, 593, 134, 41);
		finishPanel.add(backVerfBtn);
		
		fnameFinishLbl = new JLabel("");
		fnameFinishLbl.setForeground(Color.WHITE);
		fnameFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		fnameFinishLbl.setBackground(new Color(34, 36, 39));
		fnameFinishLbl.setBounds(117, 120, 199, 25);
		finishPanel.add(fnameFinishLbl);
		
		lnameFinishLbl = new JLabel("");
		lnameFinishLbl.setForeground(Color.WHITE);
		lnameFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lnameFinishLbl.setBackground(new Color(34, 36, 39));
		lnameFinishLbl.setBounds(117, 169, 199, 25);
		finishPanel.add(lnameFinishLbl);
		
		lblLastName_1 = new JLabel("Last Name: ");
		lblLastName_1.setForeground(Color.WHITE);
		lblLastName_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblLastName_1.setBackground(new Color(34, 36, 39));
		lblLastName_1.setBounds(12, 169, 100, 25);
		finishPanel.add(lblLastName_1);
		
		cityFinishLbl = new JLabel("");
		cityFinishLbl.setForeground(Color.WHITE);
		cityFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		cityFinishLbl.setBackground(new Color(34, 36, 39));
		cityFinishLbl.setBounds(55, 302, 199, 25);
		finishPanel.add(cityFinishLbl);
		
		lblCity_1 = new JLabel("City: ");
		lblCity_1.setForeground(Color.WHITE);
		lblCity_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblCity_1.setBackground(new Color(34, 36, 39));
		lblCity_1.setBounds(12, 302, 41, 25);
		finishPanel.add(lblCity_1);
		
		streetFinishLbl = new JLabel("");
		streetFinishLbl.setForeground(Color.WHITE);
		streetFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		streetFinishLbl.setBackground(new Color(34, 36, 39));
		streetFinishLbl.setBounds(75, 340, 199, 25);
		finishPanel.add(streetFinishLbl);
		
		lblStreet_1 = new JLabel("Street: ");
		lblStreet_1.setForeground(Color.WHITE);
		lblStreet_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblStreet_1.setBackground(new Color(34, 36, 39));
		lblStreet_1.setBounds(12, 340, 69, 25);
		finishPanel.add(lblStreet_1);
		
		buildingNumberFinishLbl = new JLabel("");
		buildingNumberFinishLbl.setForeground(Color.WHITE);
		buildingNumberFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		buildingNumberFinishLbl.setBackground(new Color(34, 36, 39));
		buildingNumberFinishLbl.setBounds(167, 392, 100, 25);
		finishPanel.add(buildingNumberFinishLbl);
		
		lblBuildingNumber_1 = new JLabel("Building Number: ");
		lblBuildingNumber_1.setForeground(Color.WHITE);
		lblBuildingNumber_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblBuildingNumber_1.setBackground(new Color(34, 36, 39));
		lblBuildingNumber_1.setBounds(12, 392, 160, 25);
		finishPanel.add(lblBuildingNumber_1);
		
		lblAppartmentNumber = new JLabel("Appartment Number: ");
		lblAppartmentNumber.setForeground(Color.WHITE);
		lblAppartmentNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblAppartmentNumber.setBackground(new Color(34, 36, 39));
		lblAppartmentNumber.setBounds(12, 445, 184, 25);
		finishPanel.add(lblAppartmentNumber);
		
		appNumFinishLbl = new JLabel("");
		appNumFinishLbl.setForeground(Color.WHITE);
		appNumFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		appNumFinishLbl.setBackground(new Color(34, 36, 39));
		appNumFinishLbl.setBounds(195, 445, 100, 25);
		finishPanel.add(appNumFinishLbl);
		
		emaillbl = new JLabel("Email:");
		emaillbl.setForeground(Color.WHITE);
		emaillbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		emaillbl.setBackground(new Color(34, 36, 39));
		emaillbl.setBounds(12, 212, 69, 25);
		finishPanel.add(emaillbl);
		
		emailFinishLbl = new JLabel("");
		emailFinishLbl.setForeground(Color.WHITE);
		emailFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		emailFinishLbl.setBackground(new Color(34, 36, 39));
		emailFinishLbl.setBounds(75, 212, 199, 25);
		finishPanel.add(emailFinishLbl);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPhone.setBackground(new Color(34, 36, 39));
		lblPhone.setBounds(12, 260, 69, 25);
		finishPanel.add(lblPhone);
		
		phoneNumFinishLbl = new JLabel("");
		phoneNumFinishLbl.setForeground(Color.WHITE);
		phoneNumFinishLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		phoneNumFinishLbl.setBackground(new Color(34, 36, 39));
		phoneNumFinishLbl.setBounds(90, 260, 199, 25);
		finishPanel.add(phoneNumFinishLbl);
		finishPanel.setVisible(false);
		
		personalInformationPanel = new JPanel();
		personalInformationPanel.setBackground(new Color(34,36,39));
		personalInformationPanel.setBounds(10, 17, 340, 660);
		signUpPanel.add(personalInformationPanel);
		personalInformationPanel.setLayout(null);
		
		stage1Indicator = new JPanel();
		stage1Indicator.setBounds(12, 75, 100, 4);
		personalInformationPanel.add(stage1Indicator);
		stage1Indicator.setBackground(new Color(51, 204, 153));
		
		stage2Indicator = new JPanel();
		stage2Indicator.setBounds(117, 75, 100, 4);
		personalInformationPanel.add(stage2Indicator);
		stage2Indicator.setBackground(new Color(102, 0, 204));
		
		stage3Indicator = new JPanel();
		stage3Indicator.setBounds(223, 75, 100, 4);
		personalInformationPanel.add(stage3Indicator);
		stage3Indicator.setBackground(new Color(102, 0, 204));
		
		label_5 = new JLabel("Phone Number");
		label_5.setBounds(116, 359, 129, 25);
		personalInformationPanel.add(label_5);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_5.setBackground(new Color(34, 36, 39));
		
		signUpPhoneTextField = new JTextField();
		signUpPhoneTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleOnlyDigitsCheck(signUpPhoneTextField);
			}
		});
		signUpPhoneTextField.setBounds(81, 391, 198, 31);
		personalInformationPanel.add(signUpPhoneTextField);
		signUpPhoneTextField.setCaretColor(new Color(123, 104, 238));
		signUpPhoneTextField.setToolTipText("");
		signUpPhoneTextField.setSelectionColor(new Color(102, 51, 255));
		signUpPhoneTextField.setForeground(Color.WHITE);
		signUpPhoneTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		signUpPhoneTextField.setColumns(10);
		signUpPhoneTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		signUpPhoneTextField.setBackground(new Color(34, 36, 39));
		signUpPhoneTextField.setAlignmentX(1.0f);
		
		lblPaswword = new JLabel("Password");
		lblPaswword.setBounds(140, 437, 80, 25);
		personalInformationPanel.add(lblPaswword);
		lblPaswword.setForeground(Color.WHITE);
		lblPaswword.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPaswword.setBackground(new Color(34, 36, 39));
		
		signUpPasswordTextField = new JPasswordField();
		signUpPasswordTextField.setBounds(81, 475, 198, 31);
		personalInformationPanel.add(signUpPasswordTextField);
		signUpPasswordTextField.setForeground(new Color(255, 255, 255));
		signUpPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		signUpPasswordTextField.setSelectionColor(new Color(147, 112, 219));
		signUpPasswordTextField.setCaretColor(new Color(147, 112, 219));
		signUpPasswordTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		signUpPasswordTextField.setBackground(new Color(34, 36, 39));
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(158, 521, 44, 25);
		personalInformationPanel.add(lblEmail);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblEmail.setBackground(new Color(34, 36, 39));
		
		signUpEmailTextField = new JTextField();
		signUpEmailTextField.setBounds(81, 551, 198, 31);
		personalInformationPanel.add(signUpEmailTextField);
		signUpEmailTextField.setCaretColor(new Color(147, 112, 219));
		signUpEmailTextField.setToolTipText("");
		signUpEmailTextField.setSelectionColor(new Color(102, 51, 255));
		signUpEmailTextField.setForeground(Color.WHITE);
		signUpEmailTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		signUpEmailTextField.setColumns(10);
		signUpEmailTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		signUpEmailTextField.setBackground(new Color(34, 36, 39));
		signUpEmailTextField.setAlignmentX(1.0f);
		
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
		firstNameEntry.setBounds(81, 238, 198, 31);
		personalInformationPanel.add(firstNameEntry);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblFirstName.setBackground(new Color(34, 36, 39));
		lblFirstName.setBounds(134, 208, 92, 25);
		personalInformationPanel.add(lblFirstName);
		
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
		lastNameEntry.setBounds(81, 312, 198, 31);
		personalInformationPanel.add(lastNameEntry);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblLastName.setBackground(new Color(34, 36, 39));
		lblLastName.setBounds(135, 282, 91, 25);
		personalInformationPanel.add(lblLastName);
		
		nextBtnPersonalInf = new JButton("Next");
		nextBtnPersonalInf.setBounds(102, 606, 156, 41);
		personalInformationPanel.add(nextBtnPersonalInf);
		nextBtnPersonalInf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(firstNameEntry.getText().equals("") || lastNameEntry.getText().equals("") || signUpPhoneTextField.getText().equals("") ||
						signUpEmailTextField.getText().equals("")|| signUpPasswordTextField.getText().equals("")|| signUpPasswordTextField.getText().equals(" "))
				{
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("All Fields must be Filled");
					alert.alertFrame.setVisible(true);
				}
				
				
				else if(isEmailValid(signUpEmailTextField)== false ) {
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("Email Is Not Valid");
					alert.alertFrame.setVisible(true);
				}
				
				
				else if(signUpPhoneTextField.getText().length() < 10) {
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("Phone Number Must Be 10 Digits");
					alert.alertFrame.setVisible(true);
				}
				
				
				else if(checkPhoneNumberSQL(signUpPhoneTextField) == false) {
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("This Phone Number is already Exist");
					alert.alertFrame.setVisible(true);
				}
				
				else {
					personalInformationPanel.setVisible(false);
					buildingInformationPanel.setVisible(true);
					getCities(cityComboBox);
					getStreets(streetComboBox);
					getBuildingNumber(buildingNumberComboBox);
					getAppartmentNumber(appratmentNumberComboBox);
				}
			}
		});
		nextBtnPersonalInf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				nextBtnPersonalInf.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(102, 0, 153)));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				nextBtnPersonalInf.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
				
			}
		
		});
		nextBtnPersonalInf.setForeground(Color.WHITE);
		nextBtnPersonalInf.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		nextBtnPersonalInf.setFocusPainted(false);
		nextBtnPersonalInf.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
		nextBtnPersonalInf.setBackground(new Color(34,36,39));
		
		lblSign = new JLabel("Personal Information");
		lblSign.setBounds(58, 13, 242, 33);
		personalInformationPanel.add(lblSign);
		lblSign.setToolTipText("");
		lblSign.setForeground(Color.WHITE);
		lblSign.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblSign.setBackground(new Color(34, 36, 39));
		
		JFileChooser profileImgFileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
		profileImgFileChooser.setFileFilter(filter);
		
		
		JLabel uploadImageLbl = new JLabel("");
		uploadImageLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				profileImgFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = profileImgFileChooser.showOpenDialog(personalInformationPanel);
				if(result == JFileChooser.APPROVE_OPTION ) {
					try {
						imageStream = new FileInputStream(profileImgFileChooser.getSelectedFile());
						Thread.sleep(300);
						uploadImageLbl.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/uploadSuccess.png")));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
	
		uploadImageLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uploadImageLbl.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/uploadPicutre.png")));
		uploadImageLbl.setBounds(150, 141, 54, 54);
		personalInformationPanel.add(uploadImageLbl);
		
		imagePathLbl = new JLabel("Profile Picutre(Optional)");
		imagePathLbl.setForeground(Color.WHITE);
		imagePathLbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		imagePathLbl.setBackground(new Color(34, 36, 39));
		imagePathLbl.setBounds(77, 103, 207, 25);
		personalInformationPanel.add(imagePathLbl);
		
		
		
		
		personalInformationPanel.setVisible(true);
		
		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(109, 41, 128, 128);
		signUpPanel.add(label_2);
		
		
		
		loginPanel.setBounds(306, 130, 362, 714);
		frmLoginPage.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.setBackground(new Color(34, 36, 39));
		label = new JLabel("");
		label.setBounds(108, 79, 128, 128);
		loginPanel.add(label);
		label.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/user-login.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblLogin = new JLabel("Login");
		lblLogin.setBounds(152, 214, 73, 33);
		loginPanel.add(lblLogin);
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblLogin.setBackground(new Color(34, 36, 39));
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 260, 310, 2);
		loginPanel.add(panel);
		panel.setBackground(new Color(102, 0, 204));
		
		lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(126, 326, 143, 25);
		loginPanel.add(lblPhoneNumber);
		lblPhoneNumber.setBackground(new Color(34, 36, 39));
		lblPhoneNumber.setForeground(new Color(255, 255, 255));
		lblPhoneNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(132, 420, 112, 25);
		loginPanel.add(lblPassword);
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPassword.setBackground(Color.WHITE);
		
		PasswordEntry = new JTextField();
		PasswordEntry.setCaretColor(new Color(123, 104, 238));
		PasswordEntry.setBounds(88, 442, 198, 31);
		loginPanel.add(PasswordEntry);
		PasswordEntry.setForeground(new Color(255, 255, 255));
		PasswordEntry.setAlignmentX(Component.RIGHT_ALIGNMENT);
		PasswordEntry.setSelectionColor(new Color(102, 51, 255));
		PasswordEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		PasswordEntry.setBackground(new Color(34, 36, 39));
		PasswordEntry.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(51, 204, 153)));
		PasswordEntry.setColumns(10);
		
		
		
		loginButton = new JButton("Login");
		loginButton.setBounds(88, 527, 198, 41);
		loginPanel.add(loginButton);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					handleLoginCheck();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				btnLogin.setBackground(new Color(46,139,87));
				loginButton.setBackground(new Color(51, 204, 153));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(new Color(34,36,39));
				
			}
		
		});
		
		loginButton.setFocusPainted(false);
		loginButton.setBackground(new Color(34, 36, 39));
		loginButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		loginButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(127, 255, 212)));
		
		phoneEntry = new JTextField();
		phoneEntry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				handleOnlyDigitsCheck(phoneEntry);
			}
		});
		phoneEntry.setSelectionColor(new Color(102, 51, 255));
		phoneEntry.setForeground(Color.WHITE);
		phoneEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		phoneEntry.setColumns(10);
		phoneEntry.setCaretColor(new Color(123, 104, 238));
		phoneEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		phoneEntry.setBackground(new Color(34, 36, 39));
		phoneEntry.setAlignmentX(1.0f);
		phoneEntry.setBounds(88, 353, 198, 31);
		loginPanel.add(phoneEntry);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51,53,56));
		panel_1.setBounds(0, 0, 305, 807);
		frmLoginPage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(22, 145, 271, 280);
		panel_1.add(label_3);
		label_3.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/logo_transparent.png")));
		
		JLabel lblLoginPage = new JLabel("LiveApp");
		lblLoginPage.setBounds(109, 343, 108, 34);
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
		lblWeWillHelp.setBounds(22, 408, 271, 27);
		panel_1.add(lblWeWillHelp);
		
		lblNewLabel = new JLabel("your Buidling Like it supposed ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setVerifyInputWhenFocusTarget(false);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(53, 438, 217, 27);
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
		lblToBeIn.setBounds(22, 477, 271, 16);
		panel_1.add(lblToBeIn);
		
		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		label_1.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/minimize.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setAlignmentX(1.0f);
		label_1.setBounds(586, 17, 24, 24);
		frmLoginPage.getContentPane().add(label_1);
		
		
		signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signUpLabel.setForeground(Color.WHITE);
		signUpLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		signUpLabel.setBounds(386, 65, 83, 42);
		signUpLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				loginPanel.setVisible(false);
				signUpPanel.setVisible(true);
				signUpLabel.setForeground(new Color(51, 204, 153));
				loginLabel.setForeground(new Color(255, 255, 255));
			}
		});
		frmLoginPage.getContentPane().add(signUpLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(480, 75, 4, 30);
		frmLoginPage.getContentPane().add(panel_2);
		panel_2.setBackground(new Color(102, 0, 204));
		
		loginLabel.setForeground(new Color(51, 204, 153));
		loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		loginLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		loginLabel.setBounds(500, 65, 59, 42);
		loginLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				signUpPanel.setVisible(false);
				loginPanel.setVisible(true);
				signUpLabel.setForeground(new Color(255, 255, 255));
				loginLabel.setForeground(new Color(51, 204, 153));
			}
		});
		frmLoginPage.getContentPane().add(loginLabel);
		frmLoginPage.setLocationRelativeTo(null);
		
	
		
	}







	protected void getAppartmentNumber(JComboBox appartmentComboBox) {
		ArrayList<String> avaAppr = new ArrayList<String>();
		
		
		try {
			preStatment = con.prepareStatement("select numberOfAppartments,buildingID from Building where city = ? and streetName = ? and buildingNumber = ?");
			preStatment.setString(1, cityComboBox.getSelectedItem().toString());
			preStatment.setString(2, streetComboBox.getSelectedItem().toString());
			preStatment.setString(3, buildingNumberComboBox.getSelectedItem().toString());
			
			rs = preStatment.executeQuery();
			int numogap = 0;
			int buildID =  0;
			while(rs.next()) {
				numogap = rs.getInt(1);
				buildID = rs.getInt(2);
			}
			
			
			for(int i = 1; i<= numogap ; i++ ) {
				avaAppr.add(Integer.toString(i));
			}
			
			
			preStatment = con.prepareStatement("select appartmentNum from Resident where buildingID = ?");
			preStatment.setInt(1, buildID);

			
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				avaAppr.remove(Integer.toString(rs.getInt(1)));
			}
			
			
			appartmentComboBox.setModel(new DefaultComboBoxModel(avaAppr.toArray(new String[0])));
			appartmentComboBox.setSelectedIndex(0);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}







	protected void signUpHandle() {
		
		
		
		try {
			preStatment = con.prepareStatement("select buildingID from Building where city = ? and streetName = ?");
					
			preStatment.setString(1, cityComboBox.getSelectedItem().toString());
			preStatment.setString(2, streetComboBox.getSelectedItem().toString());
			
			rs = preStatment.executeQuery();
			int BuildingID = 0;
			while(rs.next()) {
				BuildingID = rs.getInt(1);
			}
			if(imageStream != null) {
				
				preStatment = con.prepareStatement("insert into Resident(f_Name,l_Name,email,phone,buildingID,password,image)"
						+ " values(?,?,?,?,?,?,?)");
				preStatment.setString(1, firstNameEntry.getText());
				preStatment.setString(2, lastNameEntry.getText());
				preStatment.setString(3,signUpEmailTextField.getText());
				preStatment.setString(4,signUpPhoneTextField.getText());			
				preStatment.setInt(5,BuildingID);
				preStatment.setString(6,signUpPasswordTextField.getText());
				preStatment.setBlob(7,imageStream);
			}
			
			else {	
				preStatment = con.prepareStatement("insert into Resident(f_Name,l_Name,email,phone,buildingID,password,appartmentNum)"
						+ " values(?,?,?,?,?,?,?)");
				preStatment.setString(1, firstNameEntry.getText());
				preStatment.setString(2, lastNameEntry.getText());
				preStatment.setString(3,signUpEmailTextField.getText());
				preStatment.setString(4,signUpPhoneTextField.getText());			
				preStatment.setInt(5,BuildingID);
				preStatment.setString(6,signUpPasswordTextField.getText());
				preStatment.setInt(7, Integer.parseInt(appratmentNumberComboBox.getSelectedItem().toString()));
			}
		
			preStatment.executeUpdate();
			
			
			
			
			
			preStatment = con.prepareStatement("select phone,f_Name,l_Name,buildingID from Resident where phone = ?");
			
			preStatment.setString(1, signUpPhoneTextField.getText());
			
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				buildingIDSQL = rs.getInt("buildingID");
				
					userName = userName = firstNameEntry.getText() + " " + lastNameEntry.getText();
					
					preStatment = con.prepareStatement("select count(receive) from Message where receive =?");
					preStatment.setString(1, signUpPhoneTextField.getText());
					rs = preStatment.executeQuery();
					while(rs.next()) {
						
						num_of_msg += Integer.toString(rs.getInt(1)) + " Messages";
						
						
					}
					frmLoginPage.dispose();
					windowType = "User";
					PasswordEntry.setText(signUpPasswordTextField.getText());
					userPhoneNumber = signUpPhoneTextField.getText();
					return;
			
				
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}







	protected void setFinishLblText() {
		fnameFinishLbl.setText(firstNameEntry.getText());
		
		lnameFinishLbl.setText(lastNameEntry.getText());
		
		emailFinishLbl.setText(signUpEmailTextField.getText());
		
		
		
		phoneNumFinishLbl.setText(signUpPhoneTextField.getText());
		
		
		
		cityFinishLbl.setText(cityComboBox.getSelectedItem().toString());
		
		
		streetFinishLbl.setText(streetComboBox.getSelectedItem().toString());
		
		buildingNumberFinishLbl.setText(buildingNumberComboBox.getSelectedItem().toString());
		
		appNumFinishLbl.setText(appratmentNumberComboBox.getSelectedItem().toString());
	}







	protected void getBuildingNumber(JComboBox buildingNumberComboBox) {
		
		List<String> buildingNumbers = new ArrayList<String>();
		
		try {
			preStatment = con.prepareStatement("select buildingNumber from Building where city = ? and streetName = ?");
			preStatment.setString(1,cityComboBox.getSelectedItem().toString());
			preStatment.setString(2,streetComboBox.getSelectedItem().toString());
			rs = preStatment.executeQuery();
			
			
			while(rs.next()) {
				buildingNumbers.add( Integer.toString(rs.getInt("buildingNumber")));
				
			}
			
		
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		buildingNumberComboBox.setModel(new DefaultComboBoxModel(buildingNumbers.toArray(new String[0])));
		
		
	}







	protected boolean checkPhoneNumberSQL(JTextField phoneTextField) {
		
		
		
				
				
			
			
				rs = driver.sendQuery("select phone from Resident where phone = "+phoneTextField.getText());
				String phoneNumber = "";
				try {
					while(rs.next()) {
						phoneNumber = rs.getString("phone");
					}
					
					if(phoneNumber.length() < 10) {
						
						driver.disconnect();
						return true;
					}
					
					
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				driver.disconnect();
				return false;
				
		
				
			
			
			
			
			
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
	
	
	protected void getCities(JComboBox comboBox) {
		
		rs = driver.sendQuery("select * from Cities" );
		List<String> cities = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				cities.add(rs.getString(1));
				
			}
			
		
			driver.disconnect();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		cityComboBox.setModel(new DefaultComboBoxModel(cities.toArray(new String[0])));
		
	}
	
	protected void getStreets(JComboBox comboBox) {
		
		
		
		
		List<String> streets = new ArrayList<String>();
		
		try {
			preStatment = con.prepareStatement("select * from Streets where city = ?");
			preStatment.setString(1,cityComboBox.getSelectedItem().toString());
			rs = preStatment.executeQuery();
			
			
			while(rs.next()) {
				streets.add(rs.getString("streetName"));
				
			}
			
		
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		streetComboBox.setModel(new DefaultComboBoxModel(streets.toArray(new String[0])));
	}
}
