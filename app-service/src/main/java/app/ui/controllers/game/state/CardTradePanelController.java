package app.ui.controllers.game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.MainDecks;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.game.map.Continent;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;
import app.domain.services.GameManagerService;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;
import app.ui.views.animations.ArrowAnimation;
import app.ui.views.components.ErrorAlertPanel;
import app.ui.views.game.state.CardTradePanel;

public class CardTradePanelController extends BaseStatePanelController implements ActionListener {
    private static CardTradePanelController _cardTradePanelController;
    private CardTradePanel _cardTradePanel;

    private int _cardAmount;
    private List<String> _continents;
    private Territory _currentSelection;

    public CardTradePanelController() {
        _cardTradePanel = new CardTradePanel();
        _cardAmount = 15;
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
        _cardTradePanel.getAddArmyCardButton().addActionListener(this);
        _cardTradePanel.getAddTerritoryCardButton().addActionListener(this);
    }

    public void fetchData() {
        Set<Continent> continents = MapService.getInstance().getContinents();
        continents.forEach((Continent continent) -> {
            _continents.add(continent.getName());
        });
    }

    public void initilizePanel() {
        for (int i = 0; i < _cardAmount; i++) {
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
            try{
                GameManagerService.getInstance().tradeArmyCards((int) _cardTradePanel.infantryCardBox.getSelectedItem(),
                        (int) _cardTradePanel.cavalryCardBox.getSelectedItem(),
                        (int) _cardTradePanel.artilleryCardBox.getSelectedItem(),
                        _currentSelection);
                this.updateLabels();
                return;
            }
            catch(Error error){
                new ErrorAlertPanel(_cardTradePanel.getRootFrame(_cardTradePanel), error.getMessage());
            }
        }

        if (e.getSource().equals(_cardTradePanel.getTradeTerritoryCardButton())) {
            try{
                GameManagerService.getInstance().tradeTerritoryCards((String) _cardTradePanel.continentListBox.getSelectedItem());
                this.updateLabels();
                return;
            }
            catch (Error error){
                new ErrorAlertPanel(_cardTradePanel.getRootFrame(_cardTradePanel), error.getMessage());
            }
        }
        if (e.getSource().equals(_cardTradePanel.getAddArmyCardButton())) {
            try{
                MainDecks currentPlayerDeck = PlayerService.getInstance().getCurrentPlayer().getPlayerDecks();
                Random rand = new Random();
                int randomNumber = rand.nextInt(5);

                switch (randomNumber) {
                    case 0 -> currentPlayerDeck.addArmyCards(ArmyCardType.Infantry, 3);
                    case 1 -> {
                        currentPlayerDeck.addArmyCards(ArmyCardType.Infantry, 2);
                        currentPlayerDeck.addArmyCards(ArmyCardType.Cavalry, 1);
                    }
                    case 2 -> {
                        currentPlayerDeck.addArmyCards(ArmyCardType.Infantry, 2);
                        currentPlayerDeck.addArmyCards(ArmyCardType.Artillery, 1);
                    }
                    case 3 -> {
                        currentPlayerDeck.addArmyCards(ArmyCardType.Infantry, 1);
                        currentPlayerDeck.addArmyCards(ArmyCardType.Cavalry, 2);
                    }
                    case 4 -> {
                        currentPlayerDeck.addArmyCards(ArmyCardType.Cavalry, 2);
                        currentPlayerDeck.addArmyCards(ArmyCardType.Artillery, 1);
                    }
                    default -> {
                    }
                }
                updateLabels();
            }
            catch (Error error){
                new ErrorAlertPanel(_cardTradePanel.getRootFrame(_cardTradePanel), error.getMessage());
            }
        }
        if (e.getSource().equals(_cardTradePanel.getAddTerritoryCardButton())) {
            try{
                String continentName = null;
                MainDecks playerDeck = PlayerService.getInstance().getCurrentPlayer().getPlayerDecks();

                Random rand = new Random();
                int randomNumber = rand.nextInt(6);
                switch (randomNumber) {
                    case 0 -> continentName = "Australia";
                    case 1 -> continentName = "Asia";
                    case 2 -> continentName = "Africa";
                    case 3 -> continentName = "Europe";
                    case 4 -> continentName = "North America";
                    case 5 -> continentName = "South America";
                    default -> {
                    }
                }

                ArrayList<Territory> continentTerritories =
                        (ArrayList<Territory>) MapService.getInstance().getTerritoriesOfContinent(continentName);

                for (Territory t: continentTerritories){
                    playerDeck.addTerritoryCards("Territory Card", null, t.getTerritoryId());
                }
                updateLabels();
            }
            catch (Error error){
                new ErrorAlertPanel(_cardTradePanel.getRootFrame(_cardTradePanel), error.getMessage());
            }
        }

        if (e.getSource().equals(_cardTradePanel.getNextPhaseButton())) {
            this.updateLabels();
            GameManagerService.getInstance().handleNextState();
            return;
        }
    }
}
