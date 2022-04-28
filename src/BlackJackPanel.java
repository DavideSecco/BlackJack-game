import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BlackJackPanel extends JPanel implements ActionListener {
    private Image backgroundImage;

    public BlackJackPanel() throws IOException {
        super();
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

    public void init(){

    }

    public void actionPerformed(ActionEvent e){

    }
}