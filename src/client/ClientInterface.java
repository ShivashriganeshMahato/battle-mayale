package client;

import game.Game;
import mayflower.Mayflower;
import mayflower.Stage;
import player.Player;
import stages.GameStage;
import stages.QueueStage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class ClientInterface {
    private ClientManager clientManager;
    private Stage curStage;
    private Mayflower application;

    public ClientInterface(String IP, int port) {
        clientManager = new ClientManager(IP, port, this);

        curStage = new QueueStage();
        application = new Mayflower("Battle Mayale Server", 800, 600, curStage);
    }

    public void setStage(Stage stage) {
        application.setStage(stage);
    }
}
