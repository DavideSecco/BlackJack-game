package Main;
import GameElements.CardsDeck;
import Panels.MainFrame;
import Participant.Dealer;
import Participant.Player;

import java.awt.*;
import java.io.IOException;

public class TestApp {
    public static Player player = new Player();
    public static Dealer dealer = new Dealer();
    public static CardsDeck cardsDeck = new CardsDeck();
    public static Dimension dimension = new Dimension(1200, 700);

    public static void main(String[] args) throws IOException {
        player.addKnownCard();
        player.addKnownCard();
        player.addKnownCard();

        System.out.println(player.getValueCards());

        //dealer.addUnkonwCard(cardsDeck);
        //dealer.addKnownCard();

        MainFrame mainFrame = new MainFrame();
    }

    /**
     * 1 --> player ha vinto
     * 0 --> pareggio
     * -1 --> dealer ha vinto
     * @return
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

}