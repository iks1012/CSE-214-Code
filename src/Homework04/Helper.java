package Homework04;

public class Helper {
	private int timeLeftTillFree;
	private final boolean isProfessor;
	private Student helpingStudent = new Student(0,null,0);
	
	
	
	
	public int getTimeLeftTillFree() {
		return timeLeftTillFree;
	}




	public void setTimeLeftTillFree(int timeLeftTillFree) {
		this.timeLeftTillFree = timeLeftTillFree;
	}




	public Helper(boolean isProf){
		timeLeftTillFree = 0;
		isProfessor = isProf;
	}




	public Student getHelpingStudent() {
		return helpingStudent;
	}




	public void setHelpingStudent(Student helpingStudent) {
		this.helpingStudent = helpingStudent;
	}
	
	
}
