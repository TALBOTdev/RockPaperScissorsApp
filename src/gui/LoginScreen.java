package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

/**
 * 
 * @author Ben Talbot
 * Launches the login GUI
 * Takes in the client IP address and username and sends it to the ClientWindow
 */
public class LoginScreen extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textFieldAddress;
	private JTextField textFieldUsername;
	private JButton btnSubmit;

	/**
	 * Initializes the GUI elements 
	 */
	public LoginScreen() {
		setTitle("Ben's Rock Paper Scissors");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setMaximumSize(new Dimension(200, 50));
		lblNewLabel.setMinimumSize(new Dimension(200, 50));
		lblNewLabel.setPreferredSize(new Dimension(200, 50));
		lblNewLabel.setSize(new Dimension(200, 50));
		lblNewLabel.setIcon(new ImageIcon(LoginScreen.class.getResource("/res/Rock Paper Scissors Logo2.png")));
		lblNewLabel.setBounds(0, 11, 274, 156);
		contentPane.add(lblNewLabel);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setBounds(42, 175, 197, 30);
		contentPane.add(textFieldAddress);
		textFieldAddress.setColumns(10);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(42, 242, 197, 30);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblAddress = new JLabel("I.P Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(42, 160, 95, 14);
		contentPane.add(lblAddress);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(42, 227, 96, 14);
		contentPane.add(lblUsername);
		
		JButton btnSubmit = new JButton("PLAY!");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSubmit.setBounds(68, 324, 150, 39);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(this);
		

	}

	/**
	 * Sends the IP Address and username to the ClientWindow class when the submit button is pressed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String ipAddress = textFieldAddress.getText();
		String userName = textFieldUsername.getText();
		this.dispose();
		new ClientWindow(ipAddress, userName).setVisible(true);
		
	}
}
