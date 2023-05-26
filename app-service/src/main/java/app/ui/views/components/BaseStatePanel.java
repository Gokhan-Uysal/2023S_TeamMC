package app.ui.views.components;

import java.awt.*;
import javax.swing.*;

public abstract class BaseStatePanel extends JPanel implements IBaseStatePanel {
    public JLabel _infoLabel;

    public BaseStatePanel(String info) {
        this._infoLabel = new JLabel(info);
        Font currentFont = _infoLabel.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), 24);
        _infoLabel.setFont(newFont);
        this.setBackground(Color.lightGray);
        this.add(_infoLabel);
    }

    public JFrame getRootFrame(Component component) {
        JFrame rootFrame = (JFrame) SwingUtilities.getWindowAncestor(component);
        return rootFrame;
    }
}
