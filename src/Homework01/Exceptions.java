package Homework01;


/*
 * Ishan Sethi
 * E-mail: ishan.sethi@stonybrook.edu
 * Stony Brook ID: 110941217
 * NetID: isethi
 * Homework 01
 */

public class Exceptions extends Exception{
	private static final long serialVersionUID = 1L;
	public Exceptions(String message){
		super(message);
	}
	public Exceptions(){
		
	}
}
class IllegalArgumentException extends Exception{
	private static final long serialVersionUID = 1L;
	public IllegalArgumentException(){
		//System.out.println("Yo, wrong input, try again.");
	}
	
	public IllegalArgumentException(String message){
		super(message);
	}
}
class FullFloorException extends Exceptions{
	private static final long serialVersionUID = 1L;
	public FullFloorException(){
		//System.out.println("Yo, wrong input, try again.");
	}
	
	public FullFloorException(String message){
		super(message);
	}
}

class EmptyFloorException extends Exceptions{
	private static final long serialVersionUID = 1L;
	public EmptyFloorException(){
		//System.out.println("Yo, wrong input, try again.");
	}
	
	public EmptyFloorException(String message){
		super(message);
	}
}