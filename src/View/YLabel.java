package View;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import Model.BoxLogic;
import Model.CurrentState;
import Model.Player;
import Model.ReturnValue;

/** 
 * YLabel extends JLabel and view points in the playerPanel
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */
public class YLabel extends JLabel implements Observer{

	private BoxLogic boxLogic;
	private Player player;
	private String diceID; 

	/** 
	 * The constructor of the class, takes two parameters
	 * 
	 * @param String text, Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	YLabel(String text, Player player){
		setText(text);
		this.player = player;  
	}
	
	/** 
	 * The second constructor of the class, takes two parameters. 
	 * 
	 * @param ImageIcon icon, String diceID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	YLabel(ImageIcon icon, String diceID){
		setIcon(icon);
		this.diceID = diceID; 
	}
	
	/** 
	 * Uppdates the YLabel
	 * 
	 * @param Observable o, Object arg1
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	@Override
	public void update(Observable o, Object arg1) {
		ReturnValue rv = (ReturnValue)o;
		int points = rv.getPoints();
		boolean isPossible = rv.getIsPossible();

		if (boxLogic.isAlradyTaken()){
			setText(""+boxLogic.getPoints());
			Font font = new Font("Tahoma", Font.BOLD, 14);
			setBackground(Color.getHSBColor(20, 10, 50));
			setFont(font);
			setDefaultLocale(null);
			
		} else {
			
			if (CurrentState.getCurrentPlayer().equals(player)){
				
				if (rv.getBox() == null){ //TODO vad är detta
					setText("");
				} else if(isPossible){
					setText("" +  points);
					Font font = new Font("Tahoma", Font.ITALIC, 14);
					setFont(font);
			
				} else {
					setText("");
				
				}
			} else {
				setText("");
			
			}
		} 
	} 
	
	/** 
	 * Returns the instance 'player'
	 * 
	 * @return Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public Player getPlayer(){
		return player; 
	}
	
	/** 
	 * Returns the instance 'boxLogic'
	 * 
	 * @return BoxLogic boxLogic
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public BoxLogic getBoxLogic(){
		return boxLogic; 
	}
	
	/** 
	 * Sets the boxLogic.
	 * 
	 * @param BoxLogic boxLogic
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setBoxLogic(BoxLogic boxLogic){
		this.boxLogic = boxLogic; 
	}
	
	/** 
	 * Returns the diceID. 
	 * 
	 * @return String diceID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public String getDiceID(){
		return diceID; 
	}
}
