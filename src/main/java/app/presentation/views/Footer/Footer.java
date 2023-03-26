package app.presentation.views.Footer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Footer extends JPanel {

    public Footer(int height, Color bgColor) {
        this.setPreferredSize(new Dimension(this.getSize().width, height));
        this.setBackground(bgColor);
    }
}
