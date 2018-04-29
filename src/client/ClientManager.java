package client;

import mayflower.net.Client;
import player.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClientManager extends Client {
    public ClientManager(String IP, int port) {
        connect("localhost", 1234);
    }

    @Override
    public void process(String s) {
        String[] command = s.split(" ");
        if (command[0].equals("game")) {
            List<Player> players = new ArrayList<>();
            Player newPlayer = null;
            for (int i = 1; i < command.length; i++) {
                switch (i % 4) {
                    case 1:
                        newPlayer = new Player(command[i], -1);
                        break;
                    case 2:
                        newPlayer.setId(Integer.parseInt(command[i]));
                        break;
                    case 3:
                        newPlayer.setX(Double.parseDouble(command[i]));
                        break;
                    case 0:
                        newPlayer.setY(Double.parseDouble(command[i]));
                        players.add(newPlayer);
                        break;
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

//        do {
//            String output = JOptionPane.showInputDialog("What is your name?");
//            if (output.contains(" ") || output.length() == 0) {
//                JOptionPane.showMessageDialog(null, "Your name cannot contain spaces");
//                continue;
//            }
//            name = output;
//            break;
//        } while (true);

//        send("name " + name);
        send("name Joe");
    }
}
