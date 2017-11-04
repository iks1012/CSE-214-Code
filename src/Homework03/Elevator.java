/**
 * This classes generates the boolean value for if someone has requested or not depending on the probability
 * that is desired
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Elevator {
	private int currentFloor;
	private int elevatorState;
	private Request request;
	
	//Potential Elevator States
	final int IDLE = 0;
	final int TO_SOURCE = 1;
	final int TO_DESTINATION = 2;
	
	/**
	 * This returns an instance of the <code>Elevator</code> class with the state as IDLE and no request at hand
	 *
	 */
	public Elevator(){
		currentFloor = 1;
		elevatorState = IDLE;
		request = null;
	}

	/**
	 * This method returns the current floor that this elevator is on.
	 *
	 * @return
	 * 		The current floor
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * This method sets the current floor to the value passed through
	 *
	 * @param currentFloor
	 * 		This is the new current floor that is passed through
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	/**
	 * This method returns the elevator state and tells the user whether if the elevator is IDLE, TO_SOURCE or TO_DESTINATION
	 * @return
	 * 		the user whether if the elevator is IDLE, TO_SOURCE or TO_DESTINATION
	 */
	public int getElevatorState() {
		return elevatorState;
	}

	/**
	 * This method allows the elevator state to be changed to whatever is to be needed, but the default ones are IDLE, TO_SOURCE or TO_DESTINATION
	 * @param elevatorState
	 * 		The Elevator state
	 */
	public void setElevatorState(int elevatorState) {
		this.elevatorState = elevatorState;
	}

	/**
	 * This method returns an instance of the request that is related to the elevator and where the person in the elevator wants to go.
	 *
	 * @return
	 * The request
	 *
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * This method allows the request to be set and eventually changed if need be
	 *
	 * @param request
	 * 		The request.
	 */
	public void setRequest(Request request) {
		this.request = request;
	}
}
