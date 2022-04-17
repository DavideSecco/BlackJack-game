import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Da fare:
 * - verificare l'uso di quel iteratore
 * - capire quale sia la giusta eccezione
 */

public class CardsDeck {
    private static final String[] suits = {"club", "diamond", "heart", "spade"};
    private static final String[] ranks = {"ace", "deuce", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
    private static final int[] values = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    private final ArrayList<Card> cards;
    private Iterator iterator;

    public CardsDeck(){
        cards = new ArrayList<>();
        for (int i = 0; i<suits.length; i++) {
            for(int j=0; j<ranks.length; j++){
                this.cards.add(new Card(suits[i],ranks[j], values[j]));
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
     * DA TESTARE e probabilmente esiste una implementazione più semplice
     * Probabilemnte hai usato anche l'eccezione sbagliata
    */
    public Card pickCard(){
        try {
            return cards.get((Integer) iterator.next());
        } catch (Exception e){
            throw new RuntimeException("il mazzo è finito");
        }
    }
}
