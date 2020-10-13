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

public class AddNotificationWindow {

	static public JFrame addNotificationFrame;
	static JTextArea descTxtField;
	static JButton btnOpennotification;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNotificationWindow window = new AddNotificationWindow();
					window.addNotificationFrame.setVisible(true);
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
	public AddNotificationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addNotificationFrame = new JFrame();
		addNotificationFrame.setUndecorated(true);
		
		addNotificationFrame.getContentPane().setForeground(new Color(255, 255, 255));
		addNotificationFrame.getContentPane().setBackground(new Color(34,36,39));
		addNotificationFrame.setTitle("Add notification");
		addNotificationFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/logo_transparent.png")).getImage());
		addNotificationFrame.setBounds(100, 100, 624, 530);
		addNotificationFrame.setDefaultCloseOperation(this.addNotificationFrame.DISPOSE_ON_CLOSE);
		addNotificationFrame.getContentPane().setLayout(null);
		addNotificationFrame.setLocationRelativeTo(null);
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		panel_1.setBackground(new Color(34, 36, 39));
		panel_1.setBounds(0, 0, 624, 530);
		addNotificationFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		descTxtField = new JTextArea();
		descTxtField.setBounds(154, 234, 304, 202);
		panel_1.add(descTxtField);
		descTxtField.setBackground(new Color(34, 36, 39));
		descTxtField.setLineWrap(true);
		descTxtField.setForeground(new Color(255, 255, 255));
		descTxtField.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		descTxtField.setSelectionColor(new Color(153, 153, 204));
		descTxtField.setCaretColor(new Color(153, 51, 255));
		descTxtField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(246, 184, 120, 37);
		panel_1.add(lblDescription);
		lblDescription.setForeground(new Color(255, 255, 255));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		
		btnOpennotification = new JButton("Add");
		btnOpennotification.setBounds(236, 462, 158, 39);
		panel_1.add(btnOpennotification);
		btnOpennotification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (descTxtField.getText().equals("")) {
//					btnOpennotification.setEnabled(false);
				}
				
				
				else {
					try {
						
						long millis=System.currentTimeMillis();  
						java.sql.Date date=new java.sql.Date(millis);   
						preStatment = con.prepareStatement("insert into Notification (buildingID,message,date)"
								+ "values(?,?,?)");
						
						preStatment.setInt(1,buildingIDSQL);
						preStatment.setString(2,descTxtField.getText());
						preStatment.setDate(3, date);
						preStatment.executeUpdate();
						alertDefectAdd alert = new alertDefectAdd();
						AddNotification notificationmsg = new AddNotification();
						notificationmsg.alertFrame.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
					
			}
		});
		btnOpennotification.addMouseListener(new MouseAdapter() {
	    	   @Override
				public void mouseEntered(MouseEvent e) {
	    		   btnOpennotification.setBackground(new Color(153,51,255));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					btnOpennotification.setBackground(new Color(34,36,39));
				}
	       });
		btnOpennotification.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnOpennotification.setForeground(new Color(255, 255, 255));
		btnOpennotification.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnOpennotification.setFocusPainted(false);
		btnOpennotification.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(153, 51, 255)));
		btnOpennotification.setBackground(new Color(34,36,39));
		
		panel = new JPanel();
		panel.setBounds(3, 56, 618, 102);
		panel_1.add(panel);
		panel.setBackground(new Color(102, 0, 204));
		panel.setLayout(null);
		
		JLabel lblAddAnotification = new JLabel("Add a Notification");
		lblAddAnotification.setBounds(204, 0, 199, 93);
		panel.add(lblAddAnotification);
		lblAddAnotification.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAddAnotification.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAnotification.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblAddAnotification.setForeground(new Color(255, 255, 255));
		lblAddAnotification.setIcon(new ImageIcon(AddNotificationWindow.class.getResource("/Media/adddefectIcon.png")));
		lblAddAnotification.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		
		closeLabel = new JLabel("");
		closeLabel.setBounds(588, 13, 24, 24);
		panel_1.add(closeLabel);
		closeLabel.setIcon(new ImageIcon(AddNotificationWindow.class.getResource("/Media/close.png")));
		closeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addNotificationFrame.dispose();
			}
		});
		closeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		closeLabel.setAlignmentX(1.0f);
		
		minimizeLabel = new JLabel("");
		minimizeLabel.setBounds(553, 13, 24, 24);
		panel_1.add(minimizeLabel);
		minimizeLabel.setIcon(new ImageIcon(AddNotificationWindow.class.getResource("/Media/minimize.png")));
		minimizeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addNotificationFrame.setState(Frame.ICONIFIED);
				
			}
		});
		minimizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeLabel.setAlignmentX(1.0f);
	}
}
