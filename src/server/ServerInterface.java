
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

        new Mayflower("Battle Mayale Server", 800, 800, this);
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
        if (players.size() >= 3 && manager.getGame() == null) {
            button.enable();
        } else {
            button.disable();
        }

        if (button.isClicked() && !button.isDisabled()) {
            setInGame(manager.startGame());
            button.disable();
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPosition(50, 50 + i * 50);
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
        PlayerText text = new PlayerText(player, false);
        players.add(text);
        addActor(text, 0, 0);
    }

    public void editPlayer(Player player) {
        for (PlayerText text : players) {
            if (text.getId() == player.getId()) {
                text.setName(player.getName());
                break;
            }
        }
    }

    public void removePlayer(Player player) {
        PlayerText toRemove = null;
        for (PlayerText text : players) {
            if (text.getId() == player.getId()) {
                toRemove = text;
                break;
            }
        }
        removeActor(toRemove);
        players.remove(toRemove);
    }
}
