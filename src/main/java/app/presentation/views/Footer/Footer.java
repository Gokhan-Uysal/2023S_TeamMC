package app.presentation.views.Footer;

import java.awt.Dimension;

import javax.swing.JPanel;

import app.common.AppConfig;

public class Footer extends JPanel {

    public Footer(int height) {
        this.setPreferredSize(new Dimension(this.getSize().width, height));
        this.setBackground(AppConfig.color3);
    }
}
