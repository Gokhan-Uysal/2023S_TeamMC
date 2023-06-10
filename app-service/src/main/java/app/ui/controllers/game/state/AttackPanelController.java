package app.ui.controllers.game.state;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.domain.services.map.MapService;
import app.domain.services.states.AttackState;
import app.ui.views.animations.DiceAnimationPanel;
import app.ui.views.animations.NumberAnimation;
import app.ui.views.components.AlertPane;
import app.ui.views.components.ErrorAlertPanel;
import app.ui.views.game.state.AttackPanel;

public class AttackPanelController extends BaseStatePanelController implements ActionListener {
    private static AttackPanelController _attackPanelController;
    private AttackPanel _attackPanel;
    private NumberAnimation _numberAnimation;
    private List<Integer> _selectedTerritoryIds;

    private AttackPanelController() {
        this._attackPanel = new AttackPanel();
        _attackPanel.getAttackButton().addActionListener(this);
        _attackPanel.getEndPhaseButton().addActionListener(this);
        _selectedTerritoryIds = new ArrayList<>(2);
    }

    public static AttackPanelController getInstance() {

        if (_attackPanelController == null) {
            _attackPanelController = new AttackPanelController();
        }
        return _attackPanelController;
    }

    public AttackPanel getAttackPanel() {
        return this._attackPanel;
    }

    @Override
    public void update(Territory message) {
        if (_selectedTerritoryIds.size() > 1) {
            _selectedTerritoryIds.clear();
            _attackPanel.setButtonActive(false);
            _attackPanel.clearLabels();
            return;
        }

        if (_selectedTerritoryIds.size() == 0) {
            _selectedTerritoryIds.add(0, message.get_territoryId());
            _attackPanel.updateAttackerTerritory(message.getName());
            return;
        }

        if (_selectedTerritoryIds.size() == 1) {
            _selectedTerritoryIds.add(1, message.get_territoryId());
            _attackPanel.updateDefenderTerritory(message.getName());
            _attackPanel.setButtonActive(true);
            return;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(_attackPanel.getEndPhaseButton())) {
            _attackPanel._diceAnimationPanel.stopDiceAnimation();
            AttackState.drawCardIfAbleTo();
            AttackState.playerCanDrawCard = false;
            GameManagerService.getInstance().handleNextState();
            return;
        }

        try {
            String winner = GameManagerService.getInstance().attack(_selectedTerritoryIds.get(0),
                    _selectedTerritoryIds.get(1));
            _attackPanel._diceAnimationPanel.startDiceAnimation(AttackState._attackerDiceRoll,
                    AttackState._defenderDiceRoll);
            Point endPoint = new Point(MapService.getInstance().findTerritory(_selectedTerritoryIds.get(1)).getTerritoryPosition().getX(),
                                        MapService.getInstance().findTerritory(_selectedTerritoryIds.get(1)).getTerritoryPosition().getY());
           // _numberAnimation = new NumberAnimation(AttackState.changedUnitNumber, endPoint, );
            new AlertPane(_attackPanel.getRootFrame(_attackPanel), "Winner", "Winner is: " + winner,
                    AlertPane.INFORMATION_MESSAGE);
        } catch (Error error) {
            new ErrorAlertPanel(_attackPanel.getRootFrame(_attackPanel), error.getMessage());
        }

    }
}
