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
            double xNew = Double.parseDouble(command[1]);
            double yNew = Double.parseDouble(command[2]);

            for (Player player : game.getPlayers()) {
                if (player.getId() == i) {
                    player.setPosition(xNew, yNew);
                } else {
                    send(player.getId(), "move " + i + " " + xNew + " " + yNew);
                }
            }
        } else if (command[0].equals("shoot")) {
            double x = Double.parseDouble(command[1]);
            double y = Double.parseDouble(command[2]);
            double vx = Double.parseDouble(command[3]);
            double vy = Double.parseDouble(command[4]);
            int id = Integer.parseInt(command[5]);
            Player shooter = null;
            for (Player player : game.getPlayers()) {
                send(player.getId(), "shoot " + x + " " + y + " " + vx + " " + vy+ " " + id);
                System.out.println("CLIENT SENDING");
                if (player.getId() == id)
                    shooter = player;
            }
            game.addBullet(x, y, vx, vy, shooter);
        } else if(command[0].equals("removePlayer"))
        {
            for (Player player : game.getPlayers()) {
                //if (player.getId() == i) {
                //player.getStage().removeActor(player);
                //}
                // else {
                send(player.getId(), "remove " + command[1]);
                //}
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
        String[] guns = {"LMG", "Pistol", "Railgun", "Rifle", "Shotgun", "SMG", "Sniper"};
        for (String gun : guns) {
            builder.append(gun).append(" ");
            builder.append(Math.random() * 600).append(" ");
            builder.append(Math.random() * 400).append(" ");
        }

        return builder.toString();
    }

    public Game getGame() {
        return game;
    }
}
