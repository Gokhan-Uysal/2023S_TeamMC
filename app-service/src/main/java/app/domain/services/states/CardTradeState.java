package app.domain.services.states;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;
import app.domain.services.GameManagerService;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;

import java.util.ArrayList;

public class CardTradeState {

    private MapService _mapService = MapService.getInstance();

    public void tradeArmyCards(int infantryAmount, int cavalryAmount, int artilleryAmount, int playerId,
            int territoryId) {
        tradeArmyCardsHelper(infantryAmount, cavalryAmount, artilleryAmount, playerId, territoryId);

        GameManagerService.getInstance().getCentralDeck().addArmyCards(ArmyCardType.Infantry, infantryAmount);
        GameManagerService.getInstance().getCentralDeck().addArmyCards(ArmyCardType.Cavalry, cavalryAmount);
        GameManagerService.getInstance().getCentralDeck().addArmyCards(ArmyCardType.Artillery, artilleryAmount);
    }

    public void tradeTerritoryCards(String continentName, int playerId) {
        tradeTerritoryCardsHelper(continentName, playerId);

        ArrayList<Territory> territoryList = (ArrayList<Territory>) _mapService
                .getTerritoriesOfContinent(continentName);

        for (Territory t : territoryList) {
            GameManagerService.getInstance().getCentralDeck().addTerritoryCards("Territory card.", null,
                    t.get_territoryId());
        }
    }

    private void tradeTerritoryCardsHelper(String continentName, int playerId) throws Error {
        if (checkIfTerritoryCardsTradable(continentName, playerId)) {
            Player tradingPlayer = PlayerService.getInstance().getCurrentPlayer();

            ArrayList<Territory> continentTerritories = (ArrayList<Territory>) _mapService
                    .getTerritoriesOfContinent(continentName);

            for (Territory t : continentTerritories) {
                t.setOwnerId(tradingPlayer.getId());
                tradingPlayer.getPlayerDecks().drawTerritoryCard(t.get_territoryId());
            }
        } else {
            throw new Error("You do not have the appropriate territory cards.");
        }
    }

    private boolean checkIfTerritoryCardsTradable(String continentName, int playerId) {
        ArrayList<Territory> continentTerritoryList = (ArrayList<Territory>) _mapService
                .getTerritoriesOfContinent(continentName);

        ArrayList<Territory> playerTerritoryCardTerritoryList = PlayerService.getInstance()
                .getTerritoriesFromTerritoryCards();

        return playerTerritoryCardTerritoryList.containsAll(continentTerritoryList);
    }

    private void tradeArmyCardsHelper(int infantryCardAmount, int cavalryCardAmount, int artilleryCardAmount,
            int playerId,
            int territoryId) throws Error {

        Player tradingPlayer = PlayerService.getInstance().getCurrentPlayer();

        if (!PlayerService.getInstance().checkIfPlayerOwnsTerritory(playerId, territoryId)) {
            throw new Error("You do not own the territory.");
        }

        if (this.checkIfArmyCardTradable(infantryCardAmount, cavalryCardAmount, artilleryCardAmount, playerId)) {
            if (infantryCardAmount == 3 && cavalryCardAmount == 0 && artilleryCardAmount == 0) {
                _mapService.findTerritory(territoryId).getTerritoryArmy().addArmyUnits(ArmyUnitType.Chivalry, 1);
            } else if (infantryCardAmount == 2 && cavalryCardAmount == 1 && artilleryCardAmount == 0) {
                _mapService.findTerritory(territoryId).getTerritoryArmy().addArmyUnits(ArmyUnitType.Chivalry, 2);
            } else if (infantryCardAmount == 2 && cavalryCardAmount == 0 && artilleryCardAmount == 1) {
                _mapService.findTerritory(territoryId).getTerritoryArmy().addArmyUnits(ArmyUnitType.Artillery, 2);
            } else if (infantryCardAmount == 1 && cavalryCardAmount == 2 && artilleryCardAmount == 0) {
                _mapService.findTerritory(territoryId).getTerritoryArmy().addArmyUnits(ArmyUnitType.Chivalry, 1);
                _mapService.findTerritory(territoryId).getTerritoryArmy().addArmyUnits(ArmyUnitType.Artillery, 1);
            } else if (infantryCardAmount == 0 && cavalryCardAmount == 2 && artilleryCardAmount == 1) {
                _mapService.findTerritory(territoryId).getTerritoryArmy().addArmyUnits(ArmyUnitType.Artillery, 3);
            }

            for (int i = 0; i < infantryCardAmount; i++) {
                tradingPlayer.getPlayerDecks().drawArmyUnitCard(ArmyUnitType.Infantry);
            }
            for (int i = 0; i < cavalryCardAmount; i++) {
                tradingPlayer.getPlayerDecks().drawArmyUnitCard(ArmyUnitType.Chivalry);
            }
            for (int i = 0; i < artilleryCardAmount; i++) {
                tradingPlayer.getPlayerDecks().drawArmyUnitCard(ArmyUnitType.Artillery);
            }
        } else {
            throw new Error("You do not have the appropriate army cards.");
        }
    }

    private boolean checkIfArmyCardTradable(int infantryCardAmount, int cavalryCardAmount, int artilleryCardAmount,
            int playerId) {

        Player tradingPlayer = PlayerService.getInstance().getPlayer(playerId);

        return tradingPlayer.getPlayerDecks().armyUnitCardNumbers(ArmyUnitType.Infantry) >= infantryCardAmount &&
                tradingPlayer.getPlayerDecks().armyUnitCardNumbers(ArmyUnitType.Chivalry) >= cavalryCardAmount &&
                tradingPlayer.getPlayerDecks().armyUnitCardNumbers(ArmyUnitType.Artillery) >= artilleryCardAmount;
    }

}
