import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PokerCard> pokerCardsInHand1 = new ArrayList<>();
        pokerCardsInHand1.add(new PokerCard(Suit.CLUBS, Value.THREE));
        pokerCardsInHand1.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCardsInHand1.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        pokerCardsInHand1.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        pokerCardsInHand1.add(new PokerCard(Suit.CLUBS, Value.SEVEN));

        List<PokerCard> pokerCardsInHand2 = new ArrayList<>();
        pokerCardsInHand2.add(new PokerCard(Suit.CLUBS, Value.THREE));
        pokerCardsInHand2.add(new PokerCard(Suit.DIAMONDS, Value.SEVEN));
        pokerCardsInHand2.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        pokerCardsInHand2.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        pokerCardsInHand2.add(new PokerCard(Suit.CLUBS, Value.SEVEN));

        PokerHand PokerHand1 = new PokerHand(pokerCardsInHand1);
        PokerHand PokerHand2 = new PokerHand(pokerCardsInHand2);

        System.out.println("Hand 1: " + PokerHand1.getRank());
        System.out.println("Hand 2: " + PokerHand2.getRank());

        int result = PokerHand1.getRank().compareTo(PokerHand2.getRank());
        if (result > 0) {
            System.out.println("Hand 1 wins!");
        } else if (result < 0) {
            System.out.println("Hand 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }

    }
}
