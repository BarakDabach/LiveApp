package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

import com.sun.deploy.uitoolkit.impl.fx.Utils;

import Enums.Defect_Status;
import Java_Classes.Building;
import Java_Classes.Contractor;
import Java_Classes.Defect;
import Java_Classes.Message;
import Java_Classes.Resident;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DebugGraphics;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.ComponentOrientation;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Point;

public class Admin_Window {

	public JFrame frmAdminWindow;
	private static JPanel DefectFrm;
	private JLabel lblDefectsStatus;
	protected TableRowSorter<DefaultTableModel> sorter;
	private Object[] enumVal;
	private static JPanel open_Frm;
	private static JPanel ResidentsFrm;
	private static JPanel ContractorFrm;
	private static JPanel BuildingsFrm;
	private static JLabel clock;
	private static JButton residentTab;
	private static JButton HomeTab;
	private static JButton DefectTab;
	private static JButton inboxTab;
	private static JButton sendMsgTab;
	private static JButton aboutTab;
	private static JButton accountTab;
	static JPanel[] panels;
	static JButton[] tabButtons;
	private static JTable defectTable;
	private static JTable buildingTable;
	public static JLabel lblWelcomeBack;
	private static  JList list;
	private static JTable contractorTable;
	private static PreparedStatement preStatment;
	private static ResultSet rs;
	private static Connection con = Login_Page.con ;
	public static ArrayList<Integer> defectID;
	public static ArrayList<Integer> buildingID;
	public static ArrayList<String> phones;
//	static String databaseURL 
//	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 

	
	public static void setPanel(JPanel currentPanel) {
		panels  = new JPanel[]{DefectFrm,open_Frm,ResidentsFrm,ContractorFrm,BuildingsFrm};
		for(int i = 0;i<panels.length;i++) {
			if(panels[i].equals(currentPanel)) {
				currentPanel.setVisible(true);
			}
			
			else {
				panels[i].setVisible(false);
			}
		}
	}
	
	public static void setTabColorGray(JButton currentTab) {
		tabButtons  = new JButton[]{residentTab,HomeTab,DefectTab,inboxTab,sendMsgTab,accountTab,aboutTab};
		for(int i = 0;i<tabButtons.length;i++) {
			if(tabButtons[i].equals(currentTab)) {
				tabButtons[i].setBackground(new Color(86,70,119));
				
			}
			
			else {
				tabButtons[i].setBackground(new Color(62,0,110));
				
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Window window = new Admin_Window();
					window.frmAdminWindow.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		 
		
		
	}
	
	
	
	
	
	
	
	//adding the residents names to the list of residents.
	
	public static void enterResidetnsName() throws SQLException {
		
		ArrayList<String> residentsNames = new ArrayList<String>();
		phones = new ArrayList<String>();
		
		preStatment = con.prepareStatement("select CONCAT(`f_Name` , ' ' , `l_Name`)  as 'name' , phone from Resident");
		rs = preStatment.executeQuery();
		

		
		while(rs.next()) {
			residentsNames.add(rs.getString("name"));
			phones.add(rs.getString("phone"));
		}

		
		DefaultListModel listModel = new DefaultListModel();
		
		
		for(int i = 0; i<residentsNames.size();i++) {
			listModel.addElement(residentsNames.get(i));
		}
		
		list.setModel(listModel);
	}
	

	
	
	
	
	//adding the data of defects to the defects table.
	public static void addDataDefectTable() throws SQLException {
		
		preStatment = con.prepareStatement("select defectID as ID from Defect");
		rs = preStatment.executeQuery();
		
		defectID = new ArrayList<Integer>();

		while(rs.next()) {
			defectID.add(rs.getInt("ID"));
		}
		
		String[][] tableDefect = new String [defectID.size()][4];
		
			preStatment = con.prepareStatement("select * from Defect");
			rs = preStatment.executeQuery();
			int c = 0;
			while(rs.next()) {
				tableDefect[c][0] = rs.getString("defectID");
				tableDefect[c][1] = rs.getString("status");
				tableDefect[c][2] = rs.getString("field");
				tableDefect[c][3] = rs.getString("businessNumber");
				c++;
			}
			
				
		
		DefaultTableModel tableModel = 
				new DefaultTableModel(tableDefect,new String[]{"Defect ID", "Status", "Type", "Contractor"});
		tableModel.setRowCount(defectID.size());
		defectTable.setModel(tableModel);
	}
	
	
	
	//adding the data of buildings to the defects table.
	public static void addDataBuildingTable() throws SQLException {
		
		preStatment = con.prepareStatement("select buildingID as ID from Building");
		rs = preStatment.executeQuery();
		
		buildingID = new ArrayList<Integer>();
		
		while(rs.next()) {
			buildingID.add(rs.getInt("ID"));
		}
		
		String[][] tableBuilding = new String [buildingID.size()][6];
		
		preStatment = con.prepareStatement("select * from Building");
		rs = preStatment.executeQuery();
		int c = 0;
		
		while(rs.next()) {
			tableBuilding[c][0] = rs.getString("buildingID");
			tableBuilding[c][1] = rs.getString("city");
			tableBuilding[c][2] = rs.getString("streetName");
			tableBuilding[c][3] = rs.getString("bulidingNumber");
			tableBuilding[c][4] = rs.getString("numberOfAppartments");
			tableBuilding[c][5] = rs.getString("phone");
			c++;
		}
		
		
		DefaultTableModel tableModel = 
				new DefaultTableModel(tableBuilding,new String[]{"Building ID", "City", "Street", "Number", "Apartments", "Phone"});
		tableModel.setRowCount(buildingID.size());
		buildingTable.setModel(tableModel);
	}
	
	
	public static void addDataContractorTable() throws SQLException {
		
		preStatment = con.prepareStatement("select * from Contractor");
		rs = preStatment.executeQuery();
		
		int count = 0;
		while(rs.next()) {
			count++;
		}
		
		int c = 0;
		String[][] tableContractors = new String[count][7];
		
		rs = preStatment.executeQuery();

		while(rs.next()) {
			tableContractors[c][0] = rs.getString(1);
			tableContractors[c][1] = rs.getString(2);
			tableContractors[c][2] = rs.getString(3);
			tableContractors[c][3] = rs.getString(4);
			tableContractors[c][4] = rs.getString(5);
			tableContractors[c][5] = rs.getString(6);
			tableContractors[c][6] = rs.getString(7);
			c++;
		}
		DefaultTableModel tableModel = 
				new DefaultTableModel(tableContractors,new String[]{"First Name", "Last Name", "Email", "Phone", "Business Number", "Field", "City"});
		tableModel.setRowCount(count);
		contractorTable.setModel(tableModel);
	}

	
	
	
	//thread that running the time in the home screen
	static void runClock() {
		 new Thread() {
				public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Date date = new Date();
				    String strDateFormat = "HH:mm:ss";
				    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
				    String formattedDate= dateFormat.format(date);
				    clock.setText(formattedDate);
				}
				}
			}.start();
	}

	/**
	 * Create the application.
	 */
	public Admin_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//		try {
//			con = DriverManager.getConnection(databaseURL);
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}

		frmAdminWindow = new JFrame();
		frmAdminWindow.setUndecorated(true);
		frmAdminWindow.getContentPane().setBackground(new Color(34, 36, 39));
		frmAdminWindow.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/logo_transparent.png")).getImage());
		frmAdminWindow.setResizable(false);
		frmAdminWindow.setTitle("Admin Window");
		frmAdminWindow.setBounds(100, 100, 1280, 750);
		frmAdminWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminWindow.getContentPane().setLayout(null);
		frmAdminWindow.setLocationRelativeTo(null);
														
//		ContractorFrm = new JPanel();
//		ContractorFrm.setVisible(false);
//							

							
							
							
//							BuildingsFrm = new JPanel();
//							BuildingsFrm.setVisible(false);
//						
//						
//						
//						DefectFrm = new JPanel();
//						DefectFrm.setVisible(false);
						
						
						
//						ResidentsFrm = new JPanel();
//						ResidentsFrm.setVisible(false);
//						
//						
//						
//						
//
//						
//						ResidentsFrm.setBackground(new Color(169, 169, 169));
//						ResidentsFrm.setBounds(174, 0, 532, 517);
//						frmAdminWindow.getContentPane().add(ResidentsFrm);
//						ResidentsFrm.setLayout(null);
						
							JScrollPane scrollPane = new JScrollPane();
							
							scrollPane.setFocusTraversalKeysEnabled(false);
							scrollPane.setFocusable(false);
							scrollPane.setBounds(23, 101, 203, 351);
							
//								ResidentsFrm.add(scrollPane);
								
								

							
								
								list = new JList(new String[]{"",""}) ;
								scrollPane.setViewportView(list);
								list.setAlignmentX(Component.RIGHT_ALIGNMENT);
								list.setRequestFocusEnabled(false);
								list.setValueIsAdjusting(true);
								list.setFocusable(false);
								list.setFocusTraversalKeysEnabled(false);
								list.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
								list.setSelectionForeground(new Color(0, 0, 0));
								list.setSelectionBackground(new Color(255,140, 0));
								list.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 140, 0)));
								list.setBackground(new Color(255, 255, 255));
								
								list.setSelectedIndex(0);
								
								JLabel label_3 = new JLabel("");
								label_3.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/userImg1.png")));
								label_3.setBounds(284, 76, 215, 212);
//								ResidentsFrm.add(label_3);
								
	
								
								
								JLabel lblResidents = new JLabel("Residents");
								lblResidents.setForeground(new Color(0, 0, 0));
								lblResidents.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
								lblResidents.setBounds(236, 16, 95, 22);
//								ResidentsFrm.add(lblResidents);
								
								JLabel lblSelectResident = new JLabel("Select Resident");
								lblSelectResident.setForeground(new Color(0, 0, 0));
								lblSelectResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
								lblSelectResident.setBounds(23, 76, 123, 22);
//								ResidentsFrm.add(lblSelectResident);
								
								JButton removeResident = new JButton("Remove");
								removeResident.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											preStatment = con.prepareStatement("delete from Resident where phone = ?");
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
						       			String phone = "";
						       			for(int i = 0; i < list.getSelectedIndices().length; i++) {
						       				phone = phones.get(list.getSelectedIndices()[i]);
						       				try {
												preStatment.setString(1,phone);
												preStatment.executeUpdate();
												addDataDefectTable();
												
											} catch (SQLException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
						       		}
						       			try {
											enterResidetnsName();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								});
								removeResident.setForeground(new Color(0, 0, 0));
								removeResident.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/removeIcon.png")));
								removeResident.setHorizontalTextPosition(SwingConstants.RIGHT);
								removeResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
								removeResident.setFocusPainted(false);
								removeResident.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
								removeResident.setBackground(new Color(255,140, 0));
								removeResident.setBounds(321, 413, 123, 39);
								removeResident.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										removeResident.setBackground(new Color(169,100,0));
									}
									
									@Override
									public void mouseExited(MouseEvent e) {
										removeResident.setBackground(new Color(255,140, 0));
									}
									
									
								});
								
								
//								ResidentsFrm.add(removeResident);
								
								JButton addResident = new JButton("Add");
								addResident.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										addResident addWindow = null;
										try {
											addWindow = new addResident();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
							       		addWindow.addResidentFrame.setVisible(true);
									}
								});
								addResident.setForeground(new Color(0, 0, 0));
								addResident.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/addIcon.png")));
								addResident.setIconTextGap(0);
								addResident.setHorizontalTextPosition(SwingConstants.RIGHT);
								addResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
								addResident.setFocusPainted(false);
								addResident.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
								addResident.setBackground(new Color(255, 140, 0));
								addResident.setBounds(321, 343, 123, 39);
								addResident.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										addResident.setBackground(new Color(169,100,0));
									}
									
									@Override
									public void mouseExited(MouseEvent e) {
										addResident.setBackground(new Color(255,140, 0));
									}
									
									
								});
								
								
								
								
//								ResidentsFrm.add(addResident);
						
						
							
						JPanel closeminimizePanel = new JPanel();
						closeminimizePanel.setBounds(1195, 0, 78, 53);
						frmAdminWindow.getContentPane().add(closeminimizePanel);
						closeminimizePanel.setBackground(new Color(34, 36, 39));
						closeminimizePanel.setLayout(null);
						
						JLabel closeLabel = new JLabel("");
						closeLabel.setBounds(50, 13, 24, 24);
						closeminimizePanel.add(closeLabel);
						closeLabel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								try {
									con.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								System.exit(0);
							}
						});
						closeLabel.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/close.png")));
						closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
						closeLabel.setAlignmentX(1.0f);
						
						JLabel minimizeLabel = new JLabel("");
						minimizeLabel.setBounds(12, 13, 24, 24);
						closeminimizePanel.add(minimizeLabel);
						minimizeLabel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								frmAdminWindow.setState(Frame.ICONIFIED);
							}
						});
						minimizeLabel.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/minimize.png")));
						minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
						minimizeLabel.setAlignmentX(1.0f);			
							
							
							
							
							
							
							
							
							DefectFrm.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
							DefectFrm.setRequestFocusEnabled(false);
							DefectFrm.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							DefectFrm.setBounds(174, 0, 532, 517);
							DefectFrm.setBackground(new Color(169,169,169));
							frmAdminWindow.getContentPane().add(DefectFrm);
							DefectFrm.setLayout(null);
							
							lblDefectsStatus = new JLabel("Defects");
							lblDefectsStatus.setForeground(new Color(0, 0, 0));
							lblDefectsStatus.setHorizontalAlignment(SwingConstants.CENTER);
							lblDefectsStatus.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
							lblDefectsStatus.setBounds(236, 13, 73, 38);
							DefectFrm.add(lblDefectsStatus);
							
							JComboBox sortDefects = new JComboBox();
							sortDefects.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
									
									sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) defectTable.getModel());
									RowFilter<DefaultTableModel, Object> rf  = RowFilter.regexFilter(sortDefects.getSelectedItem().toString(),defectTable.getColumnModel().getColumnIndex("Status"));
					                sorter.setRowFilter(rf);
					                defectTable.setRowSorter(sorter);
								}
							});
							sortDefects.setFocusTraversalKeysEnabled(false);
							sortDefects.setFocusable(false);
							sortDefects.setForeground(new Color(0, 0, 0));
							sortDefects.setLightWeightPopupEnabled(false);
							sortDefects.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//							sortDefects.setModel(new DefaultComboBoxModel(enumVal));
							sortDefects.setRequestFocusEnabled(false);
							sortDefects.setBackground(new Color(255, 255, 255));
							sortDefects.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 127, 80)));
							sortDefects.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
							sortDefects.setBounds(199, 120, 148, 31);
							DefectFrm.add(sortDefects);
							
							defectTable = new JTable();
							defectTable.getTableHeader().setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
							defectTable.getTableHeader().setBackground(new Color(255, 140, 0));
							defectTable.setFocusTraversalKeysEnabled(false);
							defectTable.setFocusable(false);
							defectTable.setBackground(new Color(169, 169, 169));
							defectTable.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
							defectTable.setRowMargin(5);
							defectTable.setForeground(new Color(0, 0, 0));
							defectTable.setSelectionForeground(new Color(192, 192, 192));
							defectTable.setShowVerticalLines(false);
							defectTable.setSelectionBackground(new Color(255, 140, 0));
							defectTable.setRequestFocusEnabled(false);
							defectTable.setRowHeight(25);
							
							defectTable.setIntercellSpacing(new Dimension(0, 0));
							defectTable.setMinimumSize(new Dimension(0, 0));
							defectTable.setAlignmentX(Component.RIGHT_ALIGNMENT);
							defectTable.setBounds(new Rectangle(0, 0, 508, 508));
							defectTable.setBounds(18, 108, 508, 347);
							defectTable.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
							defectTable.setGridColor(new Color(255,255,255));
							
							
							       JScrollPane tablePanel = new JScrollPane(defectTable);
							       tablePanel.setBackground(new Color(192, 192, 192));
							       tablePanel.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
							       tablePanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
							       tablePanel.setBounds(new Rectangle(12, 180, 508, 229));
							       DefectFrm.add(tablePanel, BorderLayout.CENTER);
							       
							       JButton btnAddDefect = new JButton("Add Defect");
							       btnAddDefect.addActionListener(new ActionListener() {
							       	public void actionPerformed(ActionEvent arg0) {
							       		addDefectWindowAdmin addWindow = new addDefectWindowAdmin();
							       		addWindow.addDefectFrame.setVisible(true);
							       	}
							       });
							       btnAddDefect.setForeground(new Color(0, 0, 0));
							       btnAddDefect.setHorizontalTextPosition(SwingConstants.RIGHT);
							       btnAddDefect.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/addIcon.png")));
							       btnAddDefect.setFocusPainted(false);
							       btnAddDefect.setBackground(new Color(255, 140, 0));
							       btnAddDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
							       btnAddDefect.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
							       btnAddDefect.setBounds(402, 465, 118, 39);
							       btnAddDefect.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnAddDefect.setBackground(new Color(169,100,0));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnAddDefect.setBackground(new Color(255,140, 0));
					}
				});
							       
							       
							       
							       DefectFrm.add(btnAddDefect);
							       
							       JButton btnRemoveDefect = new JButton("Remove Defect");
							       btnRemoveDefect.addActionListener(new ActionListener() {
							       	public void actionPerformed(ActionEvent arg0) {
							       		try {
														preStatment = con.prepareStatement("delete from Defect where defectID = ?");
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
							       		int[] indexes = defectTable.getSelectedRows();
							       		int defectID;
							       		for(int i = 0; i < indexes.length; i++) {
							       			defectID = Integer.parseInt((String) defectTable.getModel().getValueAt(indexes[i], 0));
							       			try {
															preStatment.setInt(1,defectID);
															preStatment.executeUpdate();
															addDataDefectTable();
															
														} catch (SQLException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
							       		}
							       		
							       	}
							       });
							       btnRemoveDefect.setForeground(new Color(0, 0, 0));
							       btnRemoveDefect.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/removeIcon.png")));
							       btnRemoveDefect.setFocusPainted(false);
							       btnRemoveDefect.setBackground(new Color(255, 140, 0));
							       btnRemoveDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
							       btnRemoveDefect.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
							       btnRemoveDefect.setBounds(247, 465, 143, 39);
							       btnRemoveDefect.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnRemoveDefect.setBackground(new Color(169,100,0));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnRemoveDefect.setBackground(new Color(255,140, 0));
					}
				});
							       DefectFrm.add(btnRemoveDefect);
							       
							       JLabel lblSortBy = new JLabel("Sort By:");
							       lblSortBy.setForeground(new Color(0, 0, 0));
							       lblSortBy.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
							       lblSortBy.setBounds(249, 93, 61, 22);
							       
							       DefectFrm.add(lblSortBy);
									
									Defect_Status[]defectsVal = Defect_Status.values();
									String[] enumVal = new String[Defect_Status.values().length+1];
									enumVal[0] = "";
									for(int i = 1;i<enumVal.length;i++) {
										
										enumVal[i] = defectsVal[i-1].toString();
										
									}
						
								
//								open_Frm = new JPanel();
//								open_Frm.setBackground(new Color(169, 169, 169));
//								open_Frm.setBounds(174, 0, 532, 517);
								frmAdminWindow.getContentPane().add(open_Frm);
								open_Frm.setLayout(null);
								
								lblWelcomeBack = new JLabel("Welcome Back ");
								lblWelcomeBack.setHorizontalTextPosition(SwingConstants.LEFT);
								lblWelcomeBack.setHorizontalAlignment(SwingConstants.CENTER);
								lblWelcomeBack.setForeground(new Color(0, 0, 0));
								lblWelcomeBack.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
								lblWelcomeBack.setBounds(114, 340, 308, 34);
								open_Frm.add(lblWelcomeBack);
								
								JLabel label = new JLabel("");
								label.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/userImg1.png")));
								label.setBounds(157, 96, 208, 213);
								open_Frm.add(label);
								
								clock = new JLabel();
								clock.setHorizontalAlignment(SwingConstants.CENTER);
								clock.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
								clock.setBounds(220, 66, 77, 26);
								open_Frm.add(clock);
								
								JLabel label_2 = new JLabel("");
								label_2.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/clockIcon.png")));
								label_2.setBounds(233, 13, 50, 50);
								open_Frm.add(label_2);
							

							BuildingsFrm.setBackground(new Color(169, 169, 169));
							BuildingsFrm.setBounds(174, 0, 532, 517);
							frmAdminWindow.getContentPane().add(BuildingsFrm);
							BuildingsFrm.setLayout(null);
							
							
							
							JLabel buildingslbl = new JLabel("Buildings");
							buildingslbl.setHorizontalAlignment(SwingConstants.CENTER);
							buildingslbl.setForeground(Color.BLACK);
							buildingslbl.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
							buildingslbl.setBounds(217, 13, 94, 38);
							JTable incomingMessages = new JTable(5,5);
							incomingMessages.setTableHeader(new JTableHeader());
							incomingMessages.setBounds(120, 120, 50, 80);
							
							BuildingsFrm.add(buildingslbl,incomingMessages);
							
							buildingTable = new JTable();
							buildingTable.getTableHeader().setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
							buildingTable.getTableHeader().setBackground(new Color(255, 140, 0));
							buildingTable.setFocusTraversalKeysEnabled(false);
							buildingTable.setFocusable(false);
							buildingTable.setBackground(new Color(169, 169, 169));
							buildingTable.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
							buildingTable.setRowMargin(5);
							buildingTable.setForeground(new Color(0, 0, 0));
							buildingTable.setSelectionForeground(new Color(192, 192, 192));
							buildingTable.setShowVerticalLines(false);
							buildingTable.setSelectionBackground(new Color(255, 140, 0));
							buildingTable.setRequestFocusEnabled(false);
							buildingTable.setRowHeight(25);
							
							buildingTable.setIntercellSpacing(new Dimension(0, 0));
							buildingTable.setMinimumSize(new Dimension(0, 0));
							buildingTable.setAlignmentX(Component.RIGHT_ALIGNMENT);
							buildingTable.setBounds(new Rectangle(0, 0, 508, 508));
							buildingTable.setBounds(18, 108, 508, 347);
							buildingTable.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
							buildingTable.setGridColor(new Color(255,255,255));
							
							JScrollPane buildinglPane = new JScrollPane(buildingTable);
							
							buildinglPane.setFocusTraversalKeysEnabled(false);
							buildinglPane.setFocusable(false);
							buildinglPane.setBounds(23, 101, 473, 247);
							
								BuildingsFrm.add(buildinglPane);
								
								JButton btnAddBuilding = new JButton("Add Building");
								btnAddBuilding.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										addBuilding buildingWin = new addBuilding();
										buildingWin.addBuildingFrame.setVisible(true);
									}
								});
								btnAddBuilding.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/addIcon.png")));
								btnAddBuilding.setHorizontalTextPosition(SwingConstants.RIGHT);
								btnAddBuilding.setForeground(Color.BLACK);
								btnAddBuilding.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
								btnAddBuilding.setFocusPainted(false);
								btnAddBuilding.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
								btnAddBuilding.setBackground(new Color(255, 140, 0));
								btnAddBuilding.setBounds(23, 382, 135, 39);
								BuildingsFrm.add(btnAddBuilding);
								
								JButton btnDeleteBuilding = new JButton("Remove Building");
								btnDeleteBuilding.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/removeIcon.png")));
								btnDeleteBuilding.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
								 		try {
											preStatment = con.prepareStatement("delete from Building where buildingID = ?");
										}
								 		catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
											   	int[] indexes = buildingTable.getSelectedRows();
												int buildingID;
										  		for(int i = 0; i < indexes.length; i++) {
										  			buildingID = Integer.parseInt((String) buildingTable.getModel().getValueAt(indexes[i], 0));
										  			try {
										  				preStatment.setInt(1,buildingID);
										  				preStatment.executeUpdate();
										  				addDataBuildingTable();
												
										  			} catch (SQLException e) {
										  				// TODO Auto-generated catch block
										  				e.printStackTrace();
										  			}
											      }
									}
								});
								btnDeleteBuilding.setForeground(Color.BLACK);
								btnDeleteBuilding.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
								btnDeleteBuilding.setFocusPainted(false);
								btnDeleteBuilding.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
								btnDeleteBuilding.setBackground(new Color(255, 140, 0));
								btnDeleteBuilding.setBounds(341, 382, 155, 39);
								BuildingsFrm.add(btnDeleteBuilding);
														ContractorFrm.setBackground(new Color(169, 169, 169));
														ContractorFrm.setBounds(174, 0, 532, 517);
														frmAdminWindow.getContentPane().add(ContractorFrm);
														ContractorFrm.setLayout(null);
														
														JLabel lblContractors = new JLabel("Contractors");
														lblContractors.setBounds(202, 27, 113, 38);
														lblContractors.setHorizontalAlignment(SwingConstants.CENTER);
														lblContractors.setForeground(Color.BLACK);
														lblContractors.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
														ContractorFrm.add(lblContractors);
														
														
														
														contractorTable = new JTable();
														contractorTable.setShowVerticalLines(false);
														contractorTable.setSelectionForeground(Color.LIGHT_GRAY);
														contractorTable.setSelectionBackground(new Color(255, 140, 0));
														contractorTable.setRowMargin(5);
														contractorTable.setRowHeight(25);
														contractorTable.setRequestFocusEnabled(false);
														contractorTable.setMinimumSize(new Dimension(0, 0));
														contractorTable.setIntercellSpacing(new Dimension(0, 0));
														contractorTable.setGridColor(Color.WHITE);
														contractorTable.setForeground(Color.BLACK);
														contractorTable.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
														contractorTable.setFocusable(false);
														contractorTable.setFocusTraversalKeysEnabled(false);
														contractorTable.setBounds(new Rectangle(0, 0, 508, 508));
														contractorTable.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
														contractorTable.setBackground(new Color(169, 169, 169));
														contractorTable.setAlignmentX(1.0f);
														
															JScrollPane contractorPane = new JScrollPane(contractorTable);
															contractorPane.setBounds(27, 116, 473, 247);
															contractorPane.setFocusable(false);
															contractorPane.setFocusTraversalKeysEnabled(false);
															contractorPane.setViewportView(contractorTable);
															ContractorFrm.add(contractorPane);
															
															contractorTable.setShowVerticalLines(false);
															contractorTable.setSelectionForeground(Color.LIGHT_GRAY);
															contractorTable.setSelectionBackground(new Color(255, 140, 0));
															contractorTable.setRowMargin(5);
															contractorTable.setRowHeight(25);
															contractorTable.setRequestFocusEnabled(false);
															contractorTable.setMinimumSize(new Dimension(0, 0));
															contractorTable.setIntercellSpacing(new Dimension(0, 0));
															contractorTable.setGridColor(Color.WHITE);
															contractorTable.setForeground(Color.BLACK);
															contractorTable.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
															contractorTable.setFocusable(false);
															contractorTable.setFocusTraversalKeysEnabled(false);
															contractorTable.setBounds(new Rectangle(0, 0, 508, 508));
															contractorTable.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
															contractorTable.setBackground(new Color(169, 169, 169));
															contractorTable.setAlignmentX(1.0f);
															
															JButton removeContractor = new JButton("Remove");
															removeContractor.addActionListener(new ActionListener() {
																public void actionPerformed(ActionEvent e) {
														       		try {
																						preStatment = con.prepareStatement("delete from Contractor where businessNumber = ?");
																					} catch (SQLException e1) {
																						// TODO Auto-generated catch block
																						e1.printStackTrace();
																					}
																						   	int[] indexes = contractorTable.getSelectedRows();
																							int businessID;
																					  		for(int i = 0; i < indexes.length; i++) {
																					  			businessID = Integer.parseInt((String) contractorTable.getModel().getValueAt(indexes[i], 4));
																					  			try {
																					  				preStatment.setInt(1,businessID);
																					  				preStatment.executeUpdate();
																					  				addDataContractorTable();
																							
																					  			} catch (SQLException e1) {
																					  				// TODO Auto-generated catch block
																					  				e1.printStackTrace();
																					  			}
																						      }
																}
															});
															removeContractor.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/removeIcon.png")));
															removeContractor.setHorizontalTextPosition(SwingConstants.RIGHT);
															removeContractor.setForeground(Color.BLACK);
															removeContractor.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
															removeContractor.setFocusPainted(false);
															removeContractor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
															removeContractor.setBackground(new Color(255, 140, 0));
															removeContractor.setBounds(377, 395, 123, 39);
															ContractorFrm.add(removeContractor);
															
															JButton addContractor = new JButton("Add");
															addContractor.addActionListener(new ActionListener() {
																public void actionPerformed(ActionEvent e) {
																	addContractor contractorWin = new addContractor();
																	contractorWin.addContractorFrame.setVisible(true);
																}
															});
															addContractor.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/addIcon.png")));
															addContractor.setIconTextGap(0);
															addContractor.setHorizontalTextPosition(SwingConstants.RIGHT);
															addContractor.setForeground(Color.BLACK);
															addContractor.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
															addContractor.setFocusPainted(false);
															addContractor.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
															addContractor.setBackground(new Color(255, 140, 0));
															addContractor.setBounds(27, 395, 123, 39);
															ContractorFrm.add(addContractor);
		runClock();
		
		String[] s = new String[] {"david","barak"};
		
//		JPanel tabFrm = new JPanel();
//		tabFrm.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));
//		tabFrm.setBackground(new Color(255,140, 0));
//		
//		
//		tabFrm.setBounds(0, 0, 174, 517);
//		frmAdminWindow.getContentPane().add(tabFrm);
//		tabFrm.setLayout(null);
		
		
		JPanel tabFrm = new JPanel();
		tabFrm.setBounds(new Rectangle(0, 0, 1280, 0));
		tabFrm.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tabFrm.setBackground(new Color(62, 0, 110));
		
		
		tabFrm.setBounds(0, 0, 304, 750);
		frmAdminWindow.getContentPane().add(tabFrm);
		tabFrm.setLayout(null);
		
		
		
		JLabel lblLiveapp = new JLabel("LiveApp");
		lblLiveapp.setForeground(Color.WHITE);
		lblLiveapp.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblLiveapp.setBackground(new Color(34, 36, 39));
		lblLiveapp.setBounds(20, 46, 101, 33);
		tabFrm.add(lblLiveapp);
		
		JLabel label1 = new JLabel("");
		label1.setBounds(112, 103, 64, 64);
		tabFrm.add(label1);
		label1.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/userImg1.png")));
		
			
		HomeTab  = new JButton("Home");
		
		HomeTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/homeIcon.png")));
		HomeTab.setIconTextGap(10);
		HomeTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		HomeTab.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setPanel(open_Frm);
			setTabColorGray(HomeTab);
			
		}
});
		
		HomeTab.setRequestFocusEnabled(false);
		HomeTab.setForeground(new Color(255, 255, 255));
		HomeTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		HomeTab.setFocusTraversalKeysEnabled(false);
		HomeTab.setFocusPainted(false);
		HomeTab.setBorderPainted(false);
		HomeTab.setBorder(null);
		HomeTab.setBackground(new Color(86,70,119));
		HomeTab.setAlignmentX(1.0f);
		HomeTab.setBounds(0, 207, 304, 63);
		
		tabFrm.add(HomeTab);
		
		
		
		residentTab = new JButton("Residents");
		residentTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/residentIcon.png")));
		residentTab.setIconTextGap(10);
		residentTab.setRequestFocusEnabled(false);
		residentTab.setForeground(new Color(255, 255, 255));
		residentTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		residentTab.setFocusTraversalKeysEnabled(false);
		residentTab.setFocusPainted(false);
		residentTab.setBorderPainted(false);
		residentTab.setBorder(null);
		
		
		
		
		residentTab.setBackground(new Color(62, 0, 110));
		residentTab.setAlignmentX(1.0f);
		residentTab.setBounds(0, 270, 304, 63);
		residentTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				getResidetnsNames();
				setPanel(ResidentsFrm);
				setTabColorGray(residentTab);
				
			}
		});
		tabFrm.add(residentTab);
		DefectTab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		DefectTab.setBounds(0, 333, 304, 63);
		tabFrm.add(DefectTab);
		
		DefectTab = new JButton("Defects");
		DefectTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/DefectIcon.png")));
		DefectTab.setIconTextGap(10);
		DefectTab.setForeground(new Color(255, 255, 255));
		DefectTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				addDataDefectTable();
				setPanel(DefectFrm);
				setTabColorGray(DefectTab);
				
			}
		});
		DefectTab.setRequestFocusEnabled(false);
		DefectTab.setFocusTraversalKeysEnabled(false);
		DefectTab.setFocusPainted(false);
		DefectTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		DefectTab.setBorder(null);
		DefectTab.setBorderPainted(false);
		DefectTab.setBackground(new Color(62,0,110));

//		HomeTab.setIconTextGap(0);
//		HomeTab.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				setPanel(open_Frm);
//				
//				
//			}
//		});
//		HomeTab.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				HomeTab.setBackground(new Color(169,100,0));
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				HomeTab.setBackground(new Color(255,140, 0));
//			}
//		});
		
//		JButton DefectTab = new JButton("Defects");
//		DefectTab.setIconTextGap(0);
//		DefectTab.setForeground(new Color(0, 0, 0));
//		DefectTab.setHorizontalTextPosition(SwingConstants.RIGHT);
//		DefectTab.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				setPanel(DefectFrm);
//				try {
//					addDataDefectTable();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			}
//		});
//		DefectTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/DefectIcon.png")));
//		DefectTab.setRequestFocusEnabled(false);
//		DefectTab.setFocusTraversalKeysEnabled(false);
//		DefectTab.setFocusPainted(false);
//		DefectTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
//		DefectTab.setBorder(null);
//		DefectTab.setBorderPainted(false);
//		DefectTab.setBackground(new Color(255,140, 0));
//		DefectTab.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				DefectTab.setBackground(new Color(169,100,0));
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				DefectTab.setBackground(new Color(255,140, 0));
//			}
//			
//			
//		});
//		DefectTab.setAlignmentX(Component.RIGHT_ALIGNMENT);
//		DefectTab.setBounds(12, 216, 150, 73);
//		tabFrm.add(DefectTab);
//		HomeTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/homeIcon.png")));
//		HomeTab.setRequestFocusEnabled(false);
//		HomeTab.setHorizontalTextPosition(SwingConstants.RIGHT);
//		HomeTab.setForeground(new Color(0, 0, 0));
//		HomeTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
//		HomeTab.setFocusTraversalKeysEnabled(false);
//		HomeTab.setFocusPainted(false);
//		HomeTab.setBorderPainted(false);
//		HomeTab.setBorder(null);
//		HomeTab.setBackground(new Color(255, 140, 0));
//		HomeTab.setAlignmentX(1.0f);
//		HomeTab.setBounds(12, 20, 150, 73);
//		
//		tabFrm.add(HomeTab);
		
//		JButton residentTab = new JButton("Residents");
//		residentTab.setIconTextGap(0);
//		residentTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/residentIcon.png")));
//		residentTab.setRequestFocusEnabled(false);
//		residentTab.setHorizontalTextPosition(SwingConstants.RIGHT);
//		residentTab.setForeground(new Color(0, 0, 0));
//		residentTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
//		residentTab.setFocusTraversalKeysEnabled(false);
//		residentTab.setFocusPainted(false);
//		residentTab.setBorderPainted(false);
//		residentTab.setBorder(null);
//		residentTab.setBackground(new Color(255, 140, 0));
//		residentTab.setAlignmentX(1.0f);
//		residentTab.setBounds(12, 116, 150, 73);
//		residentTab.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				setPanel(ResidentsFrm);
//				try {
//					enterResidetnsName();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
//		residentTab.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				residentTab.setBackground(new Color(169,100,0));
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				residentTab.setBackground(new Color(255,140, 0));
//			}
//		});
//		tabFrm.add(residentTab);
		
		
		
		
		
		
		
		
//		JButton buildingsTab = new JButton("Buildings");
//		buildingsTab.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				setPanel(BuildingsFrm);
//				try {
//					addDataBuildingTable();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//			}
//		});
//		
//		buildingsTab.setIconTextGap(0);
//		buildingsTab.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/buildingsIcon.png")));
//		
//		buildingsTab.setRequestFocusEnabled(false);
//		buildingsTab.setHorizontalTextPosition(SwingConstants.RIGHT);
//		buildingsTab.setForeground(new Color(0, 0, 0));
//		buildingsTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
//		buildingsTab.setFocusTraversalKeysEnabled(false);
//		buildingsTab.setFocusPainted(false);
//		buildingsTab.setBorderPainted(false);
//		buildingsTab.setBorder(null);
//		buildingsTab.setBackground(new Color(255, 140, 0));
//		buildingsTab.setAlignmentX(1.0f);
//		buildingsTab.setBounds(12, 319, 150, 73);
//		buildingsTab.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				buildingsTab.setBackground(new Color(169,100,0));
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				buildingsTab.setBackground(new Color(255,140, 0));
//			}
//		});
//		
//				tabFrm.add(buildingsTab);
				
//				JButton contractorsTab = new JButton("Contractors");
//				contractorsTab.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						setPanel(ContractorFrm);
//						try {
//							addDataContractorTable();
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				});
//				contractorsTab.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseEntered(MouseEvent e) {
//						contractorsTab.setBackground(new Color(169,100,0));
//					}
//					
//					@Override
//					public void mouseExited(MouseEvent e) {
//						contractorsTab.setBackground(new Color(255,140, 0));
//					}
//				});
//				contractorsTab.setIcon(new ImageIcon(Admin_Window.class.getResource("/Media/contractorIcon.png")));
//				contractorsTab.setRequestFocusEnabled(false);
//				contractorsTab.setIconTextGap(0);
//				contractorsTab.setHorizontalTextPosition(SwingConstants.RIGHT);
//				contractorsTab.setForeground(new Color(0, 0, 0));
//				contractorsTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
//				contractorsTab.setFocusTraversalKeysEnabled(false);
//				contractorsTab.setFocusPainted(false);
//				contractorsTab.setBorderPainted(false);
//				contractorsTab.setBorder(null);
//				contractorsTab.setBackground(new Color(255, 140, 0));
//				contractorsTab.setAlignmentX(1.0f);
//				contractorsTab.setBounds(12, 416, 150, 73);
//				tabFrm.add(contractorsTab);
//        
//		
				
				
				

//		public static void addDataDefectTable() {
//		
//		
//		
//		
//		String[][] defects = new String[0][0] ;
//		int tableRows = 0;
//		try {
//		preStatment = con.prepareStatement("select defectID,field,status,opend,description from Defect where buildingID = ?");
//		preStatment.setInt(1,buildingIDSQL);
//		rs = preStatment.executeQuery();
//		tableRows = 0;
//		while(rs.next()) {
//		tableRows++;
//		
//		}
//		defects = new String[tableRows][4] ;
//		rs = preStatment.executeQuery();
//		while(rs.next()) {
//		for(int c = 0; c<5;c++) {
//		
//		
//		
//		if(c == 0) {
//		
//		
//		
//		defects[rs.getRow()-1][c] = rs.getString("description");
//		
//		
//		
//		}
//		
//		else if(c == 1) {
//		
//		
//		defects[rs.getRow()-1][c] = rs.getString("field");
//		
//		
//		
//		
//		}
//		
//		else if( c== 2) {
//		
//		
//		
//		defects[rs.getRow()-1][c] = rs.getString("status");
//		
//		
//		}
//		else if( c== 3) {
//		
//		defects[rs.getRow()-1][c] =rs.getDate("opend").toString();
//		
//		}
//		}
//		}
//		} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		}
//		
//		
//		
//		
//		
//		DefaultTableModel tableModel1 =
//		new DefaultTableModel(defects,new String[]{"Description", "Field","Status","Opend"});
//		tableModel1.setRowCount(tableRows);
//		defectTable.setModel(tableModel1);
//		
//		int[] coulmnWidth ={270,75,60,110};
//		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//		
//		messageTable.setModel(tableModel1);
//		int i = 0;
//		        for (int width : coulmnWidth) {
//		            TableColumn column = defectTable.getColumnModel().getColumn(i++);
//		            column.setMinWidth(width);
//		            column.setMaxWidth(width);
//		            column.setCellRenderer(centerRenderer);
//		            column.setPreferredWidth(width);
//		        }
//		}

       
       
       
		
	}
}
