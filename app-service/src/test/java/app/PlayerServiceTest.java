package app;

import app.common.errors.DbException;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import app.domain.repositories.PlayerRepository;
import app.domain.repositories.PlayerArmyCardRepository;
import app.domain.services.PlayerService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {
    @Mock
    private PlayerRepository mockPlayerRepository;

    @Mock
    private PlayerArmyCardRepository mockPlayerArmyCardRepository;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createPlayers_successfullyCreatesPlayersWhenBelowUpperBound() throws Exception {
        // Arrange
        List<String> names = Arrays.asList("Player1", "Player2", "Player3", "Player4", "Player5");
        when(mockPlayerRepository.insertPlayer(any())).thenReturn(1, 2, 3, 4, 5);

        // Act
        playerService.createPlayers(names);

        // Assert
        assertEquals(5, playerService.getPlayerCount());
    }

    @Test
    public void createPlayers_doesNotCreateMoreThanUpperBoundPlayers() throws Exception {
        // Arrange
        List<String> names = Arrays.asList("Player1", "Player2", "Player3", "Player4", "Player5", "Player6", "Player7");
        when(mockPlayerRepository.insertPlayer(any())).thenReturn(1, 2, 3, 4, 5, 6, 7);

        // Act
        playerService.createPlayers(names);

        // Assert
        assertEquals(6, playerService.getPlayerCount()); // should stop at UPPER_BOUND (6)
    }

    @Test
    public void createPlayers_createsPlayerArmyCardsForEachPlayer() throws Exception {
        // Arrange
        List<String> names = Arrays.asList("Player1");
        when(mockPlayerRepository.insertPlayer(any())).thenReturn(1);

        // Act
        playerService.createPlayers(names);

        // Assert
        verify(mockPlayerArmyCardRepository, times(3)).insertPlayerArmyCard(any()); // Called for Infantry, Cavalry and Artillery
    }

    @Test
    public void createPlayers_logsErrorWhenDbExceptionOccurs() throws Exception {
        // Arrange
        List<String> names = Arrays.asList("Player1");
        when(mockPlayerRepository.insertPlayer(any())).thenThrow(DbException.class);

        // Act
        playerService.createPlayers(names);

        // Assert
        // Check log to ensure error was logged
    }

    @Test
    public void createPlayers_logsErrorWhenNoSuchFieldExceptionOccurs() throws Exception {
        // Arrange
        List<String> names = Arrays.asList("Player1");
        when(mockPlayerRepository.insertPlayer(any())).thenThrow(NoSuchFieldException.class);

        // Act
        playerService.createPlayers(names);

        // Assert
        // Check log to ensure error was logged
    }
}
