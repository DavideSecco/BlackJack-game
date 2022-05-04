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
import static Panels.FichesPanel.ficheButton;

/**
 * E' il pannello dei pulsanti e dei controlli, nella mia testa ci va:
 * - pulsante per chiedere un'altra carta: "hit a card"
 * - pulsante per dire che sei a posto: "stay"
 * - tutti i pulsanti delle fiches per puntare --> https://stackoverflow.com/questions/8680189/clickable-images-in-java
 */

public class ControlPanel extends JPanel implements ActionListener {
    protected static JButton hitButton;
    protected static JButton standButton;
    private static FichesPanel fichesPanel;

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

        for(Fiche fiche : ficheButton)
            fiche.addActionListener(this);

        hitButton.setEnabled(false);
        standButton.setEnabled(false);

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

            if(player.isBust())
                buttonsEnable(false);
        }
        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            dealer.play(cardsDeck);
            dispenserMoney();
            buttonsEnable(false);
        }
        if(e.getSource() == ficheButton[0]){
            System.out.println("FICHE 100: sono stato premuto");
            buttonsEnable(true);
            player.bet(ficheButton[0].getValue());
        }
        if(e.getSource() == ficheButton[1]){
            System.out.println("FICHE 50: sono stato premuto");
            buttonsEnable(true);
            player.bet(ficheButton[1].getValue());
        }
        if(e.getSource() == ficheButton[2]){
            System.out.println("FICHE 10: sono stato premuto");
            buttonsEnable(true);
            player.bet(ficheButton[2].getValue());
        }

        sendToActionListeners(e);
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }

    public void buttonsEnable(Boolean bool){
        hitButton.setEnabled(bool);
        standButton.setEnabled(bool);

        for(Fiche fiche : ficheButton)
            fiche.setEnabled(bool);
    }
}