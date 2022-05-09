package Panels.Menu;

import javax.swing.*;
import java.io.IOException;

import static Main.TestApp.menuDimension;
import static Main.TestApp.menuFrame;

public class MenuFrame extends JFrame {
    public static MenuPanel menuPanel;
    public static SelectPlayerPanel selectPlayerPanel;
    public static RulesPanel rulesPanel;

    public MenuFrame() throws IOException {
        super();
        JFrame frame = new JFrame("Menu");

        menuPanel = new MenuPanel();
        frame.setContentPane(menuPanel);

        frame.setSize(menuDimension.width, menuDimension.height);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    // Non funziona...
    public void delete(){
        menuPanel.removeAll();
        this.removeAll();
        this.setVisible(false);
        this.dispose();
    }
}
