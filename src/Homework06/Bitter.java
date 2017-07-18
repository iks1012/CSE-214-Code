package Homework06;
import java.io.Serializable;


/**
 * The <code>Bitter</code> class implements the 
 * base for both the <code>User</code> and <code>Account</code> 
 * Database.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class Bitter implements Serializable{
	private UserDatabase users; //The Database of Users
	private AccountDatabase accounts; //The Database of Accounts
	
	/**
	 * Returns an instance of <code>Bitter</code>.
	 * 
	 * Basically both the databases <code>user</code> and 
	 * <code>accounts</code> are initialized
	 */
	public Bitter(){
		users = new UserDatabase();
		accounts = new AccountDatabase();
	}
	
	
	/**
	 * This method basically adds a user to the <code>users</code> database
	 * and also adds the account to the <code>accounts</code> database. The 
	 * common thing between the two is the email. That is used as the Key in
	 * these HashMaps.
	 * 
	 * 
	 * @param email
	 * 		This is the email address of the user, it is used as the Key for
	 * 		the both the HashMaps.
	 * 
	 * @param user
	 * 		This is the user that is added to the <code>users</code> database
	 * 
	 * @param account
	 * 		This is the account that is added to the <code>accounts</code> database
	 * 
	 * <dt>Precondition:
	 * 	<dd><code>email</code> must not be a duplicate email, one <code>Account</code>
	 * 	or <code>User</code> can only have one <code>email</code> associated with
	 * 	it.
	 * 
	 * <dt>Postcondition:
	 * 	<dd><code>user</code> and <code>account</code> are both added to there respective
	 * 	databases with <code>email</code> as both of there key
	 * 
	 * @exception IllegalArgumentException
	 * 	If the Precondition is violated, i.e. the <code>email</code> already exists in the 
	 * 	database, this exception is thrown.
	 * 
	 */
	public void addUser(String email, User user, Account account){
		if(users.containsKey(email) || accounts.containsKey(email)){
			throw new IllegalArgumentException();
		}
		else{
			accounts.addAccount(email, account);
			users.addUser(email, user);
		}
		
		
	}
	
	/**
	 * This method basically removes a user from the <code>users</code> database
	 * and also removes the account from the <code>accounts</code> database. The 
	 * common thing between the two is the email. That is used as the Key in
	 * these HashMaps.
	 * 
	 * 
	 * @param email
	 * 		This is the email address of the user, it is used as the Key for
	 * 		the both the HashMaps.
	 * 
	 * 
	 * <dt>Precondition:
	 * 	<dd><code>email</code> must point to a specific <code>User</code> or <code>Account</code>
	 * 	object for there respective databases. 
	 * 
	 * <dt>Postcondition:
	 * 	<dd><code>user</code> and <code>account</code> are both removed based on there <code>email</code>
	 * 
	 * @exception IllegalArgumentException
	 * 	If the Precondition is violated, i.e. the <code>email</code> does not exist in the 
	 * 	database, this exception is thrown.
	 * 
	 */	
	public void removeUser(String email){
		if(email.equals(null) || users.get(email).equals(null)){
			throw new IllegalArgumentException();
		}
		else{
			users.remove(email);
		}
	}
}
