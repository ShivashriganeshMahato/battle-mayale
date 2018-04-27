package server;

import mayflower.Stage;
import mayflower.Text;

import java.util.Map;
import java.util.Queue;

public class ServerInterface extends Stage {
    private Map<String, String> names;
    private Text clientsTxt;

    public ServerInterface(Map<String, String> names) {
        this.names = names;
        clientsTxt = new Text("");
        addActor(clientsTxt, 20, 20);
    }

    @Override
    public void update() {
        System.out.println(clientsTxt.toString());
        for (String name : names.values()) {
            clientsTxt.setText(clientsTxt.toString() + name + " ");
        }
    }
}
