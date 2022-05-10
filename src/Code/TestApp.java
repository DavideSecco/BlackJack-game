package Code;
import Code.GameElements.CardsDeck;
import Code.Panels.MainFrame;
import Code.Participant.Dealer;
import Code.Participant.Player;
import Utils.DBManager;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestApp {
    public static Player player = new Player();
    public static Dealer dealer = new Dealer();
    public static CardsDeck cardsDeck = new CardsDeck();

    public static Dimension gameDimension = new Dimension(1200, 700);
    public static Dimension menuDimension = new Dimension(400, 700);

    public static final String imagesPath = "/Utils/Images/";

    public static MainFrame mainFrame;

    public static Statement s;

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println(player.getValueCards());

        player = new Player();
        dealer = new Dealer();
        cardsDeck = new CardsDeck();

        mainFrame = new MainFrame();

        // DBTEST:
        createDB();
        inserisciUser();
        System.out.println(getPlayerDaDB());

        DBManager.close();
    }


    //-------------------------------------parte DATABASE------------------------------------//

    // secondo me va in un'altra classe questa parte, però non saprei dato che faccio riferimento ai video vecchi




    /**
     * Crea il database di nome Blackjack.db usando sqlite,
     * instaura una connessione
     * e crea la tabella dove verranno inseriti i dati di ogni giocatore
     * @throws SQLException
     */
    public static void createDB() throws SQLException {
        DBManager.setConnection(DBManager.JDBC_Driver_SQLite, DBManager.JDBC_URL_SQLite);
        DBManager.getConnection();
        DBManager.showMetadata();
        s = DBManager.connection.createStatement();
        s.executeUpdate("CREATE TABLE IF NOT EXISTS Persons (" +
                "FirstName TEXT PRIMARY KEY," +
                "Wins INTEGER," +
                "Account INTEGER" +
                ");");
    }



    /**
     * inserisce i dati dell'utente corrente nel database
     * @throws SQLException
     */
    public static void inserisciUser() throws SQLException {
        s.executeUpdate("INSERT INTO Persons (FirstName, Wins, Account) " +
                "VALUES('" + player.getName() + "'," + player.getWins() + "," + player.getAccount() + ");");
    }



    /**
     * preleva dal database i dati del player corrente
     * @return
     * @throws SQLException
     */
    public static String getPlayerDaDB() throws SQLException {
        return s.executeQuery("SELECT * FROM Persons").getString("FirstName") + " " +
                s.executeQuery("SELECT * FROM Persons").getString("Wins") + " " +
                s.executeQuery("SELECT * FROM Persons").getString("Account");
    }



    //------------------------------------PARTE LOGICA------------------------------------//

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