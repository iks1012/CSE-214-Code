/**
 * This classes will be used to actually carry out the simulation
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Simulator {

	final static int IDLE = 0;
	final static int TO_SOURCE = 1;
	final static int TO_DESTINATION = 2;


	static boolean isTesting = Analyzer.isTesting;
	static int totalRequests = 0;
	static int totalWaitTime = 0;

	/**
	 *	This method carries out the simulation for all the elevators
	 *
	 * <dt>Preconditions
	 * 	<dd>The <code>probability</code> has to be a double in between 0.0 and 1.0, inclusive.
	 * 	<dd>The <code>numFloors</code> has to be an integer greater than 1.
	 * 	<dd>The <code>numElevators</code> has to an integer greater than 0.
	 *  <dd>The <code>length</code> has to be an integer greater than 0.
	 * 
	 * @param probability
	 * 		The probability of a <code>Request</code> coming in (dependent on <code>BooleanSource</code>)
	 * @param numFloors
	 * 		The maximum number of Floors in the "building"
	 * @param numElevators
	 * 		The maximum number of <code>Elevators</code> in the "Building"
	 * @param length
	 * 		The total length of the simulation in a given time unit.
	 */
	public static void simulate(double probability, int numFloors, int numElevators, int length){

		//init everything
		RequestQueue queue = new RequestQueue();
		BooleanSource booleanSource = new BooleanSource(probability);//We arent going to get an exception because it was handled in the Analyzer class
		Elevator[] allElevators = new Elevator[numElevators];
		for(int i = 0; i < numElevators; i ++){
			allElevators[i] = new Elevator();
		}

		//Current Time
		int time = 0;


		//Begin Simulation
		if(isTesting){
			pln("Now to begin the simulation");
		}

		while(time<length){

			if(isTesting){
				pln("Step "+(time+1)+": ");
			}

			if(booleanSource.requestArrived()){
				Request tempRequest = new Request(numFloors);
				tempRequest.setTimeEntered((time+1));
				queue.enqueue(tempRequest);
				if(isTesting){
					pln("Time = "+tempRequest.getTimeEntered()+" minutes -> New Request from floor "+tempRequest.getSourceFloor()+" to floor "+tempRequest.getDestinationFloor()+"");
					pln("Pushed to the Queue!");
				}
				totalRequests++;
			}



			for(int i = 0; i<numElevators; i++){
				int state = allElevators[i].getElevatorState();
				if(state == IDLE){
					try{
						Request request = queue.dequeue();

						if(isTesting){
							pln("Handling popped request ("+request.getSourceFloor()+", "+request.getDestinationFloor()+", "+request.getTimeEntered()+")");
						}
						allElevators[i].setRequest(request);
						allElevators[i].setElevatorState(TO_SOURCE);
					}
					catch(NullPointerException npe){
						//No Requests to Handle, proceed to the occupied elevators
						if(isTesting){
							pln("No Requests");
						}
					}
				}


				if(state == TO_SOURCE){
					int currFloor = allElevators[i].getCurrentFloor();
					int srcFloor = allElevators[i].getRequest().getSourceFloor();
					if(currFloor < srcFloor){
						currFloor++;
						totalWaitTime++;
					}
					else if(currFloor > srcFloor){
						currFloor--;
						totalWaitTime++;
					}
					else{//currFloor == srcFloor
						allElevators[i].setElevatorState(TO_DESTINATION);
					}
					allElevators[i].setCurrentFloor(currFloor);
				}

				if(state == TO_DESTINATION){
					int currFloor = allElevators[i].getCurrentFloor();
					int destFloor = allElevators[i].getRequest().getDestinationFloor();
					if(currFloor < destFloor){
						currFloor++;
					}
					else if(currFloor > destFloor){
						currFloor--;
					}
					else{//currFloor == destFloor
						allElevators[i].setElevatorState(IDLE);
					}
					allElevators[i].setCurrentFloor(currFloor);
				}
			}

			pln("Elevators: ");
			for(int i = 0; i<numElevators; i++){
				int st = allElevators[i].getElevatorState();
				String state = ((st==IDLE) ? ("IDLE") : (((st==TO_SOURCE) ? ("TO_SOURCE") : (((st==TO_DESTINATION) ? ("TO_DESTINATION") : ("Error"))))));
				pln("[Floor "+allElevators[i].getCurrentFloor()+", "+state+", "+(state.equalsIgnoreCase("IDLE") ? ( "---]"):
						("("+allElevators[i].getRequest().getSourceFloor()+", "+allElevators[i].getRequest().getDestinationFloor()+", "+allElevators[i].getRequest().getTimeEntered()+")], ")));
			}

			time++;
		}



		pln("Total Wait time: "+totalWaitTime);
		pln("Total Requests: "+totalRequests);
		String answer = String.format("%.02f", (((double)totalWaitTime)/((double)totalRequests)));
		pln("Average Wait Time: "+ answer);


	}


	/**
	 * This method is to help typing speed only
	 * @param pln
	 * 		The string that is printed
	 */
	public static void pln(String pln){
		System.out.println(pln);
	}
	
}
