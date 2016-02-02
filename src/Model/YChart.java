package Model;

import java.util.ArrayList;

/** 
 * This class represent one chart and contains the rules corresponding to each box. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 4, 2015
 */
public class YChart {
	private ArrayList<Integer> intList; 
	private Player player;
	
	/** 
	 * The constructor of the class takes one parameter
	 * 
	 * @param Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	
	YChart(Player player) {
		this.player = player;
		intList = new ArrayList<>();
	}

	/** 
	 * Returns the Player 
	 * 
	 * @return Player player
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	public Player getPlayer() {
		return player;
	}

	/** 
	 * Initialize the list of integers that represents the values of the dice
	 * 
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected void initCurrentIntListToCheck(ArrayList<YDice> dices) {
		intList.clear();
		for (YDice dice : dices) {
			intList.add(dice.getDiceInt());
		}
	}

	/** 
	 * Check the upper part of the chart and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkFirstSix(ArrayList<ReturnValue> rvs) {
		for (int i = 1; i <= 6; i++) {
			checkNumbers(i, rvs.get(i - 1));
		}
		return true;
	}

	/** 
	 * Check the rule One Pair and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkPair(ReturnValue returnValue) {
		boolean returnBool = false;
		int localCheck = 0;
		int localPoint = 0;
		int localDiceInt = 0;
		boolean localBool = false;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (intList.get(i) == intList.get(j)) {
					localCheck++;
					if (localCheck == 2) {
						localBool = true;
						if (localPoint < intList.get(i) * 2) {
							localPoint = intList.get(i) * 2;
							localDiceInt = intList.get(i);
							returnValue.setTheDiceInt(localDiceInt);

						}
					}
				}
			}
			localCheck = 0;
		}
		returnValue.setValue(localBool, localPoint, Box.ONE__PAIR);
		returnValue.setTheDiceInt(localDiceInt);
		returnBool = true;
		return returnBool;
	}

	/** 
	 * Check the rule Two Pairs and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkTwoPairs(ReturnValue returnValueTwoPairs, ReturnValue returnValueOnePair) {
		boolean returnBool = false;

		if (returnValueOnePair.getIsPossible()) {
			int localCheck = 0;
			int localPoints = 0;
			boolean localBool = false;

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (intList.get(i) == intList.get(j) && intList.get(i) != returnValueOnePair.getTheDiceInt()) {
						localCheck++;
						if (localCheck == 2) {
							localBool = true;
							if (localPoints < intList.get(i) * 2) {
								localPoints = intList.get(i) * 2;
								localPoints += returnValueOnePair.getPoints();
								returnValueTwoPairs.setValue(localBool, localPoints, Box.TWO_PAIRS);
								returnBool = true;
								return returnBool;
							}
						}
					}
				}
				localCheck = 0;
			}
			returnValueTwoPairs.setValue(localBool, localPoints, Box.TWO_PAIRS);
		}
		return returnBool;
	}

	/** 
	 * Check the rule three of a kind and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkThreeOfAKind(ReturnValue returnValue) {
		boolean returnBool = false;
		int localCheck = 0;
		int localPoints = 0;
		int localDiceInt;
		boolean localBool = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (intList.get(i) == intList.get(j)) {
					localCheck++;
					if (localCheck == 3) {
						localBool = true;
						localPoints = intList.get(i) * 3;
						localDiceInt = intList.get(i);
						returnValue.setValue(localBool, localPoints, Box.THREE_OF_A_KIND);
						returnValue.setTheDiceInt(localDiceInt);
						returnBool = true;
						return returnBool;
					}
				}
			}
			localCheck = 0;
		}
		returnValue.setValue(localBool, localPoints, Box.THREE_OF_A_KIND);
		return returnBool;
	}

	/** 
	 * Check the rule Four of a kind and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkFourOfAKind(ReturnValue returnValue) {
		boolean returnBool = true;
		int localCheck = 0;
		int localPoints = 0;
		boolean localBool = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (intList.get(i) == intList.get(j)) {
					localCheck++;
					if (localCheck == 4) {
						localBool = true;
						localPoints = intList.get(i) * 4;
						returnValue.setValue(localBool, localPoints, Box.FOUR_OF_A_KIND);
						returnValue.setTheDiceInt(intList.get(i));
						return returnBool;
					}
				}
			}
			localCheck = 0;
		}
		returnValue.setValue(localBool, localPoints, Box.FOUR_OF_A_KIND);
		return returnBool;
	}

	/** 
	 * Sorts the list of integer representing the values of the dice by using the bubble sort algorithm.
	 * 
	 * @return Integer [] intLIst
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */ 
	private Integer[] bubbleSortList() {
		Integer[] localList = new Integer[intList.size()];
		localList = intList.toArray(localList);

		int temp;
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				if (localList[j] > localList[j + 1]) {
					temp = localList[j];
					localList[j] = localList[j + 1];
					localList[j + 1] = temp;
				}
			}
		}
		return localList;
	}

	/** 
	 * Check the rules Small straight and Large straight and store points in objects of type ReturnValue
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkStraight(ReturnValue returnValue1, ReturnValue returnValue2) {
		Integer[] localList;
		boolean localBool = false;
		localList = bubbleSortList();

		for (int i = 0; i < 4; i++) {
			if (localList[i] + 1 != localList[i + 1]) {
				returnValue1.setValue(localBool, 0, Box.SMALL_STRAIGHT);
				returnValue2.setValue(localBool, 0, Box.LARGE_STRAIGHT);
				return true;
			}
		}
		localBool = true;

		if (localList[0] == 1) {
			returnValue1.setValue(localBool, 15, Box.SMALL_STRAIGHT);
			returnValue2.setValue(false, 0, Box.LARGE_STRAIGHT);
		} else if (localList[0] == 2) {
			returnValue1.setValue(false, 0, Box.SMALL_STRAIGHT);
			returnValue2.setValue(localBool, 20, Box.LARGE_STRAIGHT);
		}
		return true;
	}
	
	/** 
	 * Check the rule Full house, which is divided into two parts, and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */

	protected boolean checkFullHouse(ReturnValue returnValue) {
		int localCheck = 0;
		int theDiceInt;
		int localPoints1;
		int localPoints2 = 0; 
		boolean localBool = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (intList.get(i) == intList.get(j)) {
					localCheck++;
					theDiceInt = intList.get(i);
					if (localCheck == 3) {
						checkFullHouse2(theDiceInt, returnValue);
						if (returnValue.getIsPossible()) {
							localPoints1 = returnValue.getPoints();
							localPoints2 += theDiceInt * 3;
							localBool = true;

							returnValue.setValue(localBool, (localPoints1 + localPoints2), Box.FULL_HOUSE);
							return true; 

						} else {
							returnValue.setValue(localBool, 0, Box.FULL_HOUSE);
						}
					}
				}
			}
			localCheck = 0;
		}
		returnValue.setValue(localBool, 0, Box.FULL_HOUSE);
		return true;
	}

	/** 
	 * Check the second part of the rule Full house 
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private ReturnValue checkFullHouse2(int toIgnore, ReturnValue returnValue) {
		int localCheck = 0;
		int theDiceInt;
		int localPoints;
		boolean localBool = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (intList.get(i) == intList.get(j) && intList.get(i) != toIgnore) {
					localCheck++;
					if (localCheck == 2) {
						localBool = true;
						theDiceInt = intList.get(i);
						localPoints = theDiceInt * 2;
						returnValue.setValue(localBool, localPoints, Box.FULL_HOUSE);
						return returnValue;
					}
				}
			}
			localCheck = 0;
		}
		returnValue.setValue(localBool, 0, Box.FULL_HOUSE);
		return returnValue;
	}

	/** 
	 * Check the rule Chance and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkChance(ReturnValue returnValue) {
		int sum = 0;
				for (Integer i : intList) {
					sum += i;
				}
		returnValue.setValue(true, sum, Box.CHANCE);
		return true;
	}

	/** 
	 * Check the rule Yatzy and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	protected boolean checkYazy(ReturnValue returnValue) {
		boolean localBool = false;
		int localInt = intList.get(0);
		for (int i = 1; i < 5; i++) {
			if (localInt != intList.get(i)) {
				returnValue.setValue(localBool, 0, Box.YATZY);
				return true;
			}
		}
		localBool = true;
		returnValue.setValue(localBool, 50, Box.YATZY);
		return true;
	}

	/** 
	 * Check the second part of the rules in the upper chart and store points in an object of type ReturnValues
	 * 
	 * @return boolean returnBool
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */ 
	private boolean checkNumbers(int x, ReturnValue returnValue) {
		int localCheck = 0;
		int localPoints = 0;
		boolean localBool = false;
		for (Integer i : intList) {
			if (i == x) {
				localCheck++;
			}
		}
		if (localCheck > 0) {
			localBool = true;
			localPoints = localCheck * x;
		}
		returnValue.setValue(localBool, localPoints, findBox(x));
		return true;
	}

	/** 
	 * Search thru the list of box and returns the one box corresponding to the parameter. 
	 * 
	 * @return Box box
	 * @author UlrikaGoloconda
	 * @version 1.0 December 4, 2015
	 */
	private Box findBox(int x) {
		ArrayList<BoxLogic> local = CurrentState.getCurrentListOfBoxLogic();
		return local.get(x - 1).getBox();
	}
}
