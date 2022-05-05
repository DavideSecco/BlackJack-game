package Panels.ControlPanel;

import GameElements.Fiche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActionPanel extends JPanel implements MyPanel{
    public static JButton hitButton;
    public static JButton standButton;

    private List<ActionListener> actionListener;

    public ActionPanel() {
        super();
        setLayout(new GridLayout(1,2));

        actionListener = new ArrayList<ActionListener>();

        hitButton = new JButton("Hit a Card");
        standButton = new JButton("Stand");

        add(hitButton);
        add(standButton);

        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    public void enablePanel(boolean bool){
        hitButton.setEnabled(bool);
        standButton.setEnabled(bool);
    }

    public void addActionListener(ActionListener actionListener) {
        hitButton.addActionListener(actionListener);
        standButton.addActionListener(actionListener);
    }
}
