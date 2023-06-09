package app;

import org.junit.Before;
import org.junit.Test;

import app.domain.models.game.map.Territory;
import app.domain.services.map.MapGraphService;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MapGraphServiceTest {
    private MapGraphService _mapGraphService;

    @Before
    public void setUp() {
        _mapGraphService = new MapGraphService();
        cleanUp();
    }

    public void cleanUp() {
        _mapGraphService.clear();
    }

    public List<Territory> makeMockTerritory(int count) {
        List<Territory> territoryList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Territory territory = new Territory(
                    i,
                    "Territory" + i,
                    "image" + i,
                    null,
                    null);
            territoryList.add(territory);
        }
        return territoryList;
    }

    public void buildGraphVerticies(List<Territory> territoryList) {
        _mapGraphService.addVerticies(territoryList);
    }

    public void buildGraphEdges(List<Territory> territoryList) {
        for (int i = 0; i < territoryList.size(); i++) {
            for (int j = i + 1; j < territoryList.size(); j++) {
                addEdge(territoryList.get(i), territoryList.get(j));
            }
        }
    }

    public List<Territory> buildGraph(int territoryCount) {
        List<Territory> mockTerritories = makeMockTerritory(territoryCount);
        buildGraphVerticies(mockTerritories);
        buildGraphEdges(mockTerritories);
        return mockTerritories;
    }

    public void addEdge(Territory source, Territory destination) {
        _mapGraphService.addEdge(source, destination);
    }

    public void removeEdge(Territory source, Territory destination) {
        _mapGraphService.removeEdge(source, destination);
    }

    @Test
    public void allTerritoriesConnected() {
        cleanUp();
        buildGraph(7);
        assertTrue(_mapGraphService.validateMap());
    }

    @Test
    public void allTerritoriesConnectedWithExtraEdges() {
        cleanUp();
        List<Territory> territoryList = buildGraph(7);
        _mapGraphService.addEdge(territoryList.get(0), territoryList.get(3));
        assertTrue(_mapGraphService.validateMap());
    }

    @Test
    public void allTerritoriesConnectedWithMissingEdges() {
        cleanUp();
        List<Territory> territoryList = buildGraph(7);
        _mapGraphService.removeEdge(territoryList.get(0), territoryList.get(1));
        assertFalse(_mapGraphService.validateMap());
    }

    @Test
    public void allTerritoriesConnectedWithMissingEdgesAndExtraEdges() {
        cleanUp();
        List<Territory> territoryList = buildGraph(7);
        _mapGraphService.removeEdge(territoryList.get(0), territoryList.get(1));
        _mapGraphService.addEdge(territoryList.get(0), territoryList.get(3));
        assertFalse(_mapGraphService.validateMap());
    }

    @Test
    public void singleTerritoryMap() {
        cleanUp();
        buildGraph(1);
        assertTrue(_mapGraphService.validateMap());
    }

    @Test
    public void closedTerritories() {
        cleanUp();
        List<Territory> territoryList = buildGraph(3);
        _mapGraphService.removeEdge(territoryList.get(0), territoryList.get(1));
        _mapGraphService.removeEdge(territoryList.get(0), territoryList.get(2));
        assertFalse(_mapGraphService.validateMap());
    }

    @Test
    public void allTerritoriesClosed() {
        cleanUp();
        List<Territory> territoryList = buildGraph(2);
        _mapGraphService.removeEdge(territoryList.get(0), territoryList.get(1));
        _mapGraphService.removeEdge(territoryList.get(1), territoryList.get(0));
        assertFalse(_mapGraphService.validateMap());
    }

    @Test
    public void emptyMap() {
        cleanUp();
        assertTrue(_mapGraphService.validateMap());
    }

    @Test
    public void largeMap() {
        cleanUp();
        buildGraph(1000);
        assertTrue(_mapGraphService.validateMap());
    }

    @Test
    public void disconnectedTerritories() {
        cleanUp();
        List<Territory> territoryList = buildGraph(3);
        _mapGraphService.removeEdge(territoryList.get(0), territoryList.get(1));
        _mapGraphService.removeEdge(territoryList.get(0), territoryList.get(2));
        assertFalse(_mapGraphService.validateMap());
    }
}
