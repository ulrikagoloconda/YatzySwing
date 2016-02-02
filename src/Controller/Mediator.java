package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Model.Box;
import Model.BoxLogic;
import Model.CurrentScore;
import Model.CurrentState;
import Model.GameInterface;
import Model.Player;
import Model.ReturnValue;
import Model.YDice;
import View.StartFrame;
import View.SumLabel;
import View.YCheckBox;
import View.YLabel;
/**Mediator is the class that functions as a link between View and Model. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 Dec 2, 2015
 */
public class Mediator {
	private GameInterface game;
	private ArrayList<Player> players;
	private ArrayList<ArrayList<YLabel>> arraysOfLabels;
	private ArrayList<ArrayList<YCheckBox>> arraysOfCheckBoxes;
	private ArrayList<ArrayList<SumLabel>> arraysOfSumLabels; 
	private ArrayList<ReturnValue> returnValues; 
	private StartFrame frame;

	/** 
	 *The constructor of Mediator 
	 *
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public Mediator(GameInterface game) {
		this.game = game;
		arraysOfLabels = new ArrayList<>();
		arraysOfCheckBoxes = new ArrayList<>();
		arraysOfSumLabels = new ArrayList<>();
	}

	/** 
	 *Initialize a new object of StartFrame which adds a StartPanel to the frame
	 *
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void addStartUpPanel(){
		frame = new StartFrame(game, this);
	}

	/** 
	 *Initialize the GUI by adding the GamePanel to the frame
	 *
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void initGameGUI(){
		frame.addGamePanelToFrame();
	}
	
	/** 
	 * A list of SumLabels are passed from View to Controller by this method. 
	 * 
	 *@param ArrayList<SumLabel> sumList
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void passSumList(ArrayList<SumLabel> sumList) {
		arraysOfSumLabels.add(sumList);
	}

	/** 
	 * A list of YLabels are passed from View to Controller by this method. 
	 * 
	 *@param ArrayList<YLabel> labelList
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void passPointLabels(ArrayList<YLabel> labelList){
		arraysOfLabels.add(labelList);
	}

	/** 
	 * A list of YCheckBox are passed from View to Controller by this method. 
	 * 
	 *@param ArrayList<YCheckBox> checkBoxList
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void passCheckBox(ArrayList<YCheckBox> checkBoxList){
		arraysOfCheckBoxes.add(checkBoxList);
	}

	/** 
	 * Enable all the players YCheckBoxes 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void enabelAllCheckBoxes(){
		for (int i = 0; i < arraysOfCheckBoxes.size(); i++){
			ArrayList<YCheckBox> local = arraysOfCheckBoxes.get(i);
			for(YCheckBox cb : local){
				cb.setEnabled(false);
			}
		}
	}

	/** 
	 * Set all checkBoxes of the current player to be able.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void abelAllCurrentPlayersBoxes(){
		for (int i = 0; i < arraysOfCheckBoxes.size(); i++){
			ArrayList<YCheckBox> local = arraysOfCheckBoxes.get(i);
			for(YCheckBox cb : local){
				if(cb.getPlayer().equals(CurrentState.getCurrentPlayer())){
					cb.setEnabled(true);
				}
			}
		}
	}

	/** 
	 * Sets all ReturnValues to store that all options in the current chart are available. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void setAllReturnValuesToPossible(){
		for(ReturnValue rv : CurrentState.getCurrentListOfReturnValuse()){
			rv.setIsPossible(true);
		}
	}

	/** 
	 * Connects the BoxLogic objects with YCheckBoxes and YLabels.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void addBoxLogicToCheckBoxAndLabels(){
		for(int i = 0; i < game.getListOfBoxLogicList().size(); i++){
			for (int j = 0; j < 15; j++){
				ArrayList<BoxLogic> localBlList =  (ArrayList<BoxLogic>) game.getListOfBoxLogicList().get(i);
				ArrayList<YLabel> localLabelList = arraysOfLabels.get(i);
				ArrayList<YCheckBox> localCheckBoxList = arraysOfCheckBoxes.get(i);
				localLabelList.get(j).setBoxLogic(localBlList.get(j));
				localCheckBoxList.get(j).setBoxLocig(localBlList.get(j));

			}
		}
	}

	/** 
	 * Adds ActionsListener the YCheckBoxes by using an inner class that overrides the method actionPerformed. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void addAciontListenerToCheckBox(){
		for(int i = 0; i < arraysOfCheckBoxes.size(); i++){
			ArrayList<YCheckBox> localCB = arraysOfCheckBoxes.get(i);
			for (YCheckBox checkBox : localCB){
				checkBox.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {

						YCheckBox cb = (YCheckBox)e.getSource();
						for (ReturnValue rv : CurrentState.getCurrentListOfReturnValuse()){
							if(rv.getBox()== Box.FULL_HOUSE){
							}
							if(rv.getBox()==cb.getBoxLogic().getBox()){
								cb.getBoxLogic().setPointsAndTaken(rv);
								cb.setVisible(false);
								if(game.winCheck()){
									ArrayList<CurrentScore> sortedCurrentScoreList = game.whoIsWinner();
									frame.setWinFrame(sortedCurrentScoreList);
								}
								game.clearListOfReturnValues();
								game.newCurrentPlayer();
								game.setCurretnChart();
								frame.startPosition();
								CurrentState.resetCount();
								frame.getGamePanel().getRollButton().setEnabled(true);


							}
						}
					}
				});
			}
		}
	}

	/** 
	 * Adds observer to all the ReturnValue objects and adds observers to all the SumLabels. 
	 * 
	 * @param ArrayList<ReturnValue> values
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void addObserverToReturnValues(ArrayList<ReturnValue> values) {
		returnValues = values; 
		for (int i = 0; i < arraysOfLabels.size(); i++){
			ArrayList<YLabel> localLabelList = arraysOfLabels.get(i);
			ArrayList<YCheckBox> localCheckBoxList = arraysOfCheckBoxes.get(i);
			for (int j = 0; j < localLabelList.size(); j++){
				returnValues.get(j).addObserver(localLabelList.get(j));
				returnValues.get(j).addObserver(localCheckBoxList.get(j));
			}
		}

		for (int i = 0; i < arraysOfSumLabels.size(); i++){
			ArrayList<SumLabel> localSumLabelList = arraysOfSumLabels.get(i);
			for(int j = 0; j <localSumLabelList.size(); j++){
				game.getListOfCurrentScore().get(i).addObserver(localSumLabelList.get(j));
			}
		}
	}

	/** 
	 * Returns the list of Players
	 * 
	 * @return ArrayList<Player> players
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	protected ArrayList<Player>getListOfPlayers(){
		return players;
	}

	/** 
	 * ReSets the int that counts how many times the player rolls the dice
	 *
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void reSetCount() {
		CurrentState.resetCount();
	}

	/** 
	 * This method correspond with an event that is triggered in the game when the mouse is being clicked. The method changes the state of the game
	 * by using the parameters.
	 * 
	 *@param boolean isSavedDiceLabel, String diceID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void setChangesInPanelEvenOccurred(boolean isSavedDiceLabel, String diceID ){
		if(isSavedDiceLabel){
			for(YDice dice :CurrentState.getCurrentListOfDice()){
				if(dice.getDiceID().equals(diceID)){
					frame.getGamePanel().setDiceVisibleOnDicePanel(dice);
					CurrentState.delFromSavedDiceList(dice);
					if(CurrentState.getSavedList().size()<5 && CurrentState.getCount()>0){
						frame.getGamePanel().getRollButton().setEnabled(true);
					}
					frame.getGamePanel().delDiceFromSavedList(dice);

				}
			}
		}
	}

	/** 
	 * This overloaded method correspond with an event that is triggered in the game when the mouse is being clicked. The method changes the state of the game
	 * by using the parameters.
	 * 
	 * 
	 *@param ImageIcon icon, String diceID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void setChangesInPanelEvenOccurred(ImageIcon icon, String diceID ){			
		CurrentState.addToSavedDiceList(diceID);
		if(CurrentState.getSavedList().size() == 5){
			frame.getGamePanel().getRollButton().setEnabled(false);
		}
		frame.getGamePanel().setUpdatedIcon(icon, diceID);
	}
}


