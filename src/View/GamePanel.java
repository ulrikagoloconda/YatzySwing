package View;

import Controller.PanelListener;
import Controller.Mediator;
import Controller.YMouseListener;
import Model.CurrentState;
import Model.GameInterface;
import Model.YDice;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
/** 
 * GamePanel extending JPanel and views the game while running
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 4, 2015
 */
public class GamePanel extends JPanel {

	private JPanel savedDicePanel;
	private JPanel bottomPanel;
	private JPanel dicePanel;
	private JPanel iconPanel;
	private JPanel chartPanel;
	private JPanel boxPanel; 
	private ArrayList<PlayerPanel> playerPanels; 
	private TextPageStartArea messageArea;

	private ImageIcon yatzyIcon;

	private JLabel iconLabel;
	private JButton exitButton;
	private JButton rollButton; 
	private JButton colorButton; 

	private DiceView diceView;
	private ArrayList<Integer> intList; 
	private ArrayList<DiceView> diceViewList; 
	private ArrayList <DiceView> localSavedDiceLabelList;
	private ArrayList<JPanel> panelList; 
	private ArrayList<Integer> position; 

	private GridBagConstraints gb;
	private static PanelListener panelListener; 
	private GameInterface game; 
	private Mediator mediator; 
	private Dimension dim; 
	private Color color; 
	private Random rand; 

	/** 
	 * The constructor of the class, takes two arguments
	 * 
	 * @param YGame game, Mediator mediator
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	GamePanel(GameInterface game, Mediator mediator) {
		super();
		initGamePanel();
		this.game = game; 
		this.mediator = mediator; 
		gb = new GridBagConstraints();
		localSavedDiceLabelList = new ArrayList<>();
		position = new ArrayList<>();
		diceViewList = new ArrayList<>();
		color = new Color(229, 255, 204);
		rand = new Random();
		intList = new ArrayList<>();
		addStuffToCenterGamePanel();
		add(dicePanel, BorderLayout.CENTER);
		addStuffToLineEndGamePanel();
		addStuffToLineStartGamePanel();
		addStuffToBottomGamePanel();
		updateStartPosition();
		initListOfPanels();
	}

	/** 
	 * Sets the dice and buttons in start position between the rolls.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public void updateStartPosition(){
		dicePanel.removeAll();
		dicePanel.revalidate();
		dicePanel.repaint();
		savedDicePanel.removeAll();
		savedDicePanel.revalidate();
		savedDicePanel.repaint();
		localSavedDiceLabelList.clear();
		game.setCurrentPlayerInstance(CurrentState.getCurrentPlayer());
		CurrentState.clearSavedList();
		CurrentState.resetCount();

		for (int i = 0; i < CurrentState.getCurrentListOfDice().size(); i++){
			int diceInt = CurrentState.getCurrentListOfDice().get(i).getDiceInt();
			diceView = new DiceView();
			diceView.setDiceIcon(diceInt);
			diceView.setDiceID(CurrentState.getCurrentListOfDice().get(i).getDiceID());
			gb.gridx = i; 
			dicePanel.add(diceView, gb);
		}
		mediator.enabelAllCheckBoxes();
		messageArea.setTimeToRollInfo(CurrentState.getCurrentPlayer().getName());

	}

	/** 
	 * Initializes the ArrayList of JPanels and add all the panels to it.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private void initListOfPanels(){
		panelList = new ArrayList<>();
		panelList.add(chartPanel);
		panelList.add(boxPanel);
		panelList.add(iconPanel);
		panelList.add(bottomPanel);
		panelList.add(dicePanel);
		panelList.add(savedDicePanel);
	}

	/** 
	 * The game panel is parted into five sections by using Border Layout. 
	 * This method adds the panel of saved dice to the Line Start of the Border Layout. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public void addStuffToLineStartGamePanel() {
		savedDicePanel = new JPanel();
		savedDicePanel.setLayout(new GridLayout(5,1));
		savedDicePanel.setPreferredSize(new Dimension(150, 200));
		savedDicePanel.setBorder(BorderFactory.createTitledBorder("Sparade tärningar"));
		savedDicePanel.setBackground(color);
		add(savedDicePanel, BorderLayout.LINE_START);
	}

	/** 
	 * Updates the saved dice panel with current saved dice
	 * 
	 * @param ImageIcon icon, String diceID
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public void setUpdatedIcon(ImageIcon icon, String diceID){
		boolean isSavedDiceView = true; 
		DiceView label = new DiceView(icon,diceID,isSavedDiceView);
		label.addMouseListener(new YMouseListener());
		label.setVisible(true);

		localSavedDiceLabelList.add(label);
		for(int i = 0; i < localSavedDiceLabelList.size(); i++){
				savedDicePanel.add(localSavedDiceLabelList.get(i));
			}
		}
	
	/** 
	 * Delete one dice from the list of saved dice and remove the same dice from the saved dice panel.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public void delDiceFromSavedList(YDice dice) {
		for(int i = 0; i < localSavedDiceLabelList.size(); i++){
			if (localSavedDiceLabelList.get(i).getDiceID().equals(dice.getDiceID())){
				localSavedDiceLabelList.remove(i);
			}
		}
		savedDicePanel.removeAll();
		savedDicePanel.revalidate();
		savedDicePanel.repaint();
		for(int i = 0; i < localSavedDiceLabelList.size(); i++){
			savedDicePanel.add(localSavedDiceLabelList.get(i));
		}
	}

	/** 
	 * The game panel is parted into five sections by using Border Layout. 
	 * This method adds the box panel to the Line end of the Border Layout. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private void addBoxToBoxPanel(GridBagConstraints gb){
		Border border = BorderFactory.createTitledBorder("YATZY!");
		Font font = new Font("Tahoma", Font.BOLD, 14);
		boxPanel.setBorder(border);
		boxPanel.setBackground(color);
		int j = 0; 
		for (int i = 0; i < 18; i++){
			gb.gridx = 0; 
			gb.gridy = i; 
			gb.anchor = GridBagConstraints.LINE_END;

			if (i == 6 || i == 7 || i == 17){
				JLabel emptyLabel = new JLabel(" ");
				emptyLabel.setPreferredSize(dim);
				boxPanel.add(emptyLabel,gb);
			} else {
				String box = game.getListOfBox().get(j).toString();
				j++;
				JLabel boxLabel = new JLabel(box + ": ");
				boxLabel.setFont(font);
				boxLabel.setPreferredSize(dim);
				boxPanel.add(boxLabel,gb);
			}
		}
	}

	/** 
	 * Initializes one panel for each player and adds them to the line end and adds functions to the components in the panels.
	 * 
	 * @return ArrayList<PlayerPanels> playerPanels
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private ArrayList<PlayerPanel>initPlayerPanels(){
		playerPanels = new ArrayList<>();
		int numberOfPlayers = game.getChartList().size();
		for (int i = 0; i < numberOfPlayers; i++){
			PlayerPanel playerPanel = new PlayerPanel(game.getChartList().get(i).getPlayer(), mediator);

			Border border = BorderFactory.createTitledBorder(game.getChartList().get(i).getPlayer().getName());
			playerPanel.setBorder(border);
			game.getCSObject().addObserver(playerPanel);
			playerPanels.add(playerPanel);
		}	

		playerPanels.get(0).addFunktonsToPanelComponents(); //Borde vara en statisk metod som anropades
		return playerPanels; 
	}

	/** 
	 * Adds the player Panels to the chart panel. 
	 * 
	 * @param GridBagConstraints gb
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private void addPlayerPanelsToChartPanel(GridBagConstraints gb){
		gb.gridy = 0; 
		gb.gridx = 0; 
		gb.fill = GridBagConstraints.BOTH;
		chartPanel.add(boxPanel,gb);
		for (int i = 0; i < playerPanels.size(); i++){
			gb.gridx = i + 1; 
			chartPanel.add(playerPanels.get(i), gb);
		}	
	}

	/** 
	 * The game panel is parted into five sections by using Border Layout. 
	 * This method adds the list of player panels to the Line End of the Border Layout. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private void addStuffToLineEndGamePanel() {
		chartPanel = new JPanel();
		boxPanel = new JPanel();
		boxPanel.setLayout(new GridBagLayout());
		chartPanel.setBackground(Color.ORANGE);
		chartPanel.setLayout(new GridBagLayout());
		dim = new Dimension(100,40);
		GridBagConstraints gb = new GridBagConstraints();
		gb.weighty = 1; 
		gb.weightx = 1; 
		addBoxToBoxPanel(gb);
		initPlayerPanels();
		addPlayerPanelsToChartPanel(gb);
		add(chartPanel, BorderLayout.LINE_END);
	}

	/** 
	 * Returns the roll button. 
	 * 
	 * Return JButton rollButton
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public JButton getRollButton(){
		return rollButton; 
	}

	/** 
	 * The game panel is parted into five sections by using Border Layout. 
	 * This method adds three buttons to the Page End of the Border Layout. The buttons is provided whit ActionListeners.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	private void addStuffToBottomGamePanel() {
		exitButton = new JButton("Exit");
		rollButton = new JButton("Slå tärningarna");
		colorButton = new JButton("Ändra färg");
		rollButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CurrentState.subFromCout();
				for (YDice dice : CurrentState.getCurrentListOfDice()){
					if (!CurrentState.getSavedList().contains(dice)){
						dice.rollTheDice();
						dice.setDiceChanges();
					}
				}
				game.checkChart();
				messageArea.setTimesLeftToRoll(CurrentState.getCurrentPlayer().getName(), CurrentState.getCount());
				if(!CurrentState.isStillCounting()){
					rollButton.setEnabled(false);
					if(!game.isValidOptionLeft()){
						messageArea.setNoOptionLeftText(CurrentState.getCurrentPlayer().getName());
						mediator.abelAllCurrentPlayersBoxes();
						mediator.setAllReturnValuesToPossible();
					}
				}
			addLabelsAndDIceViewToCenter();
		}
	});

		exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		colorButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				color = new Color(rand.nextInt(225),rand.nextInt(225),rand.nextInt(225));
				CurrentState.setCurrentColor(color);
				for(int i = 0; i < panelList.size();i++){
					if(i % 2 != 0|| i== 2){

						panelList.get(i).setBackground(color.brighter());
					} else {
						panelList.get(i).setBackground(color);
					}
				}

				setBackground(color);
				messageArea.setBackground(color.brighter());
			}

		});

		bottomPanel = new JPanel(new GridBagLayout());
		bottomPanel.setBackground(color); 
		bottomPanel.setPreferredSize(new Dimension(100, 70));
		gb.gridy = 0; 
		gb.gridx = 0; 
		gb.weightx = 1; 
		gb.weighty = 1;
		bottomPanel.add(colorButton, gb);
		gb.gridx = 1; 
		gb.anchor = GridBagConstraints.CENTER;
		bottomPanel.add(rollButton, gb);	
		gb.gridx = 2; 
		bottomPanel.add(exitButton, gb);

		add(bottomPanel, BorderLayout.PAGE_END);

	}

	/** 
	 * Initializes the Game panel.
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	protected void initGamePanel() {
		setLayout(new BorderLayout());
		setBackground(color);
		iconPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gb = new GridBagConstraints();
		addStuffToPageStart(gb);

		add(iconPanel, BorderLayout.PAGE_START);
	}

	/** 
	 * The game panel is parted into five sections by using Border Layout. 
	 * This method adds one icon and one text area to the Page Start of the Border Layout. 
	 * 
	 * @param GridBagConstraints gb
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	protected void addStuffToPageStart(GridBagConstraints gb){
		gb.gridx = 0;
		gb.gridy = 0;
		gb.anchor = GridBagConstraints.LINE_START;
		iconLabel = new JLabel("Yatzy");
		yatzyIcon = new ImageIcon(this.getClass().getResource("icon/yatzy.png"));
		iconLabel.setPreferredSize(new Dimension(300, 100));
		iconLabel.setIcon(yatzyIcon);
		messageArea = new TextPageStartArea();
		iconPanel.add(iconLabel, gb);
		messageArea.setBackground(color);
		messageArea.setText("Text som informerar om spelet");

		gb.gridy++;
		gb.anchor = GridBagConstraints.CENTER;
		iconPanel.add(messageArea,gb);
	}

	/** 
	 * The game panel is parted into five sections by using Border Layout. 
	 * This method adds labels to the Center of the Border Layout. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	protected void addStuffToCenterGamePanel() {

		dicePanel = new JPanel();
		dicePanel.setBackground(color);
		Border border = BorderFactory.createTitledBorder("Tärningar att slå");
		dicePanel.setBorder(border);
		dicePanel.setLayout(new GridBagLayout());
		initRandomLabelsToDicePanel();
	}

	/** 
	 * Initializes and prepare the Center part of the Game panel  
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	protected void initRandomLabelsToDicePanel() {
		for (int i = 0; i < CurrentState.getCurrentListOfDice().size(); i++){
			int diceInt = CurrentState.getCurrentListOfDice().get(i).getDiceInt();
			diceView = new DiceView();	
			diceView.setDiceIcon(diceInt);
			diceView.setDiceID(CurrentState.getCurrentListOfDice().get(i).getDiceID());
			CurrentState.getCurrentListOfDice().get(i).addObserver(diceView);
			diceView.addMouseListener(new YMouseListener());
			diceViewList.add(diceView);
		}
	}

	/** 
	 * Resets the list of random numbers 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void reSetRandomIntList(){
		intList.clear();

		Random rand = new Random(); //Gör om så att random inte är en random

		int x;
		int count = 5 - CurrentState.getSavedList().size();
		for (int i = 0; i < count; i++) {

			while (true) {
				x = rand.nextInt(63) + 1;
				if (!intList.contains(x)) {
					break;
				}
			}
			intList.add(x);
		}
	}
	
	/** 
	 * The game panel is parted into five sections by using Border Layout. 
	 * This method adds labels and diceView to the Center of the Border Layout. 
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void addLabelsAndDIceViewToCenter(){
		dicePanel.removeAll();
		dicePanel.revalidate();
		dicePanel.repaint();
		reSetRandomIntList();
		int k = 0;
		int m = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				gb.gridy = i;
				gb.gridx = j;
				if (intList.contains(k)) {

					while(m < CurrentState.getCurrentListOfDice().size()){
						if(!CurrentState.getSavedList().contains(CurrentState.getCurrentListOfDice().get(m))){
							int diceInt = CurrentState.getCurrentListOfDice().get(m).getDiceInt();
							diceViewList.get(m).setDiceIcon(diceInt);
							position.add(i);
							position.add(j);
							diceViewList.get(m).setPossition(position);
							diceViewList.get(m).setVisible(true);
							dicePanel.add(diceViewList.get(m),gb);
							m++;
							break; 
						}
						m++;
					}

				} else {

					JLabel label = new JLabel();
					if (i % 2 == 0){
						Border border = BorderFactory.createLineBorder(color.darker(), 30);
						label.setBorder(border);
						if(j % 2 != 0){
							Border border2 = BorderFactory.createLineBorder(color.brighter(), 30);
							label.setBorder(border2);
						}
					} else if( i % 2 != 0){
						label.setBackground(color.brighter());{
							if(j % 2 != 0){
								label.setBackground(color.darker());
							}
						}
					}
					dicePanel.add(label, gb);
				}
				k++;
			}
		}
	}

	/** 
	 * Puts one saved dice back in the center area
	 * 
	 * @param YCice dice
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void setDiceVisibleOnDicePanel(YDice dice) {
		
		for(DiceView diceView : diceViewList){
			if (diceView.getDiceID().equals(dice.getDiceID())){
				diceView.setVisible(true);
				dicePanel.add(diceView);
				dice.setDiceChanges();

			}
		}
	}

	/** 
	 * Sets the PanelListener of the GamePanel.
	 * 
	 * @param PanelListener panelListener
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public static void setGamePanelListener(PanelListener panelListener) {
		GamePanel.panelListener = panelListener;

	}
	
	/** 
	 * Returns the PanelListener of the GamePanel
	 * 
	 * @return PanelListener panelListener
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */

	public static PanelListener getGamePanelListener(){
		return panelListener; 
	}

	/** 
	 * Adds one empty label in the dice panel at one specified position 
	 * 
	 * @param ArrayLis<Integer> position 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 6, 2015
	 */
	public void addEmptyLabel(ArrayList<Integer> position) {
		gb.gridx = position.get(0);
		gb.gridy = position.get(1);
		JLabel label = new JLabel();
		dicePanel.add(label,gb);

	}
}

