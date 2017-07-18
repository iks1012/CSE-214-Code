package Homework04;

public class Student {
	static int studentCounter = 0;
	private int studentId;
	private int timeArrived;
	private int timeRequired;
	private Course course;
	
	/**
	 * <dt>Precondition:
	 * 	<dd><code>initProbability</code> must be greater than 0 (non inclusive)
	 * 	<dd><code>course</code>'s field <code>courseNumber</code> should be a valid course number
	 * 
	 * 
	 * @param timeArrived
	 * 	Should be greater than 0 (Non Inclusive)
	 * @param course
	 * 	
	 * @param timeRequired
	 * 	
	 * 
	 * <dt>Postcondition:
	 * 	<dd>The new ID number is assigned
	 * 	<dd>Student is Constructed
	 */
	public Student(int timeArrived, Course course, int timeRequired) {
		if(course == null || timeArrived > 0){
			this.timeArrived = timeArrived;
			this.timeRequired = timeRequired;
			this.course = course;
			studentId = ++studentCounter;
		}
		else{
			throw new IllegalArgumentException();
		}
		
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getTimeArrived() {
		return timeArrived;
	}

	public void setTimeArrived(int timeArrived) {
		this.timeArrived = timeArrived;
	}

	public int getTimeRequired() {
		return timeRequired;
	}

	public void setTimeRequired(int timeRequired) {
		this.timeRequired = timeRequired;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
}
