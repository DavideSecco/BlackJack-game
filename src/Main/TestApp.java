package Main;
import GameElements.CardsDeck;
import Panels.Game.GameFrame;
import Panels.Menu.MenuFrame;
import Participant.Dealer;
import Participant.Player;

import java.awt.*;
import java.io.IOException;

public class TestApp {
    public static Player player = new Player();
    public static Dealer dealer = new Dealer();
    public static CardsDeck cardsDeck = new CardsDeck();

    public static Dimension gameDimension = new Dimension(1200, 700);
    public static Dimension menuDimension = new Dimension(400, 700);

    public static MenuFrame menuFrame;

    public static void main(String[] args) throws IOException {
        System.out.println(player.getValueCards());

        menuFrame = new MenuFrame();
    }

    /**
     * 1 --> player ha vinto
     * 0 --> pareggio
     * -1 --> dealer ha vinto
     */

    public static int whoWon(){
        if(player.isBust())                                     // se il player ha sballato --> banco vince
            return -1;
        if(dealer.isBust())                                     // se il player non ha sballato, e il banco si --> player vince
            return 1;

        // penso sia autoesplicativo
        if(player.getValueCards() > dealer.getValueCards())
            return 1;
        else if (player.getValueCards() == dealer.getValueCards())
            return 0;
        else
            return -1;
    }

    //Non so se questa funzione vada qui nella TestApp
    //                    |
    //                    |
    //                    |
    //                    V

    /**
     * se il giocatore vince gli da i soldi della vincita nel conto più quelli scommessi
     * se pareggia gli ridà solo quelli scommessi
     * infine riporta a zero la scommessa per le prossime partite
     */

    public static void dispenserMoney(){
        if(whoWon() == 1) {                                   // player ha vinto
            player.addToAccount(2 * player.getBet());

            if (checkBlackjack())                               //se ha fatto blackjack si aggiunge la metà della bet
                player.addToAccount(player.getBet() / 2);

        }else if(whoWon() == 0)                              // player ha pareggiato
            player.addToAccount(player.getBet());
        else                                                // player ha perso
            player.addToAccount(0);
    }

    /**
     * Crea l'inizio del gioco, ovvero distribuisce le carte iniziali del player e del dealer
     */

    public static void inizio(){
        player.addKnownCard();
        player.addKnownCard();
        dealer.addUnkonwCard(cardsDeck);
        dealer.addKnownCard();
    }

    /**
     * Dice se il giocatore ha un blackjack
     * @return
     */
    public static boolean checkBlackjack(){
        return player.blackjack();
    }
}