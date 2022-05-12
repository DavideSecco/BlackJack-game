package Code;

import Code.Participant.Player;
import Utils.DBManager;
import org.sqlite.SQLiteException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDB {
    public static Statement statement;
    public static String table = "Players";
    /**
     * Crea il database di nome Blackjack.db usando sqlite, instaura una connessione
     * e crea la tabella dove verranno inseriti i dati di ogni giocatore
     * @throws SQLException
     */
    public static void createDB() throws SQLException {
        DBManager.setConnection(DBManager.JDBC_Driver_SQLite, DBManager.JDBC_URL_SQLite);
        DBManager.getConnection();
        DBManager.showMetadata();
        statement = DBManager.connection.createStatement();
        /* Questa riga andrá sicuramente tolta in futuro! */
        statement.executeUpdate("DROP TABLE " + table + ";");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + table + " (" +
                                    "FirstName TEXT PRIMARY KEY," +
                                    "Wins INTEGER," +
                                    "Account INTEGER," +
                                    "Password TEXT" + ");");
    }

    // metodo che inizializza un db di prova, alla fine non servirá nemmeno piú
    public static void setUpDB() throws SQLException {
        MyDB.createDB();
        Player p1 = new Player();
        Player p2 = new Player("Davide");
        Player p3 = new Player("Donato", 100, 10000, "Prova");

        MyDB.addPlayer(p1);
        MyDB.addPlayer(p2);
        MyDB.addPlayer(p3);
    }

    /**
     * inserisce i dati dell'utente corrente nel database
     * @throws SQLException
     */
    public static void addPlayer(Player player) throws SQLException {
        try {
            statement.executeUpdate("INSERT INTO " + table + " (FirstName, Wins, Account, Password) VALUES('" +
                                        player.getName() + "'," + player.getWins() + "," + player.getAccount() + ",'" + player.getPassword() + "');");
        }catch(SQLiteException e){
            System.out.println("Hai provato a inserire un utente che esiste giá nel db");
        }
    }

    /**
     * preleva dal database i dati del player corrente
     * @throws SQLException
     */
    public static String getPlayersFromDB() throws SQLException {
        /**
        return statement.executeQuery("SELECT * FROM Persons").getString("FirstName") + " " +
                statement.executeQuery("SELECT * FROM Persons").getString("Wins") + " " +
                statement.executeQuery("SELECT * FROM Persons").getString("Account");
         */
        ResultSet rs = statement.executeQuery("SELECT * FROM " + table + " LIMIT 100");
        System.out.println("Table " + table);
        StringBuffer sb = new StringBuffer();
        while (rs.next()) {
            sb.append(rowToString(rs) + "\n");
        }
        return sb.toString();
    }
    public static ResultSet getDataFromDB() throws SQLException{
        return statement.executeQuery("SELECT * FROM " + table + " LIMIT 100");
    }

    public static String rowToString(ResultSet rs) throws SQLException {
        return String.format("Name=%s, Wins=%d, Account=%d",
                rs.getString("FirstName"),
                rs.getInt("wins"),
                rs.getInt("account"));
    }
}
