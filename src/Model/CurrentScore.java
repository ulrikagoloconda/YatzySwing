package Model;

import java.util.Observable;
/** 
 * This class holds the scores of the players
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */
public class CurrentScore extends Observable {
	private  Player player; 
	private  int scoreTotal; 
	private  int bonus; 
	private  int firstSixSum;

	/** 
	 * The constructor of CurrentScore
	 * 
	 * @param Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	CurrentScore(Player player){
		this.player = player; 
	}
	/** 
	 * Adds score to total score and notify the observers
	 * 
	 * @param int scoreTotal
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public  void addToScoreTotal(int scoreTotal) {
		this.scoreTotal += scoreTotal;
		this.scoreTotal += bonus; 
		setChanged();
		notifyObservers();
	}

	/** 
	 * Adds score to the sum of the first six boxes, sets bonus and notify the observers
	 * 
	 * @param int sum
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void addToSum(int sum) {
		this.firstSixSum += sum;
		if(this.firstSixSum >= 63){
			this.bonus = 50;
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
	public  Player getPlayer() {
		return player;
	}

	/** 
	 * Returns the total score
	 * 
	 * @return int scoreTotal
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public  int getScoreTotal() {
		return scoreTotal;
	}

	/** 
	 * Returns the bonus
	 * 
	 * @return int bouns
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public  int getBonus() {
		return bonus;
	}

	/** 
	 * Returns the sum of the first six boxes
	 * 
	 * @return int firstSixSum
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public int getFirstSixSum() {
		return firstSixSum;
	}

	/** 
	 * Returns the total score
	 * 
	 * @return int scoreTotal
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public int getScore(){
		return scoreTotal; 
	}

}

