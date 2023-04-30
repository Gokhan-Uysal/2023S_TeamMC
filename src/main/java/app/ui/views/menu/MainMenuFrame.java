package app.ui.views.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;

import app.ui.controllers.menu.ButtonsPanelController;
import app.ui.views.components.BaseJFrame;

public class MainMenuFrame extends BaseJFrame {
    private ButtonsPanel buttonsPanel;
    private ButtonsPanelController buttonsPanelController;

    public MainMenuFrame(String title, Dimension size) {
        super(title, size);
        this.setLayout(new BorderLayout());
        initializeComponents();
        buildComponents();
    }

    @Override
    public void initializeComponents() {
        buttonsPanel = new ButtonsPanel(3, 1);
        buttonsPanelController = new ButtonsPanelController(buttonsPanel);
    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setVisible(true);

        addComponents();

        this.refresh();
    }

    @Override
    public void addComponents() {
        this.add(buttonsPanel, BorderLayout.CENTER);
    }
}
