package View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Model.YDice;
/** 
 * DiceView is the class that holds the View aspects of the dice in the game.
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 4, 2015
 */
public class DiceView extends JLabel implements Observer{


	    private ArrayList<JLabel> diceLabelIconList;
	    private ArrayList<ImageIcon> iconList;
	    private String diceID; 
	    private int count = 0; 
	    private ArrayList<Integer> position; 
	    private boolean isSavedDice; 
	    
	    /** 
		 *The constructor of the class, takes no argument. 
		 * 
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    DiceView() {
	        super();
	        if(count == 0){
	        initDiceIconList();
	        count++;
	        }
	    }
	    /** 
		 *Overloaded constructor that takes three arguments.
		 * 
		 * @param ImageIcon icon, String diceID, boolean isSavedDice
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */ 
	    DiceView(ImageIcon icon, String diceID, boolean isSavedDice){
	    	setIcon(icon);
	    	this.diceID = diceID; 
	    	this.isSavedDice = isSavedDice; 
	    }

	    /** 
		 * Initializes the list of dice icons
		 * 
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    private void initDiceIconList() {
	        diceLabelIconList = new ArrayList<>();
	        iconList = new ArrayList<>();
	        for (int i = 1; i <= 6; i++) {
	            
	            ImageIcon icon = new ImageIcon(this.getClass().getResource("icon/diceIcons/dice"+i+".png"));
	            iconList.add(icon);
	            diceLabelIconList.add(new JLabel(icon));
	        }
	    }
	   
	    /** 
		 * Returns a boolean which tells if the object is of sort SavedDice or not. 
		 * 
		 * @return boolean isSavedDice
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    public boolean isSavedDice(){
	    	return isSavedDice; 
	    }
	    
	    /** 
		 * Sets the diceID of the diceView
		 * 
		 * @param String diceID
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    public void setDiceID(String diceID){
	    	this.diceID = diceID;
	    	
	    }
	    
	    /** 
		 * Returns the diceID
		 * 
		 * @return String diceID
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    public String getDiceID(){
	    	return diceID;
	    }
	    
	    /** 
		 * Sets the ArrayList whit x and y coordinates in which the was placed in the dicePanel
		 * 
		 * @param ArrayList position 
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    public void setPossition(ArrayList<Integer> position){
	    	this.position = position; 
	    }
	    
	    /** 
		 * Returns the arrayList with x and y coordinates in whick the dice vas placed in the dicePanel 
		 * 
		 * @return ArrayList Position 
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    public ArrayList getPosition(){
	    	return position; 
	    }

	    /** 
		 * Returns the list of JLabels with dice icons. 
		 * 
		 * @return ArrayLIst diceLabelIconList
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    protected ArrayList<JLabel> getDiceLabelIconList(){
	        return diceLabelIconList;
	    }

	    /** 
		 * Returns the list of icons of type ImageIcon
		 * 
		 * @return ArrayList iconList
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    protected ArrayList getIconLIst(){
	    	return iconList;
	    }
	    
	    /** 
		 * Sets the icon to the dice based on the value in the parameter
		 * 
		 * @param int value
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	    public void setDiceIcon(int value){
	    	
	    	this.setIcon(getSingelIcon(value));
	    	
	    }
	
	    /** 
		 * Override method that updates the view to match the current value of the dice
		 * 
		 * @return boolean returnBool
		 * @author UlrikaGoloconda
		 * @version 1.0 December 4, 2015
		 */
	@Override
	public void update(Observable o, Object arg) {
		YDice diceLocal = (YDice)o; 
		int diceValue = diceLocal.getDiceInt();
		 setIcon(iconList.get(diceValue-1));
	}

	/** 
	 * Returns a ImageIcon from the list of dice icons based on the parameter 
	 * 
	 * @return ImageIcon 
	 * @param int diceValue
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public ImageIcon getSingelIcon(int diceValue) {
	
		return iconList.get(diceValue-1);
	}
}
