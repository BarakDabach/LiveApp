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

public class Resident_Window {

	public JFrame frmUserWindow;
	private static JPanel DefectFrm;
	private JLabel lblDefectsStatus;
	private static JPanel open_Frm;
	private static JPanel ResidentsFrm;
	private static JPanel Aboutfrm;
	private static JPanel inboxFrm;
	private static JTextArea messagefield;
	private static JLabel clock;
	static JPanel[] panels;
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
			preStatment = con.prepareStatement("select defectID,field,status,opend,description from Defect where buildingID = ?");
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
		
		int[] coulmnWidth ={270,75,60,110};
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
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
	public Resident_Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		runClock();
		frmUserWindow = new JFrame();
		frmUserWindow.getContentPane().setBackground(new Color(0, 0, 0));
		frmUserWindow.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		frmUserWindow.setResizable(false);
		frmUserWindow.setTitle("User Window");
		frmUserWindow.setBounds(100, 100, 712, 552);
		frmUserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUserWindow.getContentPane().setLayout(null);
						

						
						
						
						inboxFrm = new JPanel();
						inboxFrm.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								messageTable.clearSelection();
							}
						});
						inboxFrm.setVisible(false);
						
						
						
						ResidentsFrm = new JPanel();
						ResidentsFrm.setVisible(false);
						
						
						
						DefectFrm = new JPanel();
						DefectFrm.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								defectTable.clearSelection();
							}
						});
						DefectFrm.setVisible(false);
						
							   
						
						
						
						
						DefectFrm.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
						DefectFrm.setRequestFocusEnabled(false);
						DefectFrm.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						DefectFrm.setBounds(174, 0, 532, 517);
						DefectFrm.setBackground(new Color(0, 0, 0));
						frmUserWindow.getContentPane().add(DefectFrm);
						DefectFrm.setLayout(null);
						
						lblDefectsStatus = new JLabel("Defects");
						lblDefectsStatus.setForeground(new Color(255, 255, 255));
						lblDefectsStatus.setHorizontalAlignment(SwingConstants.CENTER);
						lblDefectsStatus.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
						lblDefectsStatus.setBounds(236, 13, 73, 38);
						DefectFrm.add(lblDefectsStatus);
						
						JComboBox sortDefects = new JComboBox();
						sortDefects.setVerifyInputWhenFocusTarget(false);
						sortDefects.setOpaque(false);
						sortDefects.setUI(new BasicComboBoxUI());
						sortDefects.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								
								sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) defectTable.getModel());
								RowFilter<DefaultTableModel, Object> rf  = RowFilter.regexFilter(sortDefects.getSelectedItem().toString(),defectTable.getColumnModel().getColumnIndex("Status"));
		                sorter.setRowFilter(rf);
		                defectTable.setRowSorter(sorter);
							}
						});
						Defect_Status[]defectsVal = Defect_Status.values();
						String[] enumVal = new String[Defect_Status.values().length+1];
						enumVal[0] = "";
						for(int i = 1;i<enumVal.length;i++) {
							
							enumVal[i] = defectsVal[i-1].toString();
							
						}
						sortDefects.setAlignmentY(Component.BOTTOM_ALIGNMENT);
						sortDefects.setAlignmentX(Component.RIGHT_ALIGNMENT);
						sortDefects.setLightWeightPopupEnabled(false);
						sortDefects.setFocusTraversalKeysEnabled(false);
						sortDefects.setFocusable(false);
						sortDefects.setForeground(new Color(255, 255, 255));
						sortDefects.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
						sortDefects.setModel(new DefaultComboBoxModel(enumVal));
						((JLabel)sortDefects.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
						sortDefects.setRequestFocusEnabled(false);
						sortDefects.setBackground(new Color(51, 51, 51));
						sortDefects.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 127, 80)));
						sortDefects.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
						sortDefects.setBounds(199, 120, 148, 22);
						
						
						
						DefectFrm.add(sortDefects);
						defectTable = new JTable();
						defectTable.setOpaque(false);
						defectTable.setShowGrid(false);
						defectTable.setUI(new BasicTableUI());
						defectTable.setAutoCreateRowSorter(true);
						defectTable.getTableHeader().setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
						defectTable.getTableHeader().setBackground(new Color(51, 51,51));
						defectTable.setFocusTraversalKeysEnabled(false);
						defectTable.setFocusable(false);
						defectTable.setBackground(new Color(51, 51, 51));
						defectTable.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
						defectTable.setRowMargin(5);
						defectTable.setForeground(new Color(255, 255, 255));
						defectTable.setSelectionForeground(new Color(255, 255, 255));
						defectTable.setShowVerticalLines(false);
						defectTable.setSelectionBackground(new Color(255, 140, 0));
						defectTable.setRequestFocusEnabled(false);
						defectTable.setRowHeight(50);
						defectTable.setIntercellSpacing(new Dimension(0, 0));
						defectTable.setMinimumSize(new Dimension(0, 0));
						defectTable.setAlignmentX(Component.RIGHT_ALIGNMENT);
						defectTable.setBounds(new Rectangle(0, 0, 508, 508));
						defectTable.setBounds(18, 108, 508, 347);
						defectTable.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
						defectTable.setGridColor(new Color(51, 51, 51));
						
						       JScrollPane tablePanel = new JScrollPane(defectTable);
						       tablePanel.setForeground(new Color(255, 255, 255));
						       tablePanel.setAutoscrolls(true);
						       tablePanel.setBackground(new Color(51, 51, 51));
						       tablePanel.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
						       tablePanel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
						       tablePanel.setBounds(new Rectangle(12, 180, 508, 229));
						       DefectFrm.add(tablePanel, BorderLayout.CENTER);
						       
						       JButton btnAddDefect = new JButton("Add Defect");
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
						       btnAddDefect.setBackground(new Color(0, 0, 0));
						       btnAddDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
						       btnAddDefect.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 153, 0)));
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
						       btnRemoveDefect.setBackground(new Color(0, 0, 0));
						       btnRemoveDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
						       btnRemoveDefect.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 153, 0)));
						       btnRemoveDefect.setBounds(247, 465, 143, 39);
						       btnRemoveDefect.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnRemoveDefect.setBackground(new Color(169,100,0));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnRemoveDefect.setBackground(new Color(0,0, 0));
					}
				});
						       DefectFrm.add(btnRemoveDefect);
						       
						       JLabel lblSortBy = new JLabel("Sort By:");
						       lblSortBy.setForeground(new Color(255, 255, 255));
						       lblSortBy.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
						       lblSortBy.setBounds(249, 93, 61, 22);
						       DefectFrm.add(lblSortBy);
						
						       
						       
						       
						ResidentsFrm.setBackground(new Color(169, 169, 169));
						ResidentsFrm.setBounds(174, 0, 532, 517);
						frmUserWindow.getContentPane().add(ResidentsFrm);
						ResidentsFrm.setLayout(null);
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
						
						scrollPane.setFocusTraversalKeysEnabled(false);
						scrollPane.setFocusable(false);
						scrollPane.setBounds(23, 101, 260, 144);
						
								ResidentsFrm.add(scrollPane);
								
	
								
								
								JLabel lblResidents = new JLabel("Residents");
								lblResidents.setForeground(new Color(0, 0, 0));
								lblResidents.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
								lblResidents.setBounds(236, 16, 95, 22);
								ResidentsFrm.add(lblResidents);
								
								JLabel lblSelectResident = new JLabel("Select Resident");
								lblSelectResident.setForeground(new Color(0, 0, 0));
								lblSelectResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
								lblSelectResident.setBounds(23, 76, 123, 22);
								ResidentsFrm.add(lblSelectResident);
								
								JButton sendMessagebtn = new JButton("Send");
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
								sendMessagebtn.setBounds(402, 465, 118, 39);
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
								lblWriteAMessage.setForeground(new Color(0, 0, 0));
								lblWriteAMessage.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
								lblWriteAMessage.setBounds(23, 249, 143, 22);
								ResidentsFrm.add(lblWriteAMessage);
								
								JButton clearMessageBtn = new JButton("Clear");
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
								clearMessageBtn.setBounds(23, 465, 68, 39);
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
								messagefield.setSelectionColor(new Color(255, 140, 0));
								messagefield.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
								messagefield.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 140, 0)));
								messagefield.setBounds(23, 273, 341, 179);
								ResidentsFrm.add(messagefield);
						
						
						
						
						
						
						
						
						inboxFrm.setBackground(new Color(169, 169, 169));
						inboxFrm.setBounds(174, 0, 532, 517);
						frmUserWindow.getContentPane().add(inboxFrm);
						inboxFrm.setLayout(null);
						
						JLabel Inboxlbl = new JLabel("Inbox");
						Inboxlbl.setHorizontalAlignment(SwingConstants.CENTER);
						Inboxlbl.setForeground(Color.BLACK);
						Inboxlbl.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
						Inboxlbl.setBounds(238, 13, 73, 38);
						JTable incomingMessages = new JTable(5,5);
						incomingMessages.setTableHeader(new JTableHeader());
						incomingMessages.setBounds(120, 120, 50, 80);
						
						inboxFrm.add(Inboxlbl,incomingMessages);
						
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
						tablePanelmessage.setBounds(new Rectangle(12, 180, 508, 229));
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
							msgNames[0] = "";
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
					
						
				       sortInbox.setModel(new DefaultComboBoxModel(msgNames));
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
				       sortInbox.setBounds(199, 120, 148, 31);
				       inboxFrm.add(sortInbox);
				       
				       JLabel label_3 = new JLabel("Sort By:");
				       label_3.setForeground(Color.BLACK);
				       label_3.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
				       label_3.setBounds(249, 93, 61, 22);
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
				       deleteMsgBtn.setBounds(12, 422, 97, 38);
				       inboxFrm.add(deleteMsgBtn);
				
						
						open_Frm = new JPanel();
						open_Frm.setBackground(new Color(169, 169, 169));
						open_Frm.setBounds(174, 0, 532, 517);
						frmUserWindow.getContentPane().add(open_Frm);
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
						
						msg_lbl = new JLabel(Login_Page.num_of_msg);
						msg_lbl.setHorizontalTextPosition(SwingConstants.LEFT);
						msg_lbl.setHorizontalAlignment(SwingConstants.CENTER);
						msg_lbl.setForeground(Color.BLACK);
						msg_lbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
						msg_lbl.setBounds(114, 387, 308, 34);
						open_Frm.add(msg_lbl);
		
		
		String[] s = new String[] {"david","barak"};
		
		Aboutfrm = new JPanel();
		Aboutfrm.setVisible(false);
		Aboutfrm.setBackground(new Color(169, 169, 169));
		Aboutfrm.setBounds(174, 0, 532, 517);
		frmUserWindow.getContentPane().add(Aboutfrm);
		Aboutfrm.setLayout(null);
		
		JLabel lblThisProductWas = new JLabel("This Product Was Developed By Barak And David");
		lblThisProductWas.setForeground(new Color(0, 0, 0));
		lblThisProductWas.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblThisProductWas.setBounds(52, 48, 440, 36);
		Aboutfrm.add(lblThisProductWas);
		
		JLabel lblEmailsBarakdabachgmailcom = new JLabel("BarakDabach@gmail.com");
		lblEmailsBarakdabachgmailcom.setForeground(new Color(0, 0, 0));
		lblEmailsBarakdabachgmailcom.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblEmailsBarakdabachgmailcom.setBounds(176, 164, 272, 36);
		Aboutfrm.add(lblEmailsBarakdabachgmailcom);
		
		JLabel lblDavidnahumgmailcom = new JLabel("David2Nahum@gmail.com");
		lblDavidnahumgmailcom.setForeground(new Color(0, 0, 0));
		lblDavidnahumgmailcom.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblDavidnahumgmailcom.setBounds(176, 197, 226, 36);
		Aboutfrm.add(lblDavidnahumgmailcom);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/EmailIcon.png")));
		label_1.setBounds(91, 169, 69, 64);
		Aboutfrm.add(label_1);
		
		JPanel tabFrm = new JPanel();
		tabFrm.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(0, 0, 0)));
		tabFrm.setBackground(new Color(51, 51, 51));
		
		
		tabFrm.setBounds(0, 0, 174, 517);
		frmUserWindow.getContentPane().add(tabFrm);
		tabFrm.setLayout(null);
		
		JButton DefectTab = new JButton("Defects");
		DefectTab.setIconTextGap(0);
		DefectTab.setForeground(new Color(255, 255, 255));
		DefectTab.setHorizontalTextPosition(SwingConstants.RIGHT);
		DefectTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDataDefectTable();
				setPanel(DefectFrm);
				
			}
		});
		DefectTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/DefectIcon.png")));
		DefectTab.setRequestFocusEnabled(false);
		DefectTab.setFocusTraversalKeysEnabled(false);
		DefectTab.setFocusPainted(false);
		DefectTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		DefectTab.setBorder(null);
		DefectTab.setBorderPainted(false);
		DefectTab.setBackground(new Color(51, 51, 51));
		DefectTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DefectTab.setBackground(new Color(169,100,0));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				DefectTab.setBackground(new Color(51, 51, 51));
			}
			
			
		});
		DefectTab.setAlignmentX(Component.RIGHT_ALIGNMENT);
		DefectTab.setBounds(12, 216, 150, 73);
		tabFrm.add(DefectTab);
		
		JButton HomeTab = new JButton("Home");
		HomeTab.setIconTextGap(0);
		HomeTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setPanel(open_Frm);
				
				
			}
		});
		HomeTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				HomeTab.setBackground(new Color(169,100,0));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				HomeTab.setBackground(new Color(51, 51, 51));
			}
		});
		HomeTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/homeIcon.png")));
		HomeTab.setRequestFocusEnabled(false);
		HomeTab.setHorizontalTextPosition(SwingConstants.RIGHT);
		HomeTab.setForeground(new Color(255, 255, 255));
		HomeTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		HomeTab.setFocusTraversalKeysEnabled(false);
		HomeTab.setFocusPainted(false);
		HomeTab.setBorderPainted(false);
		HomeTab.setBorder(null);
		HomeTab.setBackground(new Color(51, 51, 51));
		HomeTab.setAlignmentX(1.0f);
		HomeTab.setBounds(12, 20, 150, 73);
		
		tabFrm.add(HomeTab);
		
		JButton residentTab = new JButton("Residents");
		residentTab.setIconTextGap(0);
		residentTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/residentIcon.png")));
		residentTab.setRequestFocusEnabled(false);
		residentTab.setHorizontalTextPosition(SwingConstants.RIGHT);
		residentTab.setForeground(new Color(255, 255, 255));
		residentTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		residentTab.setFocusTraversalKeysEnabled(false);
		residentTab.setFocusPainted(false);
		residentTab.setBorderPainted(false);
		residentTab.setBorder(null);
		residentTab.setBackground(new Color(51, 51, 51));
		residentTab.setAlignmentX(1.0f);
		residentTab.setBounds(12, 116, 150, 73);
		residentTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getResidetnsNames();
				setPanel(ResidentsFrm);
				
			}
		});
		residentTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				residentTab.setBackground(new Color(169,100,0));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				residentTab.setBackground(new Color(51, 51, 51));
			}
		});
		tabFrm.add(residentTab);
		
		
		
		
		
		
		
		
		JButton inboxTab = new JButton("Inbox");
		inboxTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMsgTable();
				setPanel(inboxFrm);
			}
		});
		
		inboxTab.setIconTextGap(0);
		inboxTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/inboxIcon.png")));
		
		inboxTab.setRequestFocusEnabled(false);
		inboxTab.setHorizontalTextPosition(SwingConstants.RIGHT);
		inboxTab.setForeground(new Color(255, 255, 255));
		inboxTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		inboxTab.setFocusTraversalKeysEnabled(false);
		inboxTab.setFocusPainted(false);
		inboxTab.setBorderPainted(false);
		inboxTab.setBorder(null);
		inboxTab.setBackground(new Color(51, 51, 51));
		inboxTab.setAlignmentX(1.0f);
		inboxTab.setBounds(12, 319, 150, 73);
		inboxTab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				inboxTab.setBackground(new Color(169,100,0));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				inboxTab.setBackground(new Color(51, 51, 51));
			}
		});
		
				tabFrm.add(inboxTab);
				
				JButton aboutTab = new JButton("About");
				aboutTab.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setPanel(Aboutfrm);
					}
				});
				aboutTab.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						aboutTab.setBackground(new Color(169,100,0));
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						aboutTab.setBackground(new Color(51, 51, 51));
					}
				});
				aboutTab.setIcon(new ImageIcon(Resident_Window.class.getResource("/Media/aboutIcon.png")));
				aboutTab.setRequestFocusEnabled(false);
				aboutTab.setIconTextGap(0);
				aboutTab.setHorizontalTextPosition(SwingConstants.RIGHT);
				aboutTab.setForeground(new Color(255, 255, 255));
				aboutTab.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
				aboutTab.setFocusTraversalKeysEnabled(false);
				aboutTab.setFocusPainted(false);
				aboutTab.setBorderPainted(false);
				aboutTab.setBorder(null);
				aboutTab.setBackground(new Color(51, 51, 51));
				aboutTab.setAlignmentX(1.0f);
				aboutTab.setBounds(12, 416, 150, 73);
				tabFrm.add(aboutTab);
        
		
       
       
       
		
	}
}
