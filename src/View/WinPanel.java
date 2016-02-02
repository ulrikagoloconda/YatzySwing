package View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.CurrentScore;
import Model.CurrentState;
import Model.YatzyMain;


/** 
 * WinPanel extends JPanel and views the sorted scores and players after the game is over. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */
public class WinPanel extends JPanel{
	private static WinPanel winPanel; 
	private ArrayList<CurrentScore> sortedCurrentScoreList; 
	private JButton exitButton; 
	private JButton newGame; 

	/** 
	 * The private constructor of the class. 
	 *
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	private WinPanel(){
		setBackground(CurrentState.getCurrentColor());
	}

	/** 
	 * Returns the single instance of this class.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public static WinPanel getWinPanel(){
		if (winPanel == null){
			winPanel = new WinPanel();
			return winPanel; 
		} else {
			return winPanel;
		}
	}

	/** 
	 * Sets the sortedCurrentScore list an initialize the panel.
	 * 
	 * @param ArrayList<CurrentScore> sortedCurrentScoreList
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setScoreAndWinnerToPanel(ArrayList <CurrentScore> sortedCurretnScoreList){
		 this.sortedCurrentScoreList = sortedCurretnScoreList; 		
		 setLayout(new GridLayout(sortedCurrentScoreList.size(), 1));
		addWinnerToPanel();
		

	}
	
	/** 
	 * Adds the sortedCurrentScore and players to the WinPanel. Adds exitButton and newGameButton to the WinPanel
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	private void addWinnerToPanel(){
		
		for(int i = 0; i < sortedCurrentScoreList.size(); i++){
			String local = sortedCurrentScoreList.get(i).getPlayer().getName() + " " + "poäng " + sortedCurrentScoreList.get(i).getScoreTotal();
			if (i == 0){		
				JLabel label = new JLabel("Grattis till vinnare! ");
				add(label);
				JLabel labelPlayer = new JLabel(local);
				labelPlayer.setFont(new Font("Tahoma", Font.BOLD, 20));
				ImageIcon icon = new ImageIcon(this.getClass().getResource("icon/diceIcons/winner.png"));
				JLabel localImage = new JLabel(icon);
				add(labelPlayer);
				add(localImage);
			} else {
				JLabel labelplayer = new JLabel(local);
				add(labelplayer);
				
			}
		}
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener(){ 

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
			
		});
		
		newGame = new JButton("Spela igen?");
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				YatzyMain.newGame();
			}
			
		});
		add(newGame);
		add(exitButton);
	}
}
