package app.ui.views.game;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import app.ui.controllers.game.helpscreen.HelpPanelController;
import app.ui.controllers.game.player.TurnStatePanelController;
import app.ui.views.game.help.HelpPanel;
import app.ui.views.game.player.TurnStatePanel;

public class HeaderPanel extends JPanel {
    private HelpPanel _helpPanel;
    private HelpPanelController _helpPanelController;

    private TurnStatePanel _turnStatePanel;
    private TurnStatePanelController _turnStatePanelController;

    public HeaderPanel() {
        setLayout(new BorderLayout());
        initializeComponents();
        addComponents();
    }

    public void initializeComponents() {
        _helpPanel = new HelpPanel();
        _helpPanelController = new HelpPanelController(_helpPanel);

        _turnStatePanel = new TurnStatePanel();
        _turnStatePanelController = new TurnStatePanelController(_turnStatePanel);
    }

    public void addComponents() {
        this.add(_turnStatePanel, BorderLayout.CENTER);
        this.add(_helpPanel, BorderLayout.EAST);
    }
}
