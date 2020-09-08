package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class SignUpWindow {

	private JFrame frame;
	private JTextField phoneTextField;
	private JPasswordField confirmPasswordTextField;
	private JPasswordField PasswordTextField;
	private JTextField emailTextField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpWindow window = new SignUpWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUpWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.getContentPane().setLayout(null);
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		label.setIcon(new ImageIcon(SignUpWindow.class.getResource("/Media/close.png")));
		label.setBounds(597, 13, 32, 32);
		frame.getContentPane().add(label);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("Yu Gothic", Font.BOLD, 22));
		lblSignUp.setForeground(new Color(255, 255, 255));
		lblSignUp.setBounds(274, 165, 92, 32);
		frame.getContentPane().add(lblSignUp);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 153, 0));
		panel.setBounds(62, 199, 520, 2);
		frame.getContentPane().add(panel);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSignUp.setBackground(new Color(255,140,0));
				btnSignUp.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnSignUp.setBackground(new Color(0,0,0));
				btnSignUp.setForeground(Color.white);
			}
		});
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnSignUp.setFocusPainted(false);
		btnSignUp.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 153, 0)));
		btnSignUp.setBackground(Color.BLACK);
		btnSignUp.setBounds(184, 742, 286, 46);
		frame.getContentPane().add(btnSignUp);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(SignUpWindow.class.getResource("/Media/LoginIcon.png")));
		label_1.setBounds(253, 17, 128, 128);
		frame.getContentPane().add(label_1);
		
		JLabel lblBuildingDetails = new JLabel("Building Details");
		lblBuildingDetails.setForeground(Color.WHITE);
		lblBuildingDetails.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblBuildingDetails.setBackground(Color.WHITE);
		lblBuildingDetails.setBounds(415, 233, 112, 25);
		frame.getContentPane().add(lblBuildingDetails);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setForeground(new Color(51, 51, 51));
		panel_1.setFocusTraversalKeysEnabled(false);
		panel_1.setBounds(340, 290, 272, 428);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(31, 0, 26, 21);
		lblCity.setForeground(Color.WHITE);
		lblCity.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblCity.setBackground(Color.WHITE);
		panel_1.add(lblCity);
		
		JComboBox cityComboBox = new JComboBox();
		cityComboBox.setFocusable(false);
		cityComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		cityComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(204, 153, 0)));
		cityComboBox.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		cityComboBox.setBackground(new Color(0, 0, 0));
		cityComboBox.setFocusTraversalKeysEnabled(false);
		cityComboBox.setForeground(new Color(255, 255, 255));
		cityComboBox.setBounds(31, 24, 203, 22);
		panel_1.add(cityComboBox);
		cityComboBox.setUI(new BasicComboBoxUI());
		
		JComboBox streetComboBox = new JComboBox();
		streetComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		streetComboBox.setFocusable(false);
		streetComboBox.setForeground(Color.WHITE);
		streetComboBox.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		streetComboBox.setFocusTraversalKeysEnabled(false);
		streetComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(204, 153, 0)));
		streetComboBox.setBackground(Color.BLACK);
		streetComboBox.setBounds(31, 106, 203, 22);
		streetComboBox.setUI(new BasicComboBoxUI());
		panel_1.add(streetComboBox);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setForeground(Color.WHITE);
		lblStreet.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblStreet.setBackground(new Color(0, 0, 0));
		lblStreet.setBounds(31, 80, 45, 21);
		panel_1.add(lblStreet);
		
		JLabel lblBuildingNumber = new JLabel("Building Number");
		lblBuildingNumber.setForeground(Color.WHITE);
		lblBuildingNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblBuildingNumber.setBackground(Color.WHITE);
		lblBuildingNumber.setBounds(31, 172, 128, 25);
		panel_1.add(lblBuildingNumber);
		
		textField = new JTextField();
		textField.setFocusTraversalKeysEnabled(false);
		textField.setToolTipText("");
		textField.setSelectionColor(new Color(255, 165, 0));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 153, 0)));
		textField.setBackground(Color.BLACK);
		textField.setAutoscrolls(false);
		textField.setAlignmentX(1.0f);
		textField.setBounds(31, 198, 66, 31);
		panel_1.add(textField);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setFocusable(false);
		panel_2.setFocusTraversalKeysEnabled(false);
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(42, 290, 286, 428);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnResident = new JRadioButton("Resident");
		rdbtnResident.setBounds(97, 389, 92, 25);
		panel_2.add(rdbtnResident);
		rdbtnResident.setRequestFocusEnabled(false);
		rdbtnResident.setOpaque(false);
		rdbtnResident.setForeground(new Color(255, 255, 255));
		rdbtnResident.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		rdbtnResident.setBackground(Color.BLACK);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(12, 389, 79, 25);
		panel_2.add(rdbtnAdmin);
		rdbtnAdmin.setOpaque(false);
		rdbtnAdmin.setRequestFocusEnabled(false);
		rdbtnAdmin.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		rdbtnAdmin.setBackground(new Color(0, 0, 0));
		rdbtnAdmin.setForeground(new Color(255, 255, 255));
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(12, 355, 128, 25);
		panel_2.add(lblType);
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblType.setBackground(Color.WHITE);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(12, 290, 203, 31);
		panel_2.add(emailTextField);
		emailTextField.setToolTipText("");
		emailTextField.setSelectionColor(new Color(255, 165, 0));
		emailTextField.setHorizontalAlignment(SwingConstants.LEFT);
		emailTextField.setForeground(Color.WHITE);
		emailTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		emailTextField.setColumns(10);
		emailTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 153, 0)));
		emailTextField.setBackground(Color.BLACK);
		emailTextField.setAutoscrolls(false);
		emailTextField.setAlignmentX(1.0f);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setBounds(12, 265, 128, 25);
		panel_2.add(lblEmailAddress);
		lblEmailAddress.setForeground(Color.WHITE);
		lblEmailAddress.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblEmailAddress.setBackground(Color.WHITE);
		
		confirmPasswordTextField = new JPasswordField();
		confirmPasswordTextField.setBounds(12, 198, 203, 31);
		panel_2.add(confirmPasswordTextField);
		confirmPasswordTextField.setForeground(new Color(255, 255, 255));
		confirmPasswordTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		confirmPasswordTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(204, 153, 0)));
		confirmPasswordTextField.setBackground(Color.BLACK);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(12, 172, 128, 25);
		panel_2.add(lblConfirmPassword);
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblConfirmPassword.setBackground(Color.WHITE);
		
		PasswordTextField = new JPasswordField();
		PasswordTextField.setBounds(12, 106, 203, 31);
		panel_2.add(PasswordTextField);
		PasswordTextField.setForeground(new Color(255, 255, 255));
		PasswordTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		PasswordTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(204, 153, 0)));
		PasswordTextField.setBackground(Color.BLACK);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 80, 112, 25);
		panel_2.add(lblPassword);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblPassword.setBackground(Color.WHITE);
		
		phoneTextField = new JTextField();
		phoneTextField.setBounds(12, 24, 203, 31);
		panel_2.add(phoneTextField);
		phoneTextField.setAutoscrolls(false);
		phoneTextField.setToolTipText("");
		phoneTextField.setSelectionColor(new Color(255, 165, 0));
		phoneTextField.setHorizontalAlignment(SwingConstants.LEFT);
		phoneTextField.setForeground(Color.WHITE);
		phoneTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		phoneTextField.setColumns(10);
		phoneTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(255, 153, 0)));
		phoneTextField.setBackground(Color.BLACK);
		phoneTextField.setAlignmentX(1.0f);
		
		JLabel label_2 = new JLabel("Phone Number");
		label_2.setBounds(12, 0, 112, 25);
		panel_2.add(label_2);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		label_2.setBackground(Color.WHITE);
		
		JLabel lblPersonalDetails = new JLabel("Personal Details");
		lblPersonalDetails.setForeground(Color.WHITE);
		lblPersonalDetails.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		lblPersonalDetails.setBackground(Color.WHITE);
		lblPersonalDetails.setBounds(128, 233, 112, 25);
		frame.getContentPane().add(lblPersonalDetails);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(204, 153, 0));
		panel_3.setBounds(80, 260, 491, 2);
		frame.getContentPane().add(panel_3);
		frame.setBounds(100, 100, 641, 801);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
