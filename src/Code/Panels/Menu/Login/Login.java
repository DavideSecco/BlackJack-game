package Code.Panels.Menu.Login;

import Code.MyDB;
import Code.Participant.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Code.MyDB.getDataFromDB;

public class Login {

    public static int account;
    public static boolean authenticate(String user, String pass) throws SQLException {

        ResultSet rs = getDataFromDB();
        while(rs.next()){
            if(user.equals(rs.getString("FirstName")) && !pass.equals(rs.getString("FirstName"))){
                return false;
            } else if (user.equals(rs.getString("FirstName")) && !pass.equals(rs.getString("FirstName"))) {
                account=rs.getInt("account");
                return true;
            }
        }
        Player p = new Player(user);
        MyDB.addPlayer(p);
        return true;
    }
}
