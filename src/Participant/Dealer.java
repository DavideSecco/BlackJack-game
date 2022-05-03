package Participant;

import Cards.*;

import static Main.TestApp.player;

public class Dealer extends Participant {
    public Dealer(){
        super();
    }

    public void play(CardsDeck cardsDeck){
        while(this.getValueCards()<17 && this.getValueCards() < player.getValueCards()){          //il dealer non pesca se ha un valore maggiore di 16
            this.addKnownCard();
        }
    }

    public void addUnkonwCard(CardsDeck cardsDeck) {
        Card card = cardsDeck.pickCard();
        card.setKnown(false);
        cards.add(card);
    }

    public void discoverAll(){
        for(Card card : this.cards)
            card.setKnown(true);
    }
}
