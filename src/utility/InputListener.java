package utility;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import gui.ClientWindow;

/**
 * 
 * @author Ben Talbot
 * Sends sent client objects to the ClientHandler
 */
public class InputListener implements Runnable {

	private int id;
	private Socket socket;
	private ObjectInputStream ois;
	private List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();

	/**
	 * 
	 * @param id ID number of the client
	 * @param socket The client socket
	 * @param pcl
	 */
	public InputListener(int id, Socket socket, PropertyChangeListener pcl) {
		this.id = id;
		this.socket = socket;
		listeners.add(pcl);
	}

	/**
	 * 
	 * @param object The object being sent to the ClientHandler
	 */
	public void notifyObservers(Object object) {
		for(PropertyChangeListener listener : listeners) {
			listener.propertyChange(new PropertyChangeEvent(this, null, null, object));
		}
	}

	/**
	 * 
	 * @return the socket ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Creates the object to be sent to the ClientHandler
	 */
	@Override
	public void run() {
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			while (true) {
				Object object;
				object = ois.readObject();
				notifyObservers(object);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
		}
	}
}