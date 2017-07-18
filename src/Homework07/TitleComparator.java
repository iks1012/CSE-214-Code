package Homework07;

import java.util.Comparator;

/**
 * The <code>TitleComparator</code> class sets up an easy way to sort the 
 * <code>Movie</code>s by the titles
 * 
 * @author Ishan Sethi
 * Email: ishansethi8@gmail.com
 * Stony Brook ID: 110941217
 *
 */
public class TitleComparator implements Comparator<Object>{

	
	/**
	 * 
	 * 
	 * This just returns the difference between the two <code>titles</code> compared
	 * by using the <code>compareTo</code> method.
	 * 
	 * @param left, object containing the first title
	 * @param right, object containing the second title
	 * 
	 * 
	 * @return's the difference
	 */
	@Override
	public int compare(Object left, Object right) {
		Movie leftMovie = (Movie) left;
		Movie rightMovie = (Movie) right;
		return leftMovie.getTitle().compareTo(rightMovie.getTitle());
	}
}
