import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokerHandTest {

    private List<PokerCard> pokerCards = new ArrayList<>();

    List<PokerCard> straight_Flush_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.THREE),
            new PokerCard(Suit.SPADES, Value.SIX),
            new PokerCard(Suit.SPADES, Value.FOUR),
            new PokerCard(Suit.SPADES, Value.SEVEN),
            new PokerCard(Suit.SPADES, Value.FIVE)
    );

    List<PokerCard> four_Of_A_Kind_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.TEN),
            new PokerCard(Suit.CLUBS, Value.TEN),
            new PokerCard(Suit.CLUBS, Value.NINE),
            new PokerCard(Suit.HEARTS, Value.TEN),
            new PokerCard(Suit.DIAMONDS, Value.TEN)
    );

    List<PokerCard> full_House_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.ACE),
            new PokerCard(Suit.CLUBS, Value.ACE),
            new PokerCard(Suit.CLUBS, Value.NINE),
            new PokerCard(Suit.HEARTS, Value.NINE),
            new PokerCard(Suit.DIAMONDS, Value.NINE)
    );

    List<PokerCard> flush_Cards = List.of(
            new PokerCard(Suit.DIAMONDS, Value.QUEEN),
            new PokerCard(Suit.DIAMONDS, Value.TWO),
            new PokerCard(Suit.DIAMONDS, Value.FIVE),
            new PokerCard(Suit.DIAMONDS, Value.TEN),
            new PokerCard(Suit.DIAMONDS, Value.NINE)
    );

    List<PokerCard> straight_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.SIX),
            new PokerCard(Suit.CLUBS, Value.FOUR),
            new PokerCard(Suit.CLUBS, Value.THREE),
            new PokerCard(Suit.HEARTS, Value.FIVE),
            new PokerCard(Suit.DIAMONDS, Value.SEVEN)
    );

    List<PokerCard> three_Of_A_Kind_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.NINE),
            new PokerCard(Suit.CLUBS, Value.SEVEN),
            new PokerCard(Suit.CLUBS, Value.NINE),
            new PokerCard(Suit.HEARTS, Value.TWO),
            new PokerCard(Suit.DIAMONDS, Value.NINE)
    );

    List<PokerCard> two_Pairs_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.SEVEN),
            new PokerCard(Suit.CLUBS, Value.ACE),
            new PokerCard(Suit.CLUBS, Value.SEVEN),
            new PokerCard(Suit.HEARTS, Value.ACE),
            new PokerCard(Suit.DIAMONDS, Value.NINE)
    );

    List<PokerCard> one_Pair_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.NINE),
            new PokerCard(Suit.CLUBS, Value.FOUR),
            new PokerCard(Suit.CLUBS, Value.THREE),
            new PokerCard(Suit.HEARTS, Value.EIGHT),
            new PokerCard(Suit.DIAMONDS, Value.NINE)
    );

    List<PokerCard> high_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.ACE),
            new PokerCard(Suit.CLUBS, Value.TEN),
            new PokerCard(Suit.CLUBS, Value.KING),
            new PokerCard(Suit.HEARTS, Value.FOUR),
            new PokerCard(Suit.DIAMONDS, Value.FIVE)
    );

    List<PokerCard> low_Ace_Straight_Cards = List.of(
            new PokerCard(Suit.SPADES, Value.THREE),
            new PokerCard(Suit.CLUBS, Value.TWO),
            new PokerCard(Suit.CLUBS, Value.ACE),
            new PokerCard(Suit.HEARTS, Value.FOUR),
            new PokerCard(Suit.DIAMONDS, Value.FIVE)
    );

    PokerHand straight_Flush_Hand = new PokerHand(straight_Flush_Cards);
    PokerHand four_Of_A_Kind_Hand = new PokerHand(four_Of_A_Kind_Cards);
    PokerHand full_House_Hand = new PokerHand(full_House_Cards);
    PokerHand flush_Hand = new PokerHand(flush_Cards);
    PokerHand straight_Hand = new PokerHand(straight_Cards);
    PokerHand low_Ace_Straight_Hand = new PokerHand(low_Ace_Straight_Cards);
    PokerHand three_Of_A_Kind_Hand = new PokerHand(three_Of_A_Kind_Cards);
    PokerHand two_Pairs_Hand = new PokerHand(two_Pairs_Cards);
    PokerHand one_Pair_Hand = new PokerHand(one_Pair_Cards);
    PokerHand high_Card_Hand = new PokerHand(high_Cards);


    @BeforeEach
    public void setUp() {
        pokerCards = new ArrayList<>();
    }

    @Test
    public void evaluates_Each_Rank_Correctly() {
        assertEquals(Rank.STRAIGHT_FLUSH, straight_Flush_Hand.getRank());
        assertEquals(Rank.FOUR_OF_A_KIND, four_Of_A_Kind_Hand.getRank());
        assertEquals(Rank.FULL_HOUSE, full_House_Hand.getRank());
        assertEquals(Rank.FLUSH, flush_Hand.getRank());
        assertEquals(Rank.STRAIGHT, straight_Hand.getRank());
        assertEquals(Rank.STRAIGHT, low_Ace_Straight_Hand.getRank());
        assertEquals(Rank.THREE_OF_A_KIND, three_Of_A_Kind_Hand.getRank());
        assertEquals(Rank.TWO_PAIR, two_Pairs_Hand.getRank());
        assertEquals(Rank.ONE_PAIR, one_Pair_Hand.getRank());
        assertEquals(Rank.HIGH_CARD, high_Card_Hand.getRank());
    }

    @Test
    public void compares_Differently_Ranked_PokerHands() {
        assertEquals(1, straight_Flush_Hand.compareTo(four_Of_A_Kind_Hand));
        assertEquals(1, four_Of_A_Kind_Hand.compareTo(full_House_Hand));
        assertEquals(1, full_House_Hand.compareTo(flush_Hand));
        assertEquals(1, flush_Hand.compareTo(straight_Hand));
        assertEquals(1, flush_Hand.compareTo(low_Ace_Straight_Hand));
        assertEquals(1, straight_Hand.compareTo(three_Of_A_Kind_Hand));
        assertEquals(1, low_Ace_Straight_Hand.compareTo(three_Of_A_Kind_Hand));
        assertEquals(1, three_Of_A_Kind_Hand.compareTo(two_Pairs_Hand));
        assertEquals(1, two_Pairs_Hand.compareTo(one_Pair_Hand));
        assertEquals(1, one_Pair_Hand.compareTo(high_Card_Hand));
    }

    @Test
    public void pokerHand_Is_Only_Valid_With_Exactly_5_Cards() {
        List<PokerCard> emptyHand = Collections.emptyList();
        List<PokerCard> fourCardHand = List.of(
                new PokerCard(Suit.SPADES, Value.ACE),
                new PokerCard(Suit.CLUBS, Value.TEN),
                new PokerCard(Suit.CLUBS, Value.KING),
                new PokerCard(Suit.HEARTS, Value.FOUR)
        );
        List<PokerCard> sixCardHand = List.of(
                new PokerCard(Suit.SPADES, Value.ACE),
                new PokerCard(Suit.CLUBS, Value.TEN),
                new PokerCard(Suit.CLUBS, Value.KING),
                new PokerCard(Suit.HEARTS, Value.FOUR),
                new PokerCard(Suit.HEARTS, Value.ACE),
                new PokerCard(Suit.DIAMONDS, Value.FIVE)
        );
        assertThrows(IllegalArgumentException.class, () -> new PokerHand(emptyHand));
        assertThrows(IllegalArgumentException.class, () -> new PokerHand(fourCardHand));
        assertThrows(IllegalArgumentException.class, () -> new PokerHand(sixCardHand));
    }

    @Test
    public void low_Ace_Straight_Wins_Against_Lower_Ranks() {
        assertEquals(-1, low_Ace_Straight_Hand.compareTo(flush_Hand));
        assertEquals(1, low_Ace_Straight_Hand.compareTo(three_Of_A_Kind_Hand));
        assertEquals(1, low_Ace_Straight_Hand.compareTo(two_Pairs_Hand));
        assertEquals(1, low_Ace_Straight_Hand.compareTo(one_Pair_Hand));
        assertEquals(1, low_Ace_Straight_Hand.compareTo(high_Card_Hand));
    }
    //Tiebreaker Tests

    @Test
    public void tie_Break_In_Rank_StraightFlush() {
        pokerCards = List.of(
                new PokerCard(Suit.DIAMONDS, Value.THREE),
                new PokerCard(Suit.DIAMONDS, Value.SIX),
                new PokerCard(Suit.DIAMONDS, Value.FIVE),
                new PokerCard(Suit.DIAMONDS, Value.FOUR),
                new PokerCard(Suit.DIAMONDS, Value.SEVEN));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.HEARTS, Value.QUEEN),
                new PokerCard(Suit.HEARTS, Value.KING),
                new PokerCard(Suit.HEARTS, Value.TEN),
                new PokerCard(Suit.HEARTS, Value.JACK),
                new PokerCard(Suit.HEARTS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(-1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Stand_In_Rank_StraightFlush() {
        pokerCards = List.of(
                new PokerCard(Suit.DIAMONDS, Value.KING),
                new PokerCard(Suit.DIAMONDS, Value.QUEEN),
                new PokerCard(Suit.DIAMONDS, Value.TEN),
                new PokerCard(Suit.DIAMONDS, Value.NINE),
                new PokerCard(Suit.DIAMONDS, Value.JACK));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.HEARTS, Value.QUEEN),
                new PokerCard(Suit.HEARTS, Value.KING),
                new PokerCard(Suit.HEARTS, Value.TEN),
                new PokerCard(Suit.HEARTS, Value.JACK),
                new PokerCard(Suit.HEARTS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(0, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Four_Of_A_Kind() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.DIAMONDS, Value.NINE),
                new PokerCard(Suit.HEARTS, Value.NINE),
                new PokerCard(Suit.SPADES, Value.NINE),
                new PokerCard(Suit.CLUBS, Value.NINE));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.TEN),
                new PokerCard(Suit.CLUBS, Value.FIVE),
                new PokerCard(Suit.CLUBS, Value.TEN),
                new PokerCard(Suit.HEARTS, Value.TEN),
                new PokerCard(Suit.DIAMONDS, Value.TEN));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(-1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Full_House() {
        pokerCards = List.of(
                new PokerCard(Suit.DIAMONDS, Value.TWO),
                new PokerCard(Suit.DIAMONDS, Value.JACK),
                new PokerCard(Suit.HEARTS, Value.TWO),
                new PokerCard(Suit.SPADES, Value.JACK),
                new PokerCard(Suit.CLUBS, Value.TWO));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.DIAMONDS, Value.QUEEN),
                new PokerCard(Suit.SPADES, Value.QUEEN),
                new PokerCard(Suit.SPADES, Value.SIX),
                new PokerCard(Suit.HEARTS, Value.SIX),
                new PokerCard(Suit.DIAMONDS, Value.SIX));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(-1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Flush() {
        pokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.THREE),
                new PokerCard(Suit.SPADES, Value.SIX),
                new PokerCard(Suit.SPADES, Value.NINE),
                new PokerCard(Suit.SPADES, Value.FOUR),
                new PokerCard(Suit.SPADES, Value.SEVEN));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.DIAMONDS, Value.EIGHT),
                new PokerCard(Suit.DIAMONDS, Value.TWO),
                new PokerCard(Suit.DIAMONDS, Value.TEN),
                new PokerCard(Suit.DIAMONDS, Value.JACK),
                new PokerCard(Suit.DIAMONDS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(-1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Stand_In_Rank_Flush() {
        pokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.NINE),
                new PokerCard(Suit.SPADES, Value.JACK),
                new PokerCard(Suit.SPADES, Value.TEN),
                new PokerCard(Suit.SPADES, Value.TWO),
                new PokerCard(Suit.SPADES, Value.EIGHT));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.DIAMONDS, Value.EIGHT),
                new PokerCard(Suit.DIAMONDS, Value.TWO),
                new PokerCard(Suit.DIAMONDS, Value.TEN),
                new PokerCard(Suit.DIAMONDS, Value.JACK),
                new PokerCard(Suit.DIAMONDS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(0, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Straight() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.THREE),
                new PokerCard(Suit.DIAMONDS, Value.SIX),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.FOUR),
                new PokerCard(Suit.CLUBS, Value.SEVEN));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.QUEEN),
                new PokerCard(Suit.CLUBS, Value.KING),
                new PokerCard(Suit.CLUBS, Value.TEN),
                new PokerCard(Suit.HEARTS, Value.JACK),
                new PokerCard(Suit.DIAMONDS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(-1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Stand_In_Rank_Straight() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.THREE),
                new PokerCard(Suit.DIAMONDS, Value.SIX),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.FOUR),
                new PokerCard(Suit.CLUBS, Value.SEVEN));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.SIX),
                new PokerCard(Suit.CLUBS, Value.FIVE),
                new PokerCard(Suit.CLUBS, Value.SEVEN),
                new PokerCard(Suit.HEARTS, Value.FOUR),
                new PokerCard(Suit.DIAMONDS, Value.THREE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(0, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Straight_Again_Straight_With_Low_Ace() {
        PokerHand straightHand = new PokerHand(straight_Cards);
        PokerHand straightWithLowAceHand = new PokerHand(low_Ace_Straight_Cards);
        assertEquals(1, straightHand.compareTo(straightWithLowAceHand));
    }

    @Test
    public void tie_Break_In_Rank_Three_Of_A_Kind() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.THREE),
                new PokerCard(Suit.DIAMONDS, Value.QUEEN),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.QUEEN),
                new PokerCard(Suit.CLUBS, Value.QUEEN));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.NINE),
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.CLUBS, Value.NINE),
                new PokerCard(Suit.HEARTS, Value.FOUR),
                new PokerCard(Suit.DIAMONDS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Two_Pair_with_HigherPair() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.THREE),
                new PokerCard(Suit.DIAMONDS, Value.SIX),
                new PokerCard(Suit.HEARTS, Value.THREE),
                new PokerCard(Suit.SPADES, Value.SEVEN),
                new PokerCard(Suit.CLUBS, Value.SEVEN));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.QUEEN),
                new PokerCard(Suit.CLUBS, Value.FOUR),
                new PokerCard(Suit.CLUBS, Value.QUEEN),
                new PokerCard(Suit.HEARTS, Value.FOUR),
                new PokerCard(Suit.DIAMONDS, Value.THREE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(-1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Two_Pair_with_LowerPair() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.NINE),
                new PokerCard(Suit.DIAMONDS, Value.SIX),
                new PokerCard(Suit.HEARTS, Value.JACK),
                new PokerCard(Suit.SPADES, Value.JACK),
                new PokerCard(Suit.CLUBS, Value.NINE));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.EIGHT),
                new PokerCard(Suit.CLUBS, Value.FOUR),
                new PokerCard(Suit.CLUBS, Value.JACK),
                new PokerCard(Suit.HEARTS, Value.FOUR),
                new PokerCard(Suit.DIAMONDS, Value.JACK));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_Two_Pair_with_Kicker() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.KING),
                new PokerCard(Suit.DIAMONDS, Value.SIX),
                new PokerCard(Suit.HEARTS, Value.KING),
                new PokerCard(Suit.SPADES, Value.JACK),
                new PokerCard(Suit.CLUBS, Value.SIX));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.EIGHT),
                new PokerCard(Suit.SPADES, Value.SIX),
                new PokerCard(Suit.SPADES, Value.KING),
                new PokerCard(Suit.HEARTS, Value.SIX),
                new PokerCard(Suit.DIAMONDS, Value.KING));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Stand_In_Rank_Two_Pair() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.KING),
                new PokerCard(Suit.DIAMONDS, Value.SIX),
                new PokerCard(Suit.HEARTS, Value.KING),
                new PokerCard(Suit.DIAMONDS, Value.JACK),
                new PokerCard(Suit.CLUBS, Value.SIX));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.JACK),
                new PokerCard(Suit.SPADES, Value.SIX),
                new PokerCard(Suit.SPADES, Value.KING),
                new PokerCard(Suit.HEARTS, Value.SIX),
                new PokerCard(Suit.DIAMONDS, Value.KING));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(0, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_One_Pair() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.TWO),
                new PokerCard(Suit.DIAMONDS, Value.QUEEN),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.QUEEN),
                new PokerCard(Suit.CLUBS, Value.THREE));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.FIVE),
                new PokerCard(Suit.HEARTS, Value.QUEEN),
                new PokerCard(Suit.CLUBS, Value.KING),
                new PokerCard(Suit.CLUBS, Value.QUEEN),
                new PokerCard(Suit.DIAMONDS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(-1, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Stand_In_Rank_One_Pair() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.TWO),
                new PokerCard(Suit.DIAMONDS, Value.QUEEN),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.QUEEN),
                new PokerCard(Suit.CLUBS, Value.THREE));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.FIVE),
                new PokerCard(Suit.HEARTS, Value.QUEEN),
                new PokerCard(Suit.DIAMONDS, Value.TWO),
                new PokerCard(Suit.CLUBS, Value.QUEEN),
                new PokerCard(Suit.DIAMONDS, Value.THREE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(0, firstHand.compareTo(secondHand));
    }

    @Test
    public void tie_Break_In_Rank_HighCard() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.DIAMONDS, Value.EIGHT),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.KING),
                new PokerCard(Suit.HEARTS, Value.TWO));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.SPADES, Value.EIGHT),
                new PokerCard(Suit.DIAMONDS, Value.FOUR),
                new PokerCard(Suit.SPADES, Value.TWO),
                new PokerCard(Suit.CLUBS, Value.TWO));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(1, firstHand.compareCardsDirectlyBetweenTwoHands(secondHand));
        assertEquals(-1, secondHand.compareCardsDirectlyBetweenTwoHands(firstHand));
    }

    @Test
    public void tie_Stand_In_Rank_HighCard() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.DIAMONDS, Value.EIGHT),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.KING),
                new PokerCard(Suit.CLUBS, Value.TWO));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.KING),
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.CLUBS, Value.TWO),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.DIAMONDS, Value.EIGHT));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(0, firstHand.compareCardsDirectlyBetweenTwoHands(secondHand));
    }
}