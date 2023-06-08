package app.domain.services;

import java.util.List;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.entities.PlayerTerritoryCardEntity;
import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;
import app.domain.repositories.CountryRepository;
import app.domain.repositories.MapRepository;
import app.domain.repositories.PlayerArmyCardRepository;
import app.domain.repositories.PlayerTerritoryCardRepository;

public class DbSaveLoadService implements ISaveLoadAdapter {
    private PlayerArmyCardRepository _playerArmyCardRepository;
    private PlayerTerritoryCardRepository _playerTerritoryCardRepository;
    private CountryRepository _countryRepository;
    private MapRepository _mapRepsitory;

    public DbSaveLoadService() {
        _playerArmyCardRepository = new PlayerArmyCardRepository();
        _playerTerritoryCardRepository = new PlayerTerritoryCardRepository();
        _countryRepository = new CountryRepository();
        _mapRepsitory = new MapRepository();
    }

    @Override
    public void saveMap(List<Territory> territories) {
        territories.forEach((Territory territory) -> {
            try {
                _countryRepository.updateCountryArmy(territory.get_territoryId(),
                        territory.getInfantryCount(), ArmyUnitType.Infantry);
                _countryRepository.updateCountryArmy(territory.get_territoryId(),
                        territory.getChivalryCount(), ArmyUnitType.Chivalry);
                _countryRepository.updateCountryArmy(territory.get_territoryId(),
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
            System.out.println(infantryCardCount);
            System.out.println(chivaltryCardCount);
            System.out.println(artilleryCardCount);
            System.out.println("--------");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGameState'");
    }

    @Override
    public List<Territory> loadMap() {
        return _mapRepsitory.buildGameMapData();
    }

    @Override
    public List<Player> loadPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadPlayer'");
    }

    @Override
    public GameState loadGameState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadGameState'");
    }

}
