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
import Enums.Defect_Status;
import Enums.professionalField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addDefectWindowAdmin {

	static public JFrame addDefectFrame;
	static JComboBox defectTypecombo;
	static JTextArea descTxtField;
	static JButton btnOpenDefect;
	static JComboBox BuildingIDCombo;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	static String databaseURL 
	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 
	private static Connection con = Login_Page.con; 
	static int buildingIDSQL = Login_Page.buildingIDSQL;
	static String userPhone = Login_Page.getPhoneEntry().getText();
	static private JComboBox defectStatusCombo;
	private JLabel lblStatus;
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
	public addDefectWindowAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addDefectFrame = new JFrame();
		addDefectFrame.getContentPane().setBackground(new Color(192,192,192));
		addDefectFrame.setTitle("Add Defect");
		addDefectFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		addDefectFrame.setBounds(100, 100, 556, 477);
		addDefectFrame.setDefaultCloseOperation(this.addDefectFrame.DISPOSE_ON_CLOSE);
		addDefectFrame.getContentPane().setLayout(null);
		
		JLabel lblAddADefect = new JLabel("Add A Defect");
		lblAddADefect.setForeground(Color.BLACK);
		lblAddADefect.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/addDefectIcon.png")));
		lblAddADefect.setBounds(175, 13, 189, 58);
		lblAddADefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		addDefectFrame.getContentPane().add(lblAddADefect);
		
		descTxtField = new JTextArea();
		descTxtField.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		descTxtField.setSelectionColor(new Color(255,140,0));
		descTxtField.setCaretColor(Color.BLACK);
		descTxtField.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 140, 0)));
		descTxtField.setBounds(128, 219, 304, 127);
		addDefectFrame.getContentPane().add(descTxtField);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.BLACK);
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblDescription.setBounds(219, 183, 120, 37);
		addDefectFrame.getContentPane().add(lblDescription);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.BLACK);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblType.setBounds(205, 100, 120, 37);
		addDefectFrame.getContentPane().add(lblType);
		
		defectTypecombo = new JComboBox();
		defectTypecombo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 140, 0)));
		defectTypecombo.setFocusable(false);
		defectTypecombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		defectTypecombo.setModel(new DefaultComboBoxModel(professionalField.values()));
		defectTypecombo.setBounds(201, 133, 136, 37);
		defectTypecombo.setRequestFocusEnabled(false);
		defectTypecombo.setLightWeightPopupEnabled(false);
		defectTypecombo.setForeground(Color.BLACK);
		defectTypecombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		defectTypecombo.setFocusable(false);
		defectTypecombo.setFocusTraversalKeysEnabled(false);
		defectTypecombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		defectTypecombo.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 127, 80)));
		defectTypecombo.setBackground(Color.WHITE);
		((JLabel)defectTypecombo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		addDefectFrame.getContentPane().add(defectTypecombo);
		
		btnOpenDefect = new JButton("ADD");
		btnOpenDefect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (descTxtField.getText().equals("")) {
					alertDefectAdd alert = new alertDefectAdd();
					alert.defectAlertFrm.setVisible(true);
				}
				
				
				else {
					try {
						preStatment = con.prepareStatement("insert into Defect (buildingID,field,opendBy,status,description)"
								+ "values(?,?,?,?,?)");
						
						preStatment.setInt(1,Integer.parseInt(BuildingIDCombo.getSelectedItem().toString()));
						preStatment.setString(2,defectTypecombo.getSelectedItem().toString());
						preStatment.setString(3,userPhone);
						preStatment.setString(4,defectStatusCombo.getSelectedItem().toString());
						preStatment.setString(5,descTxtField.getText());
						preStatment.executeUpdate();
						alertDefectAdd alert = new alertDefectAdd();
						AddDefect defectmsg = new AddDefect();
						defectmsg.alertFrame.setVisible(true);
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
		addDefectFrame.getContentPane().add(btnOpenDefect);
		
		JButton clearMsg = new JButton("");
		clearMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descTxtField.setText("");
			}
		});
		clearMsg.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/clearMessageIcon.png")));
		clearMsg.addMouseListener(new MouseAdapter() {
	    	   @Override
				public void mouseEntered(MouseEvent e) {
	    		   clearMsg.setBackground(new Color(169,100,0));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					clearMsg.setBackground(new Color(255,140, 0));
				}
	       });
		clearMsg.setIconTextGap(0);
		clearMsg.setHorizontalTextPosition(SwingConstants.RIGHT);
		clearMsg.setForeground(Color.BLACK);
		clearMsg.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		clearMsg.setFocusPainted(false);
		clearMsg.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		clearMsg.setBackground(new Color(255, 140, 0));
		clearMsg.setBounds(128, 352, 37, 37);
		addDefectFrame.getContentPane().add(clearMsg);
		
		defectStatusCombo = new JComboBox();
		defectStatusCombo.setModel(new DefaultComboBoxModel(Defect_Status.values()));
		defectStatusCombo.setRequestFocusEnabled(false);
		defectStatusCombo.setLightWeightPopupEnabled(false);
		defectStatusCombo.setForeground(Color.BLACK);
		defectStatusCombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		defectStatusCombo.setFocusable(false);
		defectStatusCombo.setFocusTraversalKeysEnabled(false);
		defectStatusCombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		defectStatusCombo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 140, 0)));
		defectStatusCombo.setBackground(Color.WHITE);
		defectStatusCombo.setBounds(369, 133, 136, 37);
		addDefectFrame.getContentPane().add(defectStatusCombo);
		
		lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblStatus.setBounds(373, 100, 120, 37);
		addDefectFrame.getContentPane().add(lblStatus);
		
		JLabel lblBuilding = new JLabel("Building");
		lblBuilding.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuilding.setForeground(Color.BLACK);
		lblBuilding.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		lblBuilding.setBounds(35, 100, 120, 37);
		addDefectFrame.getContentPane().add(lblBuilding);
		
		BuildingIDCombo = new JComboBox();
		BuildingIDCombo.setModel(new DefaultComboBoxModel(Admin_Window.defectIDs.toArray()));
		BuildingIDCombo.setRequestFocusEnabled(false);
		BuildingIDCombo.setLightWeightPopupEnabled(false);
		BuildingIDCombo.setForeground(Color.BLACK);
		BuildingIDCombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		BuildingIDCombo.setFocusable(false);
		BuildingIDCombo.setFocusTraversalKeysEnabled(false);
		BuildingIDCombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		BuildingIDCombo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 140, 0)));
		BuildingIDCombo.setBackground(Color.WHITE);
		BuildingIDCombo.setBounds(31, 133, 136, 37);
		addDefectFrame.getContentPane().add(BuildingIDCombo);
	}
}
