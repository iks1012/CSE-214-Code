package Homework04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Simulation {
	int numEntities = 8;
	String[] rawData = new String[numEntities];
	int numCourses;
	static int[] courseNumbers;
	static double[] arrivalProbabilities;
	String fileName;
	static int minTime;
	static int maxTime;
	static int numCups;
	int simulationTime;
	int numTAs;
	static Helper[] TAs;
	static Helper teacher;
	static Course[] courses;
	static BooleanSource[] chanceOfStudentFromCourse;
	static StudentQueue students = new StudentQueue();
	
	/**
	 * @param file
	 * Name of the file
	 * 
	 * THis is used to assign the variables from a .txt file instead of doing
	 * it manually.
	 * 
	 */
	public Simulation(String file){
		fileName = file;
		assignVars();
		printVars();
		initHelpers();
		initCourses();
		setArrivalChances();
	}
	
	
	/**
	 * 
	 * @param numCourses
	 * @param arrivalProbabilities
	 * @param minTime
	 * @param maxTime
	 * @param numCups
	 * @param simulationTime
	 * @param numTAs
	 * 
	 * All these variables are loaded from the file, or should load atleast.
	 * 
	 * This constructor exists just to make my life easy and be able to 
	 * assign the variables directly form the Driver/Testing class if need be.
	 * 
	 */
	public Simulation(int numCourses, double[] arrivalProbabilities, int minTime, int maxTime, int numCups, int simulationTime, int numTAs) {
		this.numCourses = numCourses;
		this.arrivalProbabilities = arrivalProbabilities;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.numCups = numCups;
		this.simulationTime = simulationTime;
		this.numTAs = numTAs;
	}

	/**
	 * Initializes the teachers
	 * Works with any value of TAs because there always exists one teacher.
	 */
	public void initHelpers(){
		teacher = new Helper(true);
		TAs = new Helper[numTAs];
		for(int i = 0; i < numTAs; i++){
			TAs[i] = new Helper(false);
		}
	}
	
	/**
	 * Makes all the courses with all of its respective probabilities
	 * 
	 * Precondition
	 * probabilities must be in between (0.0, 1.0]
	 * If the precondition is violated, it will be caught
	 */
	public void initCourses(){
		try{
			courses = new Course[numCourses];
			for(int i = 0; i < numCourses; i++){
				
				courses[i] = new Course(courseNumbers[i]);
			}
		}
		catch(IllegalArgumentException i){
			System.out.println("Make sure that the probabilities are in the appropriate range, (0.0,1.0]");
		}
	}
	
	public void setArrivalChances(){
		chanceOfStudentFromCourse = new BooleanSource[arrivalProbabilities.length];
		for(int i = 0; i < arrivalProbabilities.length; i++){
			chanceOfStudentFromCourse[i] = new BooleanSource(arrivalProbabilities[i]);
		}
	}
	
	public static void simulate(int currentTime){
		int minutes;
		
		
		
		for(int i = 0; i < courses.length; i++){
			if(chanceOfStudentFromCourse[i].occurs()){
				minutes = (int)(Math.random()*(maxTime-minTime)+minTime);
				System.out.println("Student "+Student.studentCounter+" for "+courses[i].getCourseNumber()+" has arrived requiring "+minutes+" minutes.");
				students.enqueue(new Student(currentTime, courses[i], minutes));
			}
			else{
				System.out.println("Student for Course Number "+courses[i].getCourseNumber()+" has not arrived");
			}
		}
		teacher.setTimeLeftTillFree(teacher.getHelpingStudent().getTimeRequired()-numCups);
		System.out.println("Teacher is helping Student "+teacher.getHelpingStudent().getStudentId()+" with "+teacher.getTimeLeftTillFree()+" minutes remaining");
	
		teacher.setTimeLeftTillFree(teacher.getTimeLeftTillFree()-1);
		
		for(int i = 0 ; i < TAs.length+1; i++){
			
		}
		
		if(teacher.getHelpingStudent()!=null && teacher.getTimeLeftTillFree() <= 0){
			teacher.setHelpingStudent(students.dequeue());
			}
		
	}
	
	


	/**
	 * Just to make my life easier
	 */
	public void printVars(){
		System.out.println("Data:");

		System.out.printf("%-21s%-15s"+"\n","Course","Probability");
		for(int i = 0; i < (15+21); i++){
			System.out.print("-");
		}
		System.out.println();
		for(int i = 0; i < courseNumbers.length; i++){
			System.out.printf("%-21s%-15s"+"\n",courseNumbers[i],arrivalProbabilities[i]);
		}
		
		
	    System.out.println(" Time range: "+minTime+"-"+maxTime+" minutes.");
	    System.out.println(" Total simulation time: "+simulationTime);
	    System.out.println(" Total number of TAs: "+numTAs);
	    System.out.println(" Total number of coffee cups: "+numCups);
	}
	
	
	/**
	 * Precondition
	 * The file must be a valid file with valid format, only format not semantics
	 * 
	 * Postcondition
	 * All the required variables get assigned to the right place.
	 */
	public void assignVars(){
		try(BufferedReader buffReader = new BufferedReader(new FileReader(fileName));){
			System.out.println("File Found!");
			System.out.println("");
			
			int numLines = 0;
		    String tempInfo = buffReader.readLine();
		    
		    int colonIndex;
		    for(int varNumber = 0; varNumber < numEntities; varNumber++){
		    	
		    	rawData[varNumber] = tempInfo;
		    	
		    	colonIndex = 0;
		    	for(;rawData[varNumber].charAt(colonIndex) != ':';){
		    		colonIndex++;
		    	}
		    	
		    	if(rawData[varNumber].substring(0,colonIndex).equals("number of courses")){
		    		numCourses = Integer.parseInt(rawData[varNumber].substring(colonIndex+1));
		    	}
		    	else if(rawData[varNumber].substring(0,colonIndex).equals("course numbers")){
		    		String[] temp = rawData[varNumber].substring(colonIndex+1).split(" ");
		    		courseNumbers = new int[temp.length];
		    		for(int index = 0; index < temp.length; index++){
		    			courseNumbers[index] = Integer.parseInt(temp[index]);
		    		}
		    	}
		    	else if(rawData[varNumber].substring(0,colonIndex).equals("arrival probabilities")){
		    		String[] temp = rawData[varNumber].substring(colonIndex+1).split(" ");
		    		arrivalProbabilities = new double[temp.length];
		    		for(int index = 0; index < temp.length; index++){
		    			arrivalProbabilities[index] = Double.parseDouble(temp[index]);
		    		}
		    	}
		    	else if(rawData[varNumber].substring(0,colonIndex).equals("min time")){
		    		minTime = Integer.parseInt(rawData[varNumber].substring(colonIndex+1));
		    	}
		    	else if(rawData[varNumber].substring(0,colonIndex).equals("max time")){
		    		maxTime = Integer.parseInt(rawData[varNumber].substring(colonIndex+1));
		    	}
		    	else if(rawData[varNumber].substring(0,colonIndex).equals("num cups")){
		    		numCups = Integer.parseInt(rawData[varNumber].substring(colonIndex+1));
		    	}
		    	else if(rawData[varNumber].substring(0,colonIndex).equals("simulation time")){
		    		simulationTime = Integer.parseInt(rawData[varNumber].substring(colonIndex+1));
		    	}
		    	else if(rawData[varNumber].substring(0,colonIndex).equals("number of tas")){
		    		numTAs = Integer.parseInt(rawData[varNumber].substring(colonIndex+1));
		    	}
		    	else{} 
		    	
		    	
	    		try{
					tempInfo = buffReader.readLine().trim();
				}
	    		catch (NullPointerException n) {
					//Do Nothing, if its null then that means it reached the end of the file.
				}
		    }
		}
		catch(FileNotFoundException f){
			System.out.println("FileNotFoundException");
		}
		catch(IOException i){
			System.out.println("IOException");
		}
	}
	
}
