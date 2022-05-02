package Panels;

import Cards.CardsDeck;
import Participant.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Main.TestApp.*;

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

    private List<ButtonListener> buttonListeners;

    public ControlPanel(Player player, CardsDeck cardsDeck) throws IOException {
        super();
        setPreferredSize(new Dimension(dimension.width, dimension.height/9));
        setLayout(new GridLayout(1,3));
        buttonListeners = new ArrayList<ButtonListener>();

        hitButton = new JButton("Hit a Cards.Card");
        standButton = new JButton("Stand");
        fichesPanel = new FichesPanel();

        hitButton.addActionListener(this);
        standButton.addActionListener(this);

        add(hitButton);
        add(standButton);
        add(fichesPanel);
    }

    public void addButtonListener(ButtonListener buttonListener) {
        this.buttonListeners.add(buttonListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            player.addKnownCard(cardsDeck);
            sendToButtonListeners(e);
        }
        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            sendToButtonListeners(e);
        }
    }

    public void sendToButtonListeners(ActionEvent e){
        for(ButtonListener buttonListener : buttonListeners){
            buttonListener.buttonAction(e);
        }
    }
}
