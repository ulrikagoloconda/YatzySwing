package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Mediator;
import Model.CurrentState;
import Model.Player;
import Model.Sum;
/** 
 * PlayerPanel extends JPanel and is the view aspect of the players score during the game.
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */
public class PlayerPanel extends JPanel implements Observer {
	private GridBagConstraints gb; 
	private Player player; 
	private Mediator mediator; 
	private ArrayList<YLabel> labelList; 
	private ArrayList<YCheckBox> checkBoxList; 
	private ArrayList<SumLabel> sumList; 
	private Color color; 

	/** 
	 * The constructor of the class, takes two parameters
	 * 
	 * @param Player player, Mediator mediator
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	PlayerPanel(Player player, Mediator mediator){
		this.player = player;
		this.mediator = mediator; 

		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		gb = new GridBagConstraints();
		labelList = new ArrayList<>();
		checkBoxList = new ArrayList<>();
		sumList = new ArrayList<>();
		color = new Color(185,245,209);
		setPlayerPanels();
	}

	/** 
	 * Adds functions to the components of the in the panel 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void addFunktonsToPanelComponents(){
		mediator.addBoxLogicToCheckBoxAndLabels();
		mediator.addAciontListenerToCheckBox();
	}

	/** 
	 * Sets the player panel by adding labels and YChekcBoxes. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setPlayerPanels(){
		for (int j = 0; j < 18; j++){
			for (int k = 0; k < 2; k++){
				gb.gridy = j; 
				gb.gridx = k; 
				gb.fill = GridBagConstraints.BOTH;
				gb.weightx = 1; 
				gb.weighty = 1; 
				gb.anchor = GridBagConstraints.LINE_END;

				if (j == 6 ){
					if (k == 0){
						SumLabel bonus = new SumLabel(Sum.BONUS, player);
						add(bonus, gb);
						sumList.add(bonus);
					} else if (k == 1){
						JLabel test = new JLabel("");
						add(test, gb);
					}
				} else if (j == 7){
					if (k == 0){
						SumLabel sum = new SumLabel (Sum.SUM, player);
						add(sum, gb);
						sumList.add(sum);
					} else if( k== 1){
						JLabel test = new JLabel("");
						add(test, gb);
					}
				} else if (j == 17){
					if (k == 0){
						SumLabel total = new SumLabel(Sum.TOTAL, player);
						add(total,gb);
						sumList.add(total);
					} else if ( k == 1){
						JLabel test = new JLabel("");
						add(test, gb);
					}
				} else {
					if(k==0){
						YLabel points = new YLabel("" , player );
						labelList.add(points);
						add(points,gb);
					} else {
						YCheckBox checkBox = new YCheckBox(player);
						checkBoxList.add(checkBox);
						add(checkBox,gb);
					}
				}
			}
		}
		mediator.passPointLabels(labelList);
		mediator.passCheckBox(checkBoxList);
		mediator.passSumList(sumList);
	}

	/** 
	 * Override method that updates the playerPanel
	 * 
	 * @param Observabele o, Obect arg1
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	@Override
	public void update(Observable o, Object arg1) {
		CurrentState cs = (CurrentState)o;
		if(this.player.equals(cs.getCurrentPlayer())){

			setBackground(color);
			for(YCheckBox box : checkBoxList){
				box.setBackground(color);
			}
		} else {
			setBackground(CurrentState.getCurrentColor());
			for(YCheckBox box : checkBoxList){
				box.setBackground(CurrentState.getCurrentColor());
			}
		}
	}
}
