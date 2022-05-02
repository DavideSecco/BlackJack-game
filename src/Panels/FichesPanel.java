package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static Main.TestApp.dimension;

public class FichesPanel extends JPanel{
    private String path100 = "/images/fiche100.jpg";
    private final FicheButton button100;

    private String path50 = "/images/fiche50.png";
    private FicheButton button50;

    private String path10 = "/images/fiche10.png";
    private FicheButton button10;

    public FichesPanel() throws IOException {
        super();
        setPreferredSize(new Dimension(dimension.width/12,dimension.height/9));
        setLayout(new GridLayout(1,3));

        button100 = new FicheButton(path100);
        button50 = new FicheButton(path50);
        button10 = new FicheButton(path10);

        // button100.addActionListener(this);

        add(button100);
        add(button50);
        add(button10);
    }
}
