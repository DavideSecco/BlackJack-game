package Panels;

import Cards.Card;
<<<<<<< HEAD
import Participant.Dealer;
import Participant.Participant;
import Participant.Player;
=======
import Participant.*;
>>>>>>> ebb3bab56cb7dd304c47c78a474fb8da2bb5d178

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static Main.TestApp.*;

public class TablePanel extends JPanel implements ButtonListener {
    private Image backgroundImage;
    private Image faceDownCard;

    private ArrayList<Card> playerCards;
    private ArrayList<Card> dealerCards;

    public TablePanel(Player player, Dealer dealer){
        super();
<<<<<<< HEAD
        setPreferredSize(new Dimension(848,480));
=======
        setPreferredSize(new Dimension(dimension.width, (int) (dimension.height/(1.4))));
>>>>>>> ebb3bab56cb7dd304c47c78a474fb8da2bb5d178
        try{
            backgroundImage = ImageIO.read(getClass().getResource("/images/background.png"));
            faceDownCard = ImageIO.read(getClass().getResource("/images/CardsDeck1/BackHorizontal.png"));
        } catch (IOException e) {
            System.out.println("Can't read image file");
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point cardPlayerPos = new Point(dimension.width/10, (int) (dimension.height/2.2));
        Point cardDealerPos = new Point(dimension.width/10, dimension.height/13);

        Point cardDimension = new Point(dimension.width/13, dimension.height/5);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        playerCards = player.getHand();
        dealerCards = dealer.getHand();

        for (Card card : playerCards){
            cardPlayerPos.x = cardPlayerPos.x + 110;
            g.drawImage(card.getImg(), cardPlayerPos.x, cardPlayerPos.y, cardDimension.x, cardDimension.y, this);
        }

        for (Card card : dealerCards){
            cardDealerPos.x = cardDealerPos.x + 110;
            if(card.isKnown() == true)
                g.drawImage(card.getImg(), cardDealerPos.x, cardDealerPos.y, cardDimension.x, cardDimension.y, this);
            else{
                g.drawImage(faceDownCard, cardDealerPos.x, cardDealerPos.y, cardDimension.x, cardDimension.y, this);
            }
        }
    }
<<<<<<< HEAD
=======

    @Override
    public void buttonAction(ActionEvent e) {
        System.out.println("Sono il TablePanel e ho ricevuto l'ordine di ridisegnarmi");
        this.revalidate();
        this.repaint();
    }
>>>>>>> ebb3bab56cb7dd304c47c78a474fb8da2bb5d178
}
