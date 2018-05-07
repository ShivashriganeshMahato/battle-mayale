package game;

import client.ClientInterface;
import client.ClientManager;
import player.Player;

import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class Game {
    private List<Player> players;
    private List<Player> alive;
    private Map map;
    private boolean isOver;
    private ClientManager client;

    public Game(List<Player> players, ClientManager client) {
        this.players = players;
        this.client = client;
        map = new Map();
    }

    public Game(List<Player> players) {
        this(players, null);
    }

    public void addPlayer(Player player) {
        players.add(player);
        alive.add(player);
    }

    public void kill(Player player) {
        if (player != null)
            alive.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void end() {
        isOver = true;
    }

    public void sendCommand(String commandToSend) {
        // TODO For testing
//        client.send(commandToSend);
    }

    public Map getMap() {
        return map;
    }
}
