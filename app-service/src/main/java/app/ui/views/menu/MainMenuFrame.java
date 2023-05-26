package app.ui.views.menu;

import java.awt.BorderLayout;

import app.common.AppConfig;
import app.ui.controllers.menu.ButtonsPanelController;
import app.ui.views.components.BaseJFrame;

public class MainMenuFrame extends BaseJFrame {
    private ButtonsPanel buttonsPanel;
    private ButtonsPanelController buttonsPanelController;

    public MainMenuFrame() {
        super("Main Menu", AppConfig.appSize);
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
