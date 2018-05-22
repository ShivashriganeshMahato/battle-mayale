package ui;

import entities.Player;
import mayflower.Text;

/**
 * @author Shivashriganesh Mahato
 */
public class PlayerText extends Text {
    private String name;
    private int id;
    private boolean isInGame;

    public PlayerText(String name, int id, boolean isInGame) {
        this.name = name;
        this.id = id;
        this.isInGame = isInGame;
        updateText();
    }

    public PlayerText(Player player, boolean isInGame) {
        this(player.getName(), player.getId(), isInGame);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateText();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        updateText();
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean inGame) {
        isInGame = inGame;
        updateText();
    }

    private void updateText() {
        setText(id + ": " + name + " - " + (isInGame ? "IN GAME" : "WAITING FOR GAME"));
    }
}
