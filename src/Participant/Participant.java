package Participant;

import GameElements.*;

import java.util.ArrayList;
import java.util.Objects;

import static Main.TestApp.cardsDeck;
public abstract class Participant {
    ArrayList<Card> cards;

    public Participant() {
        this.cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addKnownCard(){
        cards.add(cardsDeck.pickCard());
    }

    public boolean isBust(){
        return this.getValueCards() > 21;
    }

    /**
     * Ritorna il valore delle carte che si posseggono;
     * gestisce anche il valore degli assi
     * @return totalValue
     */
    public int getValueCards(){
        int totalValue = 0;
        for(Card card : cards)
            totalValue = totalValue + card.getValue();

       for(int i=0; i < isThereAce() && totalValue > 21; i++)
            totalValue -= 10;

        return totalValue;
    }

    /**  Dice se la mano è un blackjack  */
    public boolean blackjack(){
        return cards.size() == 2 && getValueCards() == 21;                              //se con due carte ho il valore 21 sarà sicuramente blackjack
    }

    private int isThereAce() {
        int numberAces=0;
        for(Card card : this.getCards()){
            if(Objects.equals(card.getRank(), "Ace"))
                numberAces++;
        }
        return numberAces;
    }

    public void clear(){
        cards.clear();
    }
}
