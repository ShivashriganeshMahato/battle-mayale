import mayflower.net.Client;

import javax.swing.*;

public class ClientInterface extends Client {
    public ClientInterface() {
        connect("localhost", 1234);
    }

    @Override
    public void process(String s) {
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
    }
}
