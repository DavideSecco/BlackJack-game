package Panels.Game.ControlPanel;

import GameElements.Fiche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Main.TestApp.*;
import static Panels.Game.ControlPanel.ActionPanel.*;
import static Panels.Game.ControlPanel.FichesPanel.confirm;
import static Panels.Game.ControlPanel.FichesPanel.ficheButton;

/**
 * E' il pannello dei pulsanti e dei controlli, nella mia testa ci va:
 * - tutti i pulsanti di azione di gioco (dell'ActionPanel)
 * - tutti i pulsanti delle fiches per puntare --> https://stackoverflow.com/questions/8680189/clickable-images-in-java
 */

public class ControlPanel extends JPanel implements ActionListener, MyPanel {
    private static FichesPanel fichesPanel;
    public static ActionPanel actionPanel;

    private List<ActionListener> actionListener;

    public ControlPanel() throws IOException {
        super();
        setPreferredSize(new Dimension(gameDimension.width, gameDimension.height/9));
        setLayout(new GridLayout(1,2));
        actionListener = new ArrayList<ActionListener>();

        fichesPanel = new FichesPanel();
        actionPanel = new ActionPanel();

        actionPanel.addActionListener(this);
        fichesPanel.addActionListener(this);

        add(actionPanel);
        add(fichesPanel);
    }

    @Override
    public void enablePanel(boolean bool) {
        fichesPanel.enablePanel(bool);
        actionPanel.enablePanel(bool);
    }

    public void inizialize(){
        actionPanel.enablePanel(false);
        fichesPanel.inizialize();
    }

    public void addActionListener(ActionListener actionListener) {
        this.actionListener.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkEnableFiche(ficheButton);              // <----        per comodità lo scrivo qui, ma potrei anche metterlo semplicemente nei pulsanti delle fiche

        if(e.getSource() == hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            player.addKnownCard();
            fichesPanel.enablePanel(false);

            if(player.isBust())
                actionPanel.enablePanel(false);
        }
        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            dealer.play(cardsDeck);
            dispenserMoney();
            actionPanel.enablePanel(false);
        }
        if(e.getSource() == ficheButton[0]){
            confirm.setEnabled(true);
            System.out.println("FICHE 100: sono stato premuto");
            player.bet(ficheButton[0].getValue());
        }
        if(e.getSource() == ficheButton[1]){
            confirm.setEnabled(true);
            System.out.println("FICHE 50: sono stato premuto");
            player.bet(ficheButton[1].getValue());
        }
        if(e.getSource() == ficheButton[2]){
            confirm.setEnabled(true);
            System.out.println("FICHE 10: sono stato premuto");
            player.bet(ficheButton[2].getValue());
        }
        if(e.getSource() == ficheButton[3]){
            fichesPanel.enablePanel(false);
            confirm.setEnabled(true);
            System.out.println("ALL-IN: sono stato premuto");
            player.bet(player.getAccount());
        }
        if(e.getSource() == confirm){
            System.out.println("Conferma: sono stato premuto");
            inizio();
            actionPanel.enablePanel(true);
            fichesPanel.enablePanel(false);
            checkEnableDouble();

            if(checkBlackjack())
                standButton.doClick();
        }
        if(e.getSource() == doubleButton){
            System.out.println("Double: sono stato premuto");
            player.bet(player.getBet());
            hitButton.doClick();
            standButton.doClick();
        }
        if(e.getSource() == splitButton){
            System.out.println("Split: sono stato premuto");
        }

        sendToActionListeners(e);
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }

    /** Serve a disabilitare una fiche nel caso non si abbiano abbastanza "soldi" nel conto
     * Es: Account = 90 --> La fiche da 100 sarà disabilitata  */
    public void checkEnableFiche(Fiche[] fiches){
        for(Fiche fiche : fiches){
            if(player.getAccount() < 2*fiche.getValue())
                fiche.setEnabled(false);
        }
    }

    public void checkEnableDouble(){
        if(player.getBet() > player.getAccount())
            doubleButton.setEnabled(false);
    }
}