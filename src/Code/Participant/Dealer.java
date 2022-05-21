package Code.Participant;

import Code.GameElements.Card;

import static Code.TestApp.*;

public class Dealer extends Participant {
    public Dealer(){
        super();
    }

    /**
     * Logica di gioco del dealer, si ferma quando:
     * 1) Ha superato/(pareggiato(?)) il player
     * 2) il player ha sballato
     *
     * altrimenti deve pescare sempre (o sbaglio?)
     */
    public void play(){
        while(true) {
            if(player.getValueCards() <= dealer.getValueCards())
                break;

            if(player.isBust())
                break;

            this.addKnownCard();
        }
    }

    public void addUnkonwCard() {
        Card card = cardsDeck.pickCard();
        card.setKnown(false);
        cards.add(card);
    }

    public void discoverAll(){
        for(Card card : this.cards)
            card.setKnown(true);
    }
}
