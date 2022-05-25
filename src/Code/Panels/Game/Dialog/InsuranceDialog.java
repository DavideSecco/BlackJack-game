package Code.Panels.Game.Dialog;

import Code.Panels.Menu.Dialog.AbstractDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InsuranceDialog extends JDialog implements ActionListener, KeyListener {



    public static boolean siPressed = false;
    private JButton si;

    private JButton no;
    public InsuranceDialog(Frame parent) {
        super(parent, "Assicurazione");
        System.out.println("InsuranceDialog");

        JPanel allPanel = new JPanel();

        JPanel labelPanel = new JPanel();

        labelPanel.add(new JLabel("Il Dealer ha un Asso, vuoi mettere l'assicurazione?"));

        JPanel buttonPanel = new JPanel();



        si = new JButton("Si");
        no = new JButton("No");

        si.addActionListener(this);
        no.addActionListener(this);

        buttonPanel.add(si);
        buttonPanel.add(no);


        allPanel.add(labelPanel);
        allPanel.add(buttonPanel);

        getContentPane().add(allPanel, BorderLayout.CENTER);

        pack();
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == si){
            siPressed = true;
            dispose();
        }
        if(e.getSource() == no)
            dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
