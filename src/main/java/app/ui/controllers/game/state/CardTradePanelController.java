package app.ui.controllers.game.state;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import app.domain.models.game.map.Continent;
import app.domain.services.GameManagerService;
import app.domain.services.map.MapService;
import app.ui.views.game.state.CardTradePanel;

public class CardTradePanelController implements ActionListener {
    private CardTradePanel _cardTradePanel;

    private List<Integer> _cardAmount;
    private List<String> _continents;

    public CardTradePanelController() {
        _cardTradePanel = new CardTradePanel();
        _cardAmount = new ArrayList<>();
        _continents = new ArrayList<>(7);
        setupActionListeners();
        fetchData();
        initilizePanel();
    }

    public CardTradePanel getCardTradePanel() {
        return this._cardTradePanel;
    }

    public void setupActionListeners() {
        _cardTradePanel.getTradeArmyCardButton().addActionListener(this);
        _cardTradePanel.getTradeTerritoryCardButton().addActionListener(this);
        _cardTradePanel.getTradeButton().addActionListener(this);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(_cardTradePanel.getTradeArmyCardButton())) {

            return;
        }

        if (e.getSource().equals(_cardTradePanel.getTradeTerritoryCardButton())) {

            return;
        }

        if (e.getSource().equals(_cardTradePanel.getTradeButton())) {

            return;
        }

        if (e.getSource().equals(_cardTradePanel.getNextPhaseButton())) {
            GameManagerService.getInstance().handleNextState();
        }
    }

}
