package GameElements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

/**
 * suit --> seme della carta in formato stringa
 *
 * rank --> valore nominale della carta in formato stringa ("Ace", ... jack", "queen", "king")
 *
 * value --> valore della carta nel gioco del black Jack (Ace --> 11)
 */

public class Card {
    private final String suit;
    private final String rank;
    private int value;
    private boolean known;
    private Image img;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        this.known = true;
        try{
            this.img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/CardsDeck1/" + getRank() + getSuit() + ".png")));
        } catch (Exception e){
            System.out.println("Non ho trovato l'immagine di " + getRank() + " " + getSuit());
        }
    }

    public void changeAceValue(){ value = 1; }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public Boolean getKnown() {return known; }

    public int getValue() {
        return value;
    }

    public Image getImg() {
        return img;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) { this.known = known; }

    @Override
    public String toString() {
        return "Cards.Card{" +
                "suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                ", value=" + value +
                ", known=" + known +
                '}';
    }
}
