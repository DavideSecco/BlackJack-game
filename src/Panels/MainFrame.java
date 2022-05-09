package Panels;

import javax.swing.*;

import java.io.IOException;

import static Main.TestApp.*;

public class MainFrame extends JFrame {
    public static MainPanel mainPanel;

    public MainFrame() throws IOException {
        JFrame frame = new JFrame("Pong");

        mainPanel = new MainPanel();

        frame.setContentPane(mainPanel);

        frame.setSize(gameDimension.width, gameDimension.height);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
