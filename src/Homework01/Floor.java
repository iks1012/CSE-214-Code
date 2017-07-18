package Homework01;

/*
 * Ishan Sethi
 * E-mail: ishan.sethi@stonybrook.edu
 * Stony Brook ID: 110941217
 * NetID: isethi
 * Homework 01
 */
public class Floor {
	private Student[] students;
	public final int MAX = 4;
	private int numStudentsATM; //ATM stands for At the Moment
	
	public Floor(){
		numStudentsATM = 0;
		students = new Student[MAX];
		for(int i  = 0; i < MAX; i++){
			students[i] = new Student();
		}
	}
	
	
	/**
	 * 
	 * @param students
	 * 		This is the student that you wish to add to the floor
	 * @param position
	 * 		This is the position in which you wish to place said student
	 * 
	 * [Preconditions]
	 * Nothing new at all this works with the parameters even right after compiling
	 * 
	 * [Postconditions]
	 * Student is added
	 * 
	 */
	public void addStudent(Student students, int position){
		try{
			if((position < 0 || position >= MAX) /*&& position > numStudentsATM*/  ){
				throw new IllegalArgumentException();
			}
			else if(numStudentsATM >= MAX){
				throw new FullFloorException();
			}
			else if (this.students[position].getName() == ""){
				this.students[position] = students;
				numStudentsATM++;
				System.out.println(this.students[position].getName()+" was added"); //Testing purposes
				
			}
			else if (count() < MAX && position < MAX){
				shiftRight(position);
				this.students[position] = students;
			}
			else{
				throw new IllegalArgumentException();
			}
			
			
			
			
			// This lines up the Student added to the left side of the Array if need be
			if(position>0 && this.students[position-1].getName() == ""){
				shiftLeft(position);
			}
		}
		catch(IllegalArgumentException i){
			System.out.println("Yo, wrong input, try again.");
		}
		catch(FullFloorException f){
			System.out.println("The thingy is full.");
		}
	}
	
	
	/**
	 * 
	 * @param position
	 * 		This is the position at which you want to check if there is open spots to the left of.
	 * 
	 * [Precondition]
	 * 		There needs to be things within the array of Students. Other then that everything else that needs to be instantiated is already done so either at compile time or within the method itself
	 * 
	 * [Postcondition]
	 * 		The array should have certain specified spots shifted to the left thereby leaving no holes  
	 * 
	 */
	public void shiftLeft(int position) {
		int shiftLeftAMT = 0;
		for(int i = position-1; i >=0; i--){
			if(this.students[i].getName()==""){
				shiftLeftAMT++;
			}
			else{
				break;
			}
			if(i == 0){
				break;
			}
			
		}
		//System.out.println(shiftLeftAMT);
		this.students[position-shiftLeftAMT] =  this.students[position].clone();
		removeStudent(position+1);
		
		
		
	}

	/**
	 * 
	 * @param position
	 * 		This is the position at which you want to move all the elements to the right of to the right by one spot in the array of students
	 * 
	 * 
	 * [Precondition]
	 * 		There needs to be things within the array of Students. Other then that everything else that needs to be instantiated is already done so either at compile time or within the method itself
	 * 
	 * [Postcondition]
	 * 		The indicated spots should have shifted right by one.
	 * 
	 * 
	 * 
	 */
	public void shiftRight(int position){
		int tempAmt = count();
		for(int i = tempAmt; i > position; i--){
			this.students[i+1]=this.students[i];
		}
	}
	
	/**
	 * 
	 * @param position
	 * 		This is the position of the student you wish to remove
	 * 
	 * [Precondition]
	 * 		The position must point to a valid student, or else an Exception that is taken care of will be thrown
	 * 
	 * [Postcondition]
	 * 		The indicated student is wiped and the hole is removed
	 * 
	 * @return 
	 * 		returns the student that was removed, if any
	 */
	public Student removeStudent(int position){
		Student returnThis = null;
		try{
			if(position<MAX && position>=0){
				returnThis = (this.students[position].getName() != "")?(this.students[position]):(new Student("",0,0));
				this.students[position].wipe(position);
				shiftLeft(position);
				numStudentsATM--;
			}
			else if(count() == 0){
				throw new EmptyFloorException();
			}
			else{
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException i){
			System.out.println("Yo, wrong input, try again.");
		}
		catch(EmptyFloorException e){
			System.out.println("The thingy is empty, add something before removing");
		}
		return returnThis;
	}
	
	
	/**
	 * 
	 * @param position
	 * 		The position of the student you want within said floor.
	 * 
	 * [Precondition]
	 * 		The position must have a student or else an Exception that is taken care of will be thrown
	 * 
	 * [Postcondition]
	 * 		You now know what student is on the specified position.
	 * 
	 * 
	 * @return
	 * 		returns the Student that is present at that position, if any.
	 */
	public Student getStudent(int position){
		try{
			if(this.students[position].getName() != "" && position >= 0 && position < MAX){
				return this.students[position];
			}
			else{
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException i){
			System.out.println("Yo, wrong input, try again.");
		}
		return null; //this is there because compiler wants it, this should never be reached.
		
	}
	
	
	/**
	 * 
	 * @param student
	 * 		The student that you wish to place at later specified position.
	 * @param position
	 * 		The position at which you wish the student to go.
	 * 
	 * 
	 * [Precondition]
	 * 		The position must point an empty spot, or else an Exception that is taken care of will be thrown
	 * 
	 * [Postcondition]
	 * 		The student is set to that position.
	 * 
	 */
	public void setStudent(Student student, int position){
		try{
			if(this.students[position].getName() == "" && position >= 0 && position < MAX){
				this.addStudent(student, position);
				shiftLeft(position);
			}
			else{
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException i){
			System.out.println("Yo, wrong input, try again.");
		}
	}
	
	/**
	 * This is an O(1) method
	 * Everytime a student was successfully added, the returned number is increased by one.
	 * Everytime a student was successfully removed, the returned number is DECREASED by one
	 * 
	 * Moving a student boils down to really just removing then adding, so the net is unchanged
	 * 
	 * If the spot is taken, the program will just add the student to the same previous position.
	 * 
	 * @return
	 * 		The net is the total count of students and that is what is returned.
	 */
	public int count(){
		return numStudentsATM;
	}
	
	/**
	 * Does exactly what you'd expect.
	 * Deep clone of the floor you use this on.
	 * so the syntax would be
	 * theNewFloor = theOldFloor.clone();
	 * That would copy every attribute and property at the time of cloning.
	 * 
	 */
	public Floor clone(){
		Floor cloned = new Floor();
		for(int i  = 0; i < MAX; i++){
			//cloned.students[i] = new Student();
			cloned.students[i] = this.students[i].clone();
		}
		return cloned;
	}
}
