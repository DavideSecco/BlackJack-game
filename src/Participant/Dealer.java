package Participant;

import Cards.*;

public class Dealer extends Parcitipant {
    public void addUnkonwCard(CardsDeck cardsDeck) {
        hand.addUnknownCard(cardsDeck);
    }
}
