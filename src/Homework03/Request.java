/**
 * This class is the <code>Request</code> object and will contain all the properties of any given request.
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class Request {

    private int sourceFloor;
    private int destinationFloor;
    private int timeEntered;

    /**
     *
     * <dt>Preconditions
     *  <dd>The variable numFloors must be defined
     *
     *
     * This returns and instance of the <code>Request</code> object with the parameters as the properties of the Request.
     * The two integers represent the values for sourceFloor and destinationFloor, which will be randomly generated
     * within this constructor. The random values must be between 1 and the number of floors in the building, inclusive.
     *
     *
     * @param numFloors
     *      The maxNumber of floors the elevator can go upto.
     */
    public Request(int numFloors) {
        sourceFloor = ((int)(Math.random() * numFloors-1))+1;
        destinationFloor = ((int)(Math.random() * numFloors-1))+1;
    }


    /**
     * This returns the <code>sourceFloor</code> of the given <code>Request</code>
     * @return
     * the <code>sourceFloor</code>
     */
    public int getSourceFloor() {
        return sourceFloor;
    }


    /**
     * This method returns the destination floor of any given request
     * @return
     *      The destination
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * This method returns the time that was entered for the corresponding request
     * @return
     *      The request
     */
    public int getTimeEntered() {
        return timeEntered;
    }

    /**
     * This method allows the time of the request to be set.
     *
     * @param timeEntered
     *      The new time
     */
    public void setTimeEntered(int timeEntered) {
        this.timeEntered = timeEntered;
    }
}
