package GameElements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Fiche extends JButton {
    private int value;
    private String file;
    private Image img;

    public Fiche(int value, String file) {
        super();
        this.value = value;
        this.file = file;
        try{
            this.img = ImageIO.read(getClass().getResource("/images/" + file));
        } catch (IOException e){
            System.out.println("Errore nel caricamento dell'immagine " + file);
        }
    }

    public int getValue() { return value; }

    public Image getImg() {
        return img;
    }
}
