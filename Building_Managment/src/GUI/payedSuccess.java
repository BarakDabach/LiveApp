package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.border.MatteBorder;
import javax.swing.JPanel;

public class payedSuccess {

	public JFrame alertFrame;


	/**
	 * Create the application.
	 */
	public payedSuccess() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alertFrame = new JFrame();
		alertFrame.setType(Type.POPUP);
		alertFrame.setTitle("Defect Added");
		alertFrame.setLocationRelativeTo(null);
		alertFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		alertFrame.setUndecorated(true);
		alertFrame.getContentPane().setBackground(new Color(34,36,39));
		alertFrame.setBounds(100, 100, 450, 193);
		alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alertFrame.setLocation(500, 500);
		alertFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(102, 255, 204)));
		panel.setBackground(new Color(34,36,39));
		panel.setBounds(0, 0, 448, 193);
		alertFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Payed Successfully");
		lblDetailsAreIncorrect.setBounds(122, 13, 208, 36);
		panel.add(lblDetailsAreIncorrect);
		lblDetailsAreIncorrect.setForeground(new Color(255, 255, 255));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(191, 55, 71, 70);
		panel.add(label);
		label.setIcon(new ImageIcon(payedSuccess.class.getResource("/Media/uploadSuccess.png")));
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setBounds(0, 138, 448, 53);
		panel.add(btnGotIt);
		btnGotIt.setForeground(new Color(255, 255, 255));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertFrame.dispose();
				
			}
		});
		btnGotIt.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnGotIt.setFocusPainted(false);
		btnGotIt.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(102, 255, 204)));
		btnGotIt.setBackground(new Color(34,36,39));
		
		btnGotIt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				
				btnGotIt.setBackground(new Color(102,255,104));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnGotIt.setBackground(new Color(34,36,39));
				
			}
		
		});
		alertFrame.setLocationRelativeTo(null);
	}
}
