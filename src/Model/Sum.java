package Model;

/** 
 * This Enum contains of the three different kinds of sums in the game. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 4, 2015
 */
public enum Sum {
	SUM, BONUS, TOTAL;
	  /** 
     * Overloaded method that returns a useful string 
     * 
     * @return String local
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
	public String toString(){
		String local = null; 
		switch (this){
		case SUM: local = "Summa";
		break; 
		case BONUS : local = "Bonus";
		break; 
		case TOTAL : local = "Total";
		break; 
		}
		return local; 
	}
}
