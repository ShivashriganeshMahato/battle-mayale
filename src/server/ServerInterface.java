
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
    private Button playBtn;
    private Button endBtn;

    public ServerInterface(int port) {
        setBackgroundColor(Color.WHITE);
        players = new ArrayList<>();
        manager = new ServerManager(port, this);
        playBtn = new Button("PLAY");
        endBtn = new Button("STOP");
        addActor(playBtn, 600, 20);
        addActor(endBtn, 600, 50);
        addActor(new Text("Players:"), 50, 10);

        new Mayflower("Battle Mayale Server", 800, 800, this);
    }

    @Override
    public void update() {
        if (players.size() >= 3 && manager.getGame() == null) {
            playBtn.enable();
            endBtn.disable();
        } else {
//            endBtn.enable();
//            playBtn.disable();
        }

        if (playBtn.isClicked() && !playBtn.isDisabled()) {
            setInGame(manager.startGame(), true);
            playBtn.disable();
            endBtn.enable();
        }

        if (endBtn.isClicked() && !endBtn.isDisabled()) {
            setInGame(manager.endGame(), false);
            playBtn.enable();
            endBtn.disable();
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setPosition(50, 50 + i * 50);
        }

//        System.out.println(players);
    }

    private void setInGame(List<Player> players, boolean inGame) {
        for (Player player : players) {
            for (PlayerText text : this.players) {
                if (player.getId() == text.getId())
                    text.setInGame(inGame);
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
