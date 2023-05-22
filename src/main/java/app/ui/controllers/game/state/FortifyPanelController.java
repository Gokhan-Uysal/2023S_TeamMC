package app.ui.controllers.game.state;

import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.domain.services.PlayerService;
import app.ui.views.components.ErrorAlertPanel;
import app.ui.views.game.state.FortifyPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FortifyPanelController extends BaseStatePanelController implements ActionListener {
    private static FortifyPanelController _fortifyPanelController;
    private FortifyPanel _fortifyPanel;

    private List<Integer> _selectedTerritoryIDs;

    public FortifyPanel getFortifyPanel() {
        return this._fortifyPanel;
    }

    public static FortifyPanelController getInstance() {
        if (_fortifyPanelController == null) {
            _fortifyPanelController = new FortifyPanelController();
        }
        return _fortifyPanelController;
    }

    private FortifyPanelController() {
        this._fortifyPanel = new FortifyPanel();
    }

    @Override
    public void update(Territory message) {
        if (_selectedTerritoryIDs.size() > 1){
            _selectedTerritoryIDs.clear();
            _fortifyPanel.setButtonActivate(false);
            _fortifyPanel.clearLabels();
            return;
        }
        if (_selectedTerritoryIDs.size() == 0){
            _selectedTerritoryIDs.add(0, message.get_territoryId());
            _fortifyPanel.updateStartTerritory(message.getName());
            return;
        }
        if (_selectedTerritoryIDs.size() == 1){
            _selectedTerritoryIDs.add(1, message.get_territoryId());
            _fortifyPanel.updateDestinationTerritory(message.getName());
            _fortifyPanel.setButtonActivate(true);
            return;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(_fortifyPanel.getNextPhaseButton())){
            GameManagerService.getInstance().handleNextState();
            return;
        }

        try{
            GameManagerService.getInstance().fortify((int) _fortifyPanel.getInfantryComboBox().getSelectedItem(),
                                                     (int) _fortifyPanel.getCavalryComboBox().getSelectedItem(),
                                                     (int) _fortifyPanel.getArtilleryComboBox().getSelectedItem(),
                                                     _selectedTerritoryIDs.get(0), _selectedTerritoryIDs.get(1),
                                                     PlayerService.getInstance().getCurrentPlayer().getId());
        }
        catch (Error error){
            new ErrorAlertPanel(_fortifyPanel.getRootFrame(_fortifyPanel), error.getMessage());
        }
    }

    public void setupActionListeners(){
        _fortifyPanel.getFortifyButton().addActionListener(this);
        _fortifyPanel.getNextPhaseButton().addActionListener(this);
    }

    public void initializePanel(){
        for (int i = 0; i < 25; i++){
            _fortifyPanel.getInfantryComboBox().addItem(i);
            _fortifyPanel.getCavalryComboBox().addItem(i);
            _fortifyPanel.getArtilleryComboBox().addItem(i);
        }
    }
}
