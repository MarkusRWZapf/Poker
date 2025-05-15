import java.util.List;

public class PokerHandEvaluator {

    public static Rank rankCardsInHand(List<PokerCard> pokerCards) {

        if(isFlush(pokerCards)){
            return Rank.FLUSH;
        }
        return Rank.HIGH_CARD;
    }

    public static boolean isFlush (List<PokerCard> pokerCards) {
        return pokerCards.stream().map(PokerCard::getSuit).distinct().count() == 1;
    }
}
