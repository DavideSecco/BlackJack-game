package Participant;

import Cards.*;

import java.util.ArrayList;

import static Main.TestApp.player;

public abstract class Participant {
    protected Hand hand;

    protected boolean bust = false;

    public int getValueHand(){ return hand.getTotalValue(); }

    public void changeValue(){ hand.changeAce(); }

    public void checkHand(){
        if(hand.getTotalValue() > 21){
            bust=true;
        }
    }
    public void hitting(CardsDeck cardsDeck) {

        hand.addKnownCard(cardsDeck);

        for(Card card : hand.getCards()){
            if(card.getRank()=="Ace" && hand.getTotalValue()>21){
                card.changeAceValue();
            }
        }


    }

    public Participant(){
        hand = new Hand();
    }

    public boolean getBust(){ return bust; }

    public ArrayList<Card> getHand() {
        return hand.getCards();
    }
}
