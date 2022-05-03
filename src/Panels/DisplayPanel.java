package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.TestApp.*;

public class DisplayPanel extends JPanel implements ActionListener {
    private JLabel label;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(dimension.width, dimension.height/10));
        label = new JLabel("Pesca una carta o rimani con quelle che hai");

        this.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ControlPanel.standButton){
            if(whoWon() == 1)
                label.setText("Hai vinto");
            else if(whoWon() == 0)
                label.setText("Pareggio");
            else
                label.setText("Hai perso");
        }

        if(e.getSource() == ControlPanel.hitButton){
            if(player.isBust())
                label.setText("Hai Sballato");
        }
    }
}