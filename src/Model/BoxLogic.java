package Model;


/** 
 * The class BoxLogic is the class that stores points, a boolean which tells if the box is already 
 * occupied or not and other information necessary to the Model
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */
public class BoxLogic {
	private Model.Box box;
	private int points;
	private boolean isFirstSix; 
	private boolean isAlreadyTaken;
	private Player player; 
	private static GameInterface game; 
	
	/** 
	 * One constructor with two parameters
	 * 
	 * @param Box box, Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	BoxLogic(Model.Box box, Player player) {
		this.box = box;
		this.player = player; 
		setFirstSix();
	}
	
	/** 
	 * Override constructor with one parameter
	 * 
	 * @param Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	BoxLogic(Player player){
		this.player = player; 
	}

	/** 
	 * Sets the static field game. 
	 * 
	 * @param Ygame game
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public static void setGame(GameInterface game){
		BoxLogic.game = game;
	}
	
	/** 
	 * Sets points and mark this box as occupied 
	 * 
	 * @param ReturnValue rv
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void setPointsAndTaken(ReturnValue rv){
		this.points= rv.getPoints();
		this.isAlreadyTaken = rv.getIsPossible();
		for(CurrentScore cs : game.getListOfCurrentScore()){
			if(cs.getPlayer().equals(this.player)){
				if (this.isFirstSix){
					cs.addToSum(rv.getPoints());
				} 
				cs.addToScoreTotal(rv.getPoints());
			}
		}
	}
	
	/** 
	 * Returns the pints of the box.
	 * 
	 * @return int points
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public int getPoints(){
		return points;
	}
	
	/** 
	 * Returns a boolean that tells if this box is occupied or not.
	 * 
	 * @return boolean isAlreadyTaken
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public boolean isAlradyTaken(){
		return isAlreadyTaken;
	}

	/** 
	 * Returns the box
	 * 
	 * @return Box box
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public Box getBox(){
		return box;
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
	 * Returns the boolean isFirstSix which tells if the box is part of the upper chart or not
	 * 
	 * @return boolean isFirstSix
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public boolean isFirstSix(){
		return isFirstSix; 
	}
	
	/** 
	 * Sets the boolean isFirstSix to false or true
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void setFirstSix(){
		boolean local = false; 
		switch (box){
		case ONES : local = true; 
		break; 
		case TWOS : local = true; 
		break; 
		case THREES : local = true; 
		break; 
		case FOURS : local = true; 
		break; 
		case FIVES : local = true; 
		break; 
		case SIXES : local = true; 
		break; 
		default : local = false; 
		break; 
		}
		isFirstSix = local; 
		
	}
}
