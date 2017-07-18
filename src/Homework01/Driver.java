package Homework01;

/*
 * Ishan Sethi
 * E-mail: ishan.sethi@stonybrook.edu
 * Stony Brook ID: 110941217
 * NetID: isethi
 * Homework 01
 */

public class Driver {
	private static int numberOfFloors = 3;
	
	/**
	 * 
	 * @param args
	 * 		[This is the main method for the program which tests/runs everything]
	 */
	public static void main(String[] args) {
		Floor f1 = new Floor();
		Floor[] building = new Floor[numberOfFloors];
		for(int i = 0; i < numberOfFloors; i++)
			building[i] = new Floor();
		
		Student s1 = new Student("Ishan",110941217,0);
		Student s2 = new Student("Gor",110123456,0);
		Student s3 = new Student("Zach",110000000,0);
		Student s4 = new Student("Nik",110000000,0);
		Student[] studs = {s1, s2, s3, s4};
		for(int i = 0; i < numberOfFloors-1; i++)
			for(int j = 0; j < building[i].MAX; j++)
				building[i].addStudent(studs[j], j);
		
		building[2] = building[0].clone();
		//building[numberOfFloors-1] = building[numberOfFloors-2].clone();
		
		for(int i = 0; i < numberOfFloors; i++)
			for(int j = 0; j < building[i].MAX; j++)
				System.out.println(building[i].getStudent(j).getName());
		
		

	}
	
	/**
	 * Self Explanatory: Quits the program
	 */
	public static void quit(){
		System.exit(0);
	}
	
}
