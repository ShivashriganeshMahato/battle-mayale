package client;

import entities.PickupGun;
import entities.Player;
import game.Game;
import game.map.Cell;
import mayflower.net.Client;
import stages.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientManager extends Client {
    private Game game;
    private ClientInterface clientInterface;

    public ClientManager(String IP, int port, ClientInterface clientInterface) {
        connect(IP, port);
        game = null;
        this.clientInterface = clientInterface;
    }

    @Override
    public void process(String s) {
        // Handle commands sent from Server
        String[] command = s.split(" ");
        if (command[0].equals("game")) {
            // On game start, instantiate local Game with player data
            List<Player> players = new ArrayList<>();
            Player newPlayer = null;
            for (int i = 2; i < command.length; i++) {
                switch (i % 4) {
                    case 2:
                        newPlayer = new Player(command[i], -1);
                        break;
                    case 3:
                        newPlayer.setId(Integer.parseInt(command[i]));
                        break;
                    case 0:
                        newPlayer.setX(Double.parseDouble(command[i]));
                        break;
                    case 1:
                        newPlayer.setY(Double.parseDouble(command[i]));
                        players.add(newPlayer);
                        break;
                }
            }

            game = new Game(players, this);
            LoadStage lStage = new LoadStage(game, Integer.parseInt(command[1]), clientInterface);
            clientInterface.setStage(lStage);
        } else if (command[0].equals("map")) {
            int w = Integer.parseInt(command[3]);
            int h = Integer.parseInt(command[4]);
            Cell[][] grid = new Cell[h][w];
            Cell newCell = null;
            for (int i = 5; i < command.length - 21; i++) {
                switch (i % 3) {
                    case 2:
                        newCell = new Cell(-1, Integer.parseInt(command[i]));
                        break;
                    case 0:
                        newCell.setRow(Integer.parseInt(command[i]));
                        break;
                    case 1:
                        if (Boolean.parseBoolean(command[i]))
                            newCell.open();
                        else
                            newCell.close();
                        grid[newCell.getRow()][newCell.getCol()] = newCell;
                        break;
                }
            }
            PickupGun gun = null;
            for (int i = command.length - 21; i < command.length; i++) {
                switch ((i - (command.length - 21)) % 3) {
                    case 0:
                        gun = new PickupGun(-1, -1, command[i], game.getPlayers());
                        break;
                    case 1:
                        gun.getAbsPos().setX(Double.parseDouble(command[i]));
                        break;
                    case 2:
                        gun.getAbsPos().setY(Double.parseDouble(command[i]));
                        game.addGun(gun);
                        break;
                }
            }

            game.loadMap(grid);
            ((LoadStage) clientInterface.getCurStage()).goToPlay();
        } else if (command[0].equals("move")) {
            // When a entities from some Client is moved, local entities must be moved accordingly
            double xNew = Double.parseDouble(command[2]);
            double yNew = Double.parseDouble(command[3]);
            for (Player player : game.getPlayers()) {
                if (player.getId() == Integer.parseInt(command[1])) {
                    double dx = xNew - player.getAbsX();
                    double dy = yNew - player.getAbsY();
                    player.move(dx, dy);
                    player.setAbsPos(xNew, yNew);
                }
            }
        } else if (command[0].equals("shoot")) {
            // Generate bullet with absolute position given by command
            double x = Double.parseDouble(command[1]);
            double y = Double.parseDouble(command[2]);
            double vx = Double.parseDouble(command[3]);
            double vy = Double.parseDouble(command[4]);
            int id = Integer.parseInt(command[5]);
            for (Player player : game.getPlayers()) {
                if (player.getId() == id) {
                    game.addBullet(x, y, vx, vy, player);
                }
            }
        } else if (command[0].equals("remove")) {
            int id = Integer.parseInt(command[1]);
            for (Player player : game.getPlayers()) {
                if (player.getId() == id) {
                    player.getStage().removeActor(player.getWeapon());
                    player.getStage().removeActor(player.getTag());
                    player.getStage().removeActor(player);
                    game.getAlive().remove(player);
                }
            }
            if (((GameStage) clientInterface.getCurStage()).getUserID() == id) {
                clientInterface.setStage(new GameOverStage(game.getAlive().size() + 1));
            } else {
                if (game.getAlive().size() == 1) {
                    game.getAlive().get(0).writeScores(game.getAlive().get(0).getScore(), "txttxt");
                    clientInterface.setStage(new WinStage());
                }
            }
        } else if (command[0].equals("end")) {
            game = null;
            clientInterface.setStage(new QueueStage());
        } else if (command[0].equals("epress")) {
            System.out.println(Integer.parseInt(command[1]));
            for (Player player : game.getPlayers()) {
                if (player.getId() == Integer.parseInt(command[1]))
                    player.setPressingE(true);
            }
        }
    }

    @Override
    public void onDisconnect(String s) {
    }

    @Override
    public void onConnect() {
        String name;

        do {
            String output = JOptionPane.showInputDialog("What is your name?");
            if (output.contains(" ") || output.length() == 0) {
                JOptionPane.showMessageDialog(null, "Your name cannot contain spaces");
                continue;
            }
            name = output;
            break;
        } while (true);

        send("name " + name);
//         send("name Joe");
    }
}
