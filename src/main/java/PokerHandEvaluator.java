import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PokerHandEvaluator {

    public static Rank rankCardsInHand(List<PokerCard> pokerCards) {

        boolean isFlush = isFlush(pokerCards);
        boolean isStraight = isStraight(pokerCards);

        Map<Rank, Boolean> rankingMap = new LinkedHashMap<>();
        rankingMap.put(Rank.STRAIGHT_FLUSH, isFlush && isStraight);
        rankingMap.put(Rank.FLUSH, isFlush);
        rankingMap.put(Rank.STRAIGHT, isStraight);

        return rankingMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(Rank.HIGH_CARD);
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
