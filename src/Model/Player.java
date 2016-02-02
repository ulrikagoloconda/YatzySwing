package Model;

/** 
 * This class represent the player and holds the players name
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */
public class Player {
    private String name;
    static private int numberOfPlayers;

    /** 
	 * The constructor takes one parameter and sets the name.
	 * 
	 * @param String name
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
   Player(String name ){
        this.name = name;
        addNumberOfPlayers();
    }

    private void addNumberOfPlayers(){
        numberOfPlayers++;
    }

    /** 
	 * Returns the name of the player
	 * 
	 * @return String name
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    public String getName(){
        return name;
    }
    
    /** 
	 * Returns an int representing the number of players in the game 
	 * 
	 * @return int numberofPlayers
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    protected static int getNumberOfPlayers(){
        return numberOfPlayers;
    }
}
