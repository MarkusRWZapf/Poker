import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandEvaluatorTest {

    private List<PokerCard> pokerCards = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        pokerCards = new ArrayList<>();
    }

    @Test
    public void evaluatesFlushHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.SPADES, Value.FIVE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.KING));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.THREE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.ACE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.TEN));
        PokerHand flushHand = new PokerHand(pokerCards);
        assertEquals(Rank.FLUSH,flushHand.getRank());
    }

    @Test
    public void evaluatesStraightHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.EIGHT));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.TEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.JACK));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.NINE));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.STRAIGHT,straightHand.getRank());
    }

    @Test
    public void evaluatesStraightFlushHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.TEN));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.EIGHT));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.SEVEN));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.STRAIGHT_FLUSH,straightHand.getRank());
    }

    @Test
    public void evaluatesFourOfAKindHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.NINE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.NINE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.NINE));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.FOUR_OF_A_KIND,straightHand.getRank());
    }

    @Test
    public void evaluatesThreeOfAKindHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.JACK));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.JACK));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.THREE_OF_A_KIND,straightHand.getRank());
    }

    @Test
    public void evaluatesPairHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.ONE_PAIR,straightHand.getRank());
    }

    @Test
    public void evaluatesTwoPairHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.QUEEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.TWO));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.TWO));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.TWO_PAIR,straightHand.getRank());
    }

    @Test
    public void evaluatesFullHouseHand_Correctly() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.SEVEN));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.SEVEN));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.FULL_HOUSE,straightHand.getRank());
    }
}
