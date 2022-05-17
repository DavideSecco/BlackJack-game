package Code.Panels.Game;

import Code.GameElements.Card;
import Code.TestApp;
import Code.Panels.Game.ControlPanel.ActionPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.TextStyle;
import java.util.ArrayList;

public class TablePanel extends JPanel implements ActionListener {
    private Image backgroundImage;
    private Image faceDownCard;

    private ArrayList<Card> playerCards;
    private ArrayList<Card> dealerCards;

    public TablePanel(){
        super();
        setPreferredSize(new Dimension(TestApp.gameDimension.width, (int) (TestApp.gameDimension.height/(1.4))));
        try{
            backgroundImage = ImageIO.read(getClass().getResource(TestApp.imagesPath + "background.png"));
            faceDownCard = ImageIO.read(getClass().getResource(TestApp.imagesPath + "CardsDeck1/BackHorizontal.png"));
        } catch (IOException e) {
            System.out.println("Can't read image file");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point cardPlayerPos = new Point(TestApp.gameDimension.width/10, (int) (TestApp.gameDimension.height/2.2));
        Point cardDealerPos = new Point(TestApp.gameDimension.width/10, TestApp.gameDimension.height/13);

        Point cardDimension = new Point(TestApp.gameDimension.width/13, TestApp.gameDimension.height/5);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        playerCards = TestApp.player.getCards();
        dealerCards = TestApp.dealer.getCards();

        for (Card card : playerCards){
            cardPlayerPos.x = cardPlayerPos.x + 110;
            g.drawImage(card.getImg(), cardPlayerPos.x, cardPlayerPos.y, cardDimension.x, cardDimension.y, this);
        }
        playerCards = TestApp.player.getSplittedCards();
        cardPlayerPos.x = cardPlayerPos.x + 55;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Sono il TablePanel e ho ricevuto l'ordine di ridisegnarmi");

        if(e.getSource() == ActionPanel.standButton || TestApp.player.isBust())
            TestApp.dealer.discoverAll();   //scopro la carta scoperta

        this.revalidate();
        this.repaint();
    }
}