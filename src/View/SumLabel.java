package View;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import Model.CurrentScore;
import Model.CurrentState;
import Model.Player;
import Model.Sum;

/** 
 * SumLabel extends JLabel and view the sum, bonus and total score in the playerPanel. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */
public class SumLabel extends JLabel implements Observer{
	private Sum sum; 
	private Player player; 
	private Font font;
	
	/** 
	 * The constructor of the class, takes two parameters
	 * 
	 * @param Sum sum, Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	SumLabel(Sum sum, Player player){
		this.sum = sum; 
		this.player = player; 
		this.setText(sum.toString());
		font = new Font("Tahoma", Font.ITALIC, 14);
		setFont(font);
	}

	/** 
	 * Returns the variable 'sum'.
	 * 
	 * @return Sum sum
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public Sum getSum(){
		return sum; 
	}
	
	/** 
	 * Returns the variable 'player'.
	 * 
	 * @return Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public Player getPlayer(){
		return player; 
	}

	/** 
	 * Override method that updates the SumLabel
	 * 
	 * @param Observable o, Object arg1
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	@Override
	public void update(Observable o, Object arg1) {
		CurrentScore cs = (CurrentScore)o;

		if( cs.getPlayer().equals(CurrentState.getCurrentPlayer())){
			if(this.sum == Sum.BONUS){
				setText(Sum.BONUS.toString() + " " + cs.getBonus());
				setFont(font);

			} else if(this.sum == Sum.SUM){
				setText(Sum.SUM.toString() +" " + cs.getFirstSixSum());
				setFont(font);

			} else if (this.sum == Sum.TOTAL){
				setText(Sum.TOTAL.toString() + " " + cs.getScoreTotal());
				setFont(font);
			}
		}
	}
}



