package client;

import game.Game;
import mayflower.net.Client;
import player.Player;
import stages.GameStage;

import java.util.ArrayList;
import java.util.List;

public class ClientManager extends Client {
    private ClientInterface clientInterface;

    public ClientManager(String IP, int port, ClientInterface clientInterface) {
        connect(IP, port);
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

            Game game = new Game(players);
            GameStage stage = new GameStage(game, Integer.parseInt(command[1]));
            clientInterface.setStage(stage);
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
