package Model;

/** 
 * This Enum consists of the names of the boxes in the game. They are provided whit a number. 
 * 
 * @author UlrikaGoloconda
 * @version 1.0 December 2, 2015
 */
public enum Box {
    ONES(1), TWOS(2), THREES(3), FOURS(4), FIVES(5), SIXES(6), ONE__PAIR(7), TWO_PAIRS(8), THREE_OF_A_KIND(9),
    FOUR_OF_A_KIND(10), SMALL_STRAIGHT(11), LARGE_STRAIGHT(12), FULL_HOUSE(13), CHANCE(14), YATZY(15);

    private int number_;

    private Box(int number) {
        number_ = number;
    }


	/** 
	 * Override toString and returns usable strings
	 * 
	 * @return String local
	 * @author UlrikaGoloconda
	 * @version 1.0 December 2, 2015
	 */
    @Override
    public String toString() {
        String local = null;
        switch (this) {
            case ONES:
                local = "Ettor";
                break;
            case TWOS:
                local = "Tvåor";
                break;
            case THREES:
                local = "Treor";
                break;
            case FOURS:
                local = "Fyror";
                break;
            case FIVES:
                local = "Femmor";
                break;
            case SIXES:
                local = "Sexor";
                break;
            case ONE__PAIR:
                local = "Ett par";
                break;
            case TWO_PAIRS:
                local = "Två par";
                break;
            case THREE_OF_A_KIND:
                local = "Triss";
                break;
            case FOUR_OF_A_KIND:
                local = "Fyrtal";
                break;
            case SMALL_STRAIGHT:
                local = "Liten stege";
                break;
            case LARGE_STRAIGHT:
                local = "Stor stege";
                break;
            case FULL_HOUSE:
                local = "Kåk";
                break;
            case CHANCE:
                local = "Chans";
                break;
            case YATZY:
                local = "Yatsy";
                break;
        }
        return local;
    }
}