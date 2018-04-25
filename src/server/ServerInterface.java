package server;

import mayflower.Stage;
import mayflower.Text;

public class ServerInterface extends Stage {
    private Text text;
    private int timer = 0;

    public ServerInterface() {
        text = new Text("");
        addActor(text, 20, 20);
    }

    @Override
    public void update() {
        timer++;
        text.setText(Integer.toString(timer));
    }
}
