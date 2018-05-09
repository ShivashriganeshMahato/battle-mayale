package client;

import game.Game;
import mayflower.net.Client;
import player.Player;
import stages.GameStage;

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
        String[] command = s.split(" ");
        if (command[0].equals("game")) {
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
                        newPlayer.setX(Integer.parseInt(command[i]));
                        break;
                    case 1:
                        newPlayer.setY(Integer.parseInt(command[i]));
                        players.add(newPlayer);
                        break;
                }
            }

            game = new Game(players, this);
            GameStage stage = new GameStage(game, Integer.parseInt(command[1]));
            clientInterface.setStage(stage);
        } else if (command[0].equals("move")) {
            int xNew = Integer.parseInt(command[2]);
            int yNew = Integer.parseInt(command[3]);
            for (Player player : game.getPlayers()) {
                if (player.getId() == Integer.parseInt(command[1])) {
                    int dx = xNew - player.getAbsX();
                    int dy = yNew - player.getAbsY();
                    player.move(dx, dy);
                    player.setAbsPos(xNew, yNew);
                }
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
//        send("name Joe");
    }
}
