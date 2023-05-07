package app.ui.controllers;

import app.domain.models.game.GameState;
import app.domain.services.GameManagerService;
import app.domain.services.base.ISubscriber;
import app.ui.controllers.game.state.AttackPanelController;
import app.ui.controllers.game.state.DistributePanelController;
import app.ui.controllers.game.state.FortifyPanelController;
import app.ui.views.game.FooterPanel;
import app.ui.views.game.state.RecievePanel;

public class FooterPanelController implements ISubscriber<GameState> {
    private FooterPanel _footerPanel;

    public FooterPanelController(FooterPanel _footerPanel) {
        this._footerPanel = _footerPanel;
        GameManagerService.getInstance().addSubscriber(this);
    }

    @Override
    public void update(GameState message) {
        switch (message) {
            case DISTRIBUTING_STATE:
                _footerPanel.updateStatePanel(DistributePanelController.getInstance().getDistributePanel());
                break;
            case RECEIVING_STATE:
                _footerPanel.updateStatePanel(new RecievePanel());
                _footerPanel.revalidate();
                break;
            case ATTACK_STATE:
                _footerPanel.updateStatePanel(AttackPanelController.getInstance().getAttackPanel());
                break;
            case FORTIFY_STATE:
                _footerPanel.updateStatePanel(FortifyPanelController.getInstance().getFortifyPanel());
                break;
            default:
                _footerPanel.updateStatePanel(null);
                _footerPanel.revalidate();
        }
    }
}
