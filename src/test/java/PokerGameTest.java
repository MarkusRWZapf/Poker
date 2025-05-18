import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokerGameTest {
    List<PokerCard> handWithTwoSpadeKings = List.of(
            new PokerCard(Suit.CLUBS, Value.THREE),
            new PokerCard(Suit.SPADES, Value.KING),
            new PokerCard(Suit.SPADES, Value.FOUR),
            new PokerCard(Suit.SPADES, Value.KING),
            new PokerCard(Suit.DIAMONDS, Value.FIVE)
    );

    List<PokerCard> validHand1_With_A_Clubs_Queen = List.of(
            new PokerCard(Suit.HEARTS, Value.TWO),
            new PokerCard(Suit.DIAMONDS, Value.TEN),
            new PokerCard(Suit.CLUBS, Value.QUEEN),
            new PokerCard(Suit.HEARTS, Value.NINE),
            new PokerCard(Suit.DIAMONDS, Value.SEVEN)
    );

    List<PokerCard> getValidHand1_With_A_Clubs_Queen = List.of(
            new PokerCard(Suit.SPADES, Value.TEN),
            new PokerCard(Suit.HEARTS, Value.JACK),
            new PokerCard(Suit.CLUBS, Value.QUEEN),
            new PokerCard(Suit.HEARTS, Value.TEN),
            new PokerCard(Suit.DIAMONDS, Value.FIVE)
    );

    List<PokerCard> validHand1 = List.of(
            new PokerCard(Suit.HEARTS, Value.FOUR),
            new PokerCard(Suit.DIAMONDS, Value.ACE),
            new PokerCard(Suit.SPADES, Value.EIGHT),
            new PokerCard(Suit.HEARTS, Value.KING),
            new PokerCard(Suit.DIAMONDS, Value.FIVE)
    );

    List<PokerCard> validHand2 = List.of(
            new PokerCard(Suit.SPADES, Value.TEN),
            new PokerCard(Suit.HEARTS, Value.JACK),
            new PokerCard(Suit.HEARTS, Value.QUEEN),
            new PokerCard(Suit.HEARTS, Value.TEN),
            new PokerCard(Suit.DIAMONDS, Value.FOUR)
    );

    @Test
    public void detectDuplicatesAmongTwoHands() {
        assertThrows(IllegalArgumentException.class, () -> PokerGame.checkBothListsOfCardsForDuplicates(handWithTwoSpadeKings, validHand1));
        assertThrows(IllegalArgumentException.class, () -> PokerGame.checkBothListsOfCardsForDuplicates(validHand2, handWithTwoSpadeKings));
        assertThrows(IllegalArgumentException.class, () -> PokerGame.checkBothListsOfCardsForDuplicates(validHand1_With_A_Clubs_Queen, getValidHand1_With_A_Clubs_Queen));
        assertDoesNotThrow(() -> PokerGame.checkBothListsOfCardsForDuplicates(validHand1, validHand2));
    }
}
