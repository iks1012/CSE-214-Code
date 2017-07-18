package Recitation;


/*
 * Ishan Sethi
 * E-mail: ishan.sethi@stonybrook.edu
 * Stony Brook ID: 110941217
 * Homework 01
 */


public class Jan30 {
//JavaDOC
	/**
	 * 
	 * @param x
	 * 		[Brief Description]
	 * @param y
	 * 		[Brief Description]
	 * 
	 * 
	 */
	public void sampleMethod(int x, int y){
		
	}
	
	
	
	
	
	
}
class Student{
	String name;
	int idNum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdNum() {
		return idNum;
	}
	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}
	public Object clone(){
		Student cloned = new Student();
		cloned.name = this.getName();
		cloned.idNum = this.getIdNum();
		return cloned;
	}
	
	/**
	 * @param o
	 * 		[Compares Object O to the object of the class that this method is in]
	 * 
	 * returns true if all params are equal, returns false if even 1 is not equal
	 */
	public boolean equals(Object o){
		if(!(o instanceof Student)){
			return false;
		}
		Student toCompare = (Student) o;
		return (this.getName().equals(toCompare.getName())) 
				&& this.getIdNum() == toCompare.getIdNum();
		
	}
	
	
	
}