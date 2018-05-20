package client;

import game.Game;
import mayflower.net.Client;
import player.Player;
import stages.GameStage;
import stages.LoadStage;
import weapons.Bullet;

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
            LoadStage lStage = new LoadStage(game, Integer.parseInt(command[1]));
            clientInterface.setStage(lStage);
        } else if (command[0].equals("move")) {
            // When a player from some Client is moved, local player must be moved accordingly
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
            game.addBullet(new Bullet(x, y, vx, vy));
        }
    }

    @Override
    public void onDisconnect(String s) {
    }

    @Override
    public void onConnect() {
//        String name;
//
//        do {
//            String output = JOptionPane.showInputDialog("What is your name?");
//            if (output.contains(" ") || output.length() == 0) {
//                JOptionPane.showMessageDialog(null, "Your name cannot contain spaces");
//                continue;
//            }
//            name = output;
//            break;
//        } while (true);
//
//        send("name " + name);
        send("name Joe");
    }
}
