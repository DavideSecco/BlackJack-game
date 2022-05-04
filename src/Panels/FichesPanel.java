package Panels;

import GameElements.Fiche;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Main.TestApp.dimension;

public class FichesPanel extends JPanel{
    private String[] files;
    private int[] values;
    public static Fiche[] ficheButton;

    public FichesPanel() throws IOException {
        super();
        files = new String[]{"fiche100.jpg", "fiche50.png", "fiche10.png" };
        values = new int[] {100, 50, 10};
        ficheButton = new Fiche[files.length];

        setPreferredSize(new Dimension(dimension.width/12,dimension.height/9));
        setLayout(new GridLayout(1,3));

        for(int i = 0; i < ficheButton.length; i++){
            ficheButton[i] = new Fiche(values[i], files[i]);
            add(ficheButton[i]);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int pos = 0;

        for(Fiche fichebutton : ficheButton){
            fichebutton.setContentAreaFilled(false);
            g.drawImage(fichebutton.getImg(), pos, 0, getWidth()/ficheButton.length, getHeight(), this);
            pos += dimension.width/9;
        }
    }
}
