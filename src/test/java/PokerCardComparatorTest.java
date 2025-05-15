import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerCardComparatorTest {

    private final List<PokerCard> pokerCards = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        pokerCards.add(new PokerCard(Suit.CLUBS, Rank.FIVE));
        pokerCards.add(new PokerCard(Suit.SPADES, Rank.KING));
        pokerCards.add(new PokerCard(Suit.HEARTS, Rank.THREE));
        pokerCards.add(new PokerCard(Suit.CLUBS, Rank.ACE));
        pokerCards.add(new PokerCard(Suit.DIAMONDS, Rank.TEN));
    }

    @Test
    public void listIsSortedAccordingToPokerCardRanking() {
        pokerCards.sort(new PokerCardComparator());
        assertEquals(new PokerCard(Suit.HEARTS, Rank.THREE), pokerCards.getFirst());
        assertEquals(new PokerCard(Suit.CLUBS, Rank.FIVE), pokerCards.get(1));
        assertEquals(new PokerCard(Suit.DIAMONDS, Rank.TEN), pokerCards.get(2));
        assertEquals(new PokerCard(Suit.SPADES, Rank.KING), pokerCards.get(3));
        assertEquals(new PokerCard(Suit.CLUBS, Rank.ACE), pokerCards.getLast());
    }


}
