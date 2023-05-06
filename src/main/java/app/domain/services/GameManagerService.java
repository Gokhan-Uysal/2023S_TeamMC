package app.domain.services;

import app.common.Logger;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.*;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;
import app.domain.services.base.BasePublisher;
import app.domain.services.map.MapService;
import app.domain.services.states.AttackState;
import app.domain.services.states.BuildState;
import app.domain.services.states.CardTradeState;
import app.domain.services.states.DistributeState;
import app.domain.services.states.FortifyState;
import app.domain.services.states.RecieveState;
import app.domain.services.states.ReplaceState;

import java.io.IOException;
import java.util.*;

public class GameManagerService extends BasePublisher<GameState> {
    private static GameManagerService _instance;

    private BuildState _buildState;
    private DistributeState _distributeState;
    private RecieveState _recieveState;
    private ReplaceState _replaceState;
    private CardTradeState _cardTradeState;
    private AttackState _attackState;
    private FortifyState _fortifyState;

    private MapService _mapService;
    private PlayerService _playerService;
    private MainDecks _centralDeck;
    private List<Player> _players;
    private int _currentPlayer;
    private boolean _isGameFinished;

    private GameManagerService() {
        super(GameState.BUILDING_STATE);
        initDependicies();
        initializeGame();
    }

    public static GameManagerService getInstance() {
        if (_instance == null) {
            _instance = new GameManagerService();
        }
        return _instance;
    }

    private void initDependicies() {
        _buildState = new BuildState();
        _distributeState = new DistributeState();
        _recieveState = new RecieveState();
        _replaceState = new ReplaceState();
        _cardTradeState = new CardTradeState();
        _attackState = new AttackState();
        _fortifyState = new FortifyState();
        _mapService = new MapService();
        _playerService = new PlayerService();
        _centralDeck = new MainDecks();
    }

    private void initializeGame() {
        updateGameState(getState());
        _isGameFinished = false;
    }

    private void updateGameState(GameState newState) {
        setState(newState);
        notifySubscribers();
    }

    public void handleNextState() {
        GameState currentState = super.getState();
        switch (currentState) {
            case BUILDING_STATE:
                updateGameState(GameState.DISTRIBUTING_STATE);
                break;
            case DISTRIBUTING_STATE:
                updateGameState(GameState.RECEIVING_STATE);
                break;
            case RECEIVING_STATE:
                updateGameState(GameState.ATTACK_STATE);
                break;
            case ATTACK_STATE:
                updateGameState(GameState.FORTIFY_STATE);
                break;
            case FORTIFY_STATE:
                if (_isGameFinished) {
                    updateGameState(GameState.GAME_OVER_STATE);
                    break;
                }
                updateGameState(GameState.RECEIVING_STATE);
                break;
            default:
                break;
        }
    }

    public void createPlayers(ArrayList<String> names) {
        _playerService.createPlayer(names);
        initilizeArmyDeck(names.size());
    }

    public void loadMap() {
        _mapService.loadGameMapDataToGraph();
        setState(GameState.BUILDING_STATE);
        notifySubscribers();
    }

    public List<Territory> getMap() {
        List<Territory> territoryList = _mapService.getTerritoryListFromGraph();
        initilizeTerritoyDeck(territoryList);
        return territoryList;
    }

    public boolean validateNewBuildMap() {
        if (!_mapService.isValidBuildSelection()) {
            return false;
        }
        _mapService.removeClosedTerritories();
        return true;
    }

    public void initilizeArmyDeck(int playerCount) {
        _centralDeck.addArmyCards(ArmyCardType.Infantry, playerCount * 3);
        _centralDeck.addArmyCards(ArmyCardType.Cavalry, playerCount * 2);
        _centralDeck.addArmyCards(ArmyCardType.Artillery, playerCount);
    }

    public void initilizeTerritoyDeck(List<Territory> territoryList) {
        for (Territory territory : territoryList) {
            try {
                _centralDeck.addTerritoryCards(territory.getName(), territory.getImage());
            } catch (IOException e) {
                Logger.error(e);
            }
        }
    }

    public void initilizeChanceDeck() {
        // TO:DO
    }

    public void placeInitialArmy(Territory territory) {
        Player currentPlayer = _playerService.getCurrentPlayer();
        if (!_mapService.unclaimedTerritorySubPhase(territory)) {
            throw new Error("Unclaimed territories exist");
        }

        _mapService.placeArmyUnit(territory, ArmyUnitType.Infantry, 1, currentPlayer.getId());

    }

    // public void tradeArmyCards(int infantryAmount, int cavalryAmount, int
    // artilleryAmount, int playerId,
    // int territoryId) {
    // if (_playerService.tradeArmyCards(infantryAmount, cavalryAmount,
    // artilleryAmount, playerId, territoryId)) {

    // _centralDeck.addArmyCards(ArmyCardType.Infantry, infantryAmount);
    // _centralDeck.addArmyCards(ArmyCardType.Cavalry, cavalryAmount);
    // _centralDeck.addArmyCards(ArmyCardType.Artillery, artilleryAmount);
    // }
    // }

    // public void tradeTerritoryCards(String continentName, int playerId) {
    // if (_playerService.tradeTerritoryCards(continentName, playerId)) {

    // ArrayList<Territory> territoryList = (ArrayList<Territory>) _mapService
    // .getTerritoriesOfContinent(continentName);

    // for (Territory t : territoryList) {
    // _centralDeck.addTerritoryCards(t.getName(), new
    // ImageIcon("territorycard.png"));
    // }
    // }
    // }

    // public void attack(int attackingPlayerId, int attackedPlayerId, int
    // attackerTerritoryId,
    // int attackedTerritoryId) {
    // boolean playerCanDrawCard = _playerService.attack(attackingPlayerId,
    // attackedPlayerId,
    // attackerTerritoryId, attackedTerritoryId);

    // // if (playerCanDrawCard) {
    // // Player winningPlayer = _playerService.getPlayer(attackingPlayerId);
    // // BaseCard drawnCard = _centralDeck.drawCard(CardType.Army);
    // // switch (drawnCard.getDescription()) {
    // // case "Infantry card." -> {
    // // InfantryCard iDrawnCard = (InfantryCard) drawnCard;
    // // winningPlayer.getPlayerDeck().addArmyCards(CardType.Infantry,
    // // iDrawnCard.getDescription(),
    // // iDrawnCard.getImageIcon(), iDrawnCard.getValue());
    // // }
    // // case "Cavalry card." -> {
    // // CavalryCard cDrawnCard = (CavalryCard) drawnCard;
    // // winningPlayer.getPlayerDeck().addArmyCards(CardType.Cavalry,
    // // cDrawnCard.getDescription(),
    // // cDrawnCard.getImageIcon(), cDrawnCard.getValue());
    // // }
    // // case "Artillery card." -> {
    // // ArtilleryCard aDrawnCard = (ArtilleryCard) drawnCard;
    // // winningPlayer.getPlayerDeck().addArmyCards(CardType.Artillery,
    // // aDrawnCard.getDescription(),
    // // aDrawnCard.getImageIcon(), aDrawnCard.getValue());
    // // }
    // // case "Territory card." -> {
    // // TerritoryCard tDrawnCard = (TerritoryCard) drawnCard;
    // // winningPlayer.getPlayerDeck().addArmyCards(CardType.Territory,
    // // tDrawnCard.getDescription(),
    // // tDrawnCard.getImageIcon());
    // // }
    // // }
    // // }
    // }

}