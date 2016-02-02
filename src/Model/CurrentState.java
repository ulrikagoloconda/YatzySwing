package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
/** 
 * CurrentState is a class that holds the updated state of the game in static fields.
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */
public class CurrentState extends Observable {
	private static ArrayList<YDice> currentListOfDice;
	private static ArrayList<ReturnValue> currentListOfReturnValuse; 
	private static ArrayList<BoxLogic> currentListOfBoxLogic; 
	private static Player currentPlayer; 
	private static YChart currentChart;
	private static ArrayList<YDice> savedDiceList;
	private static ArrayList<Integer> savedIconIntList; 
	private static int count; 
	private static Color currentColor; 
	static {
		savedDiceList = new ArrayList<>();
		savedIconIntList = new ArrayList<>();
	}
	 
	/** 
	 * Returns the current list of dice
	 * 
	 * @return ArrayList currentListOfDice
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static ArrayList<YDice> getCurrentListOfDice() {
		return currentListOfDice;
	}

	/** 
	 * Initializes the list of dice
	 * 
	 * @param ArrayList<YDice> dices
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void initCurrentListOfDice(ArrayList<YDice> dices){
		currentListOfDice = dices;
	}

	/** 
	 * Sets the current list of dice
	 *
	 * @param ArrayList<YDice> currentListOfDice
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void setCurrentListOfDice(ArrayList<YDice> currentListOfDice) {
		for (int i = 0; i < currentListOfDice.size(); i++){
			if (!currentListOfDice.get(i).equals(currentListOfDice.get(i))){
				currentListOfDice.get(i).setDiceChanges();
			}
		}
		CurrentState.currentListOfDice = currentListOfDice;
	}

	/** 
	 * Adds a dice to the list of saved dice by using diceID
	 * 
	 * @param String diceID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void addToSavedDiceList(String diceID){
		for (YDice dice : currentListOfDice){
			if (dice.getDiceID().equals(diceID)){
				savedDiceList.add(dice);
				break; 
			}
		}	
	}
	
	/** 
	 * Adds to the list of int value of the saved dice
	 * 
	 * @param int value
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void setSavedIntList(int value){
		savedIconIntList.add(value);
	}
	
	/** 
	 * Subtract 1 from count to count down the number of times the player are rolling the dice
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void subFromCout(){
		count -=1; 
	}
	
	/** 
	 * Returns the int representing the number of times the player are allowed to roll the dice
	 * 
	 * @return int count
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static int getCount(){
		return count; 
	}
	
	/** 
	 * Sets the count to 3
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void resetCount(){
		count = 3; 
	}
	
	/** 
	 * Returns a boolean telling if the player are allowed to roll the dice again or not
	 * 
	 * @return boolean isStillCounting
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static boolean isStillCounting(){
		if(count == 0){
			return false; 
		} else {
			return true; 
		}
	}
	
	/** 
	 * Returns a list of the values held by the dice
	 * 
	 * @return ArrayList savedIconIntList
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static ArrayList getIntList(){
		return savedIconIntList; 
	}

	/** 
	 * Returns the list of saved dice
	 * 
	 * @return ArrayList savedDiceList
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static ArrayList<YDice> getSavedList(){
		return savedDiceList;
	}
	
	/** 
	 * Clear all dice from the saved list
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void clearSavedList(){
		savedDiceList.clear();
	}

	/** 
	 * Returns the current list of return values
	 * 
	 * @return ArrayList currentListOfReturnValues
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static ArrayList<ReturnValue> getCurrentListOfReturnValuse() {
		return currentListOfReturnValuse;
	}

	/** 
	 * Sets the current list of ReturnValues
	 * 
	 * @param ArrayList currentListOfReturnValues
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void setCurrentListOfReturnValuse(ArrayList<ReturnValue> currentListOfReturnValuse) {
		CurrentState.currentListOfReturnValuse = currentListOfReturnValuse;
	}
	
	/** 
	 * Returns the current player
	 * 
	 * @return Player currentPlayer
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	/** 
	 * Sets the current player and notify observers. This is the only method in the class CurrentState that is nonStatic
	 * 
	 * @param Player currentPlayer
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		CurrentState.currentPlayer = currentPlayer;
		setChanged();
		notifyObservers();
	}

	/** 
	 * Returns the current chart
	 * 
	 * @return YChart currentChart
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static YChart getCurrentChart() {
		return currentChart;
	}

	/** 
	 * Sets the current chart
	 * 
	 * @param YChart currentChart
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static  void setCurrentChart(YChart currentChart) {
		CurrentState.currentChart = currentChart;
	}

	/** 
	 * Returns the total score
	 * 
	 * @return int scoreTotal
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void setCurrentListOfBoxLogic(ArrayList<BoxLogic> listOfBoxLogic) {
		CurrentState.currentListOfBoxLogic = listOfBoxLogic;
	} 

	/** 
	 * Returns the current list of BoxLogic
	 * 
	 * @return ArrayList currentListOfBoxlogic
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static ArrayList<BoxLogic> getCurrentListOfBoxLogic(){
		return currentListOfBoxLogic;
	}
	
	/** 
	 * Sets the current color of the game
	 * 
	 * @param Color color
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void setCurrentColor(Color color){
		CurrentState.currentColor = color; 
	}

	/** 
	 * Returns the current color
	 * 
	 * @return Color color
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static Color getCurrentColor(){
		return currentColor; 
	}

	/** 
	 * Delete one object from the list of saved dice
	 * 
	 * @param YDice dice
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void delFromSavedDiceList(YDice dice) {
		savedDiceList.remove(dice);
	}
}
