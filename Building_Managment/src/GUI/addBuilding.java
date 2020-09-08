package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Enums.Cities;
import Enums.Defect_Status;
import Enums.professionalField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class addBuilding {

	static public JFrame addBuildingFrame;
	static JButton btnOpenDefect;
	static JComboBox cityCombo;
	private static ResultSet rs;
	//aSDSADAS
	private static PreparedStatement preStatment;
	static String databaseURL 
	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 
	private static Connection con = Login_Page.con; 
	static int buildingIDSQL = Login_Page.buildingIDSQL;
	static String userPhone = Login_Page.getPhoneEntry().getText();
	private JLabel lblBuildingNumber;
	private JLabel lblAddABuilding;
	private JTextField textFieldStreet;
	private JTextField textFieldBuildingNum;
	private JLabel lblSumApartments;
	private JTextField textFieldAmountAppart;
	private JLabel lblPhone;
	private JTextField textFieldPhone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addBuilding window = new addBuilding();
					window.addBuildingFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public addBuilding() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addBuildingFrame = new JFrame();
		addBuildingFrame.getContentPane().setBackground(new Color(192,192,192));
		addBuildingFrame.setTitle("Add building");
		addBuildingFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		addBuildingFrame.setBounds(100, 100, 556, 477);
		addBuildingFrame.setDefaultCloseOperation(this.addBuildingFrame.DISPOSE_ON_CLOSE);
		addBuildingFrame.getContentPane().setLayout(null);
		
		JLabel lblStrreet = new JLabel("Street:");
		lblStrreet.setForeground(Color.BLACK);
		lblStrreet.setHorizontalAlignment(SwingConstants.CENTER);
		lblStrreet.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblStrreet.setBounds(22, 139, 120, 37);
		addBuildingFrame.getContentPane().add(lblStrreet);
		
		btnOpenDefect = new JButton("ADD");
		btnOpenDefect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldStreet.getText().equals("") || (textFieldPhone.getText().equals("") || 
						textFieldBuildingNum.getText().equals("") || textFieldAmountAppart.getText().equals(""))){
					alertBuildingAdd alert = new alertBuildingAdd();
					alert.buildingAlertFrm.setVisible(true);
				}
				else {
					try {
						preStatment = con.prepareStatement("insert into Building (city,streetName,bulidingNumber,numberOfAppartments,phone)"
								+ "values(?,?,?,?,?)");
						
						preStatment.setString(1,cityCombo.getSelectedItem().toString());
						preStatment.setString(2,textFieldStreet.getText());
						preStatment.setInt(3,Integer.parseInt(textFieldBuildingNum.getText()));
						preStatment.setInt(4,Integer.parseInt(textFieldAmountAppart.getText()));
						preStatment.setString(5,textFieldPhone.getText());
						preStatment.executeUpdate();
						alertBuildingAdd alert = new alertBuildingAdd();
						AddBuildingMsg bulidingmsg = new AddBuildingMsg();
						bulidingmsg.alertFrame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
					
			});
		
		btnOpenDefect.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/addIcon.png")));
		btnOpenDefect.addMouseListener(new MouseAdapter() {
	    	   @Override
				public void mouseEntered(MouseEvent e) {
	    		   btnOpenDefect.setBackground(new Color(169,100,0));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btnOpenDefect.setBackground(new Color(255,140, 0));
				}
	       });
		btnOpenDefect.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnOpenDefect.setForeground(Color.BLACK);
		btnOpenDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnOpenDefect.setFocusPainted(false);
		btnOpenDefect.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnOpenDefect.setBackground(new Color(255, 140, 0));
		btnOpenDefect.setBounds(408, 378, 118, 39);
		addBuildingFrame.getContentPane().add(btnOpenDefect);
		
		lblBuildingNumber = new JLabel("Building Nmber:");
		lblBuildingNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuildingNumber.setForeground(Color.BLACK);
		lblBuildingNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblBuildingNumber.setBounds(32, 189, 120, 37);
		addBuildingFrame.getContentPane().add(lblBuildingNumber);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setForeground(Color.BLACK);
		lblCity.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblCity.setBounds(22, 95, 120, 37);
		addBuildingFrame.getContentPane().add(lblCity);
		
		cityCombo = new JComboBox();
		cityCombo.setModel(new DefaultComboBoxModel(Cities.values()));
		cityCombo.setRequestFocusEnabled(false);
		cityCombo.setLightWeightPopupEnabled(false);
		cityCombo.setForeground(Color.BLACK);
		cityCombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		cityCombo.setFocusable(false);
		cityCombo.setFocusTraversalKeysEnabled(false);
		cityCombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cityCombo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 140, 0)));
		cityCombo.setBackground(Color.WHITE);
		cityCombo.setBounds(221, 95, 136, 37);
		addBuildingFrame.getContentPane().add(cityCombo);
		
		lblAddABuilding = new JLabel("Add A Building:");
		lblAddABuilding.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddABuilding.setForeground(Color.BLACK);
		lblAddABuilding.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblAddABuilding.setBounds(205, 25, 120, 37);
		addBuildingFrame.getContentPane().add(lblAddABuilding);
		
		textFieldStreet = new JTextField();
		textFieldStreet.setBounds(221, 141, 136, 37);
		addBuildingFrame.getContentPane().add(textFieldStreet);
		textFieldStreet.setColumns(10);
		
		textFieldBuildingNum = new JTextField();
		textFieldBuildingNum.setColumns(10);
		textFieldBuildingNum.setBounds(221, 191, 136, 37);
		addBuildingFrame.getContentPane().add(textFieldBuildingNum);
		
		lblSumApartments = new JLabel("Amount Of Appartment:");
		lblSumApartments.setHorizontalAlignment(SwingConstants.LEFT);
		lblSumApartments.setForeground(Color.BLACK);
		lblSumApartments.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblSumApartments.setBounds(22, 239, 178, 37);
		addBuildingFrame.getContentPane().add(lblSumApartments);
		
		textFieldAmountAppart = new JTextField();
		textFieldAmountAppart.setColumns(10);
		textFieldAmountAppart.setBounds(221, 241, 136, 37);
		addBuildingFrame.getContentPane().add(textFieldAmountAppart);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblPhone.setBounds(22, 289, 120, 37);
		addBuildingFrame.getContentPane().add(lblPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(221, 289, 136, 37);
		addBuildingFrame.getContentPane().add(textFieldPhone);
	}
}
