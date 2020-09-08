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
import java.awt.SystemColor;

public class SignUpWindow {

	private JFrame frame;
	
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
		frame.getContentPane().setBackground(new Color(34, 36, 39));
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 0, 204));
		panel.setBounds(62, 199, 520, 2);
		frame.getContentPane().add(panel);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(SignUpWindow.class.getResource("/Media/logo_transparent.png")));
		label_1.setBounds(235, 13, 172, 206);
		frame.getContentPane().add(label_1);
		
		JLabel lblSignUp = new JLabel("Sign up to LiveApp");
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblSignUp.setBackground(new Color(34, 36, 39));
		lblSignUp.setBounds(62, 163, 241, 33);
		frame.getContentPane().add(lblSignUp);
		
		JLabel lblPersonalDetails = new JLabel("Personal Details");
		lblPersonalDetails.setForeground(Color.WHITE);
		lblPersonalDetails.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPersonalDetails.setBackground(new Color(34, 36, 39));
		lblPersonalDetails.setBounds(62, 245, 143, 25);
		frame.getContentPane().add(lblPersonalDetails);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 0, 204));
		panel_1.setBounds(62, 270, 207, 2);
		frame.getContentPane().add(panel_1);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnSignUp.setFocusPainted(false);
		btnSignUp.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(127, 255, 212)));
		btnSignUp.setBackground(new Color(34, 36, 39));
		btnSignUp.setBounds(216, 709, 198, 41);
		frame.getContentPane().add(btnSignUp);
		
		JLabel label_2 = new JLabel("Don't have an account? Sign Up");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		label_2.setBounds(203, 763, 247, 25);
		frame.getContentPane().add(label_2);
		frame.setBounds(100, 100, 641, 801);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
