import Cards.CardsDeck;
import Panels.GamePanel;
import Participant.Dealer;
import Participant.Player;

import javax.swing.*;
import java.io.IOException;

public class TestApp {
    public static void main(String[] args) throws IOException {
        Player player = new Player();
        Dealer dealer = new Dealer();

        CardsDeck cardsDeck = new CardsDeck();

        player.addKnownCard(cardsDeck);
        player.addKnownCard(cardsDeck);

        dealer.addUnkonwCard(cardsDeck);
        dealer.addKnownCard(cardsDeck);

        System.out.println(player.toString());

        /* setto interfaccia grafica */
        JFrame frame = new JFrame("Pong");

        GamePanel gamePanel = new GamePanel(player, dealer, cardsDeck);
        frame.setContentPane(gamePanel);

        frame.setSize(1280, 800);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        /* --------------------------- */

    }
}
