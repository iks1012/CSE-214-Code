package Homework04;

public class BooleanSource {
	private double probability;
	
	
	/**
	 * Is a constructor and defines the <code>probability</code> variable and the value is set to <code>initProbability</code>
	 * 
	 * 
	 * <dt>Precondition:
	 * 	<dd><code>initProbability</code> must in between 0 (non inclusive) and 1 (inclusive) 
	 * 
	 * @param initProbability
	 * 	The probability that is desired
	 * 
	 * 
	 * @exception IllegalArgumentException
	 * 	This occurs when <code>initProbability</code> is less then 0 (inclusive) or greater than 1 (non inclusive)
	 * 
	 */
	
	public BooleanSource(double initProbability){
		if(initProbability>0 || initProbability <=1 )
			probability = initProbability;
		else
			throw new IllegalArgumentException(); 
	}
	
	
	/**
	 * 
	 * <dt>Precondition:
	 * 	<dd><code>initProbability</code> must in between 0 (non inclusive) and 1 (inclusive) 
	 * 
	 *
	 * @return
	 * 	Returns true only based on the specified probability, <code>probability</code>
	 */
	
	public boolean occurs(){
		return (Math.random() <= probability);
	}
	
}
