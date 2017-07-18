package Homework06;

import java.io.Serializable;
import java.util.HashMap;



/**
 * The <code>UserDatabase</code> class basically has the database for the <code>Users</code>
 * residing within it.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class UserDatabase extends HashMap<String,User> implements Serializable {
	
	
	/**
	 * Adds a User to the HashMap Database
	 * 
	 * 
	 * @param email
	 * 	This serves as the key for the <code>User</code> in the HashMap.
	 * 
	 * @param user
	 * 	This is the <code>User</code> that is added to the HashMap.
	 * 
	 * <dt>Precondition
	 * 	<dd>The email does not exist within the HashMap
	 * 
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates that <code>user</code> with <code>email</code> already exists.
	 */
	public void addUser(String email, User user){
		if(containsKey(email)){
			throw new IllegalArgumentException();
		}
		else{
			put(email, user);
		}
	}
	
	/**
	 * Gets the desired user from the database with the key <code>email</code>
	 * 
	 * @param email
	 * 	The email linked to the <code>User</code>
	 * 
	 * @return
	 * 	The <code>User</code> that was wanted with the key <code>email</code>
	 */
	public User getUser(String email){
		return get(email);
	}
	
	/**
	 * Removes the User from the HashMap Database
	 * 
	 * 
	 * 
	 * 
	 * @param email
	 * 	This serves as the key for the the <code>User</code> in the HashMap.
	 * 
	 * <dt>Precondition
	 * 	<dd>The email exists within the HashMap
	 * 
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates that <code>user</code> with <code>email</code> does not exist.
	 * 
	 */
	public void removeUser(String email){
		if(!containsKey(email)){
			throw new IllegalArgumentException();
		}
		else{
			remove(email);
		}
	}
}
