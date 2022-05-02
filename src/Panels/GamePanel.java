package Panels;

import Cards.CardsDeck;
import Participant.Dealer;
import Participant.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GamePanel extends JPanel implements ActionListener{
    private JPanel totalPanel;

    private DisplayPanel displayPanel;
    private TablePanel tablePanel;
    private ControlPanel controlPanel;

    public GamePanel(Player player, Dealer dealer, CardsDeck cardsDeck) throws IOException {
        super();
        setPreferredSize(dimension);
        JPanel totalPanel = new JPanel(new BorderLayout());

        displayPanel = new DisplayPanel();
        totalPanel.add(BorderLayout.PAGE_START, displayPanel);

        tablePanel = new TablePanel(player, dealer);
        totalPanel.add(BorderLayout.CENTER, tablePanel);

        controlPanel = new ControlPanel(player, cardsDeck);
        totalPanel.add(BorderLayout.PAGE_END,controlPanel);

        controlPanel.addButtonListener(tablePanel);
        controlPanel.addButtonListener(displayPanel);


        this.add(totalPanel);
    }




    public void actionPerformed(ActionEvent e){

    }
}