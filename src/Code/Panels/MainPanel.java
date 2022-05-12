package Code.Panels;

import Code.Panels.Game.GamePanel;
import Code.Panels.Menu.MenuPanel;
import Code.Panels.Menu.RulesPanel;
import Code.Panels.Menu.SelectPlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class MainPanel extends JPanel {
    public static GamePanel gamePanel;
    public static MenuPanel menuPanel;
    public static RulesPanel rulesPanel;
    public static SelectPlayerPanel selectPlayerPanel;

    public MainPanel() throws IOException, SQLException {
        super();
        setLayout(new CardLayout());

        gamePanel = new GamePanel();
        rulesPanel = new RulesPanel();
        selectPlayerPanel = new SelectPlayerPanel();

        menuPanel = new MenuPanel();
        add(menuPanel);
    }

    public void changePanel(JPanel panel){
        removeAll();
        add(panel);

        revalidate();
        repaint();
    }
}
