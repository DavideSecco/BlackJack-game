package Code.Panels.Game.ControlPanel;

import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Code.Panels.Game.DisplayPanel.OptionsPanel.menu;
import static Code.Panels.MainFrame.gameDimension;
import static Code.TestApp.player;

/**
 * E' il pannello dei pulsanti e dei controlli, nella mia testa ci va:
 * - tutti i pulsanti di azione di gioco (dell'ActionPanel)
 * - tutti i pulsanti delle fiches per puntare --> https://stackoverflow.com/questions/8680189/clickable-images-in-java
 */

public class ControlPanel extends JPanel {
    public static FichesPanel fichesPanel;
    public static ActionPanel actionPanel;

    public ControlPanel() throws IOException {
        super();
        setPreferredSize(new Dimension(gameDimension.width, gameDimension.height/9));
        setLayout(new GridLayout(1,2));

        fichesPanel = new FichesPanel();
        actionPanel = new ActionPanel();

        add(actionPanel);
        add(fichesPanel);
    }

    public void enablePanel(boolean bool) {
        fichesPanel.enablePanel(bool);
        actionPanel.enablePanel(bool);
    }

    public void initialize(){
        actionPanel.initialize();
        fichesPanel.initialize();
    }

    public void addActionListener(ActionListener actionListener){
        fichesPanel.addActionListener(actionListener);
        actionPanel.addActionListener(actionListener);
    }
}