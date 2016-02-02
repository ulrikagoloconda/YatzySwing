

package Controller;

import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.ImageIcon;
/**
 * PanelEvent is a class that extends EventObject and stores data that is going to be used when actions are executed. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */
public class PanelEvent extends EventObject {
	private String name; 
	private ArrayList<String> names; 
	private  boolean isNamesExisting;
	private ImageIcon icon; 
	private String diceID; 
	private ArrayList<Integer> position; 
	private boolean isSavedDiceLabel; 

	/** 
	 * A constructor that endow the superclass whit the parameter needed.  
	 * 
	 * @param Object source
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public PanelEvent(Object source) {
		super(source);
	}
	
	/** 
	 * An overloaded constructor that endow the superclass whit the parameter needed and sets the name to 'name'.  
	 * 
	 * @param Object source, String name
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public PanelEvent(Object source, String name) {
		super(source);
		this.name = name;
	}

	/** 
	 * An overloaded constructor that endow the superclass whit the parameter needed and sets the ArrayList names to 'names'.  
	 * 
	 * @param Object source, ArrayList<String> names
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public PanelEvent(Object source, ArrayList<String> names) {
		super(source);
		this.names = names;
		isNamesExisting = true; 
	}

	/** 
	 * An overloaded constructor that endow the superclass whit the parameter needed, sets diceID and the boolean isSavedDiceLabel.  
	 * 
	 * @param Object source, String diceID, boolean isSavedDiceLabel
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public PanelEvent(Object source, String diceID, boolean isSavedDiceLabel){
		super(source);
		this.diceID= diceID; 
		this.isSavedDiceLabel = isSavedDiceLabel; 
	}

	/** 
	 * An overloaded constructor that endow the superclass whit the parameter needed, sets the icon, sets diceID and an ArrayList with position.  
	 * 
	 * @param Object source, String diceID, boolean isSavedDiceLabel
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public PanelEvent(Object source, ImageIcon icon, String diceID, ArrayList<Integer> position){
		super(source);
		this.icon = icon; 
		this.diceID = diceID; 
		this.position = position; 
	}

	/** 
	 * Returns the ImageIcon icon 
	 * 
	 * @return ImageIcon icon
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public ImageIcon getIcon(){
		return icon; 
	}

	/** 
	 * Returns the ArrayList with position
	 * 
	 * @return ArrayList<Integer>
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public ArrayList<Integer> getPosition(){
		return position; 
	}

	/** 
	 * Returns the string diceID
	 * 
	 * @return String diceID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public String getDiceID(){
		return diceID; 
	}


	/** 
	 * Returns the boolean that tells if the object i a savedDiceLabel or not. 
	 * 
	 * @return boolean isSavedDiceLabel
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public boolean isSavedDiceLabel(){
		return isSavedDiceLabel; 
	}
	

	/** 
	 * Sets the ArrayList of String to 'names'.
	 * 
	 * @param ArrayList<String> names
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public void setName(ArrayList<String> names) {
		this.names = names;
	}

	/** 
	 * Returns the ArrayList of String whith names
	 * 
	 * @return ArrayList<String> names
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public ArrayList<String> getNames() {
		return names;

	}

	/** 
	 * Returns the String 'name'
	 * 
	 * @return String name
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public String getName(){
		return name;
	}

	/** 
	 * Returns the boolean that tells that the list of names are initialized
	 * 
	 * @return ArrayList<String> names
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public boolean isNamesExisting(){
		return isNamesExisting; 
	}
}
