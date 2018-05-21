package game;

import client.ClientManager;
import entities.Tree;
import game.map.Cell;
import game.map.Map;
import entities.Player;
import weapons.Bullet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shivashriganesh Mahato
 */
public class Game {
    private List<Player> players;
    private List<Player> alive;
    private List<Bullet> bullets;
    private List<Tree> trees;
    private Map map;
    private boolean isOver;
    private ClientManager client;

    public Game(List<Player> players, ClientManager client) {
        this.players = players;
        alive = new ArrayList<>();
        alive.addAll(players);
        this.client = client;
        bullets = new ArrayList<>();
        trees = new ArrayList<>();
    }

    public Game(List<Player> players) {
        this(players, null);
    }

    public void loadMap() {
        map = new Map(0, 0, 600, 400);
        genTrees(map.getGrid());
    }

    public void loadMap(Cell[][] grid) {
        map = new Map(0, 0, grid);
        genTrees(grid);
    }

    private void genTrees(Cell[][] grid) {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (!cell.isOpen()) {
                    trees.add(new Tree(cell.getCol() * 100, cell.getRow() * 100, players));
                }
            }
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
        alive.add(player);
    }

    public void kill(Player player) {
        if (player != null)
            alive.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getAlive() {
        return alive;
    }

    public void end() {
        isOver = true;
    }

    public void sendCommand(String commandToSend) {
        client.send(commandToSend);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Map getMap() {
        return map;
    }

    public List<Tree> getTrees() {
        return trees;
    }
}
