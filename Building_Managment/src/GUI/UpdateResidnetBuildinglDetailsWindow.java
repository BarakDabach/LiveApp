package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class UpdateResidnetBuildinglDetailsWindow {

	public JFrame frame;
	public sqlDriver conec = new sqlDriver();
	public static int buildingNumber;
	private static  String numRegex = "[0-9]+";
	private static String lettersRegex = "[a-zA-Z]+";
	private static PreparedStatement preStatment;
	private static Connection con = Login_Page.con; 
	private static ResultSet rs ;
	private JButton updateDetailsBtn;
	private JComboBox cityComboBox;
	private JComboBox streetComboBox;
	private JComboBox buildingNumberComboBox;
	private String phoneNumber;
	private JComboBox appartmentNumComboBox;
	public UpdateResidnetBuildinglDetailsWindow(int buildingNumber,String phoneNumber ) {
		this.buildingNumber = buildingNumber;
		this.phoneNumber = phoneNumber;
		initialize();
		

		getCurrentUserBuildingDetails();
		
	}
	
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(34,36,39));
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 480, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
	
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 204, 102)));
		panel.setBackground(new Color(34,36,39));
		panel.setBounds(0, 0, 480, 640);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel closeIcon = new JLabel("");
		closeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		closeIcon.setBounds(436, 13, 32, 32);
		panel.add(closeIcon);
		closeIcon.setHorizontalAlignment(SwingConstants.CENTER);
		closeIcon.setIconTextGap(0);
		closeIcon.setIcon(new ImageIcon(UpdateResidnetBuildinglDetailsWindow.class.getResource("/Media/close.png")));
		
		updateDetailsBtn = new JButton("Update Building Detalis");
		updateDetailsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int newBuildingID = buildingNumber;
					 preStatment = con.prepareStatement("select buildingID from Building where city = ? and streetName = ? and buildingNumber = ?");
					 preStatment.setString(1,cityComboBox.getSelectedItem().toString());
					 preStatment.setString(2,streetComboBox.getSelectedItem().toString());
					 preStatment.setString(3,buildingNumberComboBox.getSelectedItem().toString());
					 
					 rs = preStatment.executeQuery();
					 while (rs.next()) {
						 newBuildingID = rs.getInt(1);
					 }
				
					
					
						preStatment = con.prepareStatement("UPDATE  Resident SET buildingID = ? , appartmentNum = ?   where phone = ?");
						preStatment.setInt(1,newBuildingID );
						preStatment.setString(3, phoneNumber);
						preStatment.setInt(2, Integer.parseInt(appartmentNumComboBox.getSelectedItem().toString()));
						preStatment.executeUpdate();

						Resident_Window.buildingIDSQL = newBuildingID;
						
						
						UpdatedDetailsSuccess success = new UpdatedDetailsSuccess();
						success.alertFrame.setVisible(true);
						frame.dispose();
			
					
					
					
					
								
				}catch(SQLException e1) {
					
					System.out.println(e1.getMessage());
				}
			}
		});
		
		
		updateDetailsBtn.setForeground(Color.WHITE);
		updateDetailsBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		updateDetailsBtn.setFocusPainted(false);
		updateDetailsBtn.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 204, 153)));
		updateDetailsBtn.setBackground(new Color(34, 36, 39));
		updateDetailsBtn.setBounds(120, 569, 249, 42);
		panel.add(updateDetailsBtn);
		
		JLabel lblUpdatePersonalInformation = new JLabel("Update Building Information");
		lblUpdatePersonalInformation.setBounds(79, 75, 331, 33);
		panel.add(lblUpdatePersonalInformation);
		lblUpdatePersonalInformation.setToolTipText("");
		lblUpdatePersonalInformation.setForeground(Color.WHITE);
		lblUpdatePersonalInformation.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblUpdatePersonalInformation.setBackground(new Color(34, 36, 39));
		
		JLabel label_1 = new JLabel("City");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_1.setBackground(new Color(34, 36, 39));
		label_1.setBounds(224, 151, 40, 25);
		panel.add(label_1);
		
		cityComboBox = new JComboBox();
		cityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getStreets(streetComboBox);
				getBuildingNumber(buildingNumberComboBox);
				getAppartmentNumber(appartmentNumComboBox);
			}
		});
		cityComboBox.setUI(new BasicComboBoxUI());
		cityComboBox.setForeground(Color.WHITE);
		cityComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		cityComboBox.setFocusable(false);
		cityComboBox.setFocusTraversalKeysEnabled(false);
		cityComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		cityComboBox.setBackground(new Color(34, 36, 39));
		cityComboBox.setBounds(145, 179, 198, 22);
		panel.add(cityComboBox);
		
		JLabel label_2 = new JLabel("Street");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_2.setBackground(new Color(34, 36, 39));
		label_2.setBounds(214, 218, 60, 25);
		panel.add(label_2);
		
		streetComboBox = new JComboBox();
		streetComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getBuildingNumber(buildingNumberComboBox);
				getAppartmentNumber(appartmentNumComboBox);
			}
		});
		streetComboBox.setUI(new BasicComboBoxUI());
		streetComboBox.setForeground(Color.WHITE);
		streetComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		streetComboBox.setFocusable(false);
		streetComboBox.setFocusTraversalKeysEnabled(false);
		streetComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		streetComboBox.setBackground(new Color(34, 36, 39));
		streetComboBox.setBounds(145, 256, 198, 22);
		panel.add(streetComboBox);
		
		JLabel label_3 = new JLabel("Building Number");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_3.setBackground(new Color(34, 36, 39));
		label_3.setAlignmentX(1.0f);
		label_3.setBounds(168, 308, 153, 25);
		panel.add(label_3);
		
		buildingNumberComboBox = new JComboBox();
		buildingNumberComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAppartmentNumber(appartmentNumComboBox);
			}
		});
		buildingNumberComboBox.setUI(new BasicComboBoxUI());
		buildingNumberComboBox.setForeground(Color.WHITE);
		buildingNumberComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		buildingNumberComboBox.setFocusable(false);
		buildingNumberComboBox.setFocusTraversalKeysEnabled(false);
		buildingNumberComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		buildingNumberComboBox.setBackground(new Color(34, 36, 39));
		buildingNumberComboBox.setAlignmentX(1.0f);
		buildingNumberComboBox.setBounds(145, 346, 198, 22);
		panel.add(buildingNumberComboBox);
		
		appartmentNumComboBox = new JComboBox();
		appartmentNumComboBox.setForeground(Color.WHITE);
		appartmentNumComboBox.setUI(new BasicComboBoxUI());
		appartmentNumComboBox.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		appartmentNumComboBox.setFocusable(false);
		appartmentNumComboBox.setFocusTraversalKeysEnabled(false);
		appartmentNumComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		appartmentNumComboBox.setBackground(new Color(34, 36, 39));
		appartmentNumComboBox.setAlignmentX(1.0f);
		appartmentNumComboBox.setBounds(177, 431, 134, 22);
		panel.add(appartmentNumComboBox);
		
		JLabel label_4 = new JLabel("Appartment Number");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_4.setBackground(new Color(34, 36, 39));
		label_4.setAlignmentX(1.0f);
		label_4.setBounds(151, 392, 186, 25);
		panel.add(label_4);
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
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void getCurrentUserBuildingDetails() {
		
		try {
			getCities(cityComboBox);
			preStatment = con.prepareStatement("select * from Building where buildingID = ?" );
			preStatment.setInt(1,buildingNumber);
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				cityComboBox.setSelectedItem(rs.getString("city"));
			}
			
			getStreets(streetComboBox);
			preStatment = con.prepareStatement("select * from Building where buildingID = ?" );
			preStatment.setInt(1,buildingNumber);
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				streetComboBox.setSelectedItem(rs.getString("streetName"));
			}
			
			
			
			getBuildingNumber(buildingNumberComboBox);
			preStatment = con.prepareStatement("select * from Building where buildingID = ?" );
			preStatment.setInt(1,buildingNumber);
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				buildingNumberComboBox.setSelectedItem(Integer.toString(rs.getInt("buildingNumber")));
			}
			
			getAppartmentNumber(appartmentNumComboBox);
			
			preStatment = con.prepareStatement("select * from Resident where phone = ?");
			preStatment.setString(1, phoneNumber);

			
			rs = preStatment.executeQuery();

			while(rs.next()) {
				
				appartmentNumComboBox.setSelectedItem(Integer.toString(rs.getInt("appartmentNum")));
			}
			
			
			
		
			
			
			
		}
		catch(SQLException e1) {
			System.out.println(e1.getMessage());
		}
		
	}
	
	protected void getCities(JComboBox comboBox) {
		
		List<String> cities;
		try {
			rs = con.prepareStatement("select * from Cities" ).executeQuery();
			cities = new ArrayList<String>();
			
		
			while(rs.next()) {
				cities.add(rs.getString(1));
				
			}
			
		
			cityComboBox.setModel(new DefaultComboBoxModel(cities.toArray(new String[0])));
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
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
	
	
	protected void getStreetsInital(JComboBox comboBox) {
		
	List<String> streets = new ArrayList<String>();
		
		try {
			preStatment = con.prepareStatement("select * from Streets where city = ?");
			preStatment.setString(1,cityComboBox.getSelectedItem().toString());
			ResultSet rs = preStatment.executeQuery();
			
			
			while(rs.next()) {
				streets.add(rs.getString("streetName"));
				
			}
			
		
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		streetComboBox.setModel(new DefaultComboBoxModel(streets.toArray(new String[0])));
		
		
}


	protected void getBuildingNumberInital(JComboBox buildingNumberComboBox) {
		
		List<String> buildingNumbers = new ArrayList<String>();
		
		try {
			preStatment = con.prepareStatement("select buildingNumber from Building where city = ? and streetName = ?");
			preStatment.setString(1,cityComboBox.getSelectedItem().toString());
			preStatment.setString(2,streetComboBox.getSelectedItem().toString());
			ResultSet rs = preStatment.executeQuery();
			
			
			while(rs.next()) {
				buildingNumbers.add( Integer.toString(rs.getInt("buildingNumber")));
				
			}
			
		
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		buildingNumberComboBox.setModel(new DefaultComboBoxModel(buildingNumbers.toArray(new String[0])));
		
	}
	
		

	
}