package app.ui.controllers.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.domain.services.Map.*;
import app.ui.views.game.GameFrame;
import app.ui.views.menu.ButtonsPanel;
import app.util.ActionListenerUtil;

public class ButtonsPanelController implements ActionListener {
    private ButtonsPanel buttonsPanel;

    public ButtonsPanelController(ButtonsPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
        ActionListenerUtil.addActionListener(buttonsPanel.startButton, this);
    }

    private JFrame getRootFrame() {
        JFrame rootFrame = (JFrame) SwingUtilities.getWindowAncestor(buttonsPanel);
        return rootFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame rootFrame = getRootFrame();
        Point location = rootFrame.getLocation();
        rootFrame.dispose();

        // Initialize dependencies
        MapReadService mapReadService = new MapReadService("src/main/java/app/resources/map.json");
        MapGraphService mapGraphService = new MapGraphService();
        MapFactory mapFactory = new MapFactory(mapReadService, mapGraphService);
        MapService mapService = new MapService(mapFactory, mapGraphService);

        // Create the GameFrame object with the initialized dependencies
        new GameFrame(AppConfig.title, AppConfig.appSize, location, mapService);
    }
}
