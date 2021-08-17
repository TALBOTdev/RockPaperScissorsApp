package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.ClientHandler;
import server.ServerInstance;
import utility.LoggerUtil;
import utility.Message;

import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * @author Ben Talbot
 * 
 * The main server window. 
 * Logs server traffic
 */
public class ServerWindow extends JFrame implements Runnable{
	public ServerWindow() {
	}

	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scroll;
	private JLabel lblServer;

	
	
	@Override
	public void run() {
		initializeWindow();
		
	}
	
	/**
	 * Initializes the GUI elements
	 */
	public void initializeWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 556);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 31, 443, 475);
		contentPane.add(scroll);

		lblServer = new JLabel("Server");
		lblServer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblServer.setBounds(10, 11, 104, 14);
		contentPane.add(lblServer);

		this.setVisible(true);
		contentPane.setVisible(true);
		
		

	}
	
	/**
	 * Processes incoming traffic and delivers appropriate message responses
	 * @param traffic
	 */
	public void parseIncomingTraffic(Object traffic) {
		Message msg = (Message) traffic;
		
		if (msg.getMessageType().equals("NEWINSTANCE")) {
			textArea.append(LoggerUtil.getInitializationMessage(msg));
			
		} else if (msg.getMessageType().equals("PLAYER_READY")) {
			textArea.append(LoggerUtil.getPlayerChoiceMessage(msg));
			
		} else if (msg.getMessageType().equals("OPPONENTGAMESTRING")) {
			
			
		} else if (msg.getMessageType().equals("QUIT")) {
			textArea.append(LoggerUtil.getDisconnectMessage(msg));
		} else {
			if (!msg.getUser().equals(null))
				textArea.append(LoggerUtil.getMessage(msg));
		}
	}

	

}
