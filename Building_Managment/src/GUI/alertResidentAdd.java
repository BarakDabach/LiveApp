package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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

public class alertResidentAdd {

	public JFrame ResidentAlertFrm;
	public static JLabel Residentslbl;


	/**
	 * Create the application.
	 */
	public alertResidentAdd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ResidentAlertFrm = new JFrame();
		ResidentAlertFrm.setType(Type.POPUP);
		ResidentAlertFrm.setTitle("Add Resident");
		ResidentAlertFrm.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		ResidentAlertFrm.setUndecorated(false);
		ResidentAlertFrm.getContentPane().setBackground(new Color(255, 255, 255));
		ResidentAlertFrm.setBounds(100, 100, 450, 193);
		ResidentAlertFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ResidentAlertFrm.setLocation(500, 500);
		ResidentAlertFrm.getContentPane().setLayout(null);
		
		Residentslbl = new JLabel("You Must Fill All The Details");
		Residentslbl.setForeground(new Color(0, 0, 0));
		Residentslbl.setIconTextGap(30);
		Residentslbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		Residentslbl.setBounds(new Rectangle(90, 49, 264, 36));
		Residentslbl.setHorizontalAlignment(SwingConstants.CENTER);
		ResidentAlertFrm.getContentPane().add(Residentslbl);
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setForeground(new Color(0, 0, 0));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResidentAlertFrm.dispose();
			}
		});
		btnGotIt.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnGotIt.setFocusPainted(false);
		btnGotIt.setBorder(null);
		btnGotIt.setBackground(new Color(255, 255, 255));
		btnGotIt.setBounds(0, 100, 432, 50);
		
		btnGotIt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnGotIt.setBackground(new Color(255,140,0));
				btnGotIt.setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnGotIt.setBackground(new Color(255,255,255));
				btnGotIt.setForeground(Color.black);
			}
		
		});
		
		JSeparator separator = new JSeparator();
		separator.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		separator.setBackground(Color.GRAY);
		separator.setBounds(2, 98, 428, 2);
		ResidentAlertFrm.getContentPane().add(separator);
		ResidentAlertFrm.getContentPane().add(btnGotIt);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Problem With Details");
		lblDetailsAreIncorrect.setForeground(new Color(255, 140, 0));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setBounds(115, 13, 210, 36);
		ResidentAlertFrm.getContentPane().add(lblDetailsAreIncorrect);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(alertDefectAdd.class.getResource("/Media/defectAlertIcon.png")));
		label.setBounds(29, 19, 60, 60);
		ResidentAlertFrm.getContentPane().add(label);
		ResidentAlertFrm.setLocationRelativeTo(null);
	}
}
