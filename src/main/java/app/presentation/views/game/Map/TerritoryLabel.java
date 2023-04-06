package app.presentation.views.game.Map;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import app.common.Logger;

public class TerritoryLabel extends JLabel {
    public String name;

    public TerritoryLabel(String name, Image resizedImage) {
        super(new ImageIcon(resizedImage));
        this.buildClass(name);
        this.buildView();
    }

    private void buildClass(String name) {
        this.name = name;
    }

    private void buildView() {
        this.setPreferredSize(new Dimension(15, 15));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onClick();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                onHover();
            }
        });
    }

    private void onClick() {
        Logger.log(String.format("Clicked on %s", this.name));
    }

    private void onHover() {
        Logger.log(String.format("On %s", this.name));
    }
}
