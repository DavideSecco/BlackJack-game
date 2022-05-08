package Panels.ControlPanel;

import GameElements.Fiche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Main.TestApp.dimension;
import static Main.TestApp.player;

public class FichesPanel extends JPanel implements MyPanel{
    private String[] files;
    private int[] values;

    public static Fiche[] ficheButton;
    public static JButton confirm;

    private List<ActionListener> actionListener;

    public FichesPanel() throws IOException {
        super();
        files = new String[]{"fiche100.jpg", "fiche50.png", "fiche10.png", "ficheAI.jpg"};
        values = new int[] {100, 50, 10, 0};
        ficheButton = new Fiche[files.length];
        confirm = new JButton("Conferma");

        actionListener = new ArrayList<ActionListener>();

        setPreferredSize(new Dimension(dimension.width/12,dimension.height/9));
        setLayout(new GridLayout(1,4));



        for(int i = 0; i < ficheButton.length; i++) {
            ficheButton[i] = new Fiche(values[i], files[i]);
            add(ficheButton[i]);
        }
        add(confirm);

        inizialize();
    }

    public void inizialize(){
        for(int i = 0; i < ficheButton.length; i++){
            ficheButton[i].setEnabled(true);
        }
        confirm.setEnabled(false);
    }

    public void enablePanel(boolean bool){
        for(Fiche fiche : ficheButton)
            fiche.setEnabled(bool);
        confirm.setEnabled(bool);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int pos = 0;

        for(Fiche fichebutton : ficheButton){
            fichebutton.setContentAreaFilled(false);
            g.drawImage(fichebutton.getImg(), pos, 0, getWidth()/ficheButton.length+1, getHeight(), this);
            pos += dimension.width/10;
        }
    }

    public void addActionListener(ActionListener actionListener) {
        for(Fiche fiche : ficheButton)
            fiche.addActionListener(actionListener);

        confirm.addActionListener(actionListener);
    }
}
