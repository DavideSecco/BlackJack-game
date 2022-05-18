package Code.Participant;

import Code.GameElements.Card;
import Code.Panels.Game.DisplayPanel.BetPanel;

import java.util.ArrayList;

public class Player extends Participant {

    private ArrayList<Card> splittedCards = new ArrayList<Card>();

    private int splittedBet;
    private String name;
    private int wins;

    private int games;
    private int account;     // potrebbe andare anche in participant ?

    private String password;
    private int bet = 0;

    /** Constructors */

    public Player(){
        super();
        this.name = "Anonymous";
        this.wins = 0;
        this.account = 1000;
        this.password = "123";
        this.games = 0;
    }

    public Player(String name){
        super();
        this.name = name;
        this.wins = 0;
        this.account = 1000;
        this.password = "123";
        this.games = 0;
    }

    public Player(String name, String password){
        super();
        this.name = name;
        this.wins = 0;
        this.account = 1000;
        this.password = password;
        this.games = 0;
    }

    public Player(String name, int wins, int account, String password, int games){
        super();
        this.name = name;
        this.wins = wins;
        this.account = account;
        this.password = password;
        this.games = games;
    }

    /** My functions */

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
        this.splittedCards.clear();
        this.splittedBet = 0;
        this.bet = 0;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementGames() {this.games++;}

    /** Getter & Setter */

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
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

    public void swapSplittedElements(){
        ArrayList<Card> tmpList = new ArrayList<Card>(cards);
        int tmp;
        cards.clear();
        cards.addAll(splittedCards);
        splittedCards.clear();
        splittedCards.addAll(tmpList);
        tmp = bet;
        bet = splittedBet;
        splittedBet = tmp;
    }

    public void createSplitHand(){
        splittedCards.add(cards.get(1));
        cards.remove(1);
        addKnownCard();
        swapSplittedElements();
        addKnownCard();
        swapSplittedElements();
    }

    public ArrayList<Card> getSplittedCards() {
        return splittedCards;
    }
}
