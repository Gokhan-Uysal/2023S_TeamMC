package app.ui.views.playermenu;

import app.common.AppConfig;
import app.domain.services.PlayerService;
import app.ui.controllers.menu.ButtonsPanelController;
import app.ui.controllers.playermenu.PlayerPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.GameFrame;
import app.ui.views.menu.ButtonsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerMenuFrame extends BaseJFrame implements ActionListener {

    private PlayerPanel playerPanel;
    private PlayerPanelController playerPanelController;

    private final JButton playButton;
    JOptionPane optionPane;


    public PlayerMenuFrame(String title, Dimension size, Point location) {
        super(title, size, location);
        this.setLayout(null);
        initilizeComponents();

        playButton = new JButton("Play");
        playButton.setBounds(1150, 620, 80, 30);
        playButton.setVisible(true);
        playButton.addActionListener(this);

        optionPane = new JOptionPane();

        buildComponents();
    }

    @Override
    public void initilizeComponents() {
        playerPanel = new PlayerPanel(50, 100);
        playerPanelController = new PlayerPanelController(playerPanel);
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
        this.add(playerPanel);
        this.add(playButton);
    }

    private JFrame getRootFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(playerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure? Do you want to start playing?");
            if (a == JOptionPane.YES_OPTION) {
                JFrame rootFrame = getRootFrame();
                Point location = rootFrame.getLocation();
                rootFrame.dispose();
                PlayerService.createPlayer(playerPanelController.newNames);
                new GameFrame(AppConfig.title, AppConfig.appSize, location);
                this.setVisible(false);
            }
        }
    }
}
