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

public class alertDefectAdd {

	public JFrame defectAlertFrm;
	public static JLabel defectstslbl;


	/**
	 * Create the application.
	 */
	public alertDefectAdd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		defectAlertFrm = new JFrame();
		defectAlertFrm.setType(Type.POPUP);
		defectAlertFrm.setTitle("Add Defect");
		defectAlertFrm.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		defectAlertFrm.setUndecorated(false);
		defectAlertFrm.getContentPane().setBackground(new Color(255, 255, 255));
		defectAlertFrm.setBounds(100, 100, 450, 193);
		defectAlertFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		defectAlertFrm.setLocation(500, 500);
		defectAlertFrm.getContentPane().setLayout(null);
		
		defectstslbl = new JLabel("You Must Fill All The Details");
		defectstslbl.setForeground(new Color(0, 0, 0));
		defectstslbl.setIconTextGap(30);
		defectstslbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		defectstslbl.setBounds(new Rectangle(90, 49, 264, 36));
		defectstslbl.setHorizontalAlignment(SwingConstants.CENTER);
		defectAlertFrm.getContentPane().add(defectstslbl);
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setForeground(new Color(0, 0, 0));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defectAlertFrm.dispose();
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
		defectAlertFrm.getContentPane().add(separator);
		defectAlertFrm.getContentPane().add(btnGotIt);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Problem With Details");
		lblDetailsAreIncorrect.setForeground(new Color(255, 140, 0));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setBounds(115, 13, 210, 36);
		defectAlertFrm.getContentPane().add(lblDetailsAreIncorrect);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(alertDefectAdd.class.getResource("/Media/defectAlertIcon.png")));
		label.setBounds(29, 19, 60, 60);
		defectAlertFrm.getContentPane().add(label);
		defectAlertFrm.setLocationRelativeTo(null);
	}
}
