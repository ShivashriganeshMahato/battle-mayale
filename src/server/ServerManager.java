package server;

import game.Game;
import mayflower.net.Server;
import player.Player;

import java.util.LinkedList;
import java.util.Queue;

public class ServerManager extends Server {
    private Game game;
    private Queue<Player> playerQueue;
    private ServerInterface serverInterface;

    public ServerManager(int port, ServerInterface serverInterface) {
        super(port);
        playerQueue = new LinkedList<>();
        this.serverInterface = serverInterface;
        game = null;
    }

    @Override
    public void process(int i, String s) {
        String[] command = s.split(" ");
        if (command[0].equals("name")) {
            for (Player player : playerQueue) {
                if (player.getId() == i) {
                    String newName = command[1];
                    player.setName(newName);
                    serverInterface.addPlayer(player);
                    break;
                }
            }
        }
    }

    @Override
    public void onJoin(int i) {
        System.out.println(i);
        Player newPlayer = new Player("", i);
        playerQueue.add(newPlayer);
    }

    @Override
    public void onExit(int i) {

    }
}
