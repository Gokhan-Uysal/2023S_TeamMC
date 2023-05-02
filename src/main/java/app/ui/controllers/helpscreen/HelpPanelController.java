package app.ui.controllers.helpscreen;

import app.common.AppConfig;
import app.ui.views.game.help.HelpPanel;
import app.ui.views.game.help.HelpScreenFrame;
import app.util.ActionListenerUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanelController implements ActionListener {
    private HelpPanel helpPanel;

    public HelpPanelController(HelpPanel helpPanel) {
        this.helpPanel = helpPanel;
        ActionListenerUtil.addActionListener(helpPanel.helpButton, this);
    }

    public void actionPerformed(ActionEvent e) {
        new HelpScreenFrame("Help", AppConfig.helpScreenSize);
    }
}
