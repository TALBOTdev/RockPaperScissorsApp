package drivers;

import java.awt.EventQueue;

import gui.ServerWindow;
import server.ServerInstance;
/**
 * 
 * @author Ben Talbot
 * Entry point of the server side of the program
 */
public class ServerDriver {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				
					ServerInstance serverInstance = new ServerInstance();
					Thread serverInstanceThread = new Thread(serverInstance);
					serverInstanceThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
