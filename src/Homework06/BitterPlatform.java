package Homework06;



import java.util.Scanner;

public class BitterPlatform {

	//This is the social network that contains the UserDatabase and AccountDatabase.
	private static Bitter bitter = new Bitter();
	
	
	
	/**
	 * Implement the login and menu options specified in the UI required functions as 
	 * well as allow the user to interact with the social network.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		boolean isRunning = true;
		boolean isLoggedin = false;
		Scanner in = new Scanner(System.in);
		char temp;
		String email;
		String name;
		String password;
		boolean data = false;
		//boolean data = FindData(); //Use Serializable.
		
		
		pln("Hello, and welcome to Bitter, a more tasteful social network");
		if(data){
			pln("Previous Data Found!");
		}
		else{
			pln("No previous Data Found.");
		}
		
		
		
		while(isRunning){
			if(!isLoggedin){
				printLoginMenu();
				temp = (in.nextLine().toUpperCase()).charAt(0);
				
				if(temp == 'L'){
					p("Please enter your email: ");
					email = in.nextLine();
					p("Please enter your password: ");
					password = in.nextLine();
					
					
					
				}
				else if(temp == 'S'){
					p("Please enter your email: ");
					email = in.nextLine();
					p("Please enter your name: ");
					name = in.nextLine();
					p("Please enter your password: ");
					password = in.nextLine();
					try{
						Password pass = new Password(password);
						User user = new User(name);
						Account acct = new Account(name, pass);
						bitter.addUser(email, user, acct);
						pln("Sign-up Success!");
					}
					catch(PasswordException p){
						pln("Invalid Password breh");
					}
					catch(IllegalArgumentException i){
						pln("Account already exists!");
					}
				}
				else if(temp == 'Q'){
					//Save things then quit
				}
				else{
					pln("Invalid Input!");
				}
			}
			
			
		}
	}
	
	public static void printLoginMenu(){
		pln("------------------------");
		pln("Login Menu");
		pln("   L) Log-in");
		pln("   S) Sign up");
		pln("   Q) Quit");
		pln("------------------------");
		pSl();
	}
	public static void printUserMenu(){
		pln("------------------------");
		pln("User Menu");
		pln("   F) Follow Someone");
		pln("   U) Unfollow Someone");
		pln("   V) View Followers");
		pln("   S) See who you follow");
		pln("   A) List all Users");
		pln("   L) Log-out");
		pln("   C) Close Your Account");
		pln("------------------------");
		pSl();
	}
	public static void pSl(){
		p("Please select an option: ");
	}
	public static void pln(String printThis){
		System.out.println(printThis);
	}
	public static void p(String printThis){
		System.out.print(printThis);
	}
}
