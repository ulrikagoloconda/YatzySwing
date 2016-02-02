package Model;

import java.util.Comparator;
/** 
 * SorterCurrentScore implements the method needed to sort objects of type CurrentScore, which is the purpose of this class
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 4, 2015
 */

public class SorterCurrentScore implements Comparator<CurrentScore> {
	
	  /** 
     * Returns an int which tells if the second object is larger, equal or smaller than the first or not
     * 
     * @param CurrentScore o1, CurrentScore o2
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
	@Override
	public int compare(CurrentScore o1, CurrentScore o2) {
		int i = 0;
		CurrentScore cs1 = (CurrentScore)o1;
		CurrentScore cs2 = (CurrentScore)o2;
		i = cs2.getScoreTotal()-cs1.getScoreTotal();
		return i;
	}

}
