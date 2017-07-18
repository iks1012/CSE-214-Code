package Homework06;

import java.io.Serializable;


/**
 * The <code>User</code> class implements the <code>User</code> object.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class User implements Serializable{
	
	private String name; //Name of the user
	
	
	/**
	 * Returns an instance of <code>User</code>.
	 * 
	 * @param name
	 *     The name of the user
	 * 
	 */
	public User(String name){
		this.name = name;
	}
	
	/**
	 * Returns the name of the <code>User</code>s name.
	 * 
	 * @return
	 * 		Returns the name of the <code>User</code>.
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Sets the <code>name</code> of the <code>User</code> to <code>n</code>.
	 * 
	 * @param n
	 * 		The desired <code>name</code> of the <code>User</code>. 
	 */
	public void setName(String n){
		name = n;
	}
}
