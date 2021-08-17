package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import server.ClientHandler;
import sun.applet.resources.MsgAppletViewer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utility.GameInstance;
import utility.InputListener;
import utility.LoggerUtil;
import utility.Message;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
/**
 * 
 * @author Ben Talbot	
 * The main game window.
 *
 */
public class ClientWindow extends JFrame implements PropertyChangeListener, ActionListener {

/**
 * Logical attributes
 */
	private String messageString = "";
	private String userName = "";
	private String ipAddress = "";
	private Socket socket;
	private ObjectOutputStream objectOutputStream = null;
	private ObjectInputStream objectInputStream = null;
	private String playerChoice;
	private String opponentChoice;
	private boolean playerReady = false;
	private boolean connected = false;
	private String opponentUserName;
	
/**
 * Swing GUI components
 */
	// Panels
	private JPanel losePanel;
	private JPanel drawPanel;
	private JPanel startOverPanel;
	private JPanel contentPane;
	private JLabel lblLose;
	private JLabel lblLoseGif;
	private JPanel matchmakingPanel;
	private JPanel selectPanel;
	private JPanel waitPanel;
	private JPanel winPanel;
	
	// Labels
	private JLabel lblDraw;
	private JLabel lblDrawGif;
	private JLabel lblWin;
	private JLabel lblWinGif;
	private JLabel lblSelectedWeapon;
	
	// Buttons
	private JButton btnSendMessage;
	private JButton btnBattleQuit;
	private JButton btnPlayAgain;
	private JButton btnRock;
	private JButton btnPaper;
	private JButton btnScissors;
	private JButton btnBattle;
	private JButton btnQuit;
	
	// Misc.
	private JScrollPane scrollPane;
	private JTextField textFieldMessage;
	private JTextArea textArea;

	/**
	 * 
	 * @param ipAddress The IP Address of the incoming client
	 * @param userName The username of the incoming client
	 * 
	 * Initializes GUI components and takes in client parameters
	 */
	public ClientWindow(String ipAddress, String userName) {
		this.ipAddress = ipAddress;
		this.userName = userName;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 621);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Game
		selectPanel = new JPanel();
		selectPanel.setBorder(new LineBorder(new Color(169, 169, 169)));
		selectPanel.setBounds(10, 11, 396, 318);
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);

		btnRock = new JButton("");
		btnRock.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRock.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/Rock Right Small.png")));
		btnRock.setBounds(159, 60, 77, 77);
		btnRock.addActionListener(this);
		selectPanel.add(btnRock);

		btnPaper = new JButton("");
		btnPaper.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/Rock Paper Small.png")));
		btnPaper.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnPaper.setBounds(41, 60, 77, 77);
		btnPaper.addActionListener(this);
		selectPanel.add(btnPaper);

		btnScissors = new JButton("");
		btnScissors.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/Rock Scissors Small.png")));
		btnScissors.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnScissors.setBounds(275, 60, 77, 77);
		btnScissors.addActionListener(this);
		selectPanel.add(btnScissors);

		JLabel lblChooseWeapon = new JLabel("");
		lblChooseWeapon.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/Choose Text2.png")));
		lblChooseWeapon.setBounds(77, 11, 319, 38);
		selectPanel.add(lblChooseWeapon);

		lblSelectedWeapon = new JLabel("");
		lblSelectedWeapon.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblSelectedWeapon.setBounds(26, 189, 104, 104);
		lblSelectedWeapon.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/NoSelection.png")));
		selectPanel.add(lblSelectedWeapon);

		JLabel lblSelected = new JLabel("SELECTED:");
		lblSelected.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblSelected.setBounds(49, 174, 69, 14);
		selectPanel.add(lblSelected);

		btnBattle = new JButton("");
		btnBattle.setBorder(new LineBorder(Color.BLACK));
		btnBattle.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/battle.gif")));
		btnBattle.setBounds(170, 211, 192, 61);
		btnBattle.addActionListener(this);
		selectPanel.add(btnBattle);
		selectPanel.setVisible(false);

		startOverPanel = new JPanel();
		startOverPanel.setBounds(10, 276, 396, 53);
		contentPane.add(startOverPanel);
		startOverPanel.setLayout(null);

		btnBattleQuit = new JButton("QUIT");
		btnBattleQuit.setBounds(207, 11, 158, 31);
		btnBattleQuit.addActionListener(this);
		startOverPanel.add(btnBattleQuit);

		btnPlayAgain = new JButton("PLAY AGAIN");
		btnPlayAgain.setBounds(28, 11, 158, 31);
		btnPlayAgain.addActionListener(this);
		startOverPanel.add(btnPlayAgain);

		drawPanel = new JPanel();
		drawPanel.setBounds(10, 11, 396, 267);
		contentPane.add(drawPanel);
		drawPanel.setLayout(null);

		lblDraw = new JLabel("DRAW");
		lblDraw.setBounds(138, 5, 120, 49);
		lblDraw.setFont(new Font("Bahnschrift", Font.BOLD, 40));
		drawPanel.add(lblDraw);

		lblDrawGif = new JLabel("");
		lblDrawGif.setBounds(20, 49, 366, 207);
		lblDrawGif.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/elmo.gif")));
		drawPanel.add(lblDrawGif);

		losePanel = new JPanel();
		losePanel.setLayout(null);
		losePanel.setBounds(10, 11, 396, 267);
		contentPane.add(losePanel);

		lblLose = new JLabel("");
		lblLose.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/youlose.png")));
		lblLose.setBounds(25, 11, 350, 74);
		losePanel.add(lblLose);

		lblLoseGif = new JLabel("");
		lblLoseGif.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/tenor.gif")));
		lblLoseGif.setBounds(80, 63, 242, 204);
		losePanel.add(lblLoseGif);

		winPanel = new JPanel();
		winPanel.setBounds(10, 11, 396, 267);
		contentPane.add(winPanel);
		winPanel.setLayout(null);

		lblWin = new JLabel("");
		lblWin.setBounds(25, 11, 350, 98);
		lblWin.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/youwin.png")));
		winPanel.add(lblWin);

		lblWinGif = new JLabel("");
		lblWinGif.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/giphy.gif")));
		lblWinGif.setBounds(80, 74, 230, 170);
		winPanel.add(lblWinGif);

		waitPanel = new JPanel();
		waitPanel.setBounds(10, 11, 396, 318);
		contentPane.add(waitPanel);
		SpringLayout sl_waitPanel = new SpringLayout();
		waitPanel.setLayout(sl_waitPanel);

		JLabel lblWaiting = new JLabel("");
		sl_waitPanel.putConstraint(SpringLayout.WEST, lblWaiting, 10, SpringLayout.WEST, waitPanel);
		sl_waitPanel.putConstraint(SpringLayout.SOUTH, lblWaiting, -8, SpringLayout.SOUTH, waitPanel);
		sl_waitPanel.putConstraint(SpringLayout.EAST, lblWaiting, -10, SpringLayout.EAST, waitPanel);
		lblWaiting.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/loading/waiting1.png")));
		waitPanel.add(lblWaiting);

		// Matchmaking
		matchmakingPanel = new JPanel();
		matchmakingPanel.setBackground(Color.WHITE);
		matchmakingPanel.setLayout(null);
		matchmakingPanel.setBorder(new LineBorder(new Color(169, 169, 169)));
		matchmakingPanel.setBounds(10, 11, 396, 318);
		contentPane.add(matchmakingPanel);

		JLabel lblFindingOpponent = new JLabel("");
		lblFindingOpponent.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/loading/matchmaking1.png")));
		lblFindingOpponent.setFont(new Font("Century Gothic", Font.BOLD, 36));
		lblFindingOpponent.setBounds(10, 25, 376, 266);
		matchmakingPanel.add(lblFindingOpponent);
		matchmakingPanel.setVisible(true);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(new Color(169, 169, 169)));
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(new LineBorder(new Color(169, 169, 169)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 340, 396, 164);
		contentPane.add(scrollPane);

		// Messaging
		textFieldMessage = new JTextField();
		textFieldMessage.setBounds(10, 514, 326, 23);
		contentPane.add(textFieldMessage);
		textFieldMessage.setColumns(10);

		btnSendMessage = new JButton("Send");
		btnSendMessage.setBounds(339, 514, 67, 23);
		btnSendMessage.addActionListener(this);
		contentPane.add(btnSendMessage);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(this);
		btnQuit.setBounds(166, 548, 89, 23);
		contentPane.add(btnQuit);

		this.setVisible(true);
		matchmakingPanel.setVisible(true);
		waitPanel.setVisible(false);
		drawPanel.setVisible(false);
		winPanel.setVisible(false);
		losePanel.setVisible(false);
		startOverPanel.setVisible(false);

		this.connectClients(true);

	}

	/**
	 * Clears the GUI and resets it back to the selection screen
	 */
	public void clearGUI() {
		this.setVisible(true);
		matchmakingPanel.setVisible(false);
		selectPanel.setVisible(true);
		waitPanel.setVisible(false);
		drawPanel.setVisible(false);
		winPanel.setVisible(false);
		losePanel.setVisible(false);
		startOverPanel.setVisible(false);

	}

	/**
	 * Creates a connection with client credentials and sends the socket to the server side
	 */
	public void connectClients(boolean newSession) {
		try {

			socket = new Socket(ipAddress, 3333);
			OutputStream os = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(os);
			InputListener il = new InputListener(0, socket, this);
			new Thread(il).start();
			Message msg = new Message(userName, messageString, new Date(), "NEWINSTANCE", true);
			objectOutputStream.writeObject(msg);
			if (newSession) {
				textArea.append(LoggerUtil.getInitializationMessage(msg));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Receives incoming message objects and parses their contents 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) throws NullPointerException {
		Message msg = (Message) evt.getNewValue();
		opponentUserName = msg.getUser();
		System.out.println(msg.getMessageType());
		
		// NEWINSTANCE is sent upon initial connection. Will send an initialization message and switch GUI elements. A user disconnect will only switch the GUI
		if (msg.getMessageType().equals("NEWINSTANCE") || msg.getMessageType().equals("USERDISCONNECTED")) {
			if (msg.getMessageType().equals("NEWINSTANCE")) {
				textArea.append(LoggerUtil.getInitializationMessage(msg));
				connected = true;
			}
			selectPanel.setVisible(true);
			matchmakingPanel.setVisible(false);
			waitPanel.setVisible(false);
			
		// PLAYER_READY is received from the opponent client. 
		} else if (msg.getMessageType().equals("PLAYER_READY")) {
			opponentChoice = msg.getMsg();			
			if (playerChoice != null && playerReady) {
				Message gameMessage = new Message(userName, opponentChoice, null, "OPPONENTGAMESTRING", playerReady);
				try {
					objectOutputStream.writeObject(gameMessage);
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.decideWinner();
			}
		// OPPONENTGAMESTRING is received by the client that makes their choice second.
		} else if (msg.getMessageType().equals("OPPONENTGAMESTRING")) {
			playerChoice = msg.getMsg();
			playerReady = msg.isReady();
			this.decideWinner();
		
		// QUIT is received if the opponent presses the quit button
		} else if (msg.getMessageType().equals("QUIT")) {
			this.userDisconnects();
		
		// Any other message type is interpreted as a message and is displayed
		} else {
			if (!msg.getUser().equals(null))
				textArea.append(LoggerUtil.getMessage(msg));
		}
	}

	/**
	 * Listens for user input on the client GUI and parses the action accordingly
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Listens for either quit button. Closes all streams and sockets, and terminates
			if (e.getSource() == btnBattleQuit || e.getSource() == btnQuit) {
				Message msg = new Message(userName, messageString, new Date(), "QUIT", false);
				objectOutputStream.writeObject(msg);
				if (!connected) {
					this.connectClients(false);
				}
				System.exit(0);
				
				objectInputStream.close();
				objectOutputStream.close();
				socket.close();
				
			// Sends generic message for instant communications
			} else if (e.getSource() == btnSendMessage) {

				messageString = textFieldMessage.getText();
				Message msg = new Message(userName, messageString, new Date(), "SENTMESSAGE", false);
				textArea.append(msg.toString());
				textFieldMessage.setText("");
				objectOutputStream.writeObject(msg);
				
			// Bound to the rock button. Switched the users selection to "rock"	
			} else if (e.getSource() == btnRock) {

				lblSelectedWeapon.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/Rock Right Large.png")));
				playerChoice = "rock";
				
			// Bound to the paper button. Switched the users selection to "paper"	
			} else if (e.getSource() == btnPaper) {

				lblSelectedWeapon.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/Rock Paper Large.png")));
				playerChoice = "paper";
				
			// Bound to the scissors button. Switched the users selection to "scissors"	
			} else if (e.getSource() == btnScissors) {

				lblSelectedWeapon
						.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/Rock Scissors Large.png")));
				playerChoice = "scissors";
				
			// Switches GUI panels and sends a message to the opponent indicating the player is ready
			} else if (e.getSource() == btnBattle) {
				waitPanel.setVisible(true);
				matchmakingPanel.setVisible(false);
				selectPanel.setVisible(false);
				playerReady = true;
				Message readyMessage = new Message(userName, playerChoice, null, "PLAYER_READY", playerReady);
				objectOutputStream.writeObject(readyMessage);
				
			// Switches GUI to selection screen if the player wants to play again.	
			} else if (e.getSource() == btnPlayAgain) {
				playerChoice = null;
				opponentChoice = null;
				playerReady = false;
				this.clearGUI();
				lblSelectedWeapon.setIcon(new ImageIcon(ClientWindow.class.getResource("/res/NoSelection.png")));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * Takes the player and opponent choices and switches the GUI elements accordingly
	 */
	public void decideWinner() {
		GameInstance gameInstance = new GameInstance();
		System.out.println("player choice: " + playerChoice + "\n" + "opponent choice: " + opponentChoice);
		String outcome = gameInstance.decideWinner(playerChoice, opponentChoice);
		System.out.println("outcome: " + outcome);

		if (outcome.equals("win")) {
			selectPanel.setVisible(false);
			waitPanel.setVisible(false);
			winPanel.setVisible(true);
			startOverPanel.setVisible(true);

		} else if (outcome.equals("lose")) {
			selectPanel.setVisible(false);
			waitPanel.setVisible(false);
			losePanel.setVisible(true);
			startOverPanel.setVisible(true);

		} else if (outcome.equals("draw")) {
			selectPanel.setVisible(false);
			waitPanel.setVisible(false);
			drawPanel.setVisible(true);
			startOverPanel.setVisible(true);
		}
	}

	/**
	 * Sends a message object to indicate a disconnect.
	 * Resets the GUI back to the "searching for opponent" state
	 */
	public void userDisconnects() {
		Message msg = new Message(opponentUserName, messageString, new Date(), "USERDISCONNECTED", false);
		textArea.append(LoggerUtil.getDisconnectMessage(msg));

		this.setVisible(true);
		matchmakingPanel.setVisible(true);
		selectPanel.setVisible(false);
		waitPanel.setVisible(false);
		drawPanel.setVisible(false);
		winPanel.setVisible(false);
		losePanel.setVisible(false);
		startOverPanel.setVisible(false);

		try {
			objectOutputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connected = false;
		this.connectClients(false);
	}
}
