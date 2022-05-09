package Panels.Game;

import GameElements.CardsDeck;
import Panels.Game.ControlPanel.ControlPanel;
import Panels.Game.DisplayPanel.DisplayPanel;
import Participant.Dealer;
import Participant.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Main.TestApp.gameDimension;
import static Panels.Game.DisplayPanel.DisplayPanel.betPanel;
import static Panels.Game.DisplayPanel.DisplayPanel.optionsPanel;

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

    public static DisplayPanel displayPanel;
    TablePanel tablePanel;
    public static ControlPanel controlPanel;

    public GamePanel(Player player, Dealer dealer, CardsDeck cardsDeck) throws IOException {
        super();
        setPreferredSize(gameDimension);
        totalPanel = new JPanel(new BorderLayout());

        displayPanel = new DisplayPanel();
        totalPanel.add(BorderLayout.PAGE_START, displayPanel);

        tablePanel = new TablePanel(player, dealer);
        totalPanel.add(BorderLayout.CENTER, tablePanel);

        controlPanel = new ControlPanel(player, cardsDeck);
        totalPanel.add(BorderLayout.PAGE_END,controlPanel);

        controlPanel.addActionListener(tablePanel);
        controlPanel.addActionListener(displayPanel);
        controlPanel.addActionListener(betPanel);

        optionsPanel.addActionListener(tablePanel);
        optionsPanel.addActionListener(displayPanel);
        optionsPanel.addActionListener(betPanel);

        this.add(totalPanel);
    }
}