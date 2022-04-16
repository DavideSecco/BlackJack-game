/**
 * suit -->
 * rank --> valore nominale della carta in formato stringa ("Ace", ... jack", "queen", "king")
 * value --> valore della carta nel gioco del black Jack (Ace --> 11)
 */

public class Card {
    private final String suit;
    private final String rank;
    private final int value;

    public Card(String suit, String rank, int value){
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                ", value=" + value +
                '}';
    }
}
