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

    private PlayerPanel playerPanel1;
    private PlayerPanelController playerPanelController1;

    private PlayerPanel playerPanel2;
    private PlayerPanelController playerPanelController2;

    private final JLabel welcomeLabel;
    private final JLabel contextLabel;
    private final JButton playButton;
    JOptionPane optionPane;


    public PlayerMenuFrame(String title, Dimension size, Point location) {
        super(title, size, location);
        this.setLayout(null);
        initilizeComponents();

        welcomeLabel = new JLabel("Welcome to ConKUeror");
        contextLabel = new JLabel("Please select players and enter names");
        welcomeLabel.setBounds(570, 20, 200, 30);
        contextLabel.setBounds(520, 50, 500, 30);

        playButton = new JButton("Play");
        playButton.setBounds(1150, 620, 80, 30);
        playButton.setVisible(true);
        playButton.addActionListener(this);

        optionPane = new JOptionPane();

        buildComponents();
    }

    @Override
    public void initilizeComponents() {
        playerPanel1 = new PlayerPanel(50, 100);
        playerPanelController1 = new PlayerPanelController(playerPanel1);

        playerPanel2 = new PlayerPanel(50, 250);
        playerPanelController2 = new PlayerPanelController(playerPanel2);

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
        this.add(playerPanel1);
        this.add(playerPanel2);
        this.add(playButton);
        this.add(welcomeLabel);
        this.add(contextLabel);
    }

    private JFrame getRootFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(playerPanel1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            int a = JOptionPane.showConfirmDialog(this, "Are you sure? Do you want to start playing?");
            if (a == JOptionPane.YES_OPTION) {
                JFrame rootFrame = getRootFrame();
                Point location = rootFrame.getLocation();
                rootFrame.dispose();
                PlayerService.createPlayer(playerPanelController1.newNames);
                PlayerService.createPlayer(playerPanelController2.newNames);
                new GameFrame(AppConfig.title, AppConfig.appSize, location);
                this.setVisible(false);
            }
        }
    }
}
