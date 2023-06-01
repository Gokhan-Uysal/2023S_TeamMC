package app.domain.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;

public class JsonSaveLoadService implements ISaveLoadAdapter {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static String SAVE_PATH = "C:\\Users\\sgtfr\\IdeaProjects\\2023S_TeamMC_new\\db-service\\territories.json";
    @Override
    public void saveMap(List<Territory> territories) {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(SAVE_PATH ), territories);
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

    @Override
    public List<Territory> loadMap() {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(new File(SAVE_PATH), new TypeReference<List<Territory>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Player> loadPlayer() {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return mapper.readValue(new File(SAVE_PATH + "players.json"), new TypeReference<List<Player>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;    }

    @Override
    public GameState loadGameState() {
        // TODO Auto-generated method stub
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(SAVE_PATH + "gameState.json"), GameState.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;    }

    public void setSavePath(String s) {
        this.SAVE_PATH = s;
    }
}
