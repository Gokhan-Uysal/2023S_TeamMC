package app.presentation.views.Body.Map;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import app.common.Logger;

public class Teritory extends JPanel {
    public String name;

    private ArrayList<Integer> borders;

    public Teritory(String name, ArrayList<Integer> borders) {
        this.buildClass(name, borders);
        this.buildView();
    }

    private void buildClass(String name, ArrayList<Integer> borders) {
        this.name = name;
        this.borders = borders;
    }

    private void buildView() {
        this.setBackground(Color.CYAN);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                update();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                update();
            }
        });
    }

    private void update() {
        Logger.log(String.format("Clicked on %s", this.name));
    }
}
