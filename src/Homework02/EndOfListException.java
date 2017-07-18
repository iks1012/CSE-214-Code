package Homework02;

public class EndOfListException extends Exception{
	private static final long serialVersionUID = 1L;
	public EndOfListException(){
		//System.out.println("Yo, wrong input, try again.");
	}
	
	public EndOfListException(String message){
		super(message);
	}

}
