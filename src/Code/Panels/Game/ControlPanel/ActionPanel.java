package Code.Panels.Game.ControlPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Comprende i pulsanti di azione del gioco:
 * - pulsante per chiedere un'altra carta: "hit a card"
 * - pulsante per dire che sei a posto: "stay"
 * - pulsante per fare il double: "double"
 * - pulsante per fare lo split: "split"
 */
public class ActionPanel extends JPanel implements MyPanel{
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

        enablePanel(false);
    }

    public void enablePanel(boolean bool){
        hitButton.setEnabled(bool);
        standButton.setEnabled(bool);
        splitButton.setEnabled(bool);
        doubleButton.setEnabled(bool);
    }

    public void addActionListener(ActionListener actionListener) {
        hitButton.addActionListener(actionListener);
        standButton.addActionListener(actionListener);
        splitButton.addActionListener(actionListener);
        doubleButton.addActionListener(actionListener);
    }
}
