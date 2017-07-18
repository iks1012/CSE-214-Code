package Homework07;

import java.util.Comparator;


/**
 * The <code>NameComparator</code> class sets up an easy way to sort the 
 * <code>Actor</code> by their names
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class NameComparator implements Comparator<Object>{
	
	
	
	/**
	 * 
	 * 
	 * This just returns the difference between the two names compared
	 * by using the <code>compareTo</code> method.
	 * 
	 * @param left, object containing the first name
	 * @param right, object containing the second name
	 * 
	 * 
	 * @return the difference
	 */
	@Override
	public int compare(Object left, Object right) {
		Actor leftActor = (Actor) left;
		Actor rightActor = (Actor) right;
		return leftActor.getName().compareTo(rightActor.getName());
	}
}
