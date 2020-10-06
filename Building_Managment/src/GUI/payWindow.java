package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import Enums.Defect_Status;
import Enums.professionalField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Component;

public class payWindow {

	static public JFrame payFrame;
	static public JFrame addNotificationFrame;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	static String databaseURL 
	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 
	private static Connection con = Login_Page.con; 
	static int buildingIDSQL = Login_Page.buildingIDSQL;
	static String userPhone = Login_Page.getPhoneEntry().getText();
	private JPanel panel;
	private JLabel closeLabel;
	private JLabel minimizeLabel;
	private JPanel panel_1;
	private static JList unpayedmonthsList;
	private JButton btnPay;
	private JLabel label;
	static String userPhoneNumber = Login_Page.userPhoneNumber;
	protected int buildingFee = Admin_Window.buildingFee;
	private JLabel lblTotalFor;
	private JLabel label_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addDefectWindow window = new addDefectWindow();
					window.addDefectFrame.setVisible(true);
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
	public payWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		System.out.println(buildingFee);
		payFrame = new JFrame();
		payFrame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(unpayedmonthsList.getSelectedValuesList().size() == 0) {
					btnPay.setEnabled(false);
				}
				else {
					btnPay.setEnabled(true);
				}
			}
		});
		payFrame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				unpayedmonthsList.clearSelection();
				btnPay.setEnabled(false);
				
				lblTotalFor.setText("");
			}
		});
		payFrame.setUndecorated(true);
		
		payFrame.getContentPane().setForeground(new Color(255, 255, 255));
		payFrame.getContentPane().setBackground(new Color(34,36,39));
		payFrame.setTitle("Add Defect");
		payFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/logo_transparent.png")).getImage());
		payFrame.setBounds(100, 100, 624, 530);
		payFrame.setDefaultCloseOperation(this.payFrame.DISPOSE_ON_CLOSE);
		payFrame.getContentPane().setLayout(null);
		payFrame.setLocationRelativeTo(null);
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		panel_1.setBackground(new Color(34, 36, 39));
		panel_1.setBounds(0, 0, 624, 530);
		payFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(3, 56, 618, 64);
		panel_1.add(panel);
		panel.setBackground(new Color(102, 0, 204));
		panel.setLayout(null);
		
		label = new JLabel("Unpayed Months");
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		label.setBounds(237, 13, 143, 34);
		panel.add(label);
		
		
		
		
		
		
		
		
		
		
		
		closeLabel = new JLabel("");
		closeLabel.setBounds(588, 13, 24, 24);
		panel_1.add(closeLabel);
		closeLabel.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/close.png")));
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin_Window.addPaymentsTable(Calendar.getInstance().get(Calendar.YEAR));
				payFrame.dispose();
			}
		});
		closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		closeLabel.setAlignmentX(1.0f);
		
		minimizeLabel = new JLabel("");
		minimizeLabel.setBounds(553, 13, 24, 24);
		panel_1.add(minimizeLabel);
		minimizeLabel.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/minimize.png")));
		minimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				payFrame.setState(Frame.ICONIFIED);
				
			}
		});
		minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeLabel.setAlignmentX(1.0f);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(201, 141, 221, 310);
		panel_1.add(scrollPane_2);
		scrollPane_2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 0, 153)));
		
		unpayedmonthsList = new JList();
		unpayedmonthsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnPay.setEnabled(true);
				int numOfMonth = unpayedmonthsList.getSelectedValuesList().size();
				
				if(numOfMonth == 0) {
					lblTotalFor.setText("");
				}
				
				else if(numOfMonth == 1) {
					lblTotalFor.setText("Total "+Integer.toString(buildingFee)+"NIS for 1 month");
				}
				
				else {
					lblTotalFor.setText("Total "+Integer.toString(buildingFee*numOfMonth)+"NIS for "+Integer.toString(numOfMonth) +" months");
				}
				
			}
		});
		unpayedmonthsList.setRequestFocusEnabled(false);
		unpayedmonthsList.setFocusTraversalKeysEnabled(false);
		unpayedmonthsList.setSelectionBackground(new Color(153, 102, 153));
		unpayedmonthsList.setForeground(new Color(255, 255, 255));
		unpayedmonthsList.setBackground(new Color(34,36,39));
		unpayedmonthsList.setBorder(null);
		unpayedmonthsList.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		unpayedmonthsList.setLocation(405, 0);
		scrollPane_2.setViewportView(unpayedmonthsList);
		
		lblTotalFor = new JLabel("");
		lblTotalFor.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTotalFor.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalFor.setForeground(Color.WHITE);
		lblTotalFor.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblTotalFor.setBounds(386, 565, 276, 34);
		payFrame.getContentPane().add(lblTotalFor);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/payIcon.png")));
		label_6.setBounds(84, 165, 128, 128);
		payFrame.getContentPane().add(label_6);
		
		btnPay = new JButton("Pay");
		btnPay.setBounds(221, 478, 180, 39);
		panel_1.add(btnPay);
		btnPay.setEnabled(false);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				List<String> selectedMonths = unpayedmonthsList.getSelectedValuesList();
				if(selectedMonths.size() >0) {
					try {
						
						for(String month : selectedMonths) {
							
							preStatment = con.prepareStatement("INSERT into ComitteePayments(amount,phone,month,year,buildingID) values(?,?,?,?,?)");
							preStatment.setInt(1,buildingFee);
							preStatment.setString(2,userPhoneNumber );
							
							if(month.equals("Januray") == true) {
								preStatment.setInt(3,1);
							}
							else if(month.equals("Feburary") == true) {
								preStatment.setInt(3,2);
							}
							
							else if(month.equals("March") == true) {
								preStatment.setInt(3,3);
							}
							
							else if(month.equals("April") == true) {
								preStatment.setInt(3,4);
							}
							
							else if(month.equals("May") == true) {
								preStatment.setInt(3,5);
							}
							
							else if(month.equals("June") == true) {
								preStatment.setInt(3,6);
							}
							
							else if(month.equals("July") == true) {
								preStatment.setInt(3,7);
							}
							
							else if(month.equals("August") == true) {
								preStatment.setInt(3,8);
							}
							
							else if(month.equals("September") == true) {
								preStatment.setInt(3,9);
							}
							
							else if(month.equals("October") == true) {
								preStatment.setInt(3,10);
							}
							
							else if(month.equals("November") == true) {
								preStatment.setInt(3,11);
							}
							
							else {
								preStatment.setInt(3,12);
							
							}
							
							preStatment.setInt(4,Calendar.getInstance().get(Calendar.YEAR));
							preStatment.setInt(5,buildingIDSQL);
							preStatment.executeUpdate();
						}
						
						
						
						payedSuccess payed = new payedSuccess();
						payed.alertFrame.setVisible(true);
						unpayedmonthsList.clearSelection();
						lblTotalFor.setText("");
						getPayedMonths();
					}
						catch(Exception e1) {
							System.out.println(e1.getMessage());
						}
							
					}	
				}
				
				
				
			
		});
		btnPay.setVerifyInputWhenFocusTarget(false);
		btnPay.setRolloverEnabled(false);
		btnPay.setRequestFocusEnabled(false);
		btnPay.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnPay.setForeground(Color.WHITE);
		btnPay.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnPay.setFocusable(false);
		btnPay.setFocusPainted(false);
		btnPay.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 255, 153)));
		btnPay.setBackground(new Color(34, 36, 39));
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/payIcon.png")));
		label_1.setBounds(24, 133, 128, 128);
		panel_1.add(label_1);
		

	}
	protected void getPayedMonths() {

		 ArrayList<String> unpayedMonths = new ArrayList<>();
		 ArrayList<String> payedMonths = new ArrayList<>();
		 
		try {
			preStatment = con.prepareStatement("select month , amount from ComitteePayments where phone = ? and buildingID = ? and year = ?");
			preStatment.setString(1,userPhoneNumber);
			preStatment.setInt(2,buildingIDSQL);
			preStatment.setString(3,Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
			
			rs = preStatment.executeQuery();
			
			for(int i = 1; i< 13; i ++) {
				unpayedMonths.add(Integer.toString(i));
			}
			
			while(rs.next()) {
				unpayedMonths.remove(Integer.toString(rs.getInt(1)));
			}
			
			
			String[] list = unpayedMonths.toArray(new String[0]);
			DefaultListModel model = new DefaultListModel();
			
			for(int i = 0 ; i< unpayedMonths.size(); i++) {
				if(list[i].equals("1") == true) {
					model.add(i,"Januray");
				}
				else if(list[i].equals("2") == true) {
					model.add(i,"Feburary");
				}
				
				else if(list[i].equals("3") == true) {
					model.add(i,"March");
				}
				
				else if(list[i].equals("4") == true) {
					model.add(i,"April");
				}
				
				else if(list[i].equals("5") == true) {
					model.add(i,"May");
				}
				
				else if(list[i].equals("6") == true) {
					model.add(i,"June");
				}
				
				else if(list[i].equals("7") == true) {
					model.add(i,"July");
				}
				
				else if(list[i].equals("8") == true) {
					model.add(i,"August");
				}
				
				else if(list[i].equals("9") == true) {
					model.add(i,"September");
				}
				
				else if(list[i].equals("10") == true) {
					model.add(i,"October");
				}
				
				else if(list[i].equals("11") == true) {
					model.add(i,"November");
				}
				
				else {
					model.add(i,"December");
				}
				
			}
			
			
			
			
			unpayedmonthsList.setModel(model);
			
			
			
			
			
		}
		catch(SQLException e1) {
			System.out.println(e1.getMessage());
		}
		
		
		
		
	}
}
