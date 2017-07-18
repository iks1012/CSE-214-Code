package Homework04;

public class Course {
	private int courseNumber;
	private int courseDifficulty;
	private double arrivalProbability;
	
	
	
	
	
	
	
	/**
	 * <dt>Precondition:
	 * 	<dd><code>course</code>'s field <code>courseNumber</code> should be a valid course number
	 * 
	 * 
	 *
	 * @param courseNum
	 * 
	 * 
	 * 
	 * @exception IllegalArgumentException
	 * 	This occurs when <code>courseNum</code> is not a valid courseNumber
	 * 
	 * 
	 */
	
	public Course(int courseNum){
		boolean exists = false;
		int i = 0;
		for(; i < Simulation.courseNumbers.length;){
			if(Simulation.courseNumbers[i] == courseNum){
				exists = true;
			}
			i++;
		}
		
		if(exists){
			courseNumber = courseNum;
			arrivalProbability = Simulation.arrivalProbabilities[i-1];
		}
		else
			throw new IllegalArgumentException();
	}

	





	public int getCourseNumber() {
		return courseNumber;
	}








	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	
}
