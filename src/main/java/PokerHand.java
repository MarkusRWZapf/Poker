import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        // Same rank detected -> resolve the tie
        return switch (rank) {
            case FOUR_OF_A_KIND -> breakTieBetweenHandsThatHaveMultipleMatches(otherHand, 4);

            // FULL_HOUSE and THREE_OF_A_KIND -> Compare the highest triple value
            case FULL_HOUSE, THREE_OF_A_KIND -> breakTieBetweenHandsThatHaveMultipleMatches(otherHand, 3);

            case TWO_PAIR -> breakTieOnRankTwoPair(otherHand);
            case ONE_PAIR -> breakTieBetweenHandsThatHaveMultipleMatches(otherHand, 2);

            // FLUSH, STRAIGHT, STRAIGHT_FLUSH -> Compare cards directly
            default -> compareCardsDirectlyBetweenTwoHands(otherHand);
        };
    }

    private Rank rankCardsInHand(List<PokerCard> pokerCards) {
        boolean isFlush = isFlush(pokerCards);
        boolean isStraight = isStraight(pokerCards);
        Map<Value, Long> valueOccurrences = countValueOccurrences(pokerCards);

        // LinkedHashMap imposes order for ranks, otherwise E.g. STRAIGHT could be returned for a STRAIGHT_FLUSH
        Map<Rank, Boolean> rankingMap = new LinkedHashMap<>();
        rankingMap.put(Rank.STRAIGHT_FLUSH, isFlush && isStraight);
        rankingMap.put(Rank.FOUR_OF_A_KIND, valueOccurrences.containsValue(4L));
        rankingMap.put(Rank.FULL_HOUSE, valueOccurrences.containsValue(3L) && valueOccurrences.containsValue(2L));
        rankingMap.put(Rank.FLUSH, isFlush);
        rankingMap.put(Rank.STRAIGHT, isStraight);
        rankingMap.put(Rank.THREE_OF_A_KIND, valueOccurrences.containsValue(3L));
        rankingMap.put(Rank.TWO_PAIR, Collections.frequency(new ArrayList<>(valueOccurrences.values()), 2L) == 2);
        rankingMap.put(Rank.ONE_PAIR, valueOccurrences.containsValue(2L));

        return rankingMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(Rank.HIGH_CARD);
    }

    private Map<Value, Long> countValueOccurrences(List<PokerCard> pokerCards) {
        return pokerCards.stream()
                         .collect(Collectors.groupingBy(PokerCard::getValue, Collectors.counting()));
    }

    private int breakTieOnRankTwoPair(PokerHand otherHand) {
        List<Value> thisPairs = getSortedPairsByCount(cards);
        List<Value> otherPairs = getSortedPairsByCount(otherHand.cards);

        int firstPairComparison = Integer.compare(thisPairs.get(0).ordinal(), otherPairs.get(0).ordinal());
        if (firstPairComparison != 0) return firstPairComparison;

        int secondPairComparison = Integer.compare(thisPairs.get(1).ordinal(), otherPairs.get(1).ordinal());
        if (secondPairComparison != 0) return secondPairComparison;

        // Compare remaining High Card
        return compareCardsDirectlyBetweenTwoHands(otherHand);
    }

    private List<Value> getSortedPairsByCount(List<PokerCard> hand) {
        return countValueOccurrences(hand).entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    private int breakTieBetweenHandsThatHaveMultipleMatches(PokerHand otherHand, int matchCount) {
        Map<Value, Long> thisHandCounts = countValueOccurrences(cards);
        Map<Value, Long> otherHandCounts = countValueOccurrences(otherHand.cards);

        List<Value> thisHandRanked = getRankedValuesByMatchCount(thisHandCounts, matchCount);
        List<Value> otherHandRanked = getRankedValuesByMatchCount(otherHandCounts, matchCount);

        // Compare highest-ranked matched values first
        int result = Integer.compare(thisHandRanked.getFirst().ordinal(), otherHandRanked.getFirst().ordinal());
        if (result != 0) return result;

        // Compare all cards to resolve the tie
        return compareCardsDirectlyBetweenTwoHands(otherHand);
    }

    private List<Value> getRankedValuesByMatchCount(Map<Value, Long> handCounts, int matchCount) {
        return handCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == matchCount)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .toList();
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

        thisHandValues = adjustForTieBreakInvolvingLowAceStraight(thisHandValues);
        otherHandValues = adjustForTieBreakInvolvingLowAceStraight(otherHandValues);

        for (int i = 0; i < thisHandValues.size(); i++) {
            int comparison = Integer.compare(thisHandValues.get(i), otherHandValues.get(i));
            if (comparison != 0) return comparison;
        }
        return 0; // Genuine tie
    }

    private boolean isFlush (List<PokerCard> pokerCards) {
        long uniqueSuitCount = pokerCards.stream().map(PokerCard::getSuit).distinct().count();
        return uniqueSuitCount == 1;
    }

    private boolean isStraight(List<PokerCard> pokerCards) {
        List<Integer> sortedValues = pokerCards.stream()
                                               .map(pokerCard -> pokerCard.getValue().ordinal())
                                               .sorted()
                                               .toList();
        if(isLowAceStraight(sortedValues)) return true;

        return IntStream.range(0, sortedValues.size() - 1)
                        .allMatch(i -> sortedValues.get(i) + 1 == sortedValues.get(i + 1));
    }

    private List<Integer> adjustForTieBreakInvolvingLowAceStraight(List<Integer> handValues) {
        if (isLowAceStraight(handValues)) {
            return List.of(3, 2, 1, 0, -1); // Adjusting Ace to be lowest, the hand with these values loses the tiebreak
        }
        return handValues; // No change if not a Low Ace Straight
    }

    private boolean isLowAceStraight(List<Integer> handValues) {
        List<Integer> ascendingOrdinalValuesOfLowAceStraight = List.of(0,1,2,3,12);
        List<Integer> descendingOrdinalValuesOfLowAceStraight = List.of(12,3,2,1,0);
        return handValues.equals(ascendingOrdinalValuesOfLowAceStraight)
                || handValues.equals(descendingOrdinalValuesOfLowAceStraight);
    }

}
