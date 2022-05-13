package Code.Panels.Menu.Login;

import Code.MyDB;
import Code.Panels.Menu.SelectPlayerPanel;
import Code.Participant.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import static Code.TestApp.*;

import static Code.MyDB.getDataFromDB;
import static Code.Panels.MainPanel.selectPlayerPanel;

public class Login {
    public static int account;

    public static boolean authenticate(String user, String pass) throws SQLException {
        if(user.equals("") || pass.equals(""))
            return false;

        ResultSet rs = getDataFromDB();

        while(rs.next()){
            if(user.equals(rs.getString("FirstName")) && !pass.equals(rs.getString("Password"))){
                return false;
            } else if (user.equals(rs.getString("FirstName")) && pass.equals(rs.getString("Password"))) {
                account=rs.getInt("account");
                player.setName(user);
                player.setPassword(pass);
                return true;
            }
        }

        //Player p = new Player(user,pass);
        player.setName(user);
        player.setPassword(pass);
        account = 1000;
        MyDB.addPlayer(player);
        selectPlayerPanel = new SelectPlayerPanel();
        return true;
    }
}
