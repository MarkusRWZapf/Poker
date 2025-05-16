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
            throw new IllegalArgumentException("You need exactly 5 cards to play!");
        }
        this.cards = cards;
        this.rank = rankCardsInHand(cards);
    }

    @Override
    public int compareTo(PokerHand otherHand) {
        int rankComparison = Integer.compare(this.rank.ordinal(), otherHand.rank.ordinal());
        if (rankComparison != 0) return rankComparison;

        //same rank detected. We need to break the tie.
        return switch (rank) {
            case FOUR_OF_A_KIND -> compareHandsWithMultipleMatches(otherHand, 4);
            case FLUSH, STRAIGHT, STRAIGHT_FLUSH -> compareCardsDirectlyBetweenTwoHands(otherHand);
            default -> compareCardsDirectlyBetweenTwoHands(otherHand);
        };
    }

    public Rank rankCardsInHand(List<PokerCard> pokerCards) {

        boolean isFlush = isFlush(pokerCards);
        boolean isStraight = isStraight(pokerCards);
        Map<Value, Long> valueOccurences = countValueOccurences(pokerCards);

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

    private Map<Value, Long> countValueOccurences(List<PokerCard> pokerCards) {
        return pokerCards.stream().collect(Collectors.groupingBy(PokerCard::getValue, Collectors.counting()));
    }

    private int compareHandsWithMultipleMatches(PokerHand otherHand, int matchCount) {
        Map<Value, Long> thisHandValueCounts = countValueOccurences(cards);
        Map<Value, Long> otherHandValueCounts = countValueOccurences(otherHand.cards);

        List<Value> thisHandRanked = thisHandValueCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == matchCount)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .toList();

        List<Value> otherHandRanked = otherHandValueCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == matchCount)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .toList();

        //Highest remaining card can already break the tie
        int result = Integer.compare(thisHandRanked.getFirst().ordinal(), otherHandRanked.getFirst().ordinal());
        if (result != 0) return result;

        //Remaining cards need to be compared to break the tie
        return compareCardsDirectlyBetweenTwoHands(otherHand);
    }

    public int compareCardsDirectlyBetweenTwoHands(PokerHand otherHand) {
        List<Integer> thisHandValues = cards.stream()
                .map(card -> card.getValue().ordinal())
                .sorted(Comparator.reverseOrder())
                .toList();

        List<Integer> otherHandValues = otherHand.getCards().stream()
                .map(card -> card.getValue().ordinal())
                .sorted(Comparator.reverseOrder())
                .toList();

        int highestCardComparison = Integer.compare(thisHandValues.getFirst(), otherHandValues.getFirst());
        if (highestCardComparison != 0) return highestCardComparison;

        for (int i = 1; i < thisHandValues.size(); i++) {
            int result = Integer.compare(thisHandValues.get(i), otherHandValues.get(i));
            if (result != 0) return result;
        }
        return 0; // Still a tie
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

}
