package app.presentation.views.Header;

import java.awt.Dimension;

import javax.swing.JPanel;

import app.common.AppConfig;

public class Header extends JPanel {
    public Header(int height) {
        this.setPreferredSize(new Dimension(this.getSize().width, height));
        this.setBackground(AppConfig.color2);
    }
}
