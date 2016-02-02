
package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import Controller.PanelEvent;
import Controller.PanelListener;
import Controller.Mediator;
import Model.CurrentScore;
import Model.GameInterface;
import Model.ReturnValue;
/** 
 * StartFrame extends JFrame and is the frame that views all the different states of the game.
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 6, 2015
 */

public class StartFrame extends JFrame {
	private GameInterface game_;
	private Mediator mediator; 
	private StartPanel startPanel;
	private GamePanel gamePanel; 
	private ArrayList<String> playersName;
	private int count = 1; 
	/** 
	 * The constructor of the class, takes two parameters. 
	 * 
	 * @param YGame game, Mediator mediator
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public StartFrame(GameInterface game, Mediator mediator) {
		super("Game");
		game_ = game;
		this.mediator = mediator; 
		initFrame();
		addStartPanelToFrame();
	}

	/** 
	 * Initializes the frame
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	private void initFrame() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 800));
		pack();
	}

	/** 
	 * Returns the variable gamePanel 
	 * 
	 * @returns GamePanel gamePanel
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public GamePanel getGamePanel(){
		return gamePanel; 
	}
	
	/** 
	 * Adds the GamePanel to the frame. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void addGamePanelToFrame(){
		startPanel.setVisible(false);
		int dim = 1100 + (playersName.size()*100);
		setPreferredSize(new Dimension(dim, 800));
		setResizable(false);
		gamePanel = new GamePanel(game_, mediator);
		Border border = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		gamePanel.setBorder(border);
		gamePanel.updateStartPosition();
		GamePanel.setGamePanelListener(new PanelListener(){

			@Override
			public void panelEventOccurred(PanelEvent e) {
				if(e.isSavedDiceLabel()){
					mediator.setChangesInPanelEvenOccurred(true, e.getDiceID());
				} else {
					mediator.setChangesInPanelEvenOccurred(e.getIcon(), e.getDiceID());
					
				}
			}
		});
		add(gamePanel);
		pack();
	}

	/** 
	 * Adds the StartPanel to the frame. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void addStartPanelToFrame() {
		startPanel = new StartPanel();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(startPanel);
		startPanel.setPanelListener(new PanelListener() {

			public void panelEventOccurred(PanelEvent e) { 
				playersName = e.getNames();
				if (e.isNamesExisting()){
					game_.setPlayersAndScoreList(playersName);
					game_.initGame();

				} else {
					String s = "Spelare " + count + ": ";
					startPanel.addTextToArea(s + e.getName()+"\n");
					count++;
				} 
			}
		});
		pack();
	}

	/** 
	 * Returns the list of names of the players 
	 * 
	 * @returns ArrayList <String> playersName
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public ArrayList getListOfNames(){
		return playersName;
	}

	/** 
	 * Returns the variable 'mediator'
	 * 
	 * @param Mediator mediator
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public Mediator getMediator(){
		return mediator; 
	}

	/** 
	 * Sets the gamePanel to view the startPosition
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void startPosition() {
		gamePanel.updateStartPosition();

	}

	/** 
	 * Sets the panel that view the sorted list of players and adds it to the frame. 
	 * 
	 * @param ArrayList<CurrentScore> sortedCurrentScoreList
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setWinFrame(ArrayList<CurrentScore> sortedCurrentScoreList) {
		gamePanel.setVisible(false);
		WinPanel.getWinPanel().setScoreAndWinnerToPanel(sortedCurrentScoreList);
		add(WinPanel.getWinPanel());
	}
}
