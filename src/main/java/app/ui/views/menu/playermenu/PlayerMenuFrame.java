package app.ui.views.menu.playermenu;

import app.common.AppConfig;
import app.domain.services.PlayerService;
import app.ui.controllers.menu.playermenu.PlayerPanelController;
import app.ui.views.components.BaseJFrame;
import app.ui.views.game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerMenuFrame extends BaseJFrame implements ActionListener {

    private ArrayList<PlayerPanel> playerPanels = new ArrayList<PlayerPanel>();
    private ArrayList<PlayerPanelController> playerPanelControllers = new ArrayList<PlayerPanelController>();

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
        int[] xCoords = {50, 50, 50, 870, 870, 870};
        int[] yCoords = {100, 250, 400, 100, 250, 400};

        for (int i = 0; i < xCoords.length; i++) {
            PlayerPanel playerPanel = new PlayerPanel(xCoords[i], yCoords[i]);
            playerPanels.add(playerPanel);

            PlayerPanelController playerPanelController = new PlayerPanelController(playerPanel);
            playerPanelControllers.add(playerPanelController);
        }
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
        for (PlayerPanel playerPanel : playerPanels) {
            this.add(playerPanel);
        }

        this.add(playButton);

        this.add(welcomeLabel);
        this.add(contextLabel);
    }

    private JFrame getRootFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(playerPanels.get(0));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            ArrayList<String> newNames = new ArrayList<String>();
            for (PlayerPanelController playerPanelController : playerPanelControllers) {
                newNames.addAll(playerPanelController.newNames);
            }
            if (newNames.size() < 2) {
                JOptionPane.showMessageDialog(this, "Please enter name for at least 2 players", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int a = JOptionPane.showConfirmDialog(this, "Are you sure? Do you want to start playing?");
            if (a == JOptionPane.YES_OPTION) {
                JFrame rootFrame = getRootFrame();
                Point location = rootFrame.getLocation();
                rootFrame.dispose();

                PlayerService.createPlayer(newNames);

                new GameFrame(AppConfig.title, AppConfig.appSize, location);
                this.setVisible(false);
            }
        }
    }
}
