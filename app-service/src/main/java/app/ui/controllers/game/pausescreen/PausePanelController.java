package app.ui.controllers.game.pausescreen;

import app.common.AppConfig;
import app.common.Logger;
import app.domain.models.game.SaveLoadState;
import app.domain.services.GameManagerService;
import app.ui.views.game.pause.PausePanel;
import app.ui.views.game.pause.PauseScreenFrame;
import app.util.ActionListenerUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanelController implements ActionListener {

    private PausePanel pausePanel;

    public PausePanelController(PausePanel pausePanel) {
        this.pausePanel = pausePanel;
        ActionListenerUtil.addActionListener(pausePanel.pauseButton, this);
        ActionListenerUtil.addActionListener(pausePanel.saveButton, this);
        ActionListenerUtil.addActionListener(pausePanel.loadButton, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(pausePanel.pauseButton)) {
            new PauseScreenFrame("Pause", AppConfig.helpScreenSize);
            return;
        }

        if (e.getSource().equals(pausePanel.saveButton)) {
            Logger.info("Saving game...");
            GameManagerService.getInstance().saveGame(SaveLoadState.DB);
            return;
        }

        if (e.getSource().equals(pausePanel.loadButton)) {
            Logger.info("Loading game...");
            GameManagerService.getInstance().loadGame(SaveLoadState.DB);
            return;
        }
    }
}
