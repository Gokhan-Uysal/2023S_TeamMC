package app.ui.controllers.game.pausescreen;

import app.common.AppConfig;
import app.ui.views.game.pause.PausePanel;
import app.ui.views.game.pause.PauseScreenFrame;
import app.util.ActionListenerUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanelController implements ActionListener {

    private PausePanel pausePanel;

    public PausePanelController(PausePanel pausePanel){
        this.pausePanel = pausePanel;
        ActionListenerUtil.addActionListener(pausePanel.pauseButton, this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        new PauseScreenFrame("Pause", AppConfig.helpScreenSize);

    }
}
