package Code.Panels.Game.ControlPanel;

import Code.GameElements.Fiche;
import Code.Gameplay;
import Code.TestApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Code.Panels.Game.ControlPanel.ControlPanel.actionPanel;
import static Code.Panels.Game.ControlPanel.ControlPanel.fichesPanel;
import static Code.Panels.Game.DisplayPanel.OptionsPanel.menu;
import static Code.TestApp.player;

public class FichesPanel extends JPanel implements ActionListener{
    private String[] files;
    private int[] values;

    public static Fiche[] ficheButton;
    public static JButton confirm;

    private List<ActionListener> actionListener;

    public FichesPanel() throws IOException {
        super();
        files = new String[]{"fiche100.jpg", "fiche50.png", "fiche10.png", "ficheAI.jpg"};
        values = new int[] {100, 50, 10, -1};
        ficheButton = new Fiche[files.length];
        confirm = new JButton("Conferma");

        actionListener = new ArrayList<ActionListener>();

        setPreferredSize(new Dimension(TestApp.gameDimension.width/12, TestApp.gameDimension.height/9));
        setLayout(new GridLayout(1,4));

        for(int i = 0; i < ficheButton.length; i++) {
            ficheButton[i] = new Fiche(values[i], files[i]);
            add(ficheButton[i]);
            ficheButton[i].addActionListener(this);
        }
        add(confirm);
        confirm.addActionListener(this);

        initialize();
    }

    public void initialize(){
        for(int i = 0; i < ficheButton.length; i++){
            ficheButton[i].setEnabled(true);
        }
        confirm.setEnabled(false);
    }

    public void enablePanel(boolean bool){
        for(Fiche fiche : ficheButton)
            fiche.setEnabled(bool);
        confirm.setEnabled(bool);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int pos = 0;

        for(Fiche fichebutton : ficheButton){
            fichebutton.setContentAreaFilled(false);
            g.drawImage(fichebutton.getImg(), pos, 0, getWidth()/ficheButton.length+1, getHeight(), this);
            pos += TestApp.gameDimension.width/10;
        }
    }

     public void addActionListener(ActionListener actionListener) {
         this.actionListener.add(actionListener);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkEnableFiche(ficheButton);              // <----        per comodità lo scrivo qui, ma potrei anche metterlo semplicemente nei pulsanti delle fiche

        /**
         * Se premo uno qualsiasi dei pulsanti delle fiches
         * caso particolare: All in --> lo abbiamo mappato a valore -1 va ovviamente puntato tutto quello
         */
        if(e.getSource().getClass() == Fiche.class){
            Fiche fiche = (Fiche) e.getSource();
            System.out.println(fiche.toString() + "sono stato premuto, la scommessa vale: " + fiche.getValue() );

            // risetto il valore della fiche all-in
            ficheButton[files.length - 1].setValue(player.getAccount());

            player.bet(fiche.getValue());

            fichesPanel.confirm.setEnabled(true);
        }

        if (e.getSource() == confirm) {
            System.out.println("Conferma: sono stato premuto");

            Gameplay.inizio();

            menu.setEnabled(false);
            actionPanel.enablePanel(true);
            fichesPanel.enablePanel(false);
            checkEnableDouble();

            if(player.hasBlackJack())
                actionPanel.standButton.doClick();
        }

        sendToActionListeners(e);
    }

    /** Serve a disabilitare una fiche nel caso non si abbiano abbastanza "soldi" nel conto
     * Es: Account = 90 --> La fiche da 100 sarà disabilitata  */
    public void checkEnableFiche(Fiche[] fiches){
        for(Fiche fiche : fiches){
            if(player.getAccount() < 2*fiche.getValue())
                fiche.setEnabled(false);
        }
    }

    public void sendToActionListeners(ActionEvent e){
        for(ActionListener actionListener : actionListener){
            actionListener.actionPerformed(e);
        }
    }

    public void checkEnableDouble(){
        if(player.getBet() > player.getAccount())
            ActionPanel.doubleButton.setEnabled(false);
    }
}
