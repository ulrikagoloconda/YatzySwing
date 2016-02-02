package Model;

import java.util.Observable;
/** 
 * ReturnValue is a class that stores data when the chart is being checked.
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */
public class ReturnValue extends Observable{
    private boolean isPossible;
    private int points;
    private int theDiceInt;
    private Box box;
	private int firstSixSum; 
    private int total; 
    private int bonus; 
    private Sum sum; 
    private boolean isSumValue; 
    private Player player; 

    /** 
	 * One constructor with no parameters
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    ReturnValue(){
    	
    }
    
    /** 
	 * Overloaded constructor that takes two parameters
	 * 
	 * @param Player player, int total
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    ReturnValue(Player player, int total) {
    this.player = player; 
    this.total = total; 
    
    }
    	
    /** 
	 * Sets the values from checking the chart
	 * 
	 * @param boolean isPossible, int points, Box box
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    protected void setValue(boolean isPossible, int points, Box box) {
    	this.isPossible = isPossible;
    	this.points = points;
    	this.box = box;
    	sendChangesToLabel();
    }

    /** 
	 * Overloaded method that takes two parameters and notify the observers
	 * 
	 * @param int firstSixSum, int total
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    protected void setValue(int firstSixSum, int total){
    	this.firstSixSum += firstSixSum; 
    	this.total += total; 
    	if(firstSixSum >= 63){
    		bonus = 50;
    	}
    	setChanged();
    	notifyObservers();
    }
    
    /** 
	 * Returns the player
	 * 
	 * @return Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    public Player getPlayer(){
    	return player; 
    }
   
    /** 
	 * Returns the total score
	 * 
	 * @return int scoreTotal
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */  
    public int getFirstSixSum() {
		return firstSixSum;
	}

    /** 
     * Sets the sum of the upper part of the score.
     * 
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
	public void setFirstSixSum(int firstSixSum) {
		this.firstSixSum = firstSixSum;
	}

	  /** 
     * Returns the sum of the chart. 
     * 
     * @return int total
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
	public int getTotal() {
		return total;
	}
	
	  /** 
     * Sets the sum of the total chart
     * 
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
	public void setTotal(int total) {
		this.total = total;
	}

	  /** 
     * Returns the bonus
     * 
     * @return int bouns
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
	public int getBonus() {
		return bonus;
	}

	  /** 
     * Sets the bonus
     * 
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
    
	  /** 
     * Returns a boolean which tells if it is a sumValue or not. 
     * 
     * @return boolean is SumValue
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    public boolean isSumValue(){
    	return isSumValue; 
    }
    
    /** 
     * Returns the 'sum', which is a enum type
     * 
     * @return Sum sum
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    
    public Sum getSum(){
    	return sum; 
    }

    /** 
     * Returns a boolean which tells if the option of choice is possible or not
     * 
     * @return boolean isPossible
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    public boolean getIsPossible() {
        return isPossible;
    }
    
    /** 
     * Sets the boolean isPossible that tells if the option is possible or not
     * 
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    public void setIsPossible(boolean bool){
    	this.isPossible = bool; 
    }
   
    /** 
     * Returns the points
     * 
     * @return int points
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    public int getPoints() {
        return points;
    }

    /** 
     * Sets the variable that represents the value of the dice
     * 
     * @return int bouns
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    protected void setTheDiceInt(int theDiceInt) {
        this.theDiceInt = theDiceInt;
    }

    /** 
     * Returns the variable that represents the value of the dice
     * 
     * @return int theDiceInt
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    protected int getTheDiceInt() {
        return theDiceInt;
    }

    /** 
     * Returns the box, an enum Variable
     * 
     * @return Box box
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    public Box getBox() {
        return box;
    }

    /** 
     * Clear the values of the object
     * 
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    protected void clearValues() {
        isPossible = false;
        points = 0;
        box = null;  
       sendChangesToLabel();
    }
    
    /** 
     * Sends notifications to the observers
     * 
     * @author UlrikaGoloconda
     * @version 1.0 December 4, 2015
     */
    protected void sendChangesToLabel(){
    	setChanged();
		notifyObservers();
    }
}

