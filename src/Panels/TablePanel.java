package Panels;

import Cards.Card;
import Participant.Dealer;
import Participant.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    private Image backgroundImage;
    private Image faceDownCard;

    private ArrayList<Card> playerCards;
    private ArrayList<Card> dealerCards;

    public TablePanel(Player player, Dealer dealer){
        super();
        setPreferredSize(new Dimension(1280,600));
        try{
            backgroundImage = ImageIO.read(getClass().getResource("/images/background.png"));
            faceDownCard = ImageIO.read(getClass().getResource("/images/CardsDeck1/BackHorizontal.png"));
        } catch (IOException e) {
            System.out.println("Can't read image file");
        }

        playerCards = player.getHand();
        dealerCards = dealer.getHand();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point cardPlayerPos = new Point(100, 350);
        Point cardDealerPos = new Point(100, 50);

        Point cardDimension = new Point(90, 135);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

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


}
