package Main;

import Cards.CardsDeck;
import Panels.GamePanel;
import Participant.Dealer;
import Participant.Participant;
import Participant.Player;

import javax.swing.*;
import java.io.IOException;

public class TestApp {

    public static Player player = new Player();
    public static Dealer dealer = new Dealer();

    public static CardsDeck cardsDeck = new CardsDeck();

    public static GamePanel gamePanel;
    public static boolean win;

    static {
        try {
            gamePanel = new GamePanel(player, dealer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {


        player.addKnownCard(cardsDeck);
        player.addKnownCard(cardsDeck);

        dealer.addUnkonwCard(cardsDeck);
        dealer.addKnownCard(cardsDeck);

        System.out.println(player.toString());

        /* setto interfaccia grafica */
        JFrame frame = new JFrame("Pong");


        frame.setContentPane(gamePanel);

        frame.setSize(1280, 800);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        /* --------------------------- */

    }
}
