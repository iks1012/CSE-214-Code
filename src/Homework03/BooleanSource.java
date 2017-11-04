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
public class BooleanSource {
	//The probability
	private double probability;
	
	/**
	 * This returns an instance of <code>BooleanSource</code> with the probability of <code>prob</code>
	 * @param prob
	 * 		The soon-to-be probability
	 */
	public BooleanSource(double prob){
		if(prob<0.0||prob>1.0){
			throw new IllegalArgumentException();
		}
		probability = prob;
	}
	
	/**
	 * This method requests for whether or not a request has occurred, obviously with respect to the probability of 
	 * the <code>BooleanSource</code>
	 * 
	 * @return
	 * 		If a <code>Request</code> occurs or not 
	 */
	public boolean requestArrived(){
		return (Math.random()<probability);
	}
}
