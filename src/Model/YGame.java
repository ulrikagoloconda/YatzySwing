package Model;

import java.util.ArrayList;
import Controller.Mediator;
/**
 * YGame is the class that functions as the hub of the Model. The logic and structure of the game is administered by YGame. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */

public class YGame implements GameInterface {
	private Mediator mediator; 
	private ArrayList<YDice> dices;
	private ArrayList<Model.Box> boxes;
	private ArrayList<Sum> sums;
	private ArrayList<YChart> chartList;
	private ArrayList<ArrayList<BoxLogic>> listOfBoxLocigList;
	private ArrayList<ReturnValue> listOfReturnValues; 
	private ArrayList<Player> players; 
	private ArrayList<CurrentScore> currentScoreList; 
	private CurrentState cs;
	/**
	 * The constructor of YGame
	 * 
	 *  @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
	public YGame() {
		mediator = new Mediator(this);
		mediator.addStartUpPanel();
		boxes = new ArrayList<>();
		sums = new ArrayList<>();
		chartList = new ArrayList<>();
		players = new ArrayList<>();
		cs = new CurrentState();
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#initGame()
	 */

	@Override
	public void initGame() { 
		initDice();
		initListOfBoxName();
		initListOfSumName();
		initListOfBoxLogicAndReturnValues();
		initChartList();
		cs.setCurrentPlayer(players.get(0)); //Här är det nödvändigt att att anvädna objekt, ickstatisk metod
		setCurretnChart();
		CurrentState.initCurrentListOfDice(dices);
		mediator.initGameGUI();
		mediator.addObserverToReturnValues(CurrentState.getCurrentListOfReturnValuse());
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#setCurrentPlayerInstance(Model.Player)
	 */
	@Override
	public void setCurrentPlayerInstance(Player player){
		cs.setCurrentPlayer(player);
	}
	
	/* (non-Javadoc)
	 * @see Model.GameInterface#setPlayersAndScoreList(java.util.ArrayList)
	 */
	@Override
	public void setPlayersAndScoreList(ArrayList<String> names){
		currentScoreList = new ArrayList<>();
		for ( String name : names){
			players.add(new Player(name));

		}

		for(Player p : players){
			currentScoreList.add(new CurrentScore(p));
		}
	}
	
	/* (non-Javadoc)
	 * @see Model.GameInterface#getListOfCurrentScore()
	 */
	@Override
	public ArrayList<CurrentScore> getListOfCurrentScore(){
		return currentScoreList; 
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#setCurretnChart()
	 */ 
	@Override
	public void setCurretnChart() {
		for (YChart chart : chartList) {
			if (chart.getPlayer().equals(CurrentState.getCurrentPlayer()))
				CurrentState.setCurrentChart(chart);
		}

		setCurrentListOfBoxLogicList();
	}
	/**
	 * Initialize a list of YChart 
	 * 
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */ 
	private void initChartList() {
		for (Player p : players) {
			chartList.add(new YChart(p));
		}
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#getChartList()
	 */ 
	@Override
	public ArrayList<YChart> getChartList(){
		return chartList;
	}
	
	/* (non-Javadoc)
	 * @see Model.GameInterface#newCurrentPlayer()
	 */ 
	@Override
	public void newCurrentPlayer() {
		for (int i = 0; i < players.size(); i++) {
			if (CurrentState.getCurrentPlayer().equals(players.get(i))) { //Här är det nödvändigt med ett ickestatiskt anrop 
				if (i == players.size() - 1) {
					cs.setCurrentPlayer(players.get(0));
				} else {
					cs.setCurrentPlayer(players.get(i + 1));
					mediator.reSetCount();
				}
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#checkChart()
	 */ 
	@Override
	public void checkChart() {

		CurrentState.getCurrentChart().initCurrentIntListToCheck(CurrentState.getCurrentListOfDice()); //Ser till sÃ¥ att en lista av int finns fÃ¶r gÃ¤mfÃ¶relse 
		ArrayList<ReturnValue> oneToSix = new ArrayList<>(listOfReturnValues.subList(0, 6));
		CurrentState.getCurrentChart().checkFirstSix(oneToSix);
		CurrentState.getCurrentChart().checkPair(listOfReturnValues.get(6));
		CurrentState.getCurrentChart().checkTwoPairs(listOfReturnValues.get(7), listOfReturnValues.get(6));
		CurrentState.getCurrentChart().checkThreeOfAKind(listOfReturnValues.get(8));
		CurrentState.getCurrentChart().checkFourOfAKind(listOfReturnValues.get(9));
		CurrentState.getCurrentChart().checkStraight(listOfReturnValues.get(10), listOfReturnValues.get(11));
		CurrentState.getCurrentChart().checkFullHouse(listOfReturnValues.get(12));
		CurrentState.getCurrentChart().checkChance(listOfReturnValues.get(13));
		CurrentState.getCurrentChart().checkYazy(listOfReturnValues.get(14));

		CurrentState.setCurrentListOfReturnValuse(listOfReturnValues);

	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#isValidOptionLeft()
	 */
	@Override
	public boolean isValidOptionLeft(){
		boolean local = false;
		for(ReturnValue rv : CurrentState.getCurrentListOfReturnValuse()){
			if(rv.getIsPossible()){
				for(BoxLogic bl : CurrentState.getCurrentListOfBoxLogic()){
					if(rv.getBox().equals(bl.getBox())){
						if(!bl.isAlradyTaken()){
							local = true;    			
							return local; 
						}
					}
				}
			}
		}
		return local; 

	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#addPoints()
	 */
	@Override
	public void addPoints(){
		for (ReturnValue rv: listOfReturnValues){
			if (rv.isSumValue()){
				for(CurrentScore cs : currentScoreList){
					if(cs.getPlayer().equals(CurrentState.getCurrentPlayer())){
						rv.setValue(cs.getFirstSixSum(),cs.getScoreTotal());
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#clearListOfReturnValues()
	 */
	@Override
	public void clearListOfReturnValues() {
		for (ReturnValue rv : CurrentState.getCurrentListOfReturnValuse()) {
			rv.clearValues();

		}
	}

	/**
	 * Initialize five new dice and saves them an ArrayList
	 * 
	 *
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	private void initDice() {
		dices = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			dices.add(new YDice());

		}
		dices = rollAllDices(dices);

	}

	/**
	 * All five dice in the list of YDice gets new random values 
	 * 
	 *@return ArrayList<YDice>
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	protected ArrayList<YDice> rollAllDices(ArrayList<YDice> d) {
		ArrayList<YDice> localList = new ArrayList<>();
		for (YDice dice : d) {
			dice.rollTheDice();
			localList.add(dice);
		}
		return localList;
	}

	/**
	 * All five dice in the list of YDice gets new random values 
	 * 
	 *@return ArrayList<YDice>
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	protected ArrayList<YDice> rollTheDices(ArrayList<YDice> arrayList) {
		ArrayList<YDice> local = new ArrayList<>();

		for (YDice dice : arrayList) {
			dice.rollTheDice();
			local.add(dice);
		}
		return local;
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#getTheRolledDices()
	 */
	@Override
	public ArrayList<YDice> getTheRolledDices() {
		return dices;
	}

	/**
	 * Initialize the list of type Box
	 * 
	 *
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	private void initListOfBoxName() {
		boxes.add(Model.Box.ONES);
		boxes.add(Model.Box.TWOS);
		boxes.add(Model.Box.THREES);
		boxes.add(Model.Box.FOURS);
		boxes.add(Model.Box.FIVES);
		boxes.add(Model.Box.SIXES);
		boxes.add(Model.Box.ONE__PAIR);
		boxes.add(Model.Box.TWO_PAIRS);
		boxes.add(Model.Box.THREE_OF_A_KIND);
		boxes.add(Model.Box.FOUR_OF_A_KIND);
		boxes.add(Model.Box.SMALL_STRAIGHT);
		boxes.add(Model.Box.LARGE_STRAIGHT);
		boxes.add(Model.Box.FULL_HOUSE);
		boxes.add(Model.Box.CHANCE);
		boxes.add(Model.Box.YATZY);

	}

	/**
	 * Initialize the list of type Sum
	 *
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	private void initListOfSumName(){

		sums.add(Sum.SUM);
		sums.add(Sum.BONUS);
		sums.add(Sum.TOTAL);
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#getListOfBox()
	 */
	@Override
	public ArrayList<Model.Box> getListOfBox() {
		return boxes;
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#setCurrentListOfBoxLogicList()
	 */
	@Override
	public void setCurrentListOfBoxLogicList() {
		ArrayList<BoxLogic> localList1 = new ArrayList<>(); 
		ArrayList<BoxLogic> localList2 = new ArrayList<>(); 
		for (int i = 0; i < listOfBoxLocigList.size(); i++){
			localList1 = listOfBoxLocigList.get(i);
			for(int j = 0; j < localList1.size(); j++){
				if(localList1.get(j).getPlayer().equals(CurrentState.getCurrentPlayer())){
					localList2.add(localList1.get(j));
				}
			}
		}
		CurrentState.setCurrentListOfBoxLogic(localList2);
	}
	
	/**
	 *Initialize list of list of type BoxLogic and list of list of ReturnValues
	 *
	 *  @author UlrikaGoloconda
	 * @version 1.0 Dec 2, 2015
	 */
	protected void initListOfBoxLogicAndReturnValues() {
		listOfBoxLocigList = new ArrayList<>();
		listOfReturnValues = new ArrayList<>();
		ArrayList<BoxLogic> localBl; 

		for (int i = 0; i < players.size(); i++){ 
			localBl = new ArrayList<>();
			for (Model.Box box : boxes) {

				localBl.add(new BoxLogic(box,players.get(i)));

			}
			listOfBoxLocigList.add(localBl);
		}

		for(Model.Box box : boxes){
			listOfReturnValues.add(new ReturnValue());
		} 

		CurrentState.setCurrentListOfReturnValuse(listOfReturnValues);
		BoxLogic.setGame(this);
	}
	
	/* (non-Javadoc)
	 * @see Model.GameInterface#winCheck()
	 */
	@Override
	public boolean winCheck(){
		boolean localCheck = true; 
		for(int i = 0; i < listOfBoxLocigList.size(); i++){
			ArrayList<BoxLogic> localList = listOfBoxLocigList.get(i);
			for (int j = 0; j < localList.size(); j++){
				if(!localList.get(j).isAlradyTaken()){ 
					localCheck = false; 
					return localCheck; 
				}
			}
		}
		return localCheck; 
	}
	
	/* (non-Javadoc)
	 * @see Model.GameInterface#whoIsWinner()
	 */

	@Override
	public ArrayList whoIsWinner(){

		for(CurrentScore cs : currentScoreList){
			System.out.println( cs.getPlayer().getName() + " " + cs.getScoreTotal());
		}
		currentScoreList.sort(new SorterCurrentScore());

		for(CurrentScore cs : currentScoreList){
			System.out.println( cs.getPlayer().getName() + " " + cs.getScoreTotal());
		}

		return currentScoreList; 
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#getListOfBoxLogicList()
	 */
	@Override
	public ArrayList<ArrayList<BoxLogic>> getListOfBoxLogicList() {

		return listOfBoxLocigList;
	}

	/* (non-Javadoc)
	 * @see Model.GameInterface#getCSObject()
	 */
	@Override
	public CurrentState getCSObject() {
		return cs; 	
	}
}

