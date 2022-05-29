package Code;
import Code.GameElements.CardsDeck;
import Code.Panels.MainFrame;
import Code.Participant.Dealer;
import Code.Participant.Player;

import java.io.IOException;
import java.sql.SQLException;

public class TestApp {
    public static Player player;
    public static Dealer dealer = new Dealer();
    public static CardsDeck cardsDeck;

    public static final String imagesPath = "/Utils/Images/";

    public static MusicManager song;
    public static MusicManager click;

    public static MainFrame mainFrame;

    public static void main(String[] args) throws IOException, SQLException {
        player = new Player();
        dealer = new Dealer();
        cardsDeck = new CardsDeck(0);

        click = new MusicManager("ClickOn.wav");
        song = new MusicManager("Song.wav");

        MyDB.setUpDB();

        System.out.println(MyDB.getPlayersFromDB());

        mainFrame = new MainFrame();
    }
}