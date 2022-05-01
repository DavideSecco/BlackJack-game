package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * E' il pannello dei pulsanti e dei controlli, nella mia testa ci va:
 * - pulsante per chiedere un'altra carta: "hit a card"
 * - pulsante per dire che sei a posto: "stay"
 * - tutti i pulsanti delle fiches per puntare --> https://stackoverflow.com/questions/8680189/clickable-images-in-java
 */

public class ControlPanel extends JPanel implements ActionListener {
    private final JButton hitButton;
    private final JButton standButton;
    private final FichesPanel fichesPanel;

    private ButtonListener buttonListener;

    public ControlPanel() throws IOException {
        super();
        setPreferredSize(new Dimension(1280, 100));
        setLayout(new GridLayout(1,3));

        hitButton = new JButton("Hit a Cards.Card");
        standButton = new JButton("Stand");
        fichesPanel = new FichesPanel();

        hitButton.addActionListener(this);
        standButton.addActionListener(this);

        add(hitButton);
        add(standButton);
        add(fichesPanel);
    }

    public void setButtonListener(ButtonListener buttonListener){
        this.buttonListener = buttonListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            buttonListener.buttonAction(e);
        }
        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            buttonListener.buttonAction(e);
        }
    }
}
