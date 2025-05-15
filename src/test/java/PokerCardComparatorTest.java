import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerCardComparatorTest {

    private final List<PokerCard> pokerCards = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.FIVE));
        pokerCards.add(new PokerCard(Suit.SPADES, Value.KING));
        pokerCards.add(new PokerCard(Suit.HEARTS, Value.THREE));
        pokerCards.add(new PokerCard(Suit.CLUBS, Value.ACE));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Value.TEN));
    }

    @Test
    public void listIsSortedAccordingToPokerCardValue() {
        pokerCards.sort(new PokerCardComparator());
        assertEquals(new PokerCard(Suit.HEARTS, Value.THREE), pokerCards.getFirst());
        assertEquals(new PokerCard(Suit.CLUBS, Value.FIVE), pokerCards.get(1));
        assertEquals(new PokerCard(Suit.DIAMONDS, Value.TEN), pokerCards.get(2));
        assertEquals(new PokerCard(Suit.SPADES, Value.KING), pokerCards.get(3));
        assertEquals(new PokerCard(Suit.CLUBS, Value.ACE), pokerCards.getLast());
    }


}
