package app.ui.controllers.game.state;

import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.ui.views.components.ErrorAlertPanel;
import app.ui.views.game.state.ReplacePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplacePanelController extends BaseStatePanelController implements ActionListener {
    private ReplacePanel _replacePanel;
    private static ReplacePanelController _replacePanelController;
    private Territory _currentSelection;

    private ReplacePanelController() {
        this._replacePanel = new ReplacePanel();
        setupActionListeners();
        initializePanel();
    }

    public static ReplacePanelController getInstance() {
        if (_replacePanelController == null) {
            _replacePanelController = new ReplacePanelController();
        }
        return _replacePanelController;
    }

    public void setupActionListeners() {
        _replacePanel.getNextPhaseButton().addActionListener(this);
        _replacePanel.getTradeUnitsButton().addActionListener(this);
    }

    public void initializePanel() {
        _replacePanel.addItemToComboBox(_replacePanel.getInfantryAmountComboBox(), 0);
        _replacePanel.addItemToComboBox(_replacePanel.getInfantryAmountComboBox(), 5);
        _replacePanel.addItemToComboBox(_replacePanel.getInfantryAmountComboBox(), 10);
        _replacePanel.addItemToComboBox(_replacePanel.getCavalryAmountComboBox(), 0);
        _replacePanel.addItemToComboBox(_replacePanel.getCavalryAmountComboBox(), 1);
        _replacePanel.addItemToComboBox(_replacePanel.getCavalryAmountComboBox(), 2);
    }

    public ReplacePanel getReplacePanel() {
        return this._replacePanel;
    }

    @Override
    public void update(Territory message) {
        _currentSelection = message;
        _replacePanel.updateTerritoryLabel(message.getName());
        _replacePanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(_replacePanel.getNextPhaseButton())) {
            GameManagerService.getInstance().handleNextState();
            return;
        }
        try {
            GameManagerService.getInstance().tradeArmyUnits(
                    (int) _replacePanel.getInfantryAmountComboBox().getSelectedItem(),
                    (int) _replacePanel.getCavalryAmountComboBox().getSelectedItem(),
                    _currentSelection.get_territoryId());
        } catch (Error error) {
            new ErrorAlertPanel(_replacePanel.getRootFrame(_replacePanel), error.getMessage());
        }
    }
}