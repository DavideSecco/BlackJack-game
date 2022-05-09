package Panels.Game;

import javax.swing.*;

import java.io.IOException;

import static Main.TestApp.*;

public class GameFrame extends JFrame {
    public static GamePanel gamePanel;

    public GameFrame() throws IOException {
        JFrame frame = new JFrame("Pong");

        gamePanel = new GamePanel(player, dealer, cardsDeck);
        frame.setContentPane(gamePanel);

        frame.setSize(gameDimension.width, gameDimension.height);   // nota che penso comprenda anche la barra di sistema: da me toglie 40px
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }


}
