package Code.GameElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CardsDeck {
    private static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static final int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    private final ArrayList<Card> cards;
    private final Iterator iterator;

    public CardsDeck(){
        cards = new ArrayList<>();
        for (int i = 0; i<ranks.length; i++) {
            for(int j=0; j<suits.length; j++){
                this.cards.add(new Card(suits[j],ranks[i], values[i]));
            }
        }
        //Shuffle after the creation
        Collections.shuffle(this.cards);
        iterator = cards.iterator();

    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Eliminato il try e catch;
     * La funzione quando veniva chiamata lanciava subito l'eccezione, adesso non c'è più questo problema
     * e funziona correttamente;
    */
    public Card pickCard(){ return (Card)iterator.next(); }
}
