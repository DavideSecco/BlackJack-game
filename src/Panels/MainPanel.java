package Panels;

import Panels.Game.GamePanel;
import Panels.Menu.MenuPanel;
import Panels.Menu.RulesPanel;
import Panels.Menu.SelectPlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPanel extends JPanel {
    public static GamePanel gamePanel;
    public static MenuPanel menuPanel;
    public static RulesPanel rulesPanel;
    public static SelectPlayerPanel selectPlayerPanel;

    public MainPanel() throws IOException {
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
