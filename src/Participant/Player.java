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

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    public void bet(int amount){
        if(amount <= this.account){
            this.bet = this.bet + amount;
            this.account = this.account - amount;
        }
    }

    public void addToAccount(int amount){
        this.account = this.account + amount;
    }

    public void clear(){
        this.cards.clear();
        this.bet = 0;
    }

}
