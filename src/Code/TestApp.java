package Code;
import Code.GameElements.CardsDeck;
import Code.Panels.MainFrame;
import Code.Participant.Dealer;
import Code.Participant.Player;
import Utils.DBManager;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

import static Code.MyDB.*;

public class TestApp {
    public static Player player;
    public static Dealer dealer = new Dealer();
    public static CardsDeck cardsDeck = new CardsDeck();

    public static Dimension gameDimension = new Dimension(1200, 700);
    public static Dimension menuDimension = new Dimension(400, 700);

    public static final String imagesPath = "/Utils/Images/";

    public static MainFrame mainFrame;

    public static void main(String[] args) throws IOException, SQLException {
        player = new Player();
        dealer = new Dealer();
        cardsDeck = new CardsDeck();

        MyDB.setUpDB();

        mainFrame = new MainFrame();
    }

    //------------------------------------PARTE LOGICA------------------------------------//

    /**
     * 1 --> player ha vinto
     * 0 --> pareggio
     * -1 --> dealer ha vinto
     */


    public static void managePlayerWins(){
        if(dealer.isBust() || (player.getValueCards() > dealer.getValueCards() && !player.isBust())){
            player.incrementWins();
            try {
                updateWinsDB();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void incrementPlayerGames(){
        player.setGames(player.getGames()+1);
        try {
            updateGamesDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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


        //ovviamente deve mettere i soldi anche nel db

        try {
            updateAccountDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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