import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    private ArrayList<Card> cards;

    public void addCard(CardsDeck cardsDeck){
        cards.add(cardsDeck.pickCard());
    }

    public int getTotalValue(){
        int totalValue = 0;
        for(Card card : cards){
            totalValue = totalValue + card.getValue();
        }
        return totalValue;
    }
}
