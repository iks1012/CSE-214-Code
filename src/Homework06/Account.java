package Homework06;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * The <code>Account</code> class basically implements the <code>Account</code> object
 * with all the requirements for the <code>Account</code>.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class Account implements Serializable{
	HashSet<User> followers = new HashSet<>(); //The set of all the followers.
	HashSet<User> following = new HashSet<>(); //The set of all the following.
	private ArrayList<String> nameOfFollowers = new ArrayList<>(); //The ArrayList of people that are being followed.
	private ArrayList<String> nameOfFollowing = new ArrayList<>(); //The ArrayList of people that are followers.
	
	
	private String name; //Name of the user for the account.
	private Password password; //The Password for this account.
	
	
	/**
	 * Returns an instance of <code>Account</code>
	 * 
	 * 
	 * 
	 * 
	 * @param name
	 * 	This is the alias that the user has
	 * 
	 * @param password
	 * 	This is the password of the account
	 * 	
	 */
	public Account(String name, Password password) {
		this.name = name;
		this.password = password;
	}

	
	/**
	 * Returns the name of this <code>Account</code>
	 * 
	 * 
	 * @return
	 * 	The name of the <code>Account</code>
	 * 
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name of the user to <code>name</name>
	 * 
	 * @param name
	 * 	The desired name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Returns the password of this <code>Account</code>
	 * 
	 * 
	 * @return
	 * 	The password of the <code>Account</code>
	 * 
	 */
	public Password getPassword() {
		return password;
	}


	/**
	 * Sets the password of the user to <code>assword</name>
	 * 
	 * @param password
	 * 	The password, preconditions don't matter here as the password should 
	 * 	already have been initialized before adding it. It will be a valid
	 * 	password if it is set to the password of <code>Account</code>.
	 */
	public void setPassword(Password password) {
		this.password = password;
	}


	/**
	 * Adds the <code>user</code> to the set of followers.
	 * 
	 * 
	 * @param user
	 * 	This is the user to be added
	 * 
	 * <dt>Precondition
	 * 	<dd>The user must not already be followed
	 * 
	 * @exception IllegalArgumentException 
	 * 	This occurs if the precondition is violated.
	 */
	public void addFollower(User user){
		if (followers.contains(user)) {
			throw new IllegalArgumentException();
		}
		else {
			followers.add(user);
			nameOfFollowers.add(user.getName());
		}
	}
	
	/**
	 * Removes the <code>user</code> from the set of followers.
	 * 
	 * 
	 * @param user
	 * 	This is the user to be removed
	 * 
	 * <dt>Precondition
	 * 	<dd>The user must already be followed
	 * 
	 * @exception IllegalArgumentException 
	 * 	This occurs if the precondition is violated.
	 */
	public void removeFollower(User user){
		if(followers.contains(user)){
			nameOfFollowers.remove(user.getName());
			followers.remove(user);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Adds the <code>user</code> to the set of following.
	 * 
	 * 
	 * @param user
	 * 	This is the user to be added
	 * 
	 * <dt>Precondition
	 * 	<dd>The user must not already be following
	 * 
	 * @exception IllegalArgumentException 
	 * 	This occurs if the precondition is violated.
	 */
	public void addFollowing(User user){
		if(following.contains(user)){
			throw new IllegalArgumentException();
		}
		else{
			following.add(user);
			nameOfFollowing.add(user.getName());
		}
	}
	
	/**
	 * Removes the <code>user</code> from the set of following.
	 * 
	 * 
	 * @param user
	 * 	This is the user to be removed
	 * 
	 * <dt>Precondition
	 * 	<dd>The user must already be following
	 * 
	 * @exception IllegalArgumentException 
	 * 	This occurs if the precondition is violated.
	 */
	public void removeFollowing(User user){
		if(followers.contains(user)){
			nameOfFollowing.remove(user.getName());
			followers.remove(user);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
}
