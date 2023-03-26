package app.presentation.views.Header;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Header extends JPanel {
    public Header(int height, Color bgColor) {
        this.setPreferredSize(new Dimension(this.getSize().width, height));
        this.setBackground(bgColor);
    }
}
