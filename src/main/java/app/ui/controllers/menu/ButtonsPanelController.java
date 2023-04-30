package app.ui.controllers.menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.ui.controllers.menu.playermenu.PlayerMenuController;
import app.ui.controllers.menu.playermenu.PlayerPanelController;
import app.ui.views.menu.ButtonsPanel;
import app.ui.views.menu.playermenu.PlayerMenuFrame;
import app.util.ActionListenerUtil;

public class ButtonsPanelController implements ActionListener {
    private ButtonsPanel buttonsPanel;

    public ButtonsPanelController(ButtonsPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
        ActionListenerUtil.addActionListener(buttonsPanel.startButton, this);
        ActionListenerUtil.addActionListener(buttonsPanel.settingsButton, this);
        ActionListenerUtil.addActionListener(buttonsPanel.exitButton, this);
    }

    private JFrame getRootFrame() {
        JFrame rootFrame = (JFrame) SwingUtilities.getWindowAncestor(buttonsPanel);
        return rootFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buttonsPanel.startButton) {
            JFrame rootFrame = getRootFrame();
            Point location = rootFrame.getLocation();
            rootFrame.dispose();

            PlayerMenuFrame playerMenuFrame = new PlayerMenuFrame(AppConfig.title, AppConfig.appSize, location);
            PlayerMenuController playerMenuController = new PlayerMenuController(playerMenuFrame);
        }
        else if (e.getSource()==buttonsPanel.exitButton) {
            System.exit(0);
        }
        else if (e.getSource()==buttonsPanel.settingsButton) {
            //Settings Button Action Listener
        }

    }
}
