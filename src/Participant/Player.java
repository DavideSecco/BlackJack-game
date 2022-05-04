package Participant;

public class Player extends Participant {
    private String name;
    private int wins;
    private int account;     // potrebbe andare anche in participant ?
    private int bet = 0;


    public Player(){
        super();
        this.name = "Pippo";
        this.wins = 0;
        this.account = 1000;
    }

    public int getBet() {
        return bet;
    }


    //non è un vero setter, perchè aggiunge le fiche alla puntata che si vuole ottenere

    public void setBet(int bet) {
        this.bet += bet;
    }


    //stessa cosa di sopra, toglie i soldi scommessi dal conto

    public void setAccount(int bet) {
        this.account -= bet;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getAccount() {
        return account;
    }
}
