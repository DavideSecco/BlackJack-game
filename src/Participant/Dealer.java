package Participant;

import Cards.*;

public class Dealer extends Participant {

    public void hitting(CardsDeck cardsDeck) {
        hand.addKnownCard(cardsDeck);
    }

    public void addUnkonwCard(CardsDeck cardsDeck) {
        hand.addUnknownCard(cardsDeck);
    }
}
