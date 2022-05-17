package Code.Participant;

import Code.GameElements.Card;
import Code.TestApp;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Participant {
    ArrayList<Card> cards;

    public Participant() {
        this.cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addKnownCard(ArrayList<Card> hand){
        hand.add(TestApp.cardsDeck.pickCard());
    }

    public boolean isBust(){
        return this.getValueCards(cards) > 21;
    }

    public void takeCard(ArrayList<Card> hand, Card card){
        hand.add(card);
    }

    public void removeCard(ArrayList<Card> hand, int index){
        hand.remove(index);
    }

    /**
     * Ritorna il valore delle carte che si posseggono;
     * gestisce anche il valore degli assi
     * @return totalValue
     */
    public int getValueCards(ArrayList<Card> hand){
        int totalValue = 0;
        for(Card card : hand)
            totalValue = totalValue + card.getValue();

       for(int i=0; i < isThereAce() && totalValue > 21; i++)
            totalValue -= 10;

        return totalValue;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**  Dice se la mano è un blackjack  */
    public boolean blackjack(){
        return cards.size() == 2 && getValueCards(cards) == 21;                              //se con due carte ho il valore 21 sarà sicuramente blackjack
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
