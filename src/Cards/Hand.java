package Cards;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(){
        cards = new ArrayList<Card>();
    }

    public void addKnownCard(CardsDeck cardsDeck){
        cards.add(cardsDeck.pickCard());
    }

    public void addUnknownCard(CardsDeck cardsDeck){
        Card card = cardsDeck.pickCard();
        card.setKnown(false);
        cards.add(card);
    }

    public int getTotalValue(){
        int totalValue = 0;
        for(Card card : cards){
            totalValue = totalValue + card.getValue();
        }
        return totalValue;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
