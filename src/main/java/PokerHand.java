import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PokerHand {
    private final List<PokerCard> cards;

    private Rank rank;

    public PokerHand(List<PokerCard> cards, Rank rank) {
        this.cards = cards;
        this.rank = rank;
    }

}
