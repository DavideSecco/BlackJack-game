package Code.Panels.Game.ControlPanel;

import Code.GameElements.Fiche;
import Code.Gameplay;
import Code.Panels.Game.Dialog.InsuranceDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Code.Panels.Game.ControlPanel.ActionPanel.*;
import static Code.Panels.Game.ControlPanel.ControlPanel.actionPanel;
import static Code.Panels.Game.ControlPanel.ControlPanel.fichesPanel;
import static Code.Panels.Game.DisplayPanel.OptionsPanel.menu;
import static Code.Panels.MainFrame.gameDimension;
import static Code.TestApp.*;

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

        setPreferredSize(new Dimension(gameDimension.width/12, gameDimension.height/10));
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
        ficheButton[files.length - 1].setValue(player.getAccount());

        checkEnableFiche(ficheButton);

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
            g.drawImage(fichebutton.getImg(), pos, 0, getWidth()/(ficheButton.length+1), (int) (getHeight()/(1.1)), this);
            pos += gameDimension.width/10;
        }
    }

     public void addActionListener(ActionListener actionListener) {
         this.actionListener.add(actionListener);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        click.play();
        /**
         * Se premo uno qualsiasi dei pulsanti delle fiches
         * caso particolare: All in --> lo abbiamo mappato a valore -1 va ovviamente puntato tutto quello
         */
        if(e.getSource().getClass() == Fiche.class){
            Fiche fiche = (Fiche) e.getSource();
            System.out.println(fiche.toString() + "sono stato premuto, la scommessa vale: " + fiche.getValue() );

            player.bet(fiche.getValue());

            // risetto il valore della fiche all-in
            ficheButton[files.length - 1].setValue(player.getAccount());
            System.out.println("Sono il bottone all in " + ficheButton[files.length - 1] + " Ora valgo: " + ficheButton[files.length - 1].getValue());

            fichesPanel.confirm.setEnabled(true);
        }

        if (e.getSource() == confirm) {
            System.out.println("Conferma: sono stato premuto");

            if(splitPressed == 0) {
                Gameplay.inizio();
            }
            if(splitPressed == 1){
                player.createSplitHand();
                player.swapSplittedBet();
            }

            menu.setEnabled(false);
            actionPanel.enablePanel(true);
            fichesPanel.enablePanel(false);

            checkSplit();
            checkEnableDouble();

            /** Ho commentato questo ultimo pezzo perchè all'atto pratico non fa funzionare correttamente il labelPanel  */
            if(player.hasBlackJack())
                actionPanel.standButton.doClick();
        }
        checkEnableFiche(ficheButton);

        sendToActionListeners(e);
        if(splitPressed == 0){
            checkInsurance();
        }
    }

    /** Serve a disabilitare una fiche nel caso non si abbiano abbastanza "soldi" nel conto
     * Es: Account = 90 --> La fiche da 100 sarà disabilitata  */
    public void checkEnableFiche(Fiche[] fiches){
        for(Fiche fiche : fiches){
            if(player.getAccount() < fiche.getValue())
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

    public void checkInsurance(){
        if(dealer.getCards().size() != 0){
            if(Objects.equals(dealer.getCards().get(1).getRank(), "Ace") && !player.hasBlackJack()){
                InsuranceDialog insuranceDialog = new InsuranceDialog(mainFrame);
                insuranceDialog.setVisible(true);
            }
        }
    }
}
