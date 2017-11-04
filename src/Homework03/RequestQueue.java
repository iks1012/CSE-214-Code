import java.util.HashMap;

/**
 * This class manages the queue of all the <code>Request</code> objects and derived from the HashSet class
 * of the Java API
 *
 * @author Ishan Sethi
 *      email: ishan.sethi@stonybrook.edu
 *      Stony Brook ID: 110941217
 *      Net ID: isethi
 *
 */
public class RequestQueue extends HashMap<Integer, Request> {
	static int addIndex = 0;
	static int removeIndex = 0;
	
	
	/**
	 * <dt>Preconditions
	 * 	<dd>The request must be a valid request
	 * 	<dd>In this program, it is never the case that enqueue is called and the request is not valid
	 * 
	 * This method basically adds a request to the database which is engineered to pose as a queue.
	 * 
	 * Order of Complexity (time): O(1)
	 * 
	 * @param req
	 * 		The request
	 * 
	 */
	public void enqueue(Request req){
		addIndex++;
		put(addIndex, req);
	}
	
	
	/**
	 * <dt>Preconditions
	 * 	<dd>There must be a request in the queue
	 * 
	 * Order of Complexity (time): O(1)
	 * 
	 * @return
	 * 		The request that was removed from the queue as per the definition of Dequeue.
	 * 
	 * @exception - IllegalArgumentException
	 * 		This is thrown if the precondition is violated
	 */
	public Request dequeue(){
		if(removeIndex>=addIndex){
			throw new NullPointerException();
		}
		else{
			Request returnThis = get(removeIndex);
			remove(removeIndex);
			removeIndex++;
			return returnThis;
		}
	}
	
	/**
	 * <dt>Preconditions
	 * 	<dd>There needs to be a request or the method will return a negative number
	 * 
	 * This method basically returns the size of the queue in O(1) time
	 * 
	 * Order of Complexity (time): O(1)
	 */
	public int size(){
		return addIndex-removeIndex;
	}
	
    
}
