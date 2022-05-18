package Code.Panels.Game;

import Code.Panels.Game.ControlPanel.ControlPanel;
import Code.Panels.Game.DisplayPanel.DisplayPanel;
import Code.Panels.Game.TablePanel.TablePanel;
import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * GamePanel: è il pannello visualizzato durante la partita, ed è composto da 3 pannelli:
 * -------------------------------------------------------------------------------
 *              DisplayPanel: Pannello dove si spiega cosa succede all'utente
 * -------------------------------------------------------------------------------
 *
 *              TablePanel: Finestra di gioco effettiva, qui compaiono le carte e il tavolo
 *
 * -------------------------------------------------------------------------------
 *              ControlPanel: Pannello dove ci sono i comandi che l'utente deve premere
 *              (hit a card, stand, puntate...)
 * -------------------------------------------------------------------------------
 */

public class GamePanel extends JPanel {
    public static DisplayPanel displayPanel;
    public static TablePanel tablePanel;
    public static ControlPanel controlPanel;

    public GamePanel() throws IOException {
        super();
        setPreferredSize(TestApp.gameDimension);

        displayPanel = new DisplayPanel();
        add(BorderLayout.PAGE_START, displayPanel);

        tablePanel = new TablePanel();
        add(BorderLayout.CENTER, tablePanel);

        controlPanel = new ControlPanel();
        add(BorderLayout.PAGE_END,controlPanel);

        controlPanel.addActionListener(displayPanel.betPanel);
        controlPanel.addActionListener(displayPanel.labelPanel);
        controlPanel.addActionListener(tablePanel);


        displayPanel.optionsPanel.addActionListener(tablePanel);
        displayPanel.optionsPanel.addActionListener(displayPanel.labelPanel);
        displayPanel.optionsPanel.addActionListener(displayPanel.betPanel);
    }

    public void initialize(){
        displayPanel.initialize();
        tablePanel.initialize();
        controlPanel.initialize();
    }
}