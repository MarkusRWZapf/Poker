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
    public void evaluates_Straight() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.EIGHT),
                new PokerCard(Suit.DIAMONDS, Value.QUEEN),
                new PokerCard(Suit.SPADES, Value.TEN),
                new PokerCard(Suit.SPADES, Value.JACK),
                new PokerCard(Suit.SPADES, Value.NINE));
        PokerHand straightHand = new PokerHand(pokerCards);
        assertEquals(Rank.STRAIGHT, straightHand.getRank());
    }

    @Test
    public void evaluates_StraightFlush() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.JACK),
                new PokerCard(Suit.CLUBS, Value.NINE),
                new PokerCard(Suit.CLUBS, Value.TEN),
                new PokerCard(Suit.CLUBS, Value.EIGHT),
                new PokerCard(Suit.CLUBS, Value.SEVEN));
        PokerHand straightFlushHand = new PokerHand(pokerCards);
        assertEquals(Rank.STRAIGHT_FLUSH, straightFlushHand.getRank());
    }

    @Test
    public void evaluates_Four_Of_A_Kind() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.JACK),
                new PokerCard(Suit.DIAMONDS, Value.NINE),
                new PokerCard(Suit.HEARTS, Value.NINE),
                new PokerCard(Suit.SPADES, Value.NINE),
                new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand fourOfAKindHand = new PokerHand(pokerCards);
        assertEquals(Rank.FOUR_OF_A_KIND, fourOfAKindHand.getRank());
    }

    @Test
    public void evaluates_Three_Of_A_Kind() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.JACK),
                new PokerCard(Suit.DIAMONDS, Value.THREE),
                new PokerCard(Suit.HEARTS, Value.JACK),
                new PokerCard(Suit.SPADES, Value.JACK),
                new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand threeOfAKindHand = new PokerHand(pokerCards);
        assertEquals(Rank.THREE_OF_A_KIND, threeOfAKindHand.getRank());
    }

    @Test
    public void evaluates_One_Pair() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.QUEEN),
                new PokerCard(Suit.DIAMONDS, Value.THREE),
                new PokerCard(Suit.HEARTS, Value.SEVEN),
                new PokerCard(Suit.SPADES, Value.SEVEN),
                new PokerCard(Suit.CLUBS, Value.NINE));
        PokerHand pairHand = new PokerHand(pokerCards);
        assertEquals(Rank.ONE_PAIR, pairHand.getRank());
    }

    @Test
    public void evaluates_Two_Pair() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.QUEEN),
                new PokerCard(Suit.DIAMONDS, Value.THREE),
                new PokerCard(Suit.HEARTS, Value.QUEEN),
                new PokerCard(Suit.SPADES, Value.TWO),
                new PokerCard(Suit.CLUBS, Value.TWO));
        PokerHand twoPairHand = new PokerHand(pokerCards);
        assertEquals(Rank.TWO_PAIR, twoPairHand.getRank());
    }

    @Test
    public void evaluates_Full_House() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.THREE),
                new PokerCard(Suit.DIAMONDS, Value.THREE),
                new PokerCard(Suit.HEARTS, Value.SEVEN),
                new PokerCard(Suit.SPADES, Value.SEVEN),
                new PokerCard(Suit.CLUBS, Value.SEVEN));
        PokerHand fullHouseHand = new PokerHand(pokerCards);
        assertEquals(Rank.FULL_HOUSE, fullHouseHand.getRank());
    }

    @Test
    public void evaluates_High_Card() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.DIAMONDS, Value.EIGHT),
                new PokerCard(Suit.HEARTS, Value.FIVE),
                new PokerCard(Suit.SPADES, Value.KING),
                new PokerCard(Suit.CLUBS, Value.TWO));
        PokerHand highCardHand = new PokerHand(pokerCards);
        assertEquals(Rank.HIGH_CARD, highCardHand.getRank());
    }

    @Test
    public void evaluates_HighCardAgainstAnotherHand_ClearWinner() {
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
    public void evaluatesHighCardAgainstAnotherHand_EndsInATie() {
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

    @Test
    public void comparesTwoPokerHandsWithTheFirstOneTheWinner() {
        pokerCards = List.of(
                new PokerCard(Suit.CLUBS, Value.THREE),
                new PokerCard(Suit.DIAMONDS, Value.QUEEN),
                new PokerCard(Suit.HEARTS, Value.QUEEN),
                new PokerCard(Suit.SPADES, Value.QUEEN),
                new PokerCard(Suit.CLUBS, Value.QUEEN));

        List<PokerCard> otherPokerCards = List.of(
                new PokerCard(Suit.SPADES, Value.NINE),
                new PokerCard(Suit.CLUBS, Value.ACE),
                new PokerCard(Suit.CLUBS, Value.NINE),
                new PokerCard(Suit.HEARTS, Value.ACE),
                new PokerCard(Suit.DIAMONDS, Value.NINE));

        PokerHand firstHand = new PokerHand(pokerCards);
        PokerHand secondHand = new PokerHand(otherPokerCards);
        assertEquals(1, firstHand.compareTo(secondHand));
    }

    //Tiebreaker Tests

    @Test
    public void TieBreakInRank_StraightFlush() {
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
    public void TieStandInRank_StraightFlush() {
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
    public void TieBreakInRank_Four_Of_A_Kind() {
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
    public void TieBreakInRank_Three_Of_A_Kind() {
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
    public void TieBreakInRank_One_Pair() {
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
    public void TieStandInRank_One_Pair() {
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
    public void TieBreakInRank_Flush() {
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
    public void TieBreakInRank_Straight() {
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
    public void TieStandInRank_Straight() {
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
    public void TieBreakInRank_Two_Pair_with_HigherPair() {
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
    public void TieBreakInRank_Two_Pair_with_LowerPair() {
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
    public void TieBreakInRank_Two_Pair_with_Kicker() {
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
    public void TieStandInRank_Two_Pair() {
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
    public void TieBreakInRank_Full_House() {
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


}
