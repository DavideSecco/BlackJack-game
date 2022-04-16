import java.util.ArrayList;
import java.util.Collections;

public class CardsDeck {
    private static final String suits[] = {"club", "diamond", "heart", "spade"};
    private static final String ranks[] = {"ace", "deuce", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
    private static final int values[] = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    private final ArrayList<Card> cards;

    public CardsDeck(){
        cards = new ArrayList<Card>();
        for (int i = 0; i<suits.length; i++) {
            for(int j=0; j<ranks.length; j++){
                this.cards.add(new Card(suits[i],ranks[j], values[j]));
            }
        }
        //Shuffle after the creation
        Collections.shuffle(this.cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
