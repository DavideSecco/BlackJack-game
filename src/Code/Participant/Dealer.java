package Code.Participant;

import Code.GameElements.Card;
import Code.GameElements.CardsDeck;

import static Code.TestApp.cardsDeck;

public class Dealer extends Participant {
    public Dealer(){
        super();
    }

    public void play(CardsDeck cardsDeck){
        while(this.getValueCards()<17){          //il dealer non pesca se ha un valore maggiore di 16
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
