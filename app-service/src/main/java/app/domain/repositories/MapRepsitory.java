package app.domain.repositories;

import java.util.*;
import java.util.stream.Collectors;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.models.entities.AdjacentCountryEntity;
import app.domain.models.entities.CountryEntity;
import app.domain.models.game.map.Territory;
import app.domain.models.game.map.TerritoryPosition;

public class MapRepsitory {
    private CountryRepository _territoryRepository;

    public MapRepsitory() {
        this._territoryRepository = new CountryRepository();
    }

    public List<Territory> buildGameMapData() {
        List<Territory> territoryList = new ArrayList<>();

        try {
            List<CountryEntity> countryEntityList = this._territoryRepository.findCountries(500, 0);
            for (CountryEntity countryEntity : countryEntityList) {
                List<AdjacentCountryEntity> adjacentCountryEntities = this._territoryRepository
                        .findAdjacentCountriesById(countryEntity.id);
                Set<String> adjList = adjacentCountryEntities.stream()
                        .map((AdjacentCountryEntity adjEntity) -> adjEntity.adjacent_country_name)
                        .collect(Collectors.toSet());
                TerritoryPosition territoryPosition = new TerritoryPosition(countryEntity.position_x,
                        countryEntity.position_y);
                Territory territory = new Territory(countryEntity.id, countryEntity.name, countryEntity.image_name,
                        territoryPosition, adjList);
                territoryList.add(territory);
            }
        } catch (DbException e) {
            Logger.warning(e.getMessage());
        }
        return territoryList;
    }
}
