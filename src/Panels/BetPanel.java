package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.TestApp.*;

public class BetPanel extends JPanel implements ActionListener {

    private JTextArea bet;

    private JTextArea account;

    private JLabel betLabel;

    private JLabel accountLabel;

    public BetPanel() {
        super();
        setPreferredSize(new Dimension(dimension.width/12, dimension.height/10));
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

        this.add(betLabel);
        this.add(accountLabel);
        this.add(bet);
        this.add(account);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //non c'è la selezione dei bottoni qui perchè
        //a prescindere dalla fiche che si clicca bisogna
        //sempre fare queste due funzioni

        bet.setText(Integer.toString(player.getBet()));
        account.setText(Integer.toString(player.getAccount()));

    }
}
