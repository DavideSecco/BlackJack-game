package Participant;

import Cards.*;

public class Player extends Participant {
    private String name;
    private int wins;
    private int fiches;     // potrebbe andare anche in participant ?

    public Player(){
        super();
        this.name = "Pippo";
        this.wins = 0;
        this.fiches = 1000;
    }


    public void hitting(CardsDeck cardsDeck) {
        hand.addKnownCard(cardsDeck);
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", name='" + name + '\'' +
                ", wins=" + wins +
                ", fiches=" + fiches +
                '}';
    }
}
