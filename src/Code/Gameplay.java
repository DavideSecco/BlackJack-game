package Code;

import Code.Participant.Player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import static Code.MyDB.*;
import static Code.Panels.Game.ControlPanel.ActionPanel.splitPressed;
import static Code.Panels.Game.Dialog.InsuranceDialog.siPressed;
import static Code.TestApp.*;

public class Gameplay {
    /** 1 --> player ha vinto     0 --> pareggio      -1 --> dealer ha vinto    */
    public static void endGame() throws SQLException {
        player.incrementGames();
        Gameplay.dispenserMoney();

        System.out.println(player.toString());
        System.out.println(dealer.toString());

        if(splitPressed != 0){
            player.swapSplittedElements();
            Gameplay.dispenserMoney();
        }

        if(siPressed)
            manageInsurance();

        if(whoWon() == 1)
            player.incrementWins();

        updatePlayerDB();
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

    /**
     * se il giocatore vince gli da i soldi della vincita nel conto più quelli scommessi
     * se pareggia gli ridà solo quelli scommessi
     * infine riporta a zero la scommessa per le prossime partite
     */
    public static void dispenserMoney(){
        if(whoWon() == 1) {                                          // player ha vinto
            vittoria();
        }
        else if(whoWon() == 0)                                      // player ha pareggiato
            pareggio();
        else                                                        // player ha perso
            sconfitta();
    }

    /** Crea l'inizio del gioco, ovvero distribuisce le carte iniziali del player e del dealer  */
    public static void inizio() {
        player.addKnownCard();
        player.addKnownCard();

        dealer.addUnkonwCard();
        dealer.addKnownCard();
    }

    public static void manageInsurance(){
        if(!dealer.hasBlackJack())
            player.addToAccount(-player.getBet()/2);
        else
            pareggio();
    }

    public static void vittoria(){
        player.addToAccount(2 * player.getBet());
        if (player.hasBlackJack())                               //se ha fatto hasBlackJack si aggiunge la metà della bet
            player.addToAccount(player.getBet() / 2);
    }

    public static void pareggio(){
        player.addToAccount(player.getBet());
    }

    public static void sconfitta(){
        player.addToAccount(0);
    }
}
