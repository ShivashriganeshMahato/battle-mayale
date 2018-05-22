package client;

import mayflower.Mayflower;
import mayflower.Stage;
import stages.QueueStage;

/**
 * @author Shivashriganesh Mahato
 */
public class ClientInterface {
    private ClientManager clientManager;
    private Stage curStage;
    private Mayflower application;

    public ClientInterface(String IP, int port) {
        clientManager = new ClientManager(IP, port, this);

        // Launch Client GUI
        curStage = new QueueStage();
        application = new Mayflower("Battle Mayale Server", 800, 600, curStage);
    }

    public void setStage(Stage stage) {
        application.setStage(stage);
        curStage = stage;
    }

    public Stage getCurStage() {
        return curStage;
    }
}
