package Participant;

import Cards.*;
import Main.TestApp;

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
}
