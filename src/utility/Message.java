/**
 * Created on Jul 4, 2006
 *
 * Project: demo03_BasicEchoClientandServerExercises
 */
package utility;

import java.io.*;
import java.util.*;

/**
 * @author dwatson, Ben Talbot
 * @version 1.1
 * Jan 30, 2020
 *
 * Class Description: A basic message class that can be transported across
 * the network.
 * Modified to accept parameters for message type and whether the player is new.
 */
public class Message implements Serializable
{
	//Constants
	static final long serialVersionUID = 5488945625178844229L;
	//Attributes
	private String 			user;
	private String			msg;
	private Date			timeStamp;
	private String		    messageType;
	private boolean 		newPlayer;
	
	//Constructors
	public Message()
	{
	}
	
	/**
	 * 
	 * @param user The user sending the message
	 * @param msg The contents of the message
	 * @param timeStamp The time the message was sent
	 * @param messageType The type of message sent, can be part of the chat or part of the game
	 * @param newPlayer Whether the player is a new player
	 */
	public Message(String user, String msg, Date timeStamp, String messageType, boolean newPlayer)
	{
		this.user = user;
		this.msg = msg;
		this.timeStamp = timeStamp;
		this.messageType = messageType;
		this.newPlayer = newPlayer;
	}

	/**
	 * @return the user
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	/**
	 * @return the msg
	 */
	public String getMsg()
	{
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp()
	{
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
	}
	
	/**
	 * 
	 * @return the messagetype
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * 
	 * @param messageType the type of message
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * 
	 * @return If the player is ready
	 */
	public boolean isReady() {
		return newPlayer;
	}

	/**
	 * 
	 * @param newPlayer Whether the player is ready
	 */
	public void setReady(boolean newPlayer) {
		this.newPlayer = newPlayer;
	}

	//Operational Methods
	public String toString()
	{
		return user + ": " + msg + "\n";
	}
}
