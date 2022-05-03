package Main;
import Cards.Card;
import Cards.CardsDeck;
import Panels.MainFrame;
import Participant.Dealer;
import Participant.Player;

import java.awt.*;
import java.io.IOException;

public class TestApp {
    public static int win;

    public static Player player = new Player();
    public static Dealer dealer = new Dealer();
    public static CardsDeck cardsDeck = new CardsDeck();
    public static Dimension dimension = new Dimension(1200, 700);

    public static void main(String[] args) throws IOException {
        player.hitting(cardsDeck);
        player.hitting(cardsDeck);

        dealer.addUnkonwCard(cardsDeck);
        dealer.hitting(cardsDeck);

        MainFrame mainFrame = new MainFrame();

    }
}