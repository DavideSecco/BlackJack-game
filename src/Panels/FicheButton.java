package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FicheButton extends JButton {
    private Image img;
    private JButton button;

    public FicheButton(String pathImage) throws IOException {
        super();
        img = ImageIO.read(getClass().getResource(pathImage));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
         g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
