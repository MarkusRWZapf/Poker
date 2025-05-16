import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class PokerHand implements Comparable<PokerHand> {
    private final List<PokerCard> cards;

    private Rank rank;

    public PokerHand(List<PokerCard> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("You need 5 cards to play!");
        }
        this.cards = cards;
        this.rank = rankCardsInHand(cards);
    }

    @Override
    public int compareTo(PokerHand other) {
        int rankComparison = Integer.compare(this.rank.ordinal(), other.rank.ordinal());
        if (rankComparison != 0) return rankComparison;

        return compareHighCardsWithTheOnesInAnotherHand(other);
    }

    public Rank rankCardsInHand(List<PokerCard> pokerCards) {

        boolean isFlush = isFlush(pokerCards);
        boolean isStraight = isStraight(pokerCards);
        Map<Value, Long> valueOccurences = countValueOccurencesInHand(pokerCards);

        Map<Rank, Boolean> rankingMap = new LinkedHashMap<>();
        rankingMap.put(Rank.STRAIGHT_FLUSH, isFlush && isStraight);
        rankingMap.put(Rank.FOUR_OF_A_KIND, valueOccurences.containsValue(4L));
        rankingMap.put(Rank.FULL_HOUSE, valueOccurences.containsValue(3L) && valueOccurences.containsValue(2L));
        rankingMap.put(Rank.FLUSH, isFlush);
        rankingMap.put(Rank.STRAIGHT, isStraight);
        rankingMap.put(Rank.THREE_OF_A_KIND, valueOccurences.containsValue(3L));
        rankingMap.put(Rank.TWO_PAIR, Collections.frequency(new ArrayList<>(valueOccurences.values()), 2L) == 2);
        rankingMap.put(Rank.ONE_PAIR, valueOccurences.containsValue(2L));

        return rankingMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(Rank.HIGH_CARD);
    }

    private boolean isFlush (List<PokerCard> pokerCards) {
        return pokerCards.stream().map(PokerCard::getSuit).distinct().count() == 1;
    }

    private boolean isStraight(List<PokerCard> pokerCards) {
        List<Integer> orderedRankIndices = pokerCards.stream()
                .map(pokerCard -> pokerCard.getValue().ordinal()).sorted().toList();
        for(int i = 0; i < orderedRankIndices.size() - 1; i++){
            if(orderedRankIndices.get(i) + 1 != orderedRankIndices.get(i + 1)){
                return false;
            }
        }  return true;
    }

    private Map<Value, Long> countValueOccurencesInHand(List<PokerCard> pokerCards) {
        return pokerCards.stream().collect(Collectors.groupingBy(PokerCard::getValue, Collectors.counting()));
    }

    public int compareHighCardsWithTheOnesInAnotherHand(PokerHand otherHand) {
        List<Integer> thisHandValues = cards.stream().map(card -> card.value.ordinal()).sorted(Comparator.reverseOrder()).toList();
        List<Integer> otherHandValues = otherHand.getCards().stream().map(card -> card.value.ordinal()).sorted(Comparator.reverseOrder()).toList();

        for (int i = 0; i < thisHandValues.size(); i++) {
            int result = Integer.compare(thisHandValues.get(i), otherHandValues.get(i));
            if (result != 0) return result;
        }
        return 0; // still a tie

    }

}
