package server;

import mayflower.Mayflower;
import mayflower.net.Server;

public class ServerManager extends Server {
    private ServerInterface stage;

    public ServerManager(int port) {
        super(port);

        stage = new ServerInterface();
        new Mayflower("Server Manager", 800, 600, stage);
    }

    @Override
    public void process(int i, String s) {
        System.out.println(s);
    }

    @Override
    public void onJoin(int i) {
        System.out.println(i);
    }

    @Override
    public void onExit(int i) {

    }
}
