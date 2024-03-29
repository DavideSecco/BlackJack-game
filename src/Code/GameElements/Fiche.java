package Code.GameElements;

import Code.TestApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Fiche extends JButton {
    private int value;
    private String file;
    private Image img;

    public Fiche(int value, String file) {
        super();
        this.value = value;
        this.file = file;
        try{
            this.img = ImageIO.read(Objects.requireNonNull(getClass().getResource(TestApp.imagesPath + file)));
        } catch (IOException e){
            System.out.println("Errore nel caricamento dell'immagine " + file);
        }
    }

    public int getValue() { return value; }

    public Image getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "Fiche{" + "value=" + value + "}";
    }

    public void setValue(int value) {
        this.value = value;
    }
}
