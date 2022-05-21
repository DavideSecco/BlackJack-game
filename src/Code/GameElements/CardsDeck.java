package Code.GameElements;

import Code.Panels.Game.GamePanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CardsDeck {
    private static final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static final int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    private static ArrayList<Card> cards;
    private static Iterator iterator;

    public CardsDeck(int index){
        cards = new ArrayList<>();
        if(index == 0){
            for (int i = 0; i<ranks.length; i++) {
                for(int j=0; j<suits.length; j++){
                    this.cards.add(new Card(suits[j],ranks[i], values[i]));
                }
            }
            //Shuffle after the creation
            Collections.shuffle(this.cards);

        } else if (index == 1) {
            Card AS = new Card("Spades","Ace",11);
            Card KH = new Card("Hearts","King",10);
            Card e = new Card("Spades","9",9);
            Card w = new Card("Hearts","9",9);
            Card r = new Card("Hearts", "4", 4);
            cards.add(AS);
            cards.add(KH);
            cards.add(w);
            cards.add(e);
            cards.add(r);
        } else if (index == 2) {
            Card AS = new Card("Spades","Ace",11);
            Card AH = new Card("Hearts","Ace",11);
            Card e = new Card("Spades","9",9);
            Card w = new Card("Hearts","9",9);
            Card KH = new Card("Hearts","King",10);
            Card KS = new Card("Spades","King",10);
            cards.add(AS);
            cards.add(AH);
            cards.add(w);
            cards.add(e);
            cards.add(KS);
            cards.add(KH);
        }

        iterator = cards.iterator();
    }

    public void inizialize(){
        Collections.shuffle(this.cards);
        this.iterator = cards.iterator();
        System.out.println("Il prossimo è: " + iterator.next().toString());
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
