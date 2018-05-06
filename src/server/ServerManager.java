package server;

import game.Game;
import mayflower.net.Server;
import player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ServerManager extends Server {
    private Game game;
    private Queue<Player> playerQueue;
    private ServerInterface serverInterface;

    public ServerManager(int port, ServerInterface serverInterface) {
        super(port);
        System.out.println(getIP());
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
        } else if (command[0].equals("move")) {
            int xNew = Integer.parseInt(command[1]);
            int yNew = Integer.parseInt(command[2]);
            for (Player player : game.getPlayers()) {
                if (player.getId() == i) {
                    player.setPosition(xNew, yNew);
                } else {
                    send(player.getId(), "move " + i + " " + xNew + " " + yNew);
                }
            }
        }
    }

    @Override
    public void onJoin(int i) {
        System.out.println(i + " connected.");
        Player newPlayer = new Player("", i);
        playerQueue.add(newPlayer);
    }

    @Override
    public void onExit(int i) {
        System.out.println(i + " disconnected.");
        Player toRemove = null;
        for (Player player : playerQueue) {
            if (player.getId() == i)
                toRemove = player;
        }
        if (game != null)
            game.kill(toRemove);
        serverInterface.removePlayer(toRemove);
        playerQueue.remove(toRemove);

    }

    public List<Player> startGame() {
        List<Player> gamePlayers = new ArrayList<>();

        while (playerQueue.size() > 0 && gamePlayers.size() <= 12) {
            gamePlayers.add(playerQueue.remove());
        }

        game = new Game(gamePlayers);

        String command = getGameCommand(gamePlayers);
        for (Player player : gamePlayers) {
            send(player.getId(), "game " + player.getId() + " " + command);
        }

        return gamePlayers;
    }

    private String getGameCommand(List<Player> players) {
        StringBuilder builder = new StringBuilder("");

        for (Player player : players) {
            builder.append(player.getName()).append(" ");
            builder.append(player.getId()).append(" ");
            builder.append(player.getX()).append(" ");
            builder.append(player.getY()).append(" ");
        }

        return builder.toString();
    }

    public Game getGame() {
        return game;
    }
}
