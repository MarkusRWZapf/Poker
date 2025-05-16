import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PokerCard> cardsInFirstPokerHand = new ArrayList<>();
        List<PokerCard> cardsInSecondPokerHand = new ArrayList<>();
        cardsInFirstPokerHand.add(new PokerCard(Suit.CLUBS, Value.THREE));
        cardsInFirstPokerHand.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        cardsInFirstPokerHand.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        cardsInFirstPokerHand.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        cardsInFirstPokerHand.add(new PokerCard(Suit.CLUBS, Value.SEVEN));

        cardsInSecondPokerHand.add(new PokerCard(Suit.CLUBS, Value.THREE));
        cardsInSecondPokerHand.add(new PokerCard(Suit.DIAMONDS, Value.SEVEN));
        cardsInSecondPokerHand.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        cardsInSecondPokerHand.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        cardsInSecondPokerHand.add(new PokerCard(Suit.CLUBS, Value.SEVEN));

        PokerHand firstPokerHand = new PokerHand(cardsInFirstPokerHand);
        PokerHand secondPokerHand = new PokerHand(cardsInSecondPokerHand);

        System.out.println("Rank of first PokerHand: " + firstPokerHand.getRank());
        System.out.println("Rank of second PokerHand: " + secondPokerHand.getRank());

        int result = firstPokerHand.compareTo(secondPokerHand);
        if (result > 0) {
            System.out.println("First PokerHand wins!");
        } else if (result < 0) {
            System.out.println("Second PokerHand wins!");
        } else {
            System.out.println("Alas, it's a tie!");
        }

    }
}
