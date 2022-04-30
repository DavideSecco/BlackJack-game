package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * E' il pannello dei pulsanti e dei controlli, nella mia testa ci va:
 * - pulsante per chiedere un'altra carta: "hit a card"
 * - pulsante per dire che sei a posto: "stay"
 * - tutti i pulsanti delle fiches per puntare --> https://stackoverflow.com/questions/8680189/clickable-images-in-java
 */

public class ControlPanel extends JPanel {
    private final JButton hitButton;
    private final JButton standButton;
    private final FichesPanel fichesPanel;

    public ControlPanel() throws IOException {
        super();
        setPreferredSize(new Dimension(1280, 100));
        setLayout(new GridLayout(1,3));

        hitButton = new JButton("Hit a Card");
        standButton = new JButton("Stand");
        fichesPanel = new FichesPanel();

        add(hitButton);
        add(standButton);
        add(fichesPanel);
    }
}
