package Panels;

import Main.TestApp;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private JLabel label;

    public DisplayPanel(){
        super();
        setPreferredSize(new Dimension(1280, 60));
        JLabel label = new JLabel("Qui è dove andranno i messaggi");

        this.add(label);
    }

    public void win(){

        if(TestApp.win==true){
            this.label.setText("Complimenti, hai vinto");
        }
        else this.label.setText("Mi dispiace, hai perso");

    }
}
