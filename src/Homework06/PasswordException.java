package Homework06;

import java.io.Serializable;

/**
 * This is not really needed, but I needed a way to distinguish from a regular IllegalArgumentException
 * and an invalid password input as that is different from the recurring user oriented exceptions.
 * 
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class PasswordException extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * This initializes the <code>PasswordException</code>
	 * @param message
	 * 	This is passed to the super class, <code>Exception</code>
	 */
	public PasswordException(String message){
		super(message);
	}
}