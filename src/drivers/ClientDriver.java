package drivers;

import java.awt.EventQueue;

import gui.LoginScreen;
/**
 * 
 * @author Ben Talbot
 * Entry point on the client side of the program.
 *
 */
public class ClientDriver {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen login = new LoginScreen();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
