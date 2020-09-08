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

public class alertBuildingAdd {

	public JFrame buildingAlertFrm;
	public static JLabel buildingslbl;


	/**
	 * Create the application.
	 */
	public alertBuildingAdd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		buildingAlertFrm = new JFrame();
		buildingAlertFrm.setType(Type.POPUP);
		buildingAlertFrm.setTitle("Add Building");
		buildingAlertFrm.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		buildingAlertFrm.setUndecorated(false);
		buildingAlertFrm.getContentPane().setBackground(new Color(255, 255, 255));
		buildingAlertFrm.setBounds(100, 100, 450, 193);
		buildingAlertFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildingAlertFrm.setLocation(500, 500);
		buildingAlertFrm.getContentPane().setLayout(null);
		
		buildingslbl = new JLabel("You Must Fill All The Details");
		buildingslbl.setForeground(new Color(0, 0, 0));
		buildingslbl.setIconTextGap(30);
		buildingslbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		buildingslbl.setBounds(new Rectangle(90, 49, 264, 36));
		buildingslbl.setHorizontalAlignment(SwingConstants.CENTER);
		buildingAlertFrm.getContentPane().add(buildingslbl);
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setForeground(new Color(0, 0, 0));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildingAlertFrm.dispose();
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
		buildingAlertFrm.getContentPane().add(separator);
		buildingAlertFrm.getContentPane().add(btnGotIt);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Problem With Details");
		lblDetailsAreIncorrect.setForeground(new Color(255, 140, 0));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setBounds(115, 13, 210, 36);
		buildingAlertFrm.getContentPane().add(lblDetailsAreIncorrect);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(alertDefectAdd.class.getResource("/Media/defectAlertIcon.png")));
		label.setBounds(29, 19, 60, 60);
		buildingAlertFrm.getContentPane().add(label);
		buildingAlertFrm.setLocationRelativeTo(null);
	}
}
