package Code.Panels.Game.ControlPanel;

import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static Code.Panels.Game.ControlPanel.ControlPanel.actionPanel;
import static Code.Panels.Game.ControlPanel.ControlPanel.fichesPanel;
import static Code.Panels.Game.DisplayPanel.OptionsPanel.menu;
import static Code.TestApp.*;


/**
 * Comprende i pulsanti di azione del gioco:
 * - pulsante per chiedere un'altra carta: "hit a card"
 * - pulsante per dire che sei a posto: "stay"
 * - pulsante per fare il double: "double"
 * - pulsante per fare lo split: "split"
 */
public class ActionPanel extends JPanel implements MyPanel, ActionListener{
    public static JButton hitButton;
    public static JButton standButton;
    public static JButton splitButton;
    public static JButton doubleButton;

    private List<ActionListener> actionListener;

    public ActionPanel() {
        super();
        setLayout(new GridLayout(1,4));

        actionListener = new ArrayList<ActionListener>();

        hitButton = new JButton("Hit a Card");
        standButton = new JButton("Stand");
        doubleButton = new JButton("Double");
        splitButton = new JButton("Split");

        add(hitButton);
        add(standButton);
        add(doubleButton);
        add(splitButton);

        hitButton.addActionListener(this);
        standButton.addActionListener(this);
        splitButton.addActionListener(this);
        doubleButton.addActionListener(this);

        initialize();
    }

    public void initialize(){
        enablePanel(false);
    }

    public void enablePanel(boolean bool){
        hitButton.setEnabled(bool);
        standButton.setEnabled(bool);
        splitButton.setEnabled(bool);
        doubleButton.setEnabled(bool);
    }

    public void addActionListener(ActionListener actionListener){
        this.actionListener.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            player.addKnownCard();

            if(player.isBust()){
                menu.setEnabled(true);
                this.enablePanel(false);
                TestApp.dispenserMoney();
            }
        }
        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");
            dealer.play(cardsDeck);

            TestApp.dispenserMoney();
            TestApp.managePlayerWins();

            menu.setEnabled(true);
            this.enablePanel(false);
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
}
