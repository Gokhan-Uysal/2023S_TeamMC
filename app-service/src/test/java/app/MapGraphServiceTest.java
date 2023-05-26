package app;

import app.domain.services.map.MapGraphService;
import app.domain.models.game.map.Territory;
import app.domain.models.game.map.TerritoryPosition;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class MapGraphServiceTest {
    private MapGraphService _mapGraphService;
    private List<Territory> territories;

    @Before
    public void setUp() {
        _mapGraphService = new MapGraphService();
        territories = new ArrayList<>();

        Territory territory1 = new Territory(1, "Territory1", "territory1", new TerritoryPosition(0, 0),
                new HashSet<String>(Arrays.asList("Territory2", "Territory3")));
        Territory territory2 = new Territory(2, "Territory2", "territory2", new TerritoryPosition(10, 10),
                new HashSet<String>(Arrays.asList("Territory1", "Territory3")));
        Territory territory3 = new Territory(3, "Territory3", "territory3", new TerritoryPosition(20, 20),
                new HashSet<String>(Arrays.asList("Territory2", "Territory1")));

        territories.add(territory1);
        territories.add(territory2);
        territories.add(territory3);

        _mapGraphService.addEdges(territories);
    }

    @Test
    public void testGetVerticies() {
        List<Territory> vertices = _mapGraphService.getVerticies();
        assertEquals(3, vertices.size());
    }

    @Test
    public void testGetVertex() {
        Territory territory = _mapGraphService.getVertex("Territory1");
        assertEquals("Territory 1", territory.getName());
    }

    @Test
    public void testGetEdges() {
        Territory territory = _mapGraphService.getVertex("Territory1");
        Set<Territory> edges = _mapGraphService.getEdges(territory);
        assertEquals(2, edges.size());
    }

    @Test
    public void testValidateMap() {
        assertTrue(_mapGraphService.validateMap());
    }

    @Test
    public void testRemoveClosedTerritories() {
        territories.get(1).setIsOpen(false);
        _mapGraphService.removeClosedTerritories();
        List<Territory> vertices = _mapGraphService.getVerticies();
        assertEquals(2, vertices.size());
    }

    @Test
    public void testOpenAllTerritories() {
        territories.forEach(t -> t.setIsOpen(false));
        _mapGraphService.openAllTerritories();
        for (Territory t : _mapGraphService.getVerticies()) {
            assertTrue(t.getIsOpen());
        }
    }
}
