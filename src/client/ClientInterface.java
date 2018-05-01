package client;

import mayflower.Mayflower;
import mayflower.Stage;

/**
 * @author Shivashriganesh Mahato
 */
public class ClientInterface extends Stage {
    private ClientManager manager;

    public ClientInterface(String IP, int port) {
        manager = new ClientManager(IP, port);
        new Mayflower("Battle Mayale Server", 800, 600, this);
    }

    @Override
    public void update() {

    }
}
