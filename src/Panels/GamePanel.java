package Panels;

import javax.imageio.ImageIO;
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

public class GamePanel extends JPanel implements ActionListener {
    private JPanel totalPanel;

    private DisplayPanel displayPanel;
    private TablePanel tablePanel;
    private ControlPanel controlPanel;


    public GamePanel() throws IOException {
        super();
        setPreferredSize(new Dimension(1280, 780));
        JPanel totalPanel = new JPanel(new BorderLayout());

        DisplayPanel displayPanel = new DisplayPanel();
        totalPanel.add(BorderLayout.PAGE_START, displayPanel);

        TablePanel tablePanel = new TablePanel();
        totalPanel.add(BorderLayout.CENTER, tablePanel);

        ControlPanel controlPanel = new ControlPanel();
        totalPanel.add(BorderLayout.PAGE_END,controlPanel);

        this.add(totalPanel);
    }


    public void init(){

    }

    public void actionPerformed(ActionEvent e){

    }
}