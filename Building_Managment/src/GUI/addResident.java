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
import java.util.Arrays;

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

public class addResident {

	static public JFrame addResidentFrame;
	static JButton btnAddResident;
	static JComboBox buildingIDCombo;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	static String databaseURL 
	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 
	private static Connection con = Login_Page.con; 
	static int buildingIDSQL = Login_Page.buildingIDSQL;
	static String userPhone = Login_Page.getPhoneEntry().getText();
	private JLabel lblLastName;
	private JLabel lblAddAResident;
	private JTextField textFieldFirstName;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblPhone;
	private JTextField textFieldPhone;
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
	 * @throws SQLException 
	 * @wbp.parser.entryPoint
	 */
	public addResident() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		addResidentFrame = new JFrame();
		addResidentFrame.getContentPane().setBackground(new Color(192,192,192));
		addResidentFrame.setTitle("Add resident");
		addResidentFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		addResidentFrame.setBounds(100, 100, 556, 477);
		addResidentFrame.setDefaultCloseOperation(this.addResidentFrame.DISPOSE_ON_CLOSE);
		addResidentFrame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(Color.BLACK);
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblFirstName.setBounds(22, 86, 120, 37);
		addResidentFrame.getContentPane().add(lblFirstName);
		
		btnAddResident = new JButton("ADD");
		btnAddResident.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textFieldFirstName.getText().equals("") || textFieldLastName.getText().equals("") || 
						textFieldEmail.getText().equals("") || textFieldPhone.getText().equals("")){
					alertResidentAdd alert = new alertResidentAdd();
					alert.ResidentAlertFrm.setVisible(true);
					
				}
				else {
					try {
						preStatment = con.prepareStatement("insert into Resident (f_Name,l_Name,email,phone,buildingID)"
								+ "values(?,?,?,?,?)");
						
						preStatment.setString(1,textFieldFirstName.getText());
						preStatment.setString(2,textFieldLastName.getText());
						preStatment.setString(3,(textFieldEmail.getText()));
						preStatment.setString(4,(textFieldPhone.getText()));
						preStatment.setInt(5,Integer.parseInt(buildingIDCombo.getSelectedItem().toString()));
						preStatment.executeUpdate();
						AddResidentMsg residentMsg = new AddResidentMsg();
						residentMsg.ResidentAddFrame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
					
			});
		
		btnAddResident.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/addIcon.png")));
		btnAddResident.addMouseListener(new MouseAdapter() {
	    	   @Override
				public void mouseEntered(MouseEvent e) {
	    		   btnAddResident.setBackground(new Color(169,100,0));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btnAddResident.setBackground(new Color(255,140, 0));
				}
	       });
		btnAddResident.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAddResident.setForeground(Color.BLACK);
		btnAddResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnAddResident.setFocusPainted(false);
		btnAddResident.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAddResident.setBackground(new Color(255, 140, 0));
		btnAddResident.setBounds(408, 378, 118, 39);
		addResidentFrame.getContentPane().add(btnAddResident);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setForeground(Color.BLACK);
		lblLastName.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblLastName.setBounds(22, 136, 120, 37);
		addResidentFrame.getContentPane().add(lblLastName);
		
		JLabel lblBuildingID = new JLabel("Building ID");
		lblBuildingID.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuildingID.setForeground(Color.BLACK);
		lblBuildingID.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblBuildingID.setBounds(22, 284, 120, 37);
		addResidentFrame.getContentPane().add(lblBuildingID);
		
		buildingIDCombo = new JComboBox();
		preStatment = con.prepareStatement("select buildingID from Building");
		rs = preStatment.executeQuery();
		
		int count = 0;
		while(rs.next()) {
			count++;
		}
		
		rs = preStatment.executeQuery();
		String[] buildingsID = new String[count];
		int c = 0;
		while(rs.next()) {
			buildingsID[c] = Integer.toString(rs.getInt(1));
			c++;
		}
		System.out.println(Arrays.deepToString(buildingsID));
		DefaultComboBoxModel dm = new DefaultComboBoxModel(buildingsID);
		buildingIDCombo.setModel(dm);
		buildingIDCombo.setRequestFocusEnabled(false);
		buildingIDCombo.setLightWeightPopupEnabled(false);
		buildingIDCombo.setForeground(Color.BLACK);
		buildingIDCombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		buildingIDCombo.setFocusable(false);
		buildingIDCombo.setFocusTraversalKeysEnabled(false);
		buildingIDCombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		buildingIDCombo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 140, 0)));
		buildingIDCombo.setBackground(Color.WHITE);
		buildingIDCombo.setBounds(221, 284, 136, 37);
		addResidentFrame.getContentPane().add(buildingIDCombo);
		
		lblAddAResident = new JLabel("Add A Resident:");
		lblAddAResident.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAResident.setForeground(Color.BLACK);
		lblAddAResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblAddAResident.setBounds(205, 13, 152, 37);
		addResidentFrame.getContentPane().add(lblAddAResident);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(221, 86, 136, 37);
		addResidentFrame.getContentPane().add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblEmail.setBounds(22, 184, 120, 37);
		addResidentFrame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(221, 186, 136, 37);
		addResidentFrame.getContentPane().add(textFieldEmail);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblPhone.setBounds(22, 234, 120, 37);
		addResidentFrame.getContentPane().add(lblPhone);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(221, 234, 136, 37);
		addResidentFrame.getContentPane().add(textFieldPhone);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(221, 134, 136, 37);
		addResidentFrame.getContentPane().add(textFieldLastName);
	}
}
