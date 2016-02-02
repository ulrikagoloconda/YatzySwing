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
		setText(name + ", nu �r det din tur att sl� t�rningarna!");
		Font font = new Font("Tahoma", Font.BOLD, 18);
		setFont(font);
	}

	public void setTimesLeftToRoll(String name, int i){
		if(i == 0){
			String string = name + ", du har slagit t�rningarna 3 g�nger, \n nu m�ste du g�ra ditt val!";
			setText(string);
		} else {
			setText("Bra gjort " + name + ", du f�r sl� t�rningarna "+ i +  " g�nger till! ");
		}
	}

	public void setNoOptionLeftText(String name) {
		setText(name + ", det finns inte n�gra m�jliga alternativ. \n Du m�ste d�rf�r v�lja ett alternativ och f� 0 po�ng");	
	}
}
