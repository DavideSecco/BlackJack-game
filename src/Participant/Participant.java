package Participant;

import Cards.*;

import java.util.ArrayList;

import static Main.TestApp.cardsDeck;

public abstract class Participant {
    ArrayList<Card> cards;

    public Participant() {
        this.cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addKnownCard(){
        cards.add(cardsDeck.pickCard());
    }

    public boolean isBust(){
        if(this.getValueCards() > 21)
            return true;
        else
            return false;
    }

    public int getValueCards(){
        int totalValue = 0;
        for(Card card : cards)
            totalValue = totalValue + card.getValue();

        if(isThereAce() && totalValue >= 21)
            return totalValue - 10;
        else
            return totalValue;
    }

    private boolean isThereAce() {
        for(Card card : this.getCards()){
            if(card.getRank()=="Ace")
                return true;
        }
        return false;
    }


}
