package app.ui.controllers.game.state;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.domain.services.states.AttackState;
import app.ui.views.animations.ArrowAnimation;
import app.ui.views.animations.NumberAnimation;
import app.ui.views.components.AlertPane;
import app.ui.views.components.ErrorAlertPanel;
import app.ui.views.game.GameFrame;
import app.ui.views.game.state.AttackPanel;

import javax.swing.*;

public class AttackPanelController extends BaseStatePanelController implements ActionListener {
    private static AttackPanelController _attackPanelController;
    private AttackPanel _attackPanel;
    private NumberAnimation _numberAnimation;
    private ArrowAnimation _arrowAnimation;
    private List<Territory> _selectedTerritories;

    private AttackPanelController() {
        this._attackPanel = new AttackPanel();
        _attackPanel.getAttackButton().addActionListener(this);
        _attackPanel.getEndPhaseButton().addActionListener(this);
        _selectedTerritories = new ArrayList<>(2);
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
        if (_selectedTerritories.size() > 1) {
            _selectedTerritories.clear();
            _attackPanel.setButtonActive(false);
            _attackPanel.clearLabels();
            return;
        }

        if (_selectedTerritories.size() == 0) {
            _selectedTerritories.add(0, message);
            _attackPanel.updateAttackerTerritory(message.getName());
            /*SwingUtilities.invokeLater(() -> {
                _arrowAnimation = new ArrowAnimation(((GameFrame)SwingUtilities.getRoot(this._attackPanel)).getMapPanel(),
                                                     message.getTerritoryPositionAsPoint(),
                                                     GameManagerService.getInstance().getPositionOfPossibleAttacks(message));
                _arrowAnimation.execute();
                ((GameFrame)SwingUtilities.getRoot(this._attackPanel)).getMapPanel().getParent().
                        setComponentZOrder(((GameFrame)SwingUtilities.getRoot(this._attackPanel)).getMapPanel(), 0);
            });*/
            return;
        }

        if (_selectedTerritories.size() == 1) {
            _selectedTerritories.add(1, message);
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
            String winner = GameManagerService.getInstance().attack(_selectedTerritories.get(0),
                                                                    _selectedTerritories.get(1));
            _attackPanel._diceAnimationPanel.startDiceAnimation(AttackState._attackerDiceRoll,
                                                                AttackState._defenderDiceRoll);
            Point endPoint = new Point(AttackState.lostTerritoryPosition.getX(),
                                       AttackState.lostTerritoryPosition.getY());
            SwingUtilities.invokeLater(() -> {
                _numberAnimation = new NumberAnimation(-1*AttackState.changedUnitNumber, endPoint,
                                                      ((GameFrame) SwingUtilities.getRoot(this._attackPanel)).getMapPanel());
                _numberAnimation.execute();
                    }
            );
            new AlertPane(_attackPanel.getRootFrame(_attackPanel), "Winner", "Winner is: " + winner,
                          AlertPane.INFORMATION_MESSAGE);
        } catch (Error error) {
            new ErrorAlertPanel(_attackPanel.getRootFrame(_attackPanel), error.getMessage());
        }

    }

    private void clearAnimation(JPanel panel) {
        if (_arrowAnimation != null && !_arrowAnimation.isDone()) {
            _arrowAnimation.cancel(true);
        }
        panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
    }
}
