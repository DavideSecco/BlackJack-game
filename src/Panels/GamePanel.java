package Panels;

import GameElements.CardsDeck;
import Participant.Dealer;
import Participant.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Main.TestApp.dimension;

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
    JPanel totalPanel;

    DisplayPanel displayPanel;
    TablePanel tablePanel;
    ControlPanel controlPanel;

    public GamePanel(Player player, Dealer dealer, CardsDeck cardsDeck) throws IOException {
        super();
        setPreferredSize(dimension);
        totalPanel = new JPanel(new BorderLayout());

        displayPanel = new DisplayPanel();
        totalPanel.add(BorderLayout.PAGE_START, displayPanel);

        tablePanel = new TablePanel(player, dealer);
        totalPanel.add(BorderLayout.CENTER, tablePanel);

        controlPanel = new ControlPanel(player, cardsDeck);
        totalPanel.add(BorderLayout.PAGE_END,controlPanel);

        controlPanel.addActionListener(tablePanel);
        controlPanel.addActionListener(displayPanel);
        controlPanel.addActionListener(displayPanel.betPanel);

        this.add(totalPanel);
    }

    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }
}