package Code.Panels;

import Code.TestApp;

import javax.swing.*;

import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    public static MainPanel mainPanel;

    public MainFrame() throws IOException, SQLException {
        JFrame frame = new JFrame("Pong");

        mainPanel = new MainPanel();

        frame.setContentPane(mainPanel);

        frame.setSize(TestApp.gameDimension.width, TestApp.gameDimension.height);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
