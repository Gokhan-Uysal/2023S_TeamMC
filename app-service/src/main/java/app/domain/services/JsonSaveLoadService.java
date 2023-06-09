package app.domain.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import app.common.AppConfig;
import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;

public class JsonSaveLoadService implements ISaveLoadAdapter {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static String SAVE_PATH = "/__resource__/";

    public JsonSaveLoadService() {
    }

    public JsonSaveLoadService(String path) {
        this.SAVE_PATH = AppConfig.basePath + path;
    }

    // Overview: Saves the current state of the territories in the game map.
    @Override
    public void saveMap(List<Territory> territories) {
        /**
         * Overview: Saves the current state of the territories in the game map.
         *
         * @requires
         *           territories != null
         *           Every element in territories should correspond to a Territory
         *           object.
         *           ObjectMapper is configured correctly.
         *           SAVE_PATH should contain a valid directory path.
         *
         * @modifies
         *           This method modifies the "territories.json" file in the path
         *           specified by SAVE_PATH.
         *
         * @effects
         *          Writes the List of territories to a file named "territories.json" in
         *          the directory specified by SAVE_PATH.
         *          The territories are saved in a pretty-printed JSON format.
         *          If an IOException occurs during the process, it catches the
         *          exception and prints the stack trace.
         */
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(SAVE_PATH + "territories.json"), territories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePlayer(List<Player> players) {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            mapper.writeValue(new File(SAVE_PATH + "players.json"), players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveGameState(GameState gameState) {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            mapper.writeValue(new File(SAVE_PATH + "gameState.json"), gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Overview: Loads the territories from a saved game state.
    @Override
    public List<Territory> loadMap() {
        /**
         * Overview: Loads the territories from a saved game state.
         *
         * @requires
         *           The file at the location SAVE_PATH + "territories.json" should
         *           exist and be readable.
         *           The file should contain a valid JSON representation of a List of
         *           Territory objects.
         *           ObjectMapper is configured correctly.
         *
         * @modifies
         *           This method does not modify any object or file.
         *
         * @effects
         *          Returns a List of Territory objects that were previously saved in
         *          the "territories.json" file in the directory specified by SAVE_PATH.
         *          If an IOException occurs during the process, it catches the
         *          exception, prints the stack trace, and returns null.
         */
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(new File(SAVE_PATH + "territories.json"), new TypeReference<List<Territory>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Player> loadPlayers() {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(new File(SAVE_PATH + "players.json"), new TypeReference<List<Player>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GameState loadGameState(int id) {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(SAVE_PATH + "gameState.json"), GameState.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
