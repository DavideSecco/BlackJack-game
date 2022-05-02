package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import Main.*;

import static Main.TestApp.dimension;

public class DisplayPanel extends JPanel implements ButtonListener{
    private JLabel label;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(dimension.width, dimension.height/10));
        JLabel label = new JLabel("Qui è dove andranno i messaggi");

        this.add(label);
    }

    @Override
    public void buttonAction(ActionEvent e) {
        /* qui andrá il codice relativo alle azioni dei pulsanti (?) */
    }
}
