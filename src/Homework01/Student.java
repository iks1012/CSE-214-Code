package Homework01;
/*
 * Ishan Sethi
 * E-mail: ishan.sethi@stonybrook.edu
 * Stony Brook ID: 110941217
 * NetID: isethi
 * Homework 01
 */

public class Student {
	final int MAX_WRITEUPS = 3;
	private String name;
	private int idNumber;
	private int numWriteups;
	public Student(){
		name = "";
		idNumber = 0;
		numWriteups = 0;
	}
	
	/**
	 * 
	 * @param n
	 * 		The name attribute of the Student
	 * @param id
	 * 		The idNumber attribute of the Student
	 * @param numWrites
	 * 		The numWriteups attribute of the Student
	 * 
	 * 		[This is just another constructor for the Student class which allows definition with parameters]
	 */
	public Student(String n, int id, int numWrites){
		name = n;
		idNumber = id;
		numWriteups = numWrites;
	}
	
	/**
	 * 
	 * [Precondition]
	 * The student and the Name itself must be specified for this to work
	 * 
	 * @return
	 * 		[Returns the name of said student as type String]
	 */
	public String getName() {
		return name;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getIdNumber() {
		return idNumber;
	}
	
	
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	
	
	public int getNumWriteups() {
		return numWriteups;
	}
	
	
	public void setNumWriteups(int numWriteups) {
		this.numWriteups = numWriteups;
	}
	
	
	public int getMAX_WRITEUPS() {
		return MAX_WRITEUPS;
	}
	
	public void wipe(int position){
		name  = "";
		idNumber = 0;
		numWriteups = 0;
	}
	
	/**
	 * [Precondition]
	 * The cloned student must be defined and also within all of its parameters, name, idNumber and numWriteUps
	 * 
	 * [Postcondition]
	 * The clonee is now the same as the cloner from a deep perspective
	 * 
	 * returns a new student with the same parameters as the Student you wish to replicate
	 */
	public Student clone(){
		return new Student(this.getName(),this.getIdNumber(),this.getNumWriteups());
	}
	
	
}
