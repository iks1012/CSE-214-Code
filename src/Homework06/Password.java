package Homework06;
import java.io.Serializable;


/**
 * The <code>Password</code> class basically implements the <code>Password</code> object
 * with all the requirements for the <code>Password</code>.
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class Password implements Serializable{	
	
	private String password;//The actual String for the password
	
	
	/**
	 * Returns an Instance of <code>Password</code>.
	 * @param passInit
	 * 	This is the initial Password that the user desires to put into it.
	 * 
	 * <dt>Preconditions:
	 * 	<dd><code>passInit</code> must have a lower case character
	 * 	<dd><code>passInit</code> must have a upper case character
	 * 	<dd><code>passInit</code> must have a special character (!@#$%^&*)
	 * 	<dd><code>passInit</code> must have a number
	 *
	 * @exception PasswordException 
	 * 	This is thrown if the precondition is violated even in the slightest.
	 * 
	 * 
	 */
	public Password(String passInit) throws PasswordException {
		boolean upperCase = false;
		boolean number = false;
		boolean specChar = false;
		boolean lowerCase = false;
		char temp;
		
		for(int i = 0; i < passInit.length(); i++){
			temp = passInit.charAt(i);
			if(Character.isUpperCase(temp)){
				upperCase|=true;
			}
			if(Character.isLowerCase(temp)){
				lowerCase|=true;
			}
			if(temp == '!'||temp == '@'||temp == '#'||temp == '$'||temp == '%'||temp == '^'||temp == '&'||temp == '*'){
				specChar|=true;
			}
			if(Character.isDigit(temp)){
				number|=true;
			}
		}
		
		
		if(upperCase && number && lowerCase && specChar){
			password = passInit;
		}
		else{
			throw new PasswordException("Invalid Password, Must contain a UpperCase Char, LowerCase Char, Special Char (!@#$%^&*) and a number");
		}
	}
	
}
