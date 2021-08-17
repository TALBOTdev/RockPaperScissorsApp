package server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import gui.ServerWindow;
import utility.InputListener;
import utility.Message;

/**
 * 
 * @author Ben Talbot
 * Creates multiple threads to dispatch objects to multiple clients.
 * Operates with a pair of clients.
 */
public class ClientHandler implements Runnable, PropertyChangeListener {

	private Socket socket1;
	private Socket socket2;
	private ObjectOutputStream clientObjectOutputStream1;
	private ObjectOutputStream clientObjectOutputStream2;
	private InputListener lis1;
	private InputListener lis2;
	private ServerWindow serverWindow;
	
	/**
	 * 
	 * @param socket1 The socket for the first client
	 * @param socket2 The socket for the second client
	 */
	public ClientHandler(Socket socket1, Socket socket2, ServerWindow serverWindow) {
		this.socket1 = socket1;
		this.socket2 = socket2;
		this.serverWindow = serverWindow;
		
		try {
			clientObjectOutputStream1 = new ObjectOutputStream(this.socket1.getOutputStream());
			clientObjectOutputStream2 = new ObjectOutputStream(this.socket2.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lis1 = new InputListener(1, this.socket1, this);
		lis2 = new InputListener(2, this.socket2, this);
	}

	/**
	 * Executes the threads
	 */
	@Override
	public void run() {
		
		new Thread(lis1).start();
		new Thread(lis2).start();
		
	}


	/**
	 * Dispatches the incoming objects to their respective clients.
	 * @param evt The event that carries the incoming object
	 */
	@Override
	public synchronized void propertyChange(PropertyChangeEvent evt) {
		
		Object passedObject = evt.getNewValue();
		serverWindow.parseIncomingTraffic(passedObject);
		
		InputListener returnedInputListener = (InputListener)evt.getSource();
		
		try {
		if (returnedInputListener.getId() == 1) {
			clientObjectOutputStream2.writeObject(passedObject);
		}
		else {
			clientObjectOutputStream1.writeObject(passedObject);
		}
		}
		catch (IOException e) {
			
		}
	}
}
