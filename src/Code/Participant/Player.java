package Code.Participant;

import Code.Panels.Game.DisplayPanel.BetPanel;

public class Player extends Participant {
    private String name;
    private int wins;
    private int account;     // potrebbe andare anche in participant ?

    private String password;
    private int bet = 0;

    public Player(){
        super();
        this.name = "Anonymous";
        this.wins = 0;
        this.account = 1000;
        this.password = "123";
    }

    public Player(String name){
        super();
        this.name = name;
        this.wins = 0;
        this.account = 1000;
        this.password = "123";
    }

    public Player(String name, String password){
        super();
        this.name = name;
        this.wins = 0;
        this.account = 1000;
        this.password = password;
    }

    public Player(String name, int wins, int account, String password){
        super();
        this.name = name;
        this.wins = wins;
        this.account = account;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBet() {
        return bet;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) { this.account = account; }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getWins() {
        return wins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

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

    public void incrementWins() {
        this.wins++;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
