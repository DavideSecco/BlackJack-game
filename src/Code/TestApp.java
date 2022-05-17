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

        MyDB.createDB();

        // MyDB.setUpDB();

        mainFrame = new MainFrame();
    }
}