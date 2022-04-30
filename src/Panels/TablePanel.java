package Panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TablePanel extends JPanel {
    private Image backgroundImage;

    public TablePanel(){
        super();
        setPreferredSize(new Dimension(1280,600));
        try{
            backgroundImage = ImageIO.read(getClass().getResource("/images/background.png"));
        } catch (IOException e) {
            System.out.println("Can't read input file");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
