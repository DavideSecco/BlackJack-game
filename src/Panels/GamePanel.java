package Panels;

import Cards.CardsDeck;
import Participant.Dealer;
import Participant.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GamePanel extends JPanel implements ActionListener{
    private JPanel totalPanel;

    private DisplayPanel displayPanel;
    private TablePanel tablePanel;
    private ControlPanel controlPanel;


    public GamePanel(Player player, Dealer dealer) throws IOException {
        super();
        setPreferredSize(new Dimension(1280, 780));
        JPanel totalPanel = new JPanel(new BorderLayout());

        DisplayPanel displayPanel = new DisplayPanel();
        totalPanel.add(BorderLayout.PAGE_START, displayPanel);

        TablePanel tablePanel = new TablePanel(player, dealer);
        totalPanel.add(BorderLayout.CENTER, tablePanel);

        ControlPanel controlPanel = new ControlPanel();
        totalPanel.add(BorderLayout.PAGE_END,controlPanel);

        controlPanel.setButtonListener(new ButtonListener() {
            @Override
            public void buttonAction(ActionEvent e) {
                System.out.println("GAME PANEL/TABLE PANEL: Hai premuto il tasto:" + e.getSource());
            }
        });


        this.add(totalPanel);
    }




    public void actionPerformed(ActionEvent e){

    }
}