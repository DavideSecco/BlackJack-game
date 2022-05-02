package Participant;

import Cards.*;

import java.util.ArrayList;

public abstract class Participant {
    protected Hand hand;

    public int getValueHand(){ return hand.getTotalValue(); }

    public abstract void hitting(CardsDeck cardsDeck);

    public Participant(){
        hand = new Hand();
    }

    public void addKnownCard(CardsDeck cardsDeck) {
        hand.addKnownCard(cardsDeck);
    }

    public ArrayList<Card> getHand() {
        return hand.getCards();
    }
}
