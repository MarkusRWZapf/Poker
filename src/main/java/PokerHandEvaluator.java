import java.util.List;

public class PokerHandEvaluator {

    public static Rank rankCardsInHand(List<PokerCard> pokerCards) {

        boolean isFlush = isFlush(pokerCards);
        boolean isStraight = isStraight(pokerCards);

        if(isFlush){
            return Rank.FLUSH;
        } else if(isStraight){
            return Rank.STRAIGHT;
        }
        return Rank.HIGH_CARD;
    }

    public static boolean isFlush (List<PokerCard> pokerCards) {
        return pokerCards.stream().map(PokerCard::getSuit).distinct().count() == 1;
    }

    public static boolean isStraight(List<PokerCard> pokerCards) {
        List<Integer> orderedRankIndices = pokerCards.stream()
                .map(pokerCard -> pokerCard.getValue().ordinal()).sorted().toList();
        for(int i = 0; i < orderedRankIndices.size() - 1; i++){
            if(orderedRankIndices.get(i) + 1 != orderedRankIndices.get(i+1)){
                return false;
            }
        }  return true;
    }
}
