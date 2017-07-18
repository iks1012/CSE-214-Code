package Homework04;

public class StudentQueue {
	Student[] students; //index 0 is the front of line, index size is the back.
	private int size = 0;
	private int initLength = 5;
	private int currLen;
	private int totalStudents = 0;
	
	public StudentQueue(){
		students = new Student[initLength];
		
		currLen = initLength;
	}
	
	public void expandLimit(){
		if(size == currLen){
			Student[] temp = new Student[currLen];
			
			for(int i = 0; i < currLen; i++){
				temp[i] = students[i];
			}
			currLen *= 2;
			students = new Student[currLen];
			for(int i = 0; i < temp.length; i++){
				students[i] = temp[i];
			}
		}
		
	}
	
	public int getTotalStudents(){
		return totalStudents;
	}
	
	public int getIndex(Student s){
		for(int i = 0; i < size; i++){
			if(students[i] != null && s.getCourse().getCourseNumber() > students[i].getCourse().getCourseNumber()){
				return i;
			}
		}
		return -1;
	}

	public void insertAtIndex(Student s, int index){
		Student temp;
		for(int i = size-1; i >= index; i--){
			if(students[i] != null){
				students[i+1] = students[i];
			}
		}
		students[index] = s;
	}
	
	
	public void enqueue(Student s){
		expandLimit();
		int newIndex = getIndex(s);
		if(newIndex == -1){
			students[size]  = s;
		}
		else{
			insertAtIndex(s,newIndex);
		}
		size++;
		totalStudents++;
	}
	
	
	public void shiftUpByOne(){
		for(int i = 0; i < size; i++){
			students[i+1] = students[i];
		}
		students[size]=null;
	}
	
	
	public Student dequeue(){
		Student s = students[0];
		students[0] = null;
		shiftUpByOne();
		size--;
		return s;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
}
