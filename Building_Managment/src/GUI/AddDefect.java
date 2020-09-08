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

public class AddDefect {

	public JFrame alertFrame;
	public static JLabel defectaddlbl;


	/**
	 * Create the application.
	 */
	public AddDefect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		alertFrame = new JFrame();
		alertFrame.setType(Type.POPUP);
		alertFrame.setTitle("Defect Added");
		alertFrame.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		alertFrame.setUndecorated(false);
		alertFrame.getContentPane().setBackground(new Color(255, 255, 255));
		alertFrame.setBounds(100, 100, 450, 193);
		alertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alertFrame.setLocation(500, 500);
		alertFrame.getContentPane().setLayout(null);
		
		defectaddlbl = new JLabel("Defect Added Succesfully");
		defectaddlbl.setForeground(new Color(0, 0, 0));
		defectaddlbl.setIconTextGap(30);
		defectaddlbl.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		defectaddlbl.setBounds(new Rectangle(90, 49, 264, 36));
		defectaddlbl.setHorizontalAlignment(SwingConstants.CENTER);
		alertFrame.getContentPane().add(defectaddlbl);
		
		JButton btnGotIt = new JButton("Got It");
		btnGotIt.setForeground(new Color(0, 0, 0));
		btnGotIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertFrame.dispose();
				addDefectWindow.addDefectFrame.dispose();
				Resident_Window.addDataDefectTable();
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
//				btnLogin.setBackground(new Color(46,139,87));
				btnGotIt.setBackground(new Color(50,205,50));
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
		alertFrame.getContentPane().add(separator);
		alertFrame.getContentPane().add(btnGotIt);
		
		JLabel lblDetailsAreIncorrect = new JLabel("Message");
		lblDetailsAreIncorrect.setForeground(new Color(255, 140, 0));
		lblDetailsAreIncorrect.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		lblDetailsAreIncorrect.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailsAreIncorrect.setBounds(115, 13, 210, 36);
		alertFrame.getContentPane().add(lblDetailsAreIncorrect);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AddDefect.class.getResource("/Media/defectsuccescIcon.png")));
		label.setBounds(33, 20, 60, 60);
		alertFrame.getContentPane().add(label);
		alertFrame.setLocationRelativeTo(null);
	}
}
