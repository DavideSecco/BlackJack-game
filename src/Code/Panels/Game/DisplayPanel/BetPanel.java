package Code.Panels.Game.DisplayPanel;

import Code.GameElements.Fiche;
import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import static Code.Panels.MainFrame.gameDimension;
import static Code.TestApp.*;

public class BetPanel extends JPanel implements ActionListener {
    private JTextArea bet;
    private JTextArea account;
    private JLabel betLabel;
    private JLabel accountLabel;

    public BetPanel() {
        super();
        setPreferredSize(new Dimension(gameDimension.width/12, gameDimension.height/10));
        setLayout(new GridLayout(2,2));

        bet = new JTextArea("0");                                        //Ho scelto delle textaree per estetica,
        account = new JTextArea(Integer.toString(player.getAccount()));  //mi sembrava più carino
        betLabel= new JLabel("Bet:");
        accountLabel= new JLabel("Account:");

        /*

        //COLORI, per ora rimane commentato perchè non riesco a trovare dei colori che mi convincano

        bet.setBackground(Color.DARK_GRAY);
        bet.setForeground(Color.WHITE);
        account.setBackground(Color.DARK_GRAY);
        account.setForeground(Color.WHITE);

        */

        //BORDI

        bet.setBorder(BorderFactory.createBevelBorder(1));
        account.setBorder(BorderFactory.createBevelBorder(1));
        betLabel.setBorder(BorderFactory.createBevelBorder(1));
        accountLabel.setBorder(BorderFactory.createBevelBorder(1));

        bet.setEditable(false);
        account.setEditable(false);

        add(betLabel);
        add(accountLabel);
        add(bet);
        add(account);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass() == Fiche.class){
            System.out.println("Sono il betPanel, ho sentito: " + e.getSource().toString() + "Per me la scommessa vale " + Integer.toString(player.getBet()));

            bet.setText(Integer.toString(player.getBet()));
            account.setText(Integer.toString(player.getAccount()));

            System.out.println("Ho risritto il valore");
        }
    }

    public void initialize(){
        bet.setText("0");
        account.setText(Integer.toString(player.getAccount()));
    }
}
