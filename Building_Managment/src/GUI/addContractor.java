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
import Enums.professionalField;
import Enums.Defect_Status;
import Enums.professionalField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class addContractor {

	static public JFrame addContractorFrame;
	static JButton btnAddContractor;
	static JComboBox cityCombo;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	static String databaseURL 
	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 
	private static Connection con = Login_Page.con; 
	static int buildingIDSQL = Login_Page.buildingIDSQL;
	static String userPhone = Login_Page.getPhoneEntry().getText();
	private JLabel lblLastName;
	private JLabel lblAddAContractor;
	private JTextField textFieldFirstName;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblPhone;
	private JTextField textFieldPhone;
	private JLabel labelBusinessNum;
	private JTextField textFieldBusinessNum;
	private JLabel labelField;
	private JComboBox FieldCombo;
	private JTextField textFieldLastName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addContractor window = new addContractor();
					window.addContractorFrame.setVisible(true);
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
	public addContractor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addContractorFrame = new JFrame();
		addContractorFrame.getContentPane().setBackground(new Color(192,192,192));
		addContractorFrame.setTitle("Add contractor");
		addContractorFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		addContractorFrame.setBounds(100, 100, 556, 477);
		addContractorFrame.setDefaultCloseOperation(this.addContractorFrame.DISPOSE_ON_CLOSE);
		addContractorFrame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(Color.BLACK);
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblFirstName.setBounds(32, 60, 120, 37);
		addContractorFrame.getContentPane().add(lblFirstName);
		
		btnAddContractor = new JButton("ADD");
		btnAddContractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldFirstName.getText().equals("") || textFieldLastName.getText().equals("") || 
						textFieldEmail.getText().equals("") || textFieldPhone.getText().equals("")
						|| textFieldBusinessNum.getText().equals("")){
					alertContractorAdd alert = new alertContractorAdd();
					alert.ContractorAlertFrm.setVisible(true);
					System.out.println(textFieldFirstName.getText());
					System.out.println(textFieldLastName.getText());
					System.out.println(textFieldBusinessNum.getText());
					System.out.println(textFieldEmail.getText());
					System.out.println(textFieldPhone.getText());
				}
				else {
					try {
						preStatment = con.prepareStatement("insert into Contractor (f_Name,l_Name,email,phone,businessNumber,field,city)"
								+ "values(?,?,?,?,?,?,?)");
						
						preStatment.setString(1,textFieldFirstName.getText());
						preStatment.setString(2,textFieldLastName.getText());
						preStatment.setString(3,(textFieldEmail.getText()));
						preStatment.setString(4,(textFieldPhone.getText()));
						preStatment.setString(5,textFieldBusinessNum.getText());
						preStatment.setString(6,FieldCombo.getSelectedItem().toString());
						preStatment.setString(7,cityCombo.getSelectedItem().toString());
						preStatment.executeUpdate();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
					
			});
		
		btnAddContractor.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/addIcon.png")));
		btnAddContractor.addMouseListener(new MouseAdapter() {
	    	   @Override
				public void mouseEntered(MouseEvent e) {
	    		   btnAddContractor.setBackground(new Color(169,100,0));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btnAddContractor.setBackground(new Color(255,140, 0));
				}
	       });
		btnAddContractor.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAddContractor.setForeground(Color.BLACK);
		btnAddContractor.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnAddContractor.setFocusPainted(false);
		btnAddContractor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAddContractor.setBackground(new Color(255, 140, 0));
		btnAddContractor.setBounds(408, 378, 118, 39);
		addContractorFrame.getContentPane().add(btnAddContractor);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblLastName.setBounds(32, 110, 120, 37);
		addContractorFrame.getContentPane().add(lblLastName);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setForeground(Color.BLACK);
		lblCity.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblCity.setBounds(22, 361, 120, 37);
		addContractorFrame.getContentPane().add(lblCity);
		
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
		cityCombo.setBounds(221, 361, 136, 37);
		addContractorFrame.getContentPane().add(cityCombo);
		
		lblAddAContractor = new JLabel("Add A Contractor:");
		lblAddAContractor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAContractor.setForeground(Color.BLACK);
		lblAddAContractor.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblAddAContractor.setBounds(205, 13, 152, 37);
		addContractorFrame.getContentPane().add(lblAddAContractor);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(221, 62, 136, 37);
		addContractorFrame.getContentPane().add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblEmail.setBounds(22, 160, 120, 37);
		addContractorFrame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(221, 162, 136, 37);
		addContractorFrame.getContentPane().add(textFieldEmail);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblPhone.setBounds(22, 210, 120, 37);
		addContractorFrame.getContentPane().add(lblPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(221, 210, 136, 37);
		addContractorFrame.getContentPane().add(textFieldPhone);
		
		labelBusinessNum = new JLabel("Business Number:");
		labelBusinessNum.setHorizontalAlignment(SwingConstants.CENTER);
		labelBusinessNum.setForeground(Color.BLACK);
		labelBusinessNum.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		labelBusinessNum.setBounds(22, 262, 152, 37);
		addContractorFrame.getContentPane().add(labelBusinessNum);
		
		textFieldBusinessNum = new JTextField();
		textFieldBusinessNum.setColumns(10);
		textFieldBusinessNum.setBounds(221, 260, 136, 37);
		addContractorFrame.getContentPane().add(textFieldBusinessNum);
		
		labelField = new JLabel("Field:");
		labelField.setHorizontalAlignment(SwingConstants.CENTER);
		labelField.setForeground(Color.BLACK);
		labelField.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		labelField.setBounds(22, 311, 120, 37);
		addContractorFrame.getContentPane().add(labelField);
		
		FieldCombo = new JComboBox();
		FieldCombo.setModel(new DefaultComboBoxModel(professionalField.values()));
		FieldCombo.setRequestFocusEnabled(false);
		FieldCombo.setLightWeightPopupEnabled(false);
		FieldCombo.setForeground(Color.BLACK);
		FieldCombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		FieldCombo.setFocusable(false);
		FieldCombo.setFocusTraversalKeysEnabled(false);
		FieldCombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		FieldCombo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 140, 0)));
		FieldCombo.setBackground(Color.WHITE);
		FieldCombo.setBounds(221, 310, 136, 37);
		addContractorFrame.getContentPane().add(FieldCombo);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(221, 110, 136, 37);
		addContractorFrame.getContentPane().add(textFieldLastName);
	}
}
