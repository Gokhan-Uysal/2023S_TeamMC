package app.domain.services;

import app.domain.models.Player.Player;
import app.ui.controllers.playermenu.PlayerPanelController;

import java.util.ArrayList;

public class PlayerService {


    static ArrayList<Player> players = new ArrayList<Player>();

    public static void createPlayer(ArrayList<String> names){
        for (int i = 0; i<names.size(); i++){
            Player newPlayer = new Player(names.get(i), i+1);
            players.add(newPlayer);
            System.out.println("New Player is created successfully with id: " + newPlayer.id + " and name: "+newPlayer.username);


        }
    }
}
