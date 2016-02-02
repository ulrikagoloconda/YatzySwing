package Model;
/** 
 * YDice is the class that holds the Model aspects of the dice in the game
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 4, 2015
 */
import java.util.Observable;
import java.util.Random;

public class YDice extends Observable{
	private Random rand;
	private static int numberOf;
	private int diceInt;
	private String diceID;

	/** 
	 * The constructor of the class, takes no parameters but gives the dice a ID
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	YDice(){
		addNumberOfDices();
		
		int intDiceID = numberOf;
		diceID = "" + intDiceID; 
		
	}
	
	/** 
	 * Give the dice a new random value between 1 and 6
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public void rollTheDice(){
		rand = new Random();
		diceInt = rand.nextInt(6)+1;
	}

	/** 
	 * Returns the value of the dice
	 * 
	 * @return int diceInt
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public int getDiceInt(){
		return diceInt;
	}

	/** 
	 * Adds one to the total number of dice
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private void addNumberOfDices(){
		numberOf++;
	}
	
	/** 
	 * Send changes to the observers
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */ 
	
	public void setDiceChanges(){
		setChanged();
		notifyObservers();
	}
	
	/** 
	 * 	Returns the ID of the dice 
	 * @return String ID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public String getDiceID(){
		return diceID;
	}
	
	/** 
	 * Override method that returns a usefull string based on the value and ID of the dice
	 * 
	 * @return String 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public String toString(){
		return ""+ diceInt + " DiceID " + diceID; 
	}
}
