import mayflower.net.Client;

public class ClientInterface extends Client {
    public ClientInterface() {
        connect("localhost", 1234);
    }

    @Override
    public void process(String s) {
        send("TEST");
    }

    @Override
    public void onDisconnect(String s) {

    }

    @Override
    public void onConnect() {
    }
}
