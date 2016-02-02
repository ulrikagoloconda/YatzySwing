package Model;

import java.util.ArrayList;

public interface GameInterface {

	/**
	 * Initialize the necessary lists and objects.
	 *  @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */

	void initGame();

	/**
	 * Sets one object of type Player to be te current player
	 * 
	 * @param player en instans av Klassen Player
	 *  @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	void setCurrentPlayerInstance(Player player);

	/**
	 * Initialize an ArrayList which can hold object of type Player and add new objects to the list. 
	 * The method also adds objects of type CurrentScore to an ArrayList
	 * 
	 * @param names En ArrayList med objekt av typen String
	 *  @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	void setPlayersAndScoreList(ArrayList<String> names);

	/**
	 * Returns the ArrayList of CurrentScore
	 * 
	 * @return ArrayList<CurrentScore> 
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	ArrayList<CurrentScore> getListOfCurrentScore();

	/**
	 * Sets the one object of YChart that is connected to the currentPlayer to be the current chart. 
	 * 
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	void setCurretnChart();

	/**
	 * Returns the list of YChart
	 * 
	 * @return ArrayList<YChart>
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	ArrayList<YChart> getChartList();

	/**
	 * Changes the current player to the next player in line.  
	 * 
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	void newCurrentPlayer();

	/**
	 * Checks the current chart by calling methods with corresponding rule 
	 * 
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	void checkChart();

	/**
	 * Check if there is any valid option for the player to choose
	 * 
	 * @return boolean 
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	boolean isValidOptionLeft();

	/**
	 * Sums up the score from the previous check of current chart
	 * 
	 *
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	void addPoints();

	/**
	 * Clears all values saved in the ReturnValue objects
	 * 
	 *
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	void clearListOfReturnValues();

	/**
	 * Returns the list of YDice
	 * 
	 *@return ArrayList<YDice>
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	ArrayList<YDice> getTheRolledDices();

	/**
	 * Returns the list of Box
	 * 
	 *@return ArrayList<Box>
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	ArrayList<Model.Box> getListOfBox();

	/**
	 *Sets a new list of type BoxLogic to the current list of BoxLogic 
	 *
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	void setCurrentListOfBoxLogicList();

	/**
	 *Checks if the game is over.
	 *
	 *@return boolean
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	boolean winCheck();

	/**
	 *Sorts the list of currentScore so that the players is placed in the ArrayList in order
	 *
	 *@return ArrayList
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */

	ArrayList whoIsWinner();

	ArrayList<ArrayList<BoxLogic>> getListOfBoxLogicList();

	/**
	 *Returns the object of CurrentState
	 *
	 *@return CurrentState
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	CurrentState getCSObject();

}