package Code;

import Code.Participant.Player;
import Utils.DBManager;
import org.sqlite.SQLiteException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static Code.TestApp.player;

public class MyDB {
    public static Statement statement;
    public static String table = "Players";

    /**
     * Crea il database di nome Blackjack.db usando sqlite, instaura una connessione
     * e crea la tabella dove verranno inseriti i dati di ogni giocatore
     *
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
                "Games INTEGER," +
                "Wins INTEGER," +
                "Account INTEGER," +
                "Password TEXT" + ");");
    }

    // metodo che inizializza un db di prova, alla fine non servirá nemmeno piú
    public static void setUpDB() throws SQLException {
        MyDB.createDB();
        // Player p1 = new Player();
        Player p2 = new Player("Davide");
        Player p3 = new Player("Donato", 100, 10000, "Prova", 0);
        Player p4 = new Player("1", "1");
        Player p5 = new Player("Marti", "pepotto36");

        // MyDB.addPlayer(p1);
        MyDB.addPlayer(p2);
        MyDB.addPlayer(p3);
        MyDB.addPlayer(p5);
    }

    /**
     * inserisce i dati dell'utente corrente nel database
     *
     * @throws SQLException
     */
    public static void addPlayer(Player player) throws SQLException {
        try {
            statement.executeUpdate("INSERT INTO " + table + " (FirstName, Games, Wins, Account, Password) VALUES('" +
                    player.getName() + "'," + player.getGames() + "," + player.getWins() + "," + player.getAccount() + ",'" + player.getPassword() + "');");
        } catch (SQLiteException e) {
            System.out.println("Hai provato a inserire un utente che esiste giá nel db");
        }
    }

    /**
     * preleva dal database i dati del player corrente
     *
     * @throws SQLException
     */
    public static String getPlayersFromDB() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM " + table + " LIMIT 100");
        System.out.println("Table " + table);
        StringBuffer sb = new StringBuffer();
        while (rs.next()) {
            sb.append(rowToString(rs) + "\n");
        }
        return sb.toString();
    }

    public static ResultSet getDataFromDB() throws SQLException {
        return statement.executeQuery("SELECT * FROM " + table + " LIMIT 100");
    }

    /**
     * cambia il player corrente in quello specificato dal playerName, con tutti i suoi dati
     *
     * @param playerName
     */
    public static void changePlayerFromDB(String playerName) throws SQLException {
        ResultSet rs = getDataFromDB();
        while (rs.next()) {
            if (Objects.equals(playerName, rs.getString("FirstName"))) {
                player.setName(rs.getString("FirstName"));
                player.setGames(rs.getInt("games"));
                player.setPassword(rs.getString("password"));
                player.setAccount(rs.getInt("account"));
                player.setWins(rs.getInt("wins"));
            }
        }
    }

    public static String rowToString(ResultSet rs) throws SQLException {
        return String.format("Name=%s, Wins=%d, Account=%d",
                rs.getString("FirstName"),
                rs.getInt("wins"),
                rs.getInt("account"));
    }

    public static void updatePlayerDB() throws SQLException {
        updateGamesDB();
        updateWinsDB();
        updateAccountDB();
    }

    private static void updateAccountDB() throws SQLException {
        statement.executeUpdate("UPDATE " + table + " SET account = " + player.getAccount() + " WHERE FirstName = '" + player.getName() + "' ;");
    }

    private static void updateWinsDB() throws SQLException {
        statement.executeUpdate("UPDATE " + table + " SET wins = " + player.getWins() + " WHERE FirstName = '" + player.getName() + "' ;");
    }

    private static void updateGamesDB() throws SQLException {
        statement.executeUpdate("UPDATE " + table + " SET games = " + player.getGames() + " WHERE FirstName = '" + player.getName() + "' ;");
    }

    public static boolean authenticate(String user, String pass) throws SQLException {
        ResultSet rs = getDataFromDB();

        while (rs.next()) {
            if (user.equals(rs.getString("FirstName")) && pass.equals(rs.getString("Password"))) {
                changePlayerFromDB(user);
                return true;
            }
        }
        return false;
    }

    public static boolean isUserinDB(String user) throws SQLException {
        ResultSet rs = getDataFromDB();
        while (rs.next()) {
            if(user.equals(rs.getString("FirstName")))
                return true;
        }
        return false;
    }
}
