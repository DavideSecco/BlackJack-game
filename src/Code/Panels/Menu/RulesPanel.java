package Code.Panels.Menu;

import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static Code.Panels.MainFrame.mainPanel;
import static Code.Panels.MainPanel.menuPanel;

public class RulesPanel extends JPanel implements ActionListener {

    private JLabel label;

    private JButton menu;
    public RulesPanel() {
        super();
        String all;
        try {                                                         //l'ho vista da un video questa funzione
            all = new Scanner(new File("src/Utils/rules.txt")).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        menu = new JButton("Menu principale");
        menu.addActionListener(this);
        add(menu);

        label = new JLabel();
        label.setText(all);
        JScrollPane scroller = new JScrollPane(label);

        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Dimension scrollerDimension = new Dimension(1150,600); //uso un'altra dimension perchè voglio che la dimensione del
        scroller.setPreferredSize(scrollerDimension);                      //sia un po' più piccola della finestra
        add(scroller);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menu){
            mainPanel.changePanel(menuPanel);
        }
    }
}
