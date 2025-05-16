import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandTest {

    private List<PokerCard> pokerCards = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        pokerCards = new ArrayList<>();
    }

    @Test
    public void evaluatesFlushHand() {
        pokerCards.add(new PokerCard(Suit.SPADES, Value.FIVE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.KING));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.THREE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.ACE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.TEN));
        PokerHand flushHand = new PokerHand(pokerCards);
        assertEquals(Rank.FLUSH,flushHand.getRank());
    }

    @Test
    public void evaluatesStraightHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.EIGHT));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.TEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.JACK));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.NINE));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.STRAIGHT,straightHand.getRank());
    }

    @Test
    public void evaluatesStraightFlushHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.TEN));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.EIGHT));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.SEVEN));
        PokerHand straightFlushHand = new PokerHand(pokerCards);
        assertEquals(Rank.STRAIGHT_FLUSH,straightFlushHand.getRank());
    }

    @Test
    public void evaluatesFourOfAKindHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.NINE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.NINE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.NINE));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand fourOfAKindHand = new PokerHand(pokerCards);
        assertEquals(Rank.FOUR_OF_A_KIND,fourOfAKindHand.getRank());
    }

    @Test
    public void evaluatesThreeOfAKindHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.JACK));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand threeOfAKindHand = new PokerHand(pokerCards);
        assertEquals(Rank.THREE_OF_A_KIND,threeOfAKindHand.getRank());
    }

    @Test
    public void evaluatesPairHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand pairHand = new PokerHand(pokerCards);
        assertEquals(Rank.ONE_PAIR,pairHand.getRank());
    }

    @Test
    public void evaluatesTwoPairHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.TWO));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.TWO));
        PokerHand twoPairHand = new PokerHand(pokerCards);
        assertEquals(Rank.TWO_PAIR,twoPairHand.getRank());
    }

    @Test
    public void evaluatesFullHouseHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.SEVEN));
        PokerHand fullHouseHand = new PokerHand(pokerCards);
        assertEquals(Rank.FULL_HOUSE,fullHouseHand.getRank());
    }

    @Test
    public void evaluatesHighCardHand() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.ACE));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.EIGHT));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.FIVE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.KING));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.TWO));
        PokerHand highCardHand = new PokerHand(pokerCards);
        assertEquals(Rank.HIGH_CARD, highCardHand.getRank());
    }

    @Test
    public void evaluatesHighCardAgainstAnotherHand_ClearWinner() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.ACE));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.EIGHT));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.FIVE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.KING));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.TWO));

        List<PokerCard> otherPokerCards = new ArrayList<>();
        otherPokerCards.add(new PokerCard(Suit.CLUBS, Value.ACE));
        otherPokerCards.add(new PokerCard(Suit.SPADES, Value.EIGHT));
        otherPokerCards.add(new PokerCard(Suit.DIAMONDS, Value.FOUR));
        otherPokerCards.add(new PokerCard(Suit.SPADES, Value.TWO));
        otherPokerCards.add(new PokerCard(Suit.CLUBS, Value.TWO));
        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(1, firstHand.compareHighCardsWithTheOnesInAnotherHand(secondHand));
        assertEquals(-1, secondHand.compareHighCardsWithTheOnesInAnotherHand(firstHand));
    }

    @Test
    public void evaluatesHighCardAgainstAnotherHand_EndsInATie() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.ACE));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.EIGHT));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.FIVE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.KING));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.TWO));

        List<PokerCard> otherPokerCards = new ArrayList<>();
        otherPokerCards.add(new PokerCard(Suit.SPADES, Value.KING));
        otherPokerCards.add(new PokerCard(Suit.CLUBS, Value.ACE));
        otherPokerCards.add(new PokerCard(Suit.CLUBS, Value.TWO));
        otherPokerCards.add(new PokerCard(Suit.HEARTS, Value.FIVE));
        otherPokerCards.add(new PokerCard(Suit.DIAMONDS, Value.EIGHT));
        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(0, firstHand.compareHighCardsWithTheOnesInAnotherHand(secondHand));
    }

}
