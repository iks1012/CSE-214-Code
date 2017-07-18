package Homework06;
import java.io.Serializable;
import java.util.HashMap;


/**
 * The <code>AccountDatabase</code> class basically hosts the <code>AccountDatabase</code>
 * with all the requirements for the <code>AccountDatabase</code>.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class AccountDatabase extends HashMap<String,Account> implements Serializable{
	
	/**
	 * Adds a Account to the HashMap Database
	 * 
	 * 
	 * @param email
	 * 	This serves as the key for the <code>Account</code> in the HashMap.
	 * 
	 * @param user
	 * 	This is the <code>Account</code> that is added to the HashMap.
	 * 
	 * <dt>Precondition
	 * 	<dd>The email does not exist within the HashMap
	 * 
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates that <code>Account</code> with <code>email</code> already exists.
	 */
	public void addAccount(String email, Account account){
		if(containsKey(email)){
			throw new IllegalArgumentException();
		}
		else{
			put(email, account);
		}
	}
	
	
	/**
	 * Gets the desired Account from the database with the key <code>email</code>
	 * 
	 * @param email
	 * 	The email linked to the <code>Account</code>
	 * 
	 * @return
	 * 	The <code>Account</code> that was wanted with the key <code>email</code>
	 */
	public Account getAccount(String email){
		if(containsKey(email)){
			return get(email);
		}
		else{
			throw new IllegalArgumentException();
		}
		
	}
	
	
	/**
	 * Removes the Account from the HashMap Database
	 * 
	 * 
	 * 
	 * 
	 * @param email
	 * 	This serves as the key for the the <code>Account</code> in the HashMap.
	 * 
	 * <dt>Precondition
	 * 	<dd>The email exists within the HashMap
	 *
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates that <code>Account</code> with <code>email</code> does not exist.
	 * 
	 */
	public void removeAccount(String email){
		if(!containsKey(email)){
			throw new IllegalArgumentException();
		}
		else{
			remove(email);
		}
	}
}
