package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Fiche extends JButton {
    private int value;
    private String pathFile;
    private Image img;

    public Fiche(int value, String pathFile) {
        super();
        this.value = value;
        this.pathFile = pathFile;
        try{
            this.img = ImageIO.read(getClass().getResource(pathFile));
        } catch (IOException e){
            System.out.println("Errore nel caricamento dell'immagine " + pathFile);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
