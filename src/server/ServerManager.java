package server;

import game.Game;
import game.map.Cell;
import game.map.Map;
import mayflower.net.Server;
import entities.Player;

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
        switch (command[0]) {
            case "name":
                for (Player player : playerQueue) {
                    if (player.getId() == i) {
                        String newName = command[1];
                        player.setName(newName);
                        serverInterface.addPlayer(player);
                        break;
                    }
                }
                break;
            case "move":
                double xNew = Double.parseDouble(command[1]);
                double yNew = Double.parseDouble(command[2]);

                for (Player player : game.getPlayers()) {
                    if (player.getId() == i) {
                        player.setPosition(xNew, yNew);
                    } else {
                        send(player.getId(), "move " + i + " " + xNew + " " + yNew);
                    }
                }
                break;
            case "shoot":
                for (Player player : game.getPlayers()) {
                    double x = Double.parseDouble(command[1]);
                    double y = Double.parseDouble(command[2]);
                    double vx = Double.parseDouble(command[3]);
                    double vy = Double.parseDouble(command[4]);
                    send(player.getId(), "shoot " + x + " " + y + " " + vx + " " + vy);
                }
                break;
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
        if (game == null) {
            for (Player player : playerQueue) {
                if (player.getId() == i)
                    toRemove = player;
            }
        } else {
            for (Player player : game.getPlayers()) {
                if (player.getId() == i)
                    toRemove = player;
            }
            game.kill(toRemove);
        }
        serverInterface.removePlayer(toRemove);
        playerQueue.remove(toRemove);

    }

    public List<Player> setupGame() {
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

    public void startGame() {
        game.loadMap();
        String command = getMapCommand(game.getMap());
        for (Player player : game.getPlayers()) {
            send(player.getId(), "map " + command);
        }
    }

    public List<Player> endGame() {
        List<Player> gamePlayers = game.getPlayers();
        game = null;
        for (Player player : gamePlayers) {
            send(player.getId(), "end _");
            playerQueue.add(player);
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

    private String getMapCommand(Map map) {
        StringBuilder builder = new StringBuilder("");

        builder.append(map.getAX()).append(" ");
        builder.append(map.getAY()).append(" ");
        builder.append(map.getGrid()[0].length).append(" ");
        builder.append(map.getGrid().length).append(" ");
        for (Cell[] cells : map.getGrid()) {
            for (Cell cell : cells) {
                builder.append(cell.getCol()).append(" ");
                builder.append(cell.getRow()).append(" ");
                builder.append(cell.isOpen()).append(" ");
            }
        }

        return builder.toString();
    }

    public Game getGame() {
        return game;
    }
}
