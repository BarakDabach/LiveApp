package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

import com.sun.deploy.uitoolkit.impl.fx.Utils;
import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;

import Java_Classes.Defect;
import Java_Classes.Message;
import Java_Classes.Resident;
import SQL.sqlDriver;
import javafx.scene.control.ComboBox;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.event.TableModelListener;
import javax.swing.plaf.TableUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.RowFilter;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Point;
import Enums.Cities;
import Enums.Defect_Status;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Admin_Window {

	public JFrame frmAdminWindow;
	private static JPanel DefectFrm;
	private static JPanel open_Frm;
	private static JPanel ResidentsFrm;
	private static JPanel Aboutfrm;
	private static JPanel inboxFrm;
	private static JPanel Notificationfrm;
	private static JButton residentTab;
	private static JButton HomeTab;
	private static JButton notificationTab;
	private static JButton DefectTab;
	private static JButton inboxTab;
	private static JButton sendMsgTab;
	private static JButton aboutTab;
	private static JButton accountTab;
	private static JTextArea messagefield;
	private static String[] enumVal ;
	static JPanel[] panels;
	static JButton[] tabButtons;
	private static JTable defectTable;
	private static JTable messageTable;
	public static JLabel lblWelcomeBack;
	private static  JList list;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	static String databaseURL 
	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 
	private static Connection con = Login_Page.con; 
	static int buildingIDSQL = Login_Page.buildingIDSQL;
	static int num_of_rows;
	static String[] residents = new String[1];
	static DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	static ListCellRenderer listcellRender ;
	static TableRowSorter<DefaultTableModel> sorter;
	static int[] messageIDs;
	static int[] defectIDs;
	static JLabel msg_lbl;
	static String[] msgNames;
	static JLabel totalDefectsLabel;
	
	
	
	public static void setPanel(JPanel currentPanel) {
		panels  = new JPanel[]{DefectFrm,open_Frm,ResidentsFrm,Aboutfrm,inboxFrm};
		
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
		tabButtons  = new JButton[]{residentTab,HomeTab,notificationTab,DefectTab,inboxTab,sendMsgTab,accountTab,aboutTab};
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

		
		 
		
		
	
	
	
	
	
	//adding the messages from the database to the messagestable
	
	public static  void addMsgTable() {


		
		String[][] msgDb = new String[1][5]; 
		int num_rows = 0;
		
		try {
			preStatment = con.prepareStatement("select messageID,sender,receive,content,date from Message where receive = ?");
			
			preStatment.setString(1,Login_Page.phoneEntry.getText());
			
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				num_rows++;
			}
			
			msgDb = new String[num_rows][3];
			messageIDs = new int[num_rows];
			
			rs = preStatment.executeQuery();
			int a = 0;
			while(rs.next()) {
				messageIDs[a] = rs.getInt("messageID");
				a++;
			}
			
			rs = preStatment.executeQuery();
			while(rs.next()) {
				
			
				for(int c = 0;c<3 ; c++) {
					if(c == 0) {
						int currow = rs.getRow()-1; 
						msgDb[currow][c] =  rs.getString(("sender"));						
						
					}
					
					else if(c == 1) {
						msgDb[rs.getRow()-1][c] =  rs.getString(("content"));
					}
					
					else {
						msgDb[rs.getRow()-1][c] =  rs.getTimestamp("date").toString();
					}
					
				}
			}
			
			
			
				
				for (int r = 0; r<msgDb.length ; r++) {
					
					PreparedStatement pre1 = con.prepareStatement
							("select CONCAT(`f_Name` ,' ' , `l_Name`) as fullname from Resident where phone = ?"); 
					pre1.setString(1,msgDb[r][0]);
					
					ResultSet rs1 = pre1.executeQuery();
					while(rs1.next()) {
						msgDb[r][0]  = rs1.getString(1);	
					}
					
					
				}
			
			
			DefaultTableModel msgModel = new DefaultTableModel(msgDb,new String[]{"sender","content","date"});
			msgModel.setRowCount(num_rows);
			
			int[] coulmnWidth ={100,300,110};
			
			
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			

			messageTable.setModel(msgModel);	
			int i = 0;
	        for (int width : coulmnWidth) {
	            TableColumn column = messageTable.getColumnModel().getColumn(i++);
	            column.setCellRenderer(centerRenderer);
	            column.setMinWidth(width);
	            column.setMaxWidth(width);
	            column.setPreferredWidth(width);
	        }
		}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//adding the residents names to the list of residents.
	
	public static  void getResidetnsNames() {


		
		
		int i = 0;
		try {

			preStatment = con.prepareStatement("select count(phone) as rowsCount from Resident where buildingID = ?");
			preStatment.setInt(1,buildingIDSQL);
			rs = preStatment.executeQuery();
			while(rs.next()) {
				num_of_rows = rs.getInt("rowsCount");
			}
			
			
			
			residents = new String[num_of_rows];
			preStatment = con.prepareStatement("select CONCAT(`f_Name`,' ',`l_Name`) as fullname from Resident where buildingID = ?");
			preStatment.setInt(1,buildingIDSQL);
			rs = preStatment.executeQuery();
			
			
			while(rs.next()) {
				if(!rs.getString("fullname").equalsIgnoreCase(Login_Page.userName))
					residents[i] = rs.getString("fullname");
					i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		DefaultListModel listModel = new DefaultListModel();
		
		for(i = 0; i<residents.length;i++) {
			listModel.addElement(residents[i]);
		}
		
		list.setModel(listModel);
	}
	

	
	
	
	
	//adding the data of defects to the defects table.
	public static void addDataDefectTable() {





		String[][] defects = new String[0][0] ;
		int tableRows = 0;
		try {
			preStatment = con.prepareStatement("select defectID,field,status,opend,description,closed from Defect where buildingID = ? and ISNULL(closed)");
			preStatment.setInt(1,buildingIDSQL);
			rs = preStatment.executeQuery();
			tableRows = 0;
			while(rs.next()) {
				tableRows++;
				
			}
			defects = new String[tableRows][4] ;
			defectIDs = new int[tableRows];
			rs = preStatment.executeQuery();
			
			while(rs.next()) {
				
				defectIDs[rs.getRow()-1] = rs.getInt("defectID");
				for(int c = 0; c<5;c++) {
					
					
					
					if(c == 0) {
						
						
							
							defects[rs.getRow()-1][c] = rs.getString("description");
						

						
					}
					
					else if(c == 1) {
						
						
							defects[rs.getRow()-1][c] = rs.getString("field");
						
						
						
						
					}
					
					else if( c== 2) {
						
						
						
							defects[rs.getRow()-1][c] = rs.getString("status");
						
						
					}
					else if( c== 3) {
						
							defects[rs.getRow()-1][c] =rs.getDate("opend").toString();
						
					}
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
	
		DefaultTableModel tableModel1 = 
				new DefaultTableModel(defects,new String[]{"Description", "Field","Status","Opend"});
		tableModel1.setRowCount(tableRows);
		defectTable.setModel(tableModel1);
		
		int[] coulmnWidth ={500,100,150,140};
		centerRenderer.setHorizontalAlignment( JLabel.LEFT );
		
		messageTable.setModel(tableModel1);	
		int i = 0;
        for (int width : coulmnWidth) {
            TableColumn column = defectTable.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setCellRenderer(centerRenderer);
            column.setPreferredWidth(width);
        }
	}

	
	public static void getNumOfDefects() {
		try {
			preStatment = con.prepareStatement("select COUNT(defectID) from Defect where buildingID = ? and closed IS NULL;");
			preStatment.setInt(1,buildingIDSQL);
			rs = preStatment.executeQuery();
			while(rs.next()) {
				String numOfOpenDefects = Integer.toString(rs.getInt(1));
				totalDefectsLabel.setText("There Are "+numOfOpenDefects+ " Defects Open");
			}

					
				
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				 
				}
				}
			}.start();
	}

	/**
	 * Create the application.
	 */
	public Admin_Window() {
		initialize();
		getNumOfDefects();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		
		frmAdminWindow = new JFrame();
		frmAdminWindow.setUndecorated(true);
		frmAdminWindow.getContentPane().setBackground(new Color(34, 36, 39));
		frmAdminWindow.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/logo_transparent.png")).getImage());
		frmAdminWindow.setResizable(false);
		frmAdminWindow.setTitle("User Window");
		frmAdminWindow.setBounds(100, 100, 1280, 750);
		frmAdminWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminWindow.getContentPane().setLayout(null);
		frmAdminWindow.setLocationRelativeTo(null);
		

		Notificationfrm = new JPanel();
		Notificationfrm.setBounds(new Rectangle(0, 0, 1280, 0));
		Notificationfrm.setVisible(false);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(null);
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(51, 153, 153));
		panel_6.setBounds(0, 0, 973, 142);
		Notificationfrm.add(panel_6);
		Notificationfrm.setBackground(new Color(34, 36, 39));
		Notificationfrm.setBounds(306, 45, 993, 705);
		frmAdminWindow.getContentPane().add(Notificationfrm);
		Notificationfrm.setLayout(null);
		
		
		open_Frm = new JPanel();
		open_Frm.setBorder(null);
		open_Frm.setBounds(new Rectangle(0, 0, 1280, 0));
		open_Frm.setBackground(new Color(34, 36, 39));
		open_Frm.setBounds(304, 45, 974, 705);
		frmAdminWindow.getContentPane().add(open_Frm);
		open_Frm.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(51, 153, 153));
		panel_3.setBounds(0, 0, 973, 142);
		open_Frm.add(panel_3);
		
		lblWelcomeBack = new JLabel("Welcome Back ");
		lblWelcomeBack.setBounds(32, 32, 464, 34);
		panel_3.add(lblWelcomeBack);
		lblWelcomeBack.setHorizontalTextPosition(SwingConstants.LEFT);
		lblWelcomeBack.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeBack.setForeground(new Color(255, 255, 255));
		lblWelcomeBack.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		
		JLabel lblBuildingStatus = new JLabel("Your Building Status");
		lblBuildingStatus.setHorizontalTextPosition(SwingConstants.LEFT);
		lblBuildingStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuildingStatus.setForeground(Color.WHITE);
		lblBuildingStatus.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblBuildingStatus.setBounds(32, 79, 308, 34);
		panel_3.add(lblBuildingStatus);
		
		JScrollPane notificationRubrik = new JScrollPane();
		notificationRubrik.setBorder(null);
		notificationRubrik.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		notificationRubrik.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		notificationRubrik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				notificationRubrik.setBackground(new Color(86,70,119));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				notificationRubrik.setBackground(new Color(152,102,204));
			}
		});
		notificationRubrik.setBackground(new Color(153, 102, 204));
		notificationRubrik.setBounds(116, 155, 743, 211);
		open_Frm.add(notificationRubrik);
		notificationRubrik.setLayout(null);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/alert.png")));
		label_4.setBounds(12, 13, 64, 64);
		notificationRubrik.add(label_4);
		
		JLabel lblElectrictyStoppesFor = new JLabel("Electricty Stoppes for One Hour");
		lblElectrictyStoppesFor.setHorizontalTextPosition(SwingConstants.LEFT);
		lblElectrictyStoppesFor.setHorizontalAlignment(SwingConstants.LEFT);
		lblElectrictyStoppesFor.setForeground(Color.WHITE);
		lblElectrictyStoppesFor.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblElectrictyStoppesFor.setBounds(22, 86, 308, 34);
		notificationRubrik.add(lblElectrictyStoppesFor);
		
		JPanel messagesRubrik = new JPanel();
		messagesRubrik.setBorder(null);
		messagesRubrik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				messagesRubrik.setBackground(new Color(86,70,119));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				messagesRubrik.setBackground(new Color(152,102,204));
			}
		});
		messagesRubrik.setBackground(new Color(153, 102, 204));
		messagesRubrik.setBounds(116, 379, 407, 130);
		open_Frm.add(messagesRubrik);
		messagesRubrik.setLayout(null);
		
		msg_lbl = new JLabel(Login_Page.num_of_msg);
		msg_lbl.setBounds(12, 90, 308, 34);
		messagesRubrik.add(msg_lbl);
		msg_lbl.setHorizontalTextPosition(SwingConstants.LEFT);
		msg_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		msg_lbl.setForeground(new Color(255, 255, 255));
		msg_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(12, 13, 64, 64);
		messagesRubrik.add(label_2);
		label_2.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/notification.png")));
		
		JPanel defectRubrik = new JPanel();
		defectRubrik.setBorder(null);
		defectRubrik.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		defectRubrik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				defectRubrik.setBackground(new Color(86,70,119));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				defectRubrik.setBackground(new Color(152,102,204));
			}
		});
		defectRubrik.setBackground(new Color(153, 102, 204));
		defectRubrik.setBounds(535, 379, 324, 130);
		open_Frm.add(defectRubrik);
		defectRubrik.setLayout(null);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/defects.png")));
		label_5.setBounds(12, 13, 64, 64);
		defectRubrik.add(label_5);
		
		totalDefectsLabel = new JLabel("Total 3 Defects");
		totalDefectsLabel.setBounds(12, 90, 300, 34);
		defectRubrik.add(totalDefectsLabel);
		totalDefectsLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		totalDefectsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		totalDefectsLabel.setForeground(Color.WHITE);
		totalDefectsLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		
		
		
		inboxFrm = new JPanel();
		inboxFrm.setBounds(new Rectangle(0, 0, 1280, 0));
		inboxFrm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				messageTable.clearSelection();
			}
		});
		inboxFrm.setVisible(false);
		
		
		ResidentsFrm = new JPanel();
		ResidentsFrm.setBounds(new Rectangle(0, 0, 1280, 0));
		ResidentsFrm.setVisible(false);
		
		
		
		DefectFrm = new JPanel();
		DefectFrm.setBounds(new Rectangle(0, 0, 1280, 0));
		DefectFrm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				defectTable.clearSelection();
			}
		});
		DefectFrm.setVisible(false);
		
		Aboutfrm = new JPanel();
		Aboutfrm.setBounds(new Rectangle(0, 0, 1280, 0));
		Aboutfrm.setVisible(false);
		Aboutfrm.setBackground(new Color(34, 36, 39));
		Aboutfrm.setBounds(306, 45, 993, 705);
		frmAdminWindow.getContentPane().add(Aboutfrm);
		Aboutfrm.setLayout(null);
		
		JLabel lblThisProductWas = new JLabel("This Product Was Developed By Barak And David");
		lblThisProductWas.setForeground(new Color(0, 0, 0));
		lblThisProductWas.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblThisProductWas.setBounds(215, 233, 440, 36);
		Aboutfrm.add(lblThisProductWas);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 973, 100);
		Aboutfrm.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(101, 26, 163));
		
		JLabel lblAboutOurApp = new JLabel("About Our App");
		lblAboutOurApp.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutOurApp.setForeground(Color.WHITE);
		lblAboutOurApp.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 23));
		lblAboutOurApp.setBounds(22, 30, 207, 38);
		panel_4.add(lblAboutOurApp);
		
		JLabel lblEmailsBarakdabachgmailcom = new JLabel("BarakDabach@gmail.com");
		lblEmailsBarakdabachgmailcom.setForeground(new Color(0, 0, 0));
		lblEmailsBarakdabachgmailcom.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblEmailsBarakdabachgmailcom.setBounds(339, 349, 272, 36);
		Aboutfrm.add(lblEmailsBarakdabachgmailcom);
		
		JLabel lblDavidnahumgmailcom = new JLabel("David2Nahum@gmail.com");
		lblDavidnahumgmailcom.setForeground(new Color(0, 0, 0));
		lblDavidnahumgmailcom.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblDavidnahumgmailcom.setBounds(339, 382, 226, 36);
		Aboutfrm.add(lblDavidnahumgmailcom);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/EmailIcon.png")));
		label_1.setBounds(254, 354, 69, 64);
		Aboutfrm.add(label_1);
		
			   
		
		
		
		
		DefectFrm.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		DefectFrm.setRequestFocusEnabled(false);
		DefectFrm.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		DefectFrm.setBounds(306, 45, 974, 705);
		DefectFrm.setBackground(new Color(34,36,39));
		frmAdminWindow.getContentPane().add(DefectFrm);
		DefectFrm.setLayout(null);
		
		JComboBox sortDefects = new JComboBox();
		enumVal = new String[Defect_Status.values().length+1];
		sortDefects.setModel(new DefaultComboBoxModel(enumVal));
		sortDefects.setVerifyInputWhenFocusTarget(false);
		sortDefects.setOpaque(false);
		sortDefects.setBackground(new Color(34,36,39));
		sortDefects.setUI(new BasicComboBoxUI());
		sortDefects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) defectTable.getModel());
				RowFilter<DefaultTableModel, Object> rf  = RowFilter.regexFilter(sortDefects.getSelectedItem().toString(),defectTable.getColumnModel().getColumnIndex("Status"));
				sorter.setRowFilter(rf);
        
				defectTable.setRowSorter(sorter);
			}
		});
		sortDefects.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		sortDefects.setAlignmentX(Component.RIGHT_ALIGNMENT);
		sortDefects.setLightWeightPopupEnabled(false);
		sortDefects.setFocusTraversalKeysEnabled(false);
		sortDefects.setFocusable(false);
		sortDefects.setForeground(Color.WHITE);
		sortDefects.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		((JLabel)sortDefects.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		sortDefects.setRequestFocusEnabled(false);
		sortDefects.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		sortDefects.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		sortDefects.setBounds(434, 212, 148, 22);
		
		
		sortDefects.setForeground(Color.WHITE);
		DefectFrm.add(sortDefects);
		
		JButton btnAddDefect = new JButton("Open a Defect");
		btnAddDefect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDefectWindow addWindow = new addDefectWindow();
				addWindow.addDefectFrame.setVisible(true);
			}
		});
		btnAddDefect.setForeground(new Color(255, 255, 255));
		btnAddDefect.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAddDefect.setIcon(null);
		btnAddDefect.setFocusPainted(false);
		btnAddDefect.setBackground(new Color(34,36,39));
		btnAddDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnAddDefect.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		btnAddDefect.setBounds(755, 537, 173, 39);
		
		
		       
		       
		DefectFrm.add(btnAddDefect);
		
		JButton btnRemoveDefect = new JButton("Remove Defect");
		btnRemoveDefect.setEnabled(false);
		btnRemoveDefect.setToolTipText("Select a defect");
		btnRemoveDefect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[]selectedRows = defectTable.getSelectedRows();
				
				for(int i = 0; i<selectedRows.length;i++) {
					try {
					preStatment = con.prepareStatement("delete from Defect where defectID = ?");
					preStatment.setInt(1,defectIDs[selectedRows[i]]);
	       			preStatment.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
									       		
					
				
				}
				
				addDataDefectTable();
			}
		});
		btnRemoveDefect.setForeground(new Color(255, 255, 255));
		btnRemoveDefect.setIcon(null);
		btnRemoveDefect.setFocusPainted(false);
		btnRemoveDefect.setBackground(new Color(34,36,39));
		btnRemoveDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnRemoveDefect.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		btnRemoveDefect.setBounds(560, 537, 173, 39);
		
				DefectFrm.add(btnRemoveDefect);
				
				JLabel lblSortBy = new JLabel("Sort By:");
				lblSortBy.setForeground(new Color(255, 255, 255));
				lblSortBy.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
				lblSortBy.setBounds(477, 177, 67, 22);
				DefectFrm.add(lblSortBy);
				
				JPanel panel_5 = new JPanel();
				panel_5.setBounds(0, 0, 973, 142);
				DefectFrm.add(panel_5);
				panel_5.setLayout(null);
				panel_5.setBorder(null);
				panel_5.setBackground(new Color(51, 153, 153));
				
				JLabel lblDefects = new JLabel("Defects");
				lblDefects.setHorizontalTextPosition(SwingConstants.LEFT);
				lblDefects.setHorizontalAlignment(SwingConstants.LEFT);
				lblDefects.setForeground(Color.WHITE);
				lblDefects.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
				lblDefects.setBounds(32, 32, 94, 34);
				panel_5.add(lblDefects);
				
				JLabel lblViewAndOpen = new JLabel("View/ Open Defects in Your Building");
				lblViewAndOpen.setHorizontalTextPosition(SwingConstants.LEFT);
				lblViewAndOpen.setHorizontalAlignment(SwingConstants.LEFT);
				lblViewAndOpen.setForeground(Color.WHITE);
				lblViewAndOpen.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
				lblViewAndOpen.setBounds(32, 79, 350, 34);
				panel_5.add(lblViewAndOpen);
				
				JPanel tablePanel = new JPanel();
				tablePanel.setBorder(null);
				tablePanel.setBackground(new Color(34, 36, 39));
				tablePanel.setBounds(41, 268, 887, 249);
				DefectFrm.add(tablePanel);	
				tablePanel.setLayout(new BorderLayout(0, 0));
				

				defectTable = new JTable();		
				defectTable.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent arg0) {
						
						if (defectTable.getSelectedRowCount() == 0) {
								
								btnRemoveDefect.setEnabled(false);
								System.out.println(defectTable.getSelectedRowCount());
							}

					}
				});
				defectTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						btnRemoveDefect.setEnabled(true);
						System.out.println(defectTable.getSelectedRowCount());
					}
				});





				
				defectTable.setBounds(new Rectangle(41, 268, 887, 249));
				defectTable.setOpaque(false);
				defectTable.setShowGrid(false);
				defectTable.setUI(new BasicTableUI());
				defectTable.setAutoCreateRowSorter(true);
				defectTable.getTableHeader().setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
				defectTable.setFocusTraversalKeysEnabled(false);
				defectTable.setBackground(new Color(34, 36, 39));
				defectTable.setBorder(null);
				defectTable.setRowMargin(10);
				defectTable.setForeground(new Color(255, 255, 255));
				defectTable.setSelectionForeground(new Color(255, 255, 255));
				defectTable.setShowVerticalLines(false);
				defectTable.setSelectionBackground(new Color(153, 102, 204));
				defectTable.setRequestFocusEnabled(false);
				defectTable.setRowHeight(50);
				defectTable.setIntercellSpacing(new Dimension(0, 0));
				

				defectTable.setFont(new Font("Yu Gothic", Font.BOLD, 16));
				defectTable.setGridColor(new Color(0, 0, 0));
				defectTable.getTableHeader().setBackground(new Color(34,36,39));
				defectTable.getTableHeader().setForeground(new Color(255,255,255));
				
				
		JScrollPane scrollTablePane = new JScrollPane(defectTable);
		scrollTablePane.setBorder(null);
		scrollTablePane.setBackground(new Color(34,36,39));
		
		tablePanel.add(scrollTablePane, BorderLayout.CENTER);
		scrollTablePane.setPreferredSize(new Dimension(887, 249));
		
		       
		       
		       
		ResidentsFrm.setBackground(new Color(34, 36, 39));
		ResidentsFrm.setBounds(306, 45, 993, 705);
		frmAdminWindow.getContentPane().add(ResidentsFrm);
		ResidentsFrm.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(101, 26, 163));
		panel_1.setBounds(0, 0, 973, 100);
		ResidentsFrm.add(panel_1);
		
		JLabel lblYourNeiboohors = new JLabel("Your Neiboohors");
		lblYourNeiboohors.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourNeiboohors.setForeground(Color.WHITE);
		lblYourNeiboohors.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 23));
		lblYourNeiboohors.setBounds(22, 30, 207, 38);
		panel_1.add(lblYourNeiboohors);
		list = new JList(new String[]{"",""}) ;
		list.setBounds(new Rectangle(0, 0, 300, 300));
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
		
		list.setBounds(23, 101, 260, 144);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(206, 220, 260, 144);
		
		scrollPane.setFocusTraversalKeysEnabled(false);
		scrollPane.setFocusable(false);
		
		ResidentsFrm.add(scrollPane);
		
		JLabel lblSelectResident = new JLabel("Select Resident");
		lblSelectResident.setBounds(206, 185, 123, 22);
		lblSelectResident.setForeground(new Color(0, 0, 0));
		lblSelectResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		ResidentsFrm.add(lblSelectResident);
		
		JButton sendMessagebtn = new JButton("Send");
		sendMessagebtn.setBounds(590, 616, 118, 39);
		sendMessagebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValuesList().size() == 0) {
					alertMsg  msgalert = new alertMsg();
					msgalert.errordetlbl.setText("You Must Select A Resident");
					msgalert.alertFrame.setVisible(true);
				}
				
				
				else {
					
					
					if(messagefield.getText().equals("")) {
						alertMsg msgalt = new alertMsg();
						alertMsg.errordetlbl.setText("Message Field Is Empty");
						msgalt.alertFrame.setVisible(true);;
					}
					
					
					
					else {
					
					for(int i = 0;i<list.getSelectedValuesList().size();i++) {
						try {
							
							preStatment = con.prepareStatement("select phone from Resident where f_Name = ? and"
									+ " l_Name = ? and buildingID = ? ");
							String[]name = ((String)list.getSelectedValuesList().get(i)).split(" ");
							preStatment.setString(1,name[0]);
							preStatment.setString(2,name[1]);
							preStatment.setInt(3,buildingIDSQL);
							
							rs = preStatment.executeQuery();
							String msgrec = "";
							while(rs.next()) {
								msgrec = rs.getString("phone");
							}
							
							preStatment = con.prepareStatement("insert into Message(sender,receive,content)"
									+ " values(?,?,?)");
							preStatment.setString(1, Login_Page.phoneEntry.getText());
							preStatment.setString(2, msgrec);
							preStatment.setString(3,messagefield.getText());
							preStatment.executeUpdate();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
					AddMsg msgalt = new AddMsg();
					msgalt.alertFrame.setVisible(true);
					messagefield.setText("");
				}
				}
			}
		});
		sendMessagebtn.setForeground(new Color(0, 0, 0));
		sendMessagebtn.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/sendMessageIcon.png")));
		sendMessagebtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		sendMessagebtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		sendMessagebtn.setFocusPainted(false);
		sendMessagebtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		sendMessagebtn.setBackground(new Color(255,140, 0));
		sendMessagebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sendMessagebtn.setBackground(new Color(169,100,0));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				sendMessagebtn.setBackground(new Color(0,0, 0));
			}
			
			
		});
		
		
		ResidentsFrm.add(sendMessagebtn);
		
		JLabel lblWriteAMessage = new JLabel("Write A Message");
		lblWriteAMessage.setBounds(207, 377, 143, 22);
		lblWriteAMessage.setForeground(new Color(0, 0, 0));
		lblWriteAMessage.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		ResidentsFrm.add(lblWriteAMessage);
		
		JButton clearMessageBtn = new JButton("Clear");
		clearMessageBtn.setBounds(211, 616, 68, 39);
		clearMessageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messagefield.setText("");
			}
		});
		clearMessageBtn.setForeground(new Color(0, 0, 0));
		clearMessageBtn.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/clearMessageIcon.png")));
		clearMessageBtn.setIconTextGap(0);
		clearMessageBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		clearMessageBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		clearMessageBtn.setFocusPainted(false);
		clearMessageBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		clearMessageBtn.setBackground(new Color(255, 140, 0));
		clearMessageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clearMessageBtn.setBackground(new Color(169,100,0));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				clearMessageBtn.setBackground(new Color(255,140, 0));
			}
			
			
		});
		
		
		
		
		ResidentsFrm.add(clearMessageBtn);
		
		messagefield = new JTextArea();
		messagefield.setBounds(207, 401, 341, 179);
		messagefield.setSelectionColor(new Color(255, 140, 0));
		messagefield.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		messagefield.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 140, 0)));
		ResidentsFrm.add(messagefield);
		
		Defect_Status[]defectsVal = Defect_Status.values();
		
		enumVal[0] = "";
		for(int i = 1;i<enumVal.length;i++) {
			
			enumVal[i] = defectsVal[i-1].toString();
			
		}
		
		JPanel tabFrm = new JPanel();
		tabFrm.setBounds(new Rectangle(0, 0, 1280, 0));
		tabFrm.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tabFrm.setBackground(new Color(62, 0, 110));
		
		
		tabFrm.setBounds(0, 0, 304, 750);
		frmAdminWindow.getContentPane().add(tabFrm);
		tabFrm.setLayout(null);
		
		DefectTab = new JButton("Defects");
		DefectTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/DefectIcon.png")));
		DefectTab.setIconTextGap(10);
		DefectTab.setForeground(new Color(255, 255, 255));
		DefectTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDataDefectTable();
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
		//		DefectTab.addMouseListener(new MouseAdapter() {
		//			@Override
		//			public void mouseEntered(MouseEvent e) {
		//				DefectTab.setBackground(new Color(34,36,39));
		//			}
		//			
		//			@Override
		//			public void mouseExited(MouseEvent e) {
		//				DefectTab.setBackground(new Color(62, 0, 110));
		//			}
		//			
		//			
		//		});
				
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
		residentTab.setBounds(0, 335, 304, 63);
		residentTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getResidetnsNames();
				setPanel(ResidentsFrm);
				setTabColorGray(residentTab);
				
			}
		});
		tabFrm.add(residentTab);
		DefectTab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		DefectTab.setBounds(0, 398, 304, 63);
		tabFrm.add(DefectTab);
			
			
			
			
			
			
			
			
			inboxTab = new JButton("Inbox");
			inboxTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/inboxIcon.png")));
			inboxTab.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addMsgTable();
					setPanel(inboxFrm);
					setTabColorGray(inboxTab);
				}
			});
			
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
			//		HomeTab.addMouseListener(new MouseAdapter() {
			//			@Override
			//			public void mouseEntered(MouseEvent e) {
			//				HomeTab.setBackground(new Color(34,36,39));
			//			}
			//			
			//			@Override
			//			public void mouseExited(MouseEvent e) {
			//				HomeTab.setBackground(new Color(62, 0, 110));
			//			}
			//		});
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
					
					
					
					notificationTab = new JButton("Notification");
					notificationTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/notificationIcon4.png")));
					notificationTab.setIconTextGap(10);
					notificationTab.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					notificationTab.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setPanel(Notificationfrm);
							setTabColorGray(notificationTab);
						}
					});
					notificationTab.setRequestFocusEnabled(false);
					notificationTab.setForeground(new Color(255, 255, 255));
					notificationTab.setForeground(Color.WHITE);
					notificationTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
					notificationTab.setFocusTraversalKeysEnabled(false);
					notificationTab.setFocusPainted(false);
					notificationTab.setBorderPainted(false);
					notificationTab.setBorder(null);
					notificationTab.setBackground(new Color(62, 0,110));
					notificationTab.setAlignmentX(1.0f);
					notificationTab.setBounds(0, 271, 304, 63);
					tabFrm.add(notificationTab);
			
			inboxTab.setIconTextGap(10);
			
			inboxTab.setRequestFocusEnabled(false);
			inboxTab.setForeground(new Color(255, 255, 255));
			inboxTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
			inboxTab.setFocusTraversalKeysEnabled(false);
			inboxTab.setFocusPainted(false);
			inboxTab.setBorderPainted(false);
			inboxTab.setBorder(null);
			inboxTab.setBackground(new Color(62, 0,110));
			inboxTab.setAlignmentX(1.0f);
			inboxTab.setBounds(0, 461, 304, 63);
			//		inboxTab.addMouseListener(new MouseAdapter() {
			//			@Override
			//			public void mouseEntered(MouseEvent e) {
			//				inboxTab.setBackground(new Color(34,36,39));
			//			}
			//			
			//			@Override
			//			public void mouseExited(MouseEvent e) {
			//				inboxTab.setBackground(new Color(62, 0, 110));
			//			}
			//		});

			tabFrm.add(inboxTab);
			
			sendMsgTab = new JButton("Send Message");
			sendMsgTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/sendMessage.png")));
			sendMsgTab.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setPanel(Aboutfrm);
					setTabColorGray(sendMsgTab);
				}
			});
			//				sendMsgTab.addMouseListener(new MouseAdapter() {
			//					@Override
			//					public void mouseEntered(MouseEvent e) {
			//						sendMsgTab.setBackground(new Color(34,36,39));
			//					}
			//					
			//					@Override
			//					public void mouseExited(MouseEvent e) {
			//						sendMsgTab.setBackground(new Color(62, 0, 110));
			//					}
			//				});
			sendMsgTab.setRequestFocusEnabled(false);
			sendMsgTab.setIconTextGap(10);
			sendMsgTab.setForeground(new Color(255, 255, 255));
			sendMsgTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
			sendMsgTab.setFocusTraversalKeysEnabled(false);
			sendMsgTab.setFocusPainted(false);
			sendMsgTab.setBorderPainted(false);
			sendMsgTab.setBorder(null);
			sendMsgTab.setBackground(new Color(62,0,110));
			sendMsgTab.setAlignmentX(1.0f);
			sendMsgTab.setBounds(0, 524, 304, 63);
			tabFrm.add(sendMsgTab);
			
			JLabel lblLiveapp = new JLabel("LiveApp");
			lblLiveapp.setForeground(Color.WHITE);
			lblLiveapp.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
			lblLiveapp.setBackground(new Color(34, 36, 39));
			lblLiveapp.setBounds(20, 46, 101, 33);
			tabFrm.add(lblLiveapp);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(51, 204, 153));
			panel.setBounds(20, 83, 140, 2);
			tabFrm.add(panel);
			
			accountTab = new JButton("Account");
			accountTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/user.png")));
			accountTab.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setTabColorGray(accountTab);
				}
			});
			accountTab.setRequestFocusEnabled(false);
			accountTab.setIconTextGap(10);
			accountTab.setForeground(Color.WHITE);
			accountTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
			accountTab.setFocusTraversalKeysEnabled(false);
			accountTab.setFocusPainted(false);
			accountTab.setBorderPainted(false);
			accountTab.setBorder(null);
			accountTab.setBackground(new Color(62,0,110));
			accountTab.setAlignmentX(1.0f);
			accountTab.setBounds(0, 587, 304, 63);
			tabFrm.add(accountTab);
			
			aboutTab = new JButton("About");
			aboutTab.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setPanel(Aboutfrm);
					setTabColorGray(aboutTab);
				}
			});
			aboutTab.setRequestFocusEnabled(false);
			aboutTab.setIconTextGap(30);
			aboutTab.setForeground(Color.WHITE);
			aboutTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
			aboutTab.setFocusTraversalKeysEnabled(false);
			aboutTab.setFocusPainted(false);
			aboutTab.setBorderPainted(false);
			aboutTab.setBorder(null);
			aboutTab.setBackground(new Color(62, 0, 110));
			aboutTab.setAlignmentX(1.0f);
			aboutTab.setBounds(0, 651, 304, 63);
			tabFrm.add(aboutTab);
			
			
			
			JLabel label = new JLabel("");
			label.setBounds(112, 103, 64, 64);
			tabFrm.add(label);
			label.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/userImg1.png")));




		
		
		
		
			inboxFrm.setBackground(new Color(34, 36, 39));
			inboxFrm.setBounds(306, 45, 993, 705);
			frmAdminWindow.getContentPane().add(inboxFrm);
			inboxFrm.setLayout(null);
			
			JPanel panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBackground(new Color(101, 26, 163));
			panel_2.setBounds(0, 0, 973, 100);
			inboxFrm.add(panel_2);
			
			JLabel lblYourInbox = new JLabel("Your Inbox");
			lblYourInbox.setHorizontalAlignment(SwingConstants.CENTER);
			lblYourInbox.setForeground(Color.WHITE);
			lblYourInbox.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 23));
			lblYourInbox.setBounds(22, 30, 207, 38);
			panel_2.add(lblYourInbox);
			
			JLabel lblIncomigMessages = new JLabel("Incoming Messages");
			lblIncomigMessages.setHorizontalAlignment(SwingConstants.CENTER);
			lblIncomigMessages.setHorizontalTextPosition(SwingConstants.RIGHT);
			lblIncomigMessages.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/incomingIcon.png")));
			lblIncomigMessages.setForeground(new Color(0, 0, 0));
			lblIncomigMessages.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			lblIncomigMessages.setBounds(12, 69, 190, 26);
			inboxFrm.add(lblIncomigMessages);
			
			messageTable = new JTable();
			messageTable.setSurrendersFocusOnKeystroke(true);
			messageTable.getTableHeader().setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
			messageTable.getTableHeader().setBackground(new Color(255, 140, 0));
			messageTable.setFocusTraversalKeysEnabled(false);
			messageTable.setFocusable(false);
			messageTable.setBackground(new Color(169, 169, 169));
			messageTable.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			messageTable.setRowMargin(5);
			messageTable.setForeground(new Color(0, 0, 0));
			messageTable.setSelectionForeground(new Color(192, 192, 192));
			messageTable.setShowVerticalLines(false);
			messageTable.setSelectionBackground(new Color(255, 140, 0));
			messageTable.setRequestFocusEnabled(false);
			messageTable.setRowHeight(50);
			
			messageTable.setIntercellSpacing(new Dimension(0, 0));
			messageTable.setMinimumSize(new Dimension(0, 0));
			messageTable.setAlignmentX(Component.RIGHT_ALIGNMENT);
			messageTable.setBounds(new Rectangle(0, 0, 508, 508));
			messageTable.setBounds(18, 108, 508, 347);
			messageTable.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			messageTable.setGridColor(new Color(255,255,255));
			JScrollPane tablePanelmessage = new JScrollPane(messageTable);
			tablePanelmessage.setAutoscrolls(true);
			tablePanelmessage.setBackground(new Color(169, 169, 169));
			tablePanelmessage.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
			tablePanelmessage.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			tablePanelmessage.setBounds(new Rectangle(190, 288, 508, 229));
			inboxFrm.add(tablePanelmessage, BorderLayout.CENTER);
			
			JComboBox sortInbox = new JComboBox();
			sortInbox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
	
	            sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) messageTable.getModel());
				RowFilter<DefaultTableModel, Object> rf  = RowFilter.regexFilter(sortInbox.getSelectedItem().toString(),messageTable.getColumnModel().getColumnIndex("sender"));
	            sorter.setRowFilter(rf);
	            messageTable.setRowSorter(sorter);
				}
			});
			
				
	//				       sortInbox.setModel(new DefaultComboBoxModel(msgNames));
	       sortInbox.setModel(new DefaultComboBoxModel(new String[]{ "a"}) );
	       sortInbox.setRequestFocusEnabled(false);
	       sortInbox.setLightWeightPopupEnabled(false);
	       sortInbox.setForeground(Color.BLACK);
	       sortInbox.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
	       sortInbox.setFocusable(false);
	       sortInbox.setFocusTraversalKeysEnabled(false);
	       sortInbox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	       sortInbox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 127, 80)));
	       sortInbox.setBackground(Color.WHITE);
	       sortInbox.setAlignmentY(1.0f);
	       sortInbox.setAlignmentX(1.0f);
	       sortInbox.setBounds(381, 230, 148, 31);
	       inboxFrm.add(sortInbox);
	       
	       JLabel label_3 = new JLabel("Sort By:");
	       label_3.setForeground(Color.BLACK);
	       label_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
	       label_3.setBounds(431, 203, 61, 22);
	       inboxFrm.add(label_3);
	       
	       JButton deleteMsgBtn = new JButton("Delete");
	       deleteMsgBtn.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		
	       		
	       		
	       		int currentmsgnum = messageIDs.length;
	       		int[]selectedRows = messageTable.getSelectedRows();
	       		
	       		for(int i = 0; i<selectedRows.length;i++) {
	       			try {
						preStatment = con.prepareStatement("delete from Message where messageID = ?");
						preStatment.setInt(1,messageIDs[selectedRows[i]]);
		       			preStatment.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	       							       		
	       			
	       		
	       		}
	       		
	       		addMsgTable();
	       		currentmsgnum -= selectedRows.length;
	       		
	       		msg_lbl.setText("You Have "+currentmsgnum+ " Messages" );
	       	}
	       });
	       deleteMsgBtn.addMouseListener(new MouseAdapter() {
	    	   @Override
				public void mouseEntered(MouseEvent e) {
	    		   deleteMsgBtn.setBackground(new Color(169,100,0));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					deleteMsgBtn.setBackground(new Color(255,140, 0));
				}
	       });
	       deleteMsgBtn.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/deletemsgIcon.png")));
	       deleteMsgBtn.setForeground(Color.BLACK);
	       deleteMsgBtn.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
	       deleteMsgBtn.setFocusPainted(false);
	       deleteMsgBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	       deleteMsgBtn.setBackground(new Color(255, 140, 0));
	       deleteMsgBtn.setBounds(190, 530, 97, 38);
	       inboxFrm.add(deleteMsgBtn);
	
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
		
		String[] msgNames = new String[3];
	       try {
	       
			preStatment = con.prepareStatement("select CONCAT(`f_Name` , ' ', `l_Name`) as fullname,phone from Resident where buildingID = ?");
			preStatment.setInt(1,buildingIDSQL);
			rs = preStatment.executeQuery();
			int i = -1;
			while(rs.next()) {
					i++;
			}
			
			msgNames = new String[i+1];
			//msgNames[0] = "";
			rs = preStatment.executeQuery();
			int k = 1;
			while(rs.next()) {
				
				if(!rs.getString("phone").equalsIgnoreCase(Login_Page.getPasswordEntry().getText())) {
					msgNames[k] = rs.getString("fullname");
					k++;
				}
				
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
					e.printStackTrace();
				}


	       String[] s = new String[] {"david","barak"};

		
       
       
       
		
	}
}
