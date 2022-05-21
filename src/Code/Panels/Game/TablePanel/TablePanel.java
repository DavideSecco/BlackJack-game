package Code.Panels.Game.TablePanel;

import Code.GameElements.Card;
import Code.TestApp;
import Code.Panels.Game.ControlPanel.ActionPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static Code.Panels.MainFrame.gameDimension;

public class TablePanel extends JPanel implements ActionListener {
    private Image backgroundImage;
    private Image faceDownCard;

    private ArrayList<Card> playerCards;

    private ArrayList<Card> playerSplittedCards;
    private ArrayList<Card> dealerCards;

    public TablePanel(){
        super();
        setPreferredSize(new Dimension(gameDimension.width, (int) (((gameDimension.height)/(1.45)))));
        try{
            backgroundImage = ImageIO.read(getClass().getResource(TestApp.imagesPath + "background.png"));
            faceDownCard = ImageIO.read(getClass().getResource(TestApp.imagesPath + "CardsDeck1/BackHorizontal.png"));
        } catch (IOException e) {
            System.out.println("Can't read image file");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point cardPlayerPos = new Point(gameDimension.width/10, (int) (gameDimension.height/2.2));
        Point cardDealerPos = new Point(gameDimension.width/10, gameDimension.height/13);

        Point cardDimension = new Point(gameDimension.width/13, gameDimension.height/5);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        playerCards = TestApp.player.getCards();
        playerSplittedCards = TestApp.player.getSplittedCards();
        dealerCards = TestApp.dealer.getCards();

        for (Card card : playerCards){
            cardPlayerPos.x = cardPlayerPos.x + 110;
            g.drawImage(card.getImg(), cardPlayerPos.x, cardPlayerPos.y, cardDimension.x, cardDimension.y, this);
        }

        cardPlayerPos.x = cardPlayerPos.x + 55;
        for (Card card : playerSplittedCards){
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

        revalidate();
        repaint();
    }

    public void initialize() {
        revalidate();
        repaint();
    }
}