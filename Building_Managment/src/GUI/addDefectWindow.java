package GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Component;

public class addDefectWindow {

	static public JFrame addDefectFrame;
	static public JFrame addNotificationFrame;
	static JComboBox defectTypecombo;
	static JTextArea descTxtField;
	static JButton btnOpenDefect;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	static String databaseURL 
	= "jdbc:sqlserver://localhost;databaseName=BuildingsManagment;integratedSecurity=true;" ; 
	private static Connection con = Login_Page.con; 
	static int buildingIDSQL = Login_Page.buildingIDSQL;
	static String userPhone = Login_Page.getPhoneEntry().getText();
	static private JComboBox defectStatusCombo;
	private JLabel lblStatus;
	private JPanel panel;
	private JLabel closeLabel;
	private JLabel minimizeLabel;
	private JPanel panel_1;
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
	 */
	public addDefectWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addDefectFrame = new JFrame();
		addDefectFrame.setUndecorated(true);
		
		addDefectFrame.getContentPane().setForeground(new Color(255, 255, 255));
		addDefectFrame.getContentPane().setBackground(new Color(34,36,39));
		addDefectFrame.setTitle("Add Defect");
		addDefectFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/logo_transparent.png")).getImage());
		addDefectFrame.setBounds(100, 100, 624, 530);
		addDefectFrame.setDefaultCloseOperation(this.addDefectFrame.DISPOSE_ON_CLOSE);
		addDefectFrame.getContentPane().setLayout(null);
		addDefectFrame.setLocationRelativeTo(null);
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		panel_1.setBackground(new Color(34, 36, 39));
		panel_1.setBounds(0, 0, 624, 530);
		addDefectFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		descTxtField = new JTextArea();
		descTxtField.setBounds(154, 309, 304, 127);
		panel_1.add(descTxtField);
		descTxtField.setBackground(new Color(34, 36, 39));
		descTxtField.setLineWrap(true);
		descTxtField.setForeground(new Color(255, 255, 255));
		descTxtField.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		descTxtField.setSelectionColor(new Color(153, 153, 204));
		descTxtField.setCaretColor(new Color(153, 51, 255));
		descTxtField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(251, 273, 120, 37);
		panel_1.add(lblDescription);
		lblDescription.setForeground(new Color(255, 255, 255));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(358, 171, 62, 37);
		panel_1.add(lblType);
		lblType.setHorizontalTextPosition(SwingConstants.CENTER);
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		
		defectTypecombo = new JComboBox();
		defectTypecombo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		defectTypecombo.setBounds(329, 205, 136, 22);
		panel_1.add(defectTypecombo);
		defectTypecombo.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		defectTypecombo.setFocusable(false);
		defectTypecombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		defectTypecombo.setModel(new DefaultComboBoxModel(professionalField.values()));
		defectTypecombo.setRequestFocusEnabled(false);
		defectTypecombo.setLightWeightPopupEnabled(false);
		defectTypecombo.setForeground(Color.white);
		defectTypecombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		defectTypecombo.setFocusable(false);
		defectTypecombo.setFocusTraversalKeysEnabled(false);
		defectTypecombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		defectTypecombo.setBackground(new Color(34,36,39));
		defectTypecombo.setUI(new BasicComboBoxUI());
		
		btnOpenDefect = new JButton("Report");
		btnOpenDefect.setBounds(236, 462, 158, 39);
		panel_1.add(btnOpenDefect);
		btnOpenDefect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (descTxtField.getText().equals("")) {
//					alertDefectAdd alert = new alertDefectAdd();
//					alert.defectAlertFrm.setVisible(true);
				}
				
				
				else {
					try {
						preStatment = con.prepareStatement("insert into Defect (buildingID,field,opendBy,status,description)"
								+ "values(?,?,?,?,?)");
						
						preStatment.setInt(1,buildingIDSQL);
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
		btnOpenDefect.addMouseListener(new MouseAdapter() {
	    	   @Override
				public void mouseEntered(MouseEvent e) {
	    		   btnOpenDefect.setBackground(new Color(153,51,255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btnOpenDefect.setBackground(new Color(34,36,39));
				}
	       });
		btnOpenDefect.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnOpenDefect.setForeground(new Color(255, 255, 255));
		btnOpenDefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnOpenDefect.setFocusPainted(false);
		btnOpenDefect.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 255)));
		btnOpenDefect.setBackground(new Color(34,36,39));
		
		defectStatusCombo = new JComboBox();
		defectStatusCombo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		defectStatusCombo.setBounds(149, 205, 136, 22);
		panel_1.add(defectStatusCombo);
		defectStatusCombo.setModel(new DefaultComboBoxModel(Defect_Status.values()));
		defectStatusCombo.setRequestFocusEnabled(false);
		defectStatusCombo.setLightWeightPopupEnabled(false);
		defectStatusCombo.setForeground(Color.white);
		defectStatusCombo.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		defectStatusCombo.setFocusable(false);
		defectStatusCombo.setFocusTraversalKeysEnabled(false);
		defectStatusCombo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		defectStatusCombo.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		defectStatusCombo.setBackground(new Color(34,36,39));
		defectStatusCombo.setUI(new BasicComboBoxUI());
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(185, 171, 54, 37);
		panel_1.add(lblStatus);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setForeground(new Color(255, 255, 255));
		lblStatus.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		
		panel = new JPanel();
		panel.setBounds(3, 56, 618, 102);
		panel_1.add(panel);
		panel.setBackground(new Color(102, 0, 204));
		panel.setLayout(null);
		
		JLabel lblAddADefect = new JLabel("Report a Defect");
		lblAddADefect.setBounds(245, 5, 130, 93);
		panel.add(lblAddADefect);
		lblAddADefect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAddADefect.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddADefect.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblAddADefect.setForeground(new Color(255, 255, 255));
		lblAddADefect.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/addDefectIcon.png")));
		lblAddADefect.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		
		closeLabel = new JLabel("");
		closeLabel.setBounds(588, 13, 24, 24);
		panel_1.add(closeLabel);
		closeLabel.setIcon(new ImageIcon(addDefectWindow.class.getResource("/Media/close.png")));
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addDefectFrame.dispose();
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
				addDefectFrame.setState(Frame.ICONIFIED);
				
			}
		});
		minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeLabel.setAlignmentX(1.0f);
		((JLabel)defectTypecombo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	}
}
