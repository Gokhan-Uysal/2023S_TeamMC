package app.domain.services;

import java.util.ArrayList;
import java.util.List;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.MainDecks;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.entities.GameStatePersistEntity;
import app.domain.models.entities.PlayerEntity;
import app.domain.models.entities.PlayerTerritoryCardEntity;
import app.domain.models.entities.TerritoryCardEntity;
import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.modelViews.PlayerArmyCardViewModel;
import app.domain.models.player.Player;
import app.domain.repositories.CountryRepository;
import app.domain.repositories.GameStatePersistRepostiry;
import app.domain.repositories.MapRepository;
import app.domain.repositories.PlayerArmyCardRepository;
import app.domain.repositories.PlayerRepository;
import app.domain.repositories.PlayerTerritoryCardRepository;

public class DbSaveLoadService implements ISaveLoadAdapter {
    private PlayerRepository _playerRepository;
    private PlayerArmyCardRepository _playerArmyCardRepository;
    private PlayerTerritoryCardRepository _playerTerritoryCardRepository;
    private CountryRepository _countryRepository;
    private GameStatePersistRepostiry _gameStatePersistRepostiry;
    private MapRepository _mapRepsitory;

    public DbSaveLoadService() {
        _playerRepository = new PlayerRepository();
        _playerArmyCardRepository = new PlayerArmyCardRepository();
        _playerTerritoryCardRepository = new PlayerTerritoryCardRepository();
        _countryRepository = new CountryRepository();
        _gameStatePersistRepostiry = new GameStatePersistRepostiry();
        _mapRepsitory = new MapRepository();
    }

    @Override
    public void saveMap(List<Territory> territories) {
        territories.forEach((Territory territory) -> {
            try {
                _countryRepository.updateCountryArmy(territory.getTerritoryId(),
                        territory.getInfantryCount(), ArmyUnitType.Infantry);
                _countryRepository.updateCountryArmy(territory.getTerritoryId(),
                        territory.getChivalryCount(), ArmyUnitType.Chivalry);
                _countryRepository.updateCountryArmy(territory.getTerritoryId(),
                        territory.getArtilleryCount(), ArmyUnitType.Artillery);
            } catch (DbException e) {
                Logger.error(e);
            }
        });
    }

    @Override
    public void savePlayer(List<Player> players) {
        players.forEach((Player player) -> {
            int infantryCardCount = player.getArmyCardCount(ArmyUnitType.Infantry);
            int chivaltryCardCount = player.getArmyCardCount(ArmyUnitType.Chivalry);
            int artilleryCardCount = player.getArmyCardCount(ArmyUnitType.Artillery);

            _playerArmyCardRepository.updatePlayerArmyCard(player.getId(), infantryCardCount,
                    ArmyCardType.Infantry);

            _playerArmyCardRepository.updatePlayerArmyCard(player.getId(), chivaltryCardCount,
                    ArmyCardType.Cavalry);

            _playerArmyCardRepository.updatePlayerArmyCard(player.getId(), artilleryCardCount,
                    ArmyCardType.Artillery);

            try {
                _playerTerritoryCardRepository.deletePlayerTerritoryCards();
            } catch (DbException e) {
                Logger.error(e.getMessage());
            }

            player.getTerritoryCardIds().forEach((Integer territoryCardId) -> {
                try {
                    PlayerTerritoryCardEntity playerTerritoryCardEntity = new PlayerTerritoryCardEntity(
                            player.getId(), territoryCardId);
                    _playerTerritoryCardRepository.insertPlayerTerritoryCard(playerTerritoryCardEntity);
                } catch (DbException e) {
                    Logger.error(e);
                } catch (NoSuchFieldException e) {
                    Logger.error(e);
                } catch (SecurityException e) {
                    Logger.error(e);
                }
            });
        });
    }

    @Override
    public void saveGameState(GameState gameState) {
        GameStatePersistEntity.Builder builder = new GameStatePersistEntity.Builder();
        builder.setGameState(gameState.ordinal());
        try {
            _gameStatePersistRepostiry.insertGameState(builder.build());
        } catch (NoSuchFieldException | SecurityException | DbException e) {
            Logger.error(e);
        }
    }

    @Override
    public List<Territory> loadMap() {
        return _mapRepsitory.buildGameMapData();
    }

    @Override
    public List<Player> loadPlayers() {
        List<Player> playerList = new ArrayList<Player>();
        try {
            List<PlayerEntity> playerEnityList = _playerRepository.findPlayers(6, 0);
            playerEnityList.forEach((PlayerEntity playerEntity) -> {
                try {
                    PlayerArmyCardViewModel playerArmyCardEntity = _playerArmyCardRepository
                            .findPlayerArmyCard(playerEntity.id);
                    MainDecks playerDecks = new MainDecks();
                    playerDecks.addArmyCards(ArmyCardType.Infantry, playerArmyCardEntity.infantry_count);
                    playerDecks.addArmyCards(ArmyCardType.Cavalry, playerArmyCardEntity.cavalry_count);
                    playerDecks.addArmyCards(ArmyCardType.Artillery, playerArmyCardEntity.artillery_count);

                    List<TerritoryCardEntity> playerTerritoryCardList = _playerTerritoryCardRepository
                            .findTerritoryCardsByPlayerId(playerEntity.id);
                    playerTerritoryCardList.forEach((TerritoryCardEntity territoryCardEntity) -> {
                        playerDecks.addTerritoryCards(territoryCardEntity.description, null,
                                territoryCardEntity.country_id);
                    });

                    Player player = new Player(playerEntity.id, playerEntity.username, playerDecks);
                    playerList.add(player);

                } catch (DbException e) {
                    Logger.error(e.getMessage());
                }
            });
        } catch (DbException e) {
            Logger.error(e);
        }

        return playerList;
    }

    @Override
    public GameState loadGameState(int id) {
        try {
            GameStatePersistEntity gameStatePersistEntity = _gameStatePersistRepostiry.findGameStateBySeasionId(id);
            return GameState.fromInt(gameStatePersistEntity.getGameState());
        } catch (DbException e) {
            Logger.error(e);
        }
        return GameState.BUILDING_STATE;
    }

}
