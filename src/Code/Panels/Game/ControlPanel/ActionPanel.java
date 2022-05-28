package Code.Panels.Game.ControlPanel;

import Code.Gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Code.Panels.Game.ControlPanel.ControlPanel.actionPanel;
import static Code.Panels.Game.ControlPanel.ControlPanel.fichesPanel;
import static Code.Panels.Game.DisplayPanel.OptionsPanel.menu;
import static Code.TestApp.*;
import static Code.Panels.Game.GamePanel.tablePanel;


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

    public static int splitPressed = 0;
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
        this.enablePanel(false);
        splitPressed = 0;
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
        click.play();

        if(e.getSource() == hitButton){
            System.out.println("PULSANTE HIT: sono stato premuto");
            player.addKnownCard();

            splitButton.setEnabled(false);
            doubleButton.setEnabled(false);
            if(player.isBust()){
                menu.setEnabled(true);
                this.enablePanel(false);
                Gameplay.dispenserMoney();
            }
        }

        if(e.getSource() == standButton){
            System.out.println("STAND BUTTON: sono stato premuto");

            if(splitPressed == 1){
                splitPressed++;
                player.swapSplittedElements();
                tablePanel.refresh();

                if(player.hasBlackJack())
                    actionPanel.standButton.doClick();
                return;
            }

            dealer.play();

            try {
                Gameplay.endGame();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            menu.setEnabled(true);
            this.enablePanel(false);
        }

        if(e.getSource() == doubleButton){
            System.out.println("Double: sono stato premuto");
            splitButton.setEnabled(false);
            player.bet(player.getBet());
            hitButton.doClick();
            standButton.doClick();
        }
        if(e.getSource() == splitButton){
            System.out.println("Split: sono stato premuto");
            this.initialize();
            splitPressed++;
            fichesPanel.enablePanel(true);
            player.swapSplittedBet();
        }

        sendToActionListeners(e);
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }

    public static void checkSplit(){
        if((!Objects.equals(player.getCards().get(0).getRank(), player.getCards().get(1).getRank())) || splitPressed != 0)
            splitButton.setEnabled(false);
    }
}
