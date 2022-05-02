package Panels;

import Main.TestApp;

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

<<<<<<< HEAD
    public void win(){

        if(TestApp.win==true){
            this.label.setText("Complimenti, hai vinto");
        }
        else this.label.setText("Mi dispiace, hai perso");

=======
    @Override
    public void buttonAction(ActionEvent e) {
        /* qui andrá il codice relativo alle azioni dei pulsanti (?) */
>>>>>>> ebb3bab56cb7dd304c47c78a474fb8da2bb5d178
    }
}
