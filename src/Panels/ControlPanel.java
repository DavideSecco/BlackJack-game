package Panels;

import GameElements.CardsDeck;
import GameElements.Fiche;
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
    protected static JButton hitButton;
    protected static JButton standButton;
    private FichesPanel fichesPanel;

    private List<ActionListener> actionListener;

    public ControlPanel(Player player, CardsDeck cardsDeck) throws IOException {
        super();
        setPreferredSize(new Dimension(dimension.width, dimension.height/9));
        setLayout(new GridLayout(1,3));
        actionListener = new ArrayList<ActionListener>();

        hitButton = new JButton("Hit a Card");
        standButton = new JButton("Stand");
        fichesPanel = new FichesPanel();

        hitButton.addActionListener(this);
        standButton.addActionListener(this);

        for(Fiche fiche : fichesPanel.ficheButton){
            fiche.addActionListener(this);
        }

        add(hitButton);
        add(standButton);
        add(fichesPanel);
    }

    public void addActionListener(ActionListener actionListener) {
        this.actionListener.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            player.addKnownCard();
            sendToActionListeners(e);
            if(player.isBust())
                disableAll();
        }
        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            dealer.play(cardsDeck);
            dispenserMoney();
            sendToActionListeners(e);
            disableAll();
        }
        if(e.getSource() == fichesPanel.ficheButton[0]){
            System.out.println("FICHE 100: sono stato premuto");
            player.setBet(100);
            player.setAccount(100);
            sendToActionListeners(e);
        }
        if(e.getSource() == fichesPanel.ficheButton[1]){
            System.out.println("FICHE 50: sono stato premuto");
            player.setBet(50);
            player.setAccount(50);
            sendToActionListeners(e);
        }
        if(e.getSource() == fichesPanel.ficheButton[2]){
            System.out.println("FICHE 10: sono stato premuto");
            player.setBet(10);
            player.setAccount(10);
            sendToActionListeners(e);
        }
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }

    public void disableAll(){
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }
}