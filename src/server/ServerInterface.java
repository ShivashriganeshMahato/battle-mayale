
package server;

import mayflower.Mayflower;
import mayflower.Stage;
import mayflower.Text;
import player.Player;
import ui.PlayerText;
import ui.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ServerInterface extends Stage {
    private List<PlayerText> players;
    private ServerManager manager;
    private Button button;

    public ServerInterface(int port) {
        setBackgroundColor(Color.WHITE);
        players = new ArrayList<>();
        manager = new ServerManager(port, this);
        button = new Button("PLAY");
        addActor(button, 600, 20);
        addActor(new Text("Players:"), 50, 10);

        new Mayflower("Battle Mayale Server", 800, 600, this);
    }

    /*
     * onClick PLAY
     *  sm.startgame()
     *  ^
     *  | ret list of players
     *  change playertexts
     */

    @Override
    public void update() {
        if (players.size() < 3) {
            button.disable();
        } else {
            button.enable();
        }

        if (button.isClicked() && !button.isDisabled()) {
            setInGame(manager.startGame());
            button.disable();
        }
    }

    private void setInGame(List<Player> players) {
        for (Player player : players) {
            for (PlayerText text : this.players) {
                if (player.getId() == text.getId())
                    text.setInGame(true);
            }
        }
    }

    public void addPlayer(Player player) {
        System.out.println("CALLING");
        PlayerText text = new PlayerText(player, false);
        players.add(text);
        addActor(text, 50, 50 * players.size());
    }

    public void editPlayer(Player player) {
        for (PlayerText text : players) {
            if (text.getId() == player.getId()) {
                text.setName(player.getName());
                break;
            }
        }
    }
}
