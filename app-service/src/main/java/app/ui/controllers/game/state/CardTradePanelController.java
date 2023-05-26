package app.ui.controllers.game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.MainDecks;
import app.domain.models.game.map.Continent;
import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;
import app.ui.views.game.state.CardTradePanel;

public class CardTradePanelController extends BaseStatePanelController implements ActionListener {
    private static CardTradePanelController _cardTradePanelController;
    private CardTradePanel _cardTradePanel;

    private List<Integer> _cardAmount;
    private List<String> _continents;
    private Territory _currentSelection;

    public CardTradePanelController() {
        _cardTradePanel = new CardTradePanel();
        _cardAmount = new ArrayList<>();
        _continents = new ArrayList<>(7);
        setupActionListeners();
        fetchData();
        initilizePanel();
    }

    public static CardTradePanelController getInstance(){
        if (_cardTradePanelController == null){
            _cardTradePanelController = new CardTradePanelController();
        }
        return _cardTradePanelController;
    }

    public CardTradePanel getCardTradePanel() {
        return this._cardTradePanel;
    }

    public void setupActionListeners() {
        _cardTradePanel.getTradeArmyCardButton().addActionListener(this);
        _cardTradePanel.getTradeTerritoryCardButton().addActionListener(this);
        _cardTradePanel.getNextPhaseButton().addActionListener(this);
    }

    public void fetchData() {
        Set<Continent> continents = MapService.getInstance().getContinents();
        continents.forEach((Continent continent) -> {
            _continents.add(continent.getName());
        });
        for (int i = 0; i < 11; i++) {
            _cardAmount.add(i);
        }
    }

    public void initilizePanel() {
        for (int i : _cardAmount) {
            _cardTradePanel.addItemToCardBox(_cardTradePanel.artilleryCardBox, i);
            _cardTradePanel.addItemToCardBox(_cardTradePanel.cavalryCardBox, i);
            _cardTradePanel.addItemToCardBox(_cardTradePanel.infantryCardBox, i);
        }
        for (String s : _continents) {
            _cardTradePanel.addItemToTerritoryBox(_cardTradePanel.continentListBox, s);
        }

        updateLabels();

    }

    public void updateLabels(){
        ArrayList<String> territoryNames = PlayerService.getInstance().getTerritoryCardNames();
        MainDecks playerDeck = PlayerService.getInstance().getCurrentPlayer().getPlayerDecks();

        _cardTradePanel.updateTerritoryCardLabel(territoryNames);
        _cardTradePanel.updateArmyCardLabel(playerDeck.armyUnitCardNumbers(ArmyUnitType.Infantry),
                                            playerDeck.armyUnitCardNumbers(ArmyUnitType.Chivalry),
                                            playerDeck.armyUnitCardNumbers(ArmyUnitType.Artillery));
    }

    @Override
    public void update(Territory message) {
        _currentSelection = message;
        _cardTradePanel.updateSelectedTerritory(message.getName());
        _cardTradePanel.refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(_cardTradePanel.getTradeArmyCardButton())) {
            GameManagerService.getInstance().tradeArmyCards((int) _cardTradePanel.infantryCardBox.getSelectedItem(),
                                                            (int) _cardTradePanel.cavalryCardBox.getSelectedItem(),
                                                            (int) _cardTradePanel.artilleryCardBox.getSelectedItem(),
                                                            _currentSelection.get_territoryId());
            this.updateLabels();
            return;
        }

        if (e.getSource().equals(_cardTradePanel.getTradeTerritoryCardButton())) {
            GameManagerService.getInstance().tradeTerritoryCards((String) _cardTradePanel.continentListBox.getSelectedItem());
            this.updateLabels();
            return;
        }

        if (e.getSource().equals(_cardTradePanel.getNextPhaseButton())) {
            GameManagerService.getInstance().handleNextState();
            return;
        }
    }
}
