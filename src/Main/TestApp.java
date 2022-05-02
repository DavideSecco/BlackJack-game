package Main;
import Cards.CardsDeck;
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
        player.addKnownCard(cardsDeck);
        player.addKnownCard(cardsDeck);

        dealer.addUnkonwCard(cardsDeck);
        dealer.addKnownCard(cardsDeck);

        MainFrame mainFrame = new MainFrame();

    }
}
