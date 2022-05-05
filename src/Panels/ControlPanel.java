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
import static Panels.FichesPanel.confirm;
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
        setLayout(new GridLayout(1,2));
        actionListener = new ArrayList<ActionListener>();

        hitButton = new JButton("Hit a Card");
        standButton = new JButton("Stand");
        fichesPanel = new FichesPanel();

        hitButton.addActionListener(this);
        standButton.addActionListener(this);

        for(Fiche fiche : ficheButton)
            fiche.addActionListener(this);

        confirm.addActionListener(this);

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

        checkEnableFiche(ficheButton);              // <----        per comodità lo scrivo qui, ma potrei anche metterlo semplicemente
                                                    //              nei pulsanti delle fiche
        if(e.getSource() == hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            player.addKnownCard();

            buttonsEnable(false, ficheButton[0]);           //adesso che la funzione "buttonsEnable" lavora con un bottone alla volta
            buttonsEnable(false, ficheButton[1]);           //ovviamente bisogna disabilitare un pulsante alla volta.
            buttonsEnable(false, ficheButton[2]);           //essendo pochi quelli delle fiche ho preferito non usare il for
            buttonsEnable(false, ficheButton[3]);           //ma si può semprde cambiare

            if(player.isBust()){
                buttonsEnable(false, standButton);
                buttonsEnable(false, hitButton);
            }


        }
        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            dealer.play(cardsDeck);
            dispenserMoney();
            buttonsEnable(false, standButton);
            buttonsEnable(false, hitButton);
            buttonsEnable(false, ficheButton[0]);
            buttonsEnable(false, ficheButton[1]);
            buttonsEnable(false, ficheButton[2]);
            buttonsEnable(false, ficheButton[3]);
        }
        if(e.getSource() == ficheButton[0]){
            buttonsEnable(true, confirm);
            System.out.println("FICHE 100: sono stato premuto");
            player.bet(ficheButton[0].getValue());
        }
        if(e.getSource() == ficheButton[1]){
            buttonsEnable(true, confirm);
            System.out.println("FICHE 50: sono stato premuto");
            player.bet(ficheButton[1].getValue());
        }
        if(e.getSource() == ficheButton[2]){
            buttonsEnable(true, confirm);
            System.out.println("FICHE 10: sono stato premuto");
            player.bet(ficheButton[2].getValue());
        }
        if(e.getSource() == ficheButton[3]){
            buttonsEnable(true, confirm);
            System.out.println("ALL-IN: sono stato premuto");
            player.bet(player.getAccount());
        }
        if(e.getSource() == confirm){                                      //essenzialmente questo deve solo disabilitare le fiche e
            System.out.println("Conferma: sono stato premuto");            //abilitare "stand" e "hit"
            buttonsEnable(true, hitButton);
            buttonsEnable(true, standButton);
            buttonsEnable(false, ficheButton[0]);
            buttonsEnable(false, ficheButton[1]);
            buttonsEnable(false, ficheButton[2]);
            buttonsEnable(false, ficheButton[3]);
            buttonsEnable(false, confirm);
        }

        sendToActionListeners(e);
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }


    /**
     * Serve a disabilitare una fiche nel caso non si abbiano abbastanza "soldi" nel conto
     *
     * Esempio:
     * Account --> 90
     * La fiche da 100 sarà disabilitata
     * @param fiche
     */

    public void checkEnableFiche(Fiche[] fiche){
        for(Fiche f : fiche){
            if(player.getAccount() < 2*f.getValue()){
                buttonsEnable(false, f);
            }
        }
    }


    /**
     * Serve ad abilitare/disabilitare il bottone che si passa
     * @param bool
     * @param button
     */

    public void buttonsEnable(Boolean bool, JButton button) {
        button.setEnabled(bool);
    }
}