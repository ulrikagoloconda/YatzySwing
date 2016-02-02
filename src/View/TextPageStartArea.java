package View;

import java.awt.Font;

import javax.swing.JTextArea;

/** 
 * TextPageStartArea extends JTextArea and views the information needed in the game. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */
public class TextPageStartArea extends JTextArea{

	/** 
	 * The constructor of the class.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	TextPageStartArea(){
		setEditable(false);
	}

	/** 
	 * Sets the m
	 * 
	 * @param String name
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setTimeToRollInfo(String name){
		setText(name + ", nu är det din tur att slå tärningarna!");
		Font font = new Font("Tahoma", Font.BOLD, 18);
		setFont(font);
	}

	public void setTimesLeftToRoll(String name, int i){
		if(i == 0){
			String string = name + ", du har slagit tärningarna 3 gånger, \n nu måste du göra ditt val!";
			setText(string);
		} else {
			setText("Bra gjort " + name + ", du får slå tärningarna "+ i +  " gånger till! ");
		}
	}

	public void setNoOptionLeftText(String name) {
		setText(name + ", det finns inte några möjliga alternativ. \n Du måste därför välja ett alternativ och få 0 poäng");	
	}
}
