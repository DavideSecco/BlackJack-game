package Panels;

import Main.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



import static Main.TestApp.dimension;

public class DisplayPanel extends JPanel implements ButtonListener {
    private JLabel label;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(dimension.width, dimension.height/10));
        label = new JLabel("Pesca una carta o rimani con quelle che hai");

        this.add(label);
    }

    @Override
    public void buttonAction(ActionEvent e, JButton button) {

        if(e.getSource() == button){
            if(TestApp.win==1)
                label.setText("Hai vinto");
            else if(TestApp.win==0)
                label.setText("Pareggio");
            else label.setText("Hai perso");
        }
        if(TestApp.player.getBust()==true) label.setText("Hai Sballato");


    }


}