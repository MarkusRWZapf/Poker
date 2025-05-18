import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokerGame {
    public static void main(String[] args) {
        List<PokerCard> cardsInFirstPokerHand =List.of(
        new PokerCard(Suit.DIAMONDS, Value.THREE),
        new PokerCard(Suit.DIAMONDS, Value.JACK),
        new PokerCard(Suit.HEARTS, Value.KING),
        new PokerCard(Suit.SPADES, Value.SEVEN),
        new PokerCard(Suit.CLUBS, Value.SEVEN)
        );

        List<PokerCard> cardsInSecondPokerHand = List.of(
        new PokerCard(Suit.CLUBS, Value.THREE),
        new PokerCard(Suit.SPADES, Value.THREE),
        new PokerCard(Suit.HEARTS, Value.THREE),
        new PokerCard(Suit.DIAMONDS, Value.TWO),
        new PokerCard(Suit.CLUBS, Value.NINE)
        );

        try{
            checkBothListsOfCardsForDuplicates(cardsInFirstPokerHand, cardsInSecondPokerHand);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal Argument: " + e.getMessage());
            System.exit(1);
        }

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

    public static void checkBothListsOfCardsForDuplicates(List<PokerCard> firstList, List<PokerCard> secondList) {
        List<PokerCard> allKnownCardsInPlay = new ArrayList<>(firstList);
        allKnownCardsInPlay.addAll(secondList);

        Set<PokerCard> uniquePokerCards = new HashSet<>();
        List<PokerCard> duplicateCards = new ArrayList<>();

        for (PokerCard card : allKnownCardsInPlay) {
            if (!uniquePokerCards.add(card)) {
                duplicateCards.add(card);
            }
        }

        if (!duplicateCards.isEmpty()) {
            throw new IllegalArgumentException("Duplicate cards detected: " + duplicateCards);
        }
    }
}
