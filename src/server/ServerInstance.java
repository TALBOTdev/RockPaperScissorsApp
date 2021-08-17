
package server;

import java.io.*;
import java.net.*;
import java.util.*;

import gui.ServerWindow;
import utility.Message;

/**
 * @author dwatson, Ben Talbot
 * @version 1.1
 * 
 */
public class ServerInstance extends Thread {

	@Override
	public void run() {
		initaializeServer();
		
	}
	
	/**
	 * Creates a ServerWindow on a new thread and initializes the ServerSocket.accept loop
	 */
	public void initaializeServer() {
		ServerWindow serverWindow = new ServerWindow();
		Thread serverWindowThread = new Thread(serverWindow);
		serverWindowThread.start();
		
		ServerSocket server = null;
		Socket socket = null;
		
		ArrayList<Socket> sockets = new ArrayList<Socket>(2);
		
		try
		{
			server = new ServerSocket(3333);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("Server up and running!!!!!!!!!!");
		
		while(true)
		{
			try
			{
				socket = server.accept();
				System.out.println("Accepted a client connection.");
				sockets.add(socket);
				if (sockets.size() == 2) {
					new Thread(new ClientHandler(sockets.get(0), sockets.get(1), serverWindow)).start();
					sockets.clear();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}		
}
		
	
		
	

