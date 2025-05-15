import java.util.Comparator;

public class PokerCardComparator implements Comparator<PokerCard> {

    @Override
    public int compare(PokerCard card1, PokerCard card2) {
        return card1.value.compareTo(card2.value);
    }
}
