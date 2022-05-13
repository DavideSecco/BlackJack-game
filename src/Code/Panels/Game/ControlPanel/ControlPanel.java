package Code.Panels.Game.ControlPanel;

import Code.GameElements.Fiche;
import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        setPreferredSize(new Dimension(TestApp.gameDimension.width, TestApp.gameDimension.height/9));
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
        checkEnableFiche(FichesPanel.ficheButton);              // <----        per comodità lo scrivo qui, ma potrei anche metterlo semplicemente nei pulsanti delle fiche

        if(e.getSource() == ActionPanel.hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            TestApp.player.addKnownCard();
            fichesPanel.enablePanel(false);

            if(TestApp.player.isBust())
                actionPanel.enablePanel(false);
        }
        if(e.getSource() == ActionPanel.standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            TestApp.dealer.play(TestApp.cardsDeck);
            TestApp.dispenserMoney();
            TestApp.managePlayerWins();
            actionPanel.enablePanel(false);
        }
        if(e.getSource() == FichesPanel.ficheButton[0]){
            FichesPanel.confirm.setEnabled(true);
            System.out.println("FICHE 100: sono stato premuto");
            TestApp.player.bet(FichesPanel.ficheButton[0].getValue());
        }
        if(e.getSource() == FichesPanel.ficheButton[1]){
            FichesPanel.confirm.setEnabled(true);
            System.out.println("FICHE 50: sono stato premuto");
            TestApp.player.bet(FichesPanel.ficheButton[1].getValue());
        }
        if(e.getSource() == FichesPanel.ficheButton[2]){
            FichesPanel.confirm.setEnabled(true);
            System.out.println("FICHE 10: sono stato premuto");
            TestApp.player.bet(FichesPanel.ficheButton[2].getValue());
        }
        if(e.getSource() == FichesPanel.ficheButton[3]){
            fichesPanel.enablePanel(false);
            FichesPanel.confirm.setEnabled(true);
            System.out.println("ALL-IN: sono stato premuto");
            TestApp.player.bet(TestApp.player.getAccount());
        }
        if(e.getSource() == FichesPanel.confirm){
            System.out.println("Conferma: sono stato premuto");
            TestApp.inizio();
            actionPanel.enablePanel(true);
            fichesPanel.enablePanel(false);
            checkEnableDouble();

            if(TestApp.checkBlackjack())
                ActionPanel.standButton.doClick();
        }
        if(e.getSource() == ActionPanel.doubleButton){
            System.out.println("Double: sono stato premuto");
            TestApp.player.bet(TestApp.player.getBet());
            ActionPanel.hitButton.doClick();
            ActionPanel.standButton.doClick();
        }
        if(e.getSource() == ActionPanel.splitButton){
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
            if(TestApp.player.getAccount() < 2*fiche.getValue())
                fiche.setEnabled(false);
        }
    }

    public void checkEnableDouble(){
        if(TestApp.player.getBet() > TestApp.player.getAccount())
            ActionPanel.doubleButton.setEnabled(false);
    }
}