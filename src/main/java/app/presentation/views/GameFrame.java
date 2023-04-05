package app.presentation.views;

import app.common.AppConfig;
import app.presentation.views.Body.Body;
import app.presentation.views.Footer.Footer;
import app.presentation.views.Header.Header;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame extends BaseJFrame{

    public GameFrame(String title, Dimension size, Color bgColor) {
        super(title, size, bgColor);
        this.setLayout(new BorderLayout());
    }

    public void buildComponents(){
        this.pack();
        this.setLocation(AppConfig.screenSize);
        this.setVisible(true);

        Header header = new Header(50, AppConfig.color2);
        this.add(header, BorderLayout.NORTH);

        Body body = new Body();
        body.drawMap();
        this.add(body, BorderLayout.CENTER);

        Footer footer = new Footer(50, AppConfig.color3);
        this.add(footer, BorderLayout.SOUTH);

        this.refresh();
    }

}
