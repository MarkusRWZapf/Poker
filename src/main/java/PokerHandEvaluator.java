import java.util.List;

public class PokerHandEvaluator {

    public static Rank rankCardsInHand(List<PokerCard> pokerCards) {

        if(isFlush(pokerCards)){
            return Rank.FLUSH;
        } else if(isStraight(pokerCards)){
            return Rank.STRAIGHT;
        }
        return Rank.HIGH_CARD;
    }

    public static boolean isFlush (List<PokerCard> pokerCards) {
        return pokerCards.stream().map(PokerCard::getSuit).distinct().count() == 1;
    }

    public static boolean isStraight(List<PokerCard> pokerCards) {
        List<Integer> sortedOrdinalRanks = pokerCards.stream()
                .map(pokerCard -> pokerCard.getValue().ordinal()).sorted().toList();
        for(int i = 0; i < sortedOrdinalRanks.size() - 1; i++){
            if(sortedOrdinalRanks.get(i) + 1 != sortedOrdinalRanks.get(i+1)){
                return false;
            }
        }  return true;
    }
}
