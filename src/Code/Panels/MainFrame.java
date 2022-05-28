package Code.Panels;

import Code.Gameplay;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    public static MainPanel mainPanel;

    public static Dimension gameDimension = new Dimension(1200, 700);
    public static Dimension menuDimension = new Dimension(400, 700);

    public MainFrame() throws IOException, SQLException{
        JFrame frame = new JFrame("Black Jack");

        mainPanel = new MainPanel();

        frame.setContentPane(mainPanel);

        frame.setSize(gameDimension.width, gameDimension.height);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
