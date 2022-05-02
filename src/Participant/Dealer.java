package Participant;

import Cards.*;
import Main.TestApp;

public class Dealer extends Participant {

    public void hitting(CardsDeck cardsDeck) {
        hand.addKnownCard(cardsDeck);
    }

    public void play(CardsDeck cardsDeck){

        hand.getCards().get(0).setKnown(true);   //scopro la carta scoperta

        while(hand.getTotalValue()<17 && hand.getTotalValue() < TestApp.player.getValueHand()){          //il dealer non pesca se ha un valore maggiore di 16
            hand.addKnownCard(cardsDeck);
        }

        //semplice confronto per capire chi ha vinto

        if((hand.getTotalValue() < TestApp.player.getValueHand() && hand.getTotalValue() < 22) || hand.getTotalValue() > 21) TestApp.win=true;
        else TestApp.win=false;

    }

    public void addUnkonwCard(CardsDeck cardsDeck) {
        hand.addUnknownCard(cardsDeck);
    }
}
