import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerHandEvaluator {

    public static Rank rankCardsInHand(List<PokerCard> pokerCards) {

        boolean isFlush = isFlush(pokerCards);
        boolean isStraight = isStraight(pokerCards);
        Map<Value, Long> valueOccurences = countValueOccurencesInHand(pokerCards);

        Map<Rank, Boolean> rankingMap = new LinkedHashMap<>();
        rankingMap.put(Rank.STRAIGHT_FLUSH, isFlush && isStraight);
        rankingMap.put(Rank.FOUR_OF_A_KIND, valueOccurences.containsValue(4L));
        rankingMap.put(Rank.FLUSH, isFlush);
        rankingMap.put(Rank.STRAIGHT, isStraight);
        rankingMap.put(Rank.THREE_OF_A_KIND, valueOccurences.containsValue(3L));
        rankingMap.put(Rank.ONE_PAIR, valueOccurences.containsValue(2L));

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
            if(orderedRankIndices.get(i) + 1 != orderedRankIndices.get(i + 1)){
                return false;
            }
        }  return true;
    }

    public static Map<Value, Long> countValueOccurencesInHand(List<PokerCard> pokerCards) {
        return pokerCards.stream().collect(Collectors.groupingBy(PokerCard::getValue, Collectors.counting()));
    }
}
