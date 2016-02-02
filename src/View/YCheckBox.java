package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;

import Model.BoxLogic;
import Model.CurrentState;
import Model.Player;
import Model.ReturnValue;
/** 
 * YCheckBox extends JCheckBox and is viewed in playerPanel when their is a valid option.
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */
public class YCheckBox extends JCheckBox implements Observer{
	private Player player;
	private BoxLogic boxLogic; 

	/** 
	 * The constructor of the class, takes one parameter. 
	 * 
	 * @param Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	YCheckBox(Player player){
		this.player = player; 
	}

	/** 
	 * Override Updates the YCheckBox. 
	 * 
	 * @param Observable o, Object arg1
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	@Override
	public void update(Observable o, Object arg1) {
		if(CurrentState.getCurrentPlayer() == player){
			setEnabled(((ReturnValue)o).getIsPossible());
		} else {
			setEnabled(false);
		}
	}
	
	/** 
	 * Sets the BoxLogic.
	 * 
	 * @param BoxLogic boxLogic
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setBoxLocig(BoxLogic boxLogic){
		this.boxLogic = boxLogic; 
	}
	
	/** 
	 * Returns the instance of BoxLogic
	 * 
	 * @return BoxLogic boxLogic
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public BoxLogic getBoxLogic(){
		return boxLogic; 
	}
	
	/** 
	 * Returns the instance of the Player
	 * 
	 * @return Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public Player getPlayer(){
		return player; 
	}
}
