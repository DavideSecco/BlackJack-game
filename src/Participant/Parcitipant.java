package Participant;

import Cards.*;

import java.util.ArrayList;

public abstract class Parcitipant{
    protected Hand hand;

    public Parcitipant(){
        hand = new Hand();
    }

    public void addKnownCard(CardsDeck cardsDeck) {
        hand.addKnownCard(cardsDeck);
    }

    public ArrayList<Card> getHand() {
        return hand.getCards();
    }
}
