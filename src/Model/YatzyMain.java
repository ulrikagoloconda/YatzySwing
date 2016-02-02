package Model;
/**
 * This class contains the main-method. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 *
 */
public class YatzyMain {
	/**
	 * The main method starts the game by creating a new instance of type YGame.. 
	 * @param args
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void main (String[] args){

		GameInterface game =  new YGame();
	}
	/**
	 * Starts a new game by creating a new instance of type YGame 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void newGame(){
		GameInterface game = new YGame();
	}

}
