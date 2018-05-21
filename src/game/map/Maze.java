package game.map;

import util.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Shivashriganesh Mahato
 */

public class Maze {
    private Random rand = new Random();
    private final int WIDTH;
    private final int HEIGHT;
    private final CellState OPEN = CellState.OPEN;
    private final CellState BLOCKED = CellState.BLOCKED;
    private Cell[][] grid;
    private Vector2 goal;
    private ArrayList<Cell[]> frontierCells;
    private boolean[][] solveVisited;
    private boolean[][] solvedPath;

    public Maze(int WIDTH, int HEIGHT, int goalCol, int goalRow) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        grid = new Cell[this.HEIGHT][this.WIDTH];
        frontierCells = new ArrayList<>();
        solveVisited = new boolean[WIDTH][HEIGHT];
        solvedPath = new boolean[WIDTH][HEIGHT];
        goal = new Vector2(goalCol, goalRow);
    }

    public Maze(int WIDTH, int HEIGHT) {
        this(WIDTH, HEIGHT, WIDTH - 1, HEIGHT - 2);
    }

    public Cell[][] generate() {
        do {
            generateRaw();
        } while (!canBeSolved());

        return grid;
    }

    private Cell[][] generateRaw() {
        if (WIDTH < 6 || HEIGHT < 6) {
            System.out.println("Error: Width and Height of maze are too small. Cannot generate");
            return null;
        }

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                grid[i][j] = new Cell(i, j);

        int row = rand.nextInt(HEIGHT);
        int col = rand.nextInt(WIDTH);
        frontierCells.add(new Cell[]{grid[row][col], grid[row][col]});

        while (!frontierCells.isEmpty()) {
            Cell[] lFrontierCells = frontierCells.remove(rand.nextInt(frontierCells.size()));
            Cell frontCell = lFrontierCells[1];
            Cell connectCell = lFrontierCells[0];

            row = frontCell.getRow();
            col = frontCell.getCol();
            if (frontCell.getState().equals(BLOCKED)) {
                frontCell.setState(OPEN);
                connectCell.setState(OPEN);

                if (row >= 2 && grid[row - 2][col].getState().equals(BLOCKED))
                    frontierCells.add(new Cell[]{
                            grid[row - 1][col],
                            grid[row - 2][col]
                    });
                if (col >= 2 && grid[row][col - 2].getState().equals(BLOCKED))
                    frontierCells.add(new Cell[]{
                            grid[row][col - 1],
                            grid[row][col - 2]
                    });
                if (row < HEIGHT - 2 && grid[row + 2][col].getState().equals(BLOCKED))
                    frontierCells.add(new Cell[]{
                            grid[row + 1][col],
                            grid[row + 2][col]
                    });
                if (col < WIDTH - 2 && grid[row][col + 2].getState().equals(BLOCKED))
                    frontierCells.add(new Cell[]{
                            grid[row][col + 1],
                            grid[row][col + 2]
                    });
            }
        }

        // Open entities's starting location
        grid[1][1].setState(OPEN);

        // Block left and right sides
        for (int i = 0; i < WIDTH; i++) {
            grid[0][i].setState(BLOCKED);
            grid[HEIGHT - 1][i].setState(BLOCKED);
        }
        // Block top and bottom sides
        for (int i = 0; i < HEIGHT; i++) {
            grid[i][0].setState(BLOCKED);
            grid[i][WIDTH - 1].setState(BLOCKED);
        }

        // Open path for goal (every blocked cell left adjacent to goal)
        for (int i = WIDTH - 1; grid[HEIGHT - 2][i].getState().equals(BLOCKED);) {
            grid[HEIGHT - 2][i].setState(OPEN);
            i--;
        }

        return grid;
    }

    public void printMaze() {
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.getRow() == goal.getY() && cell.getCol() == goal.getX())
                    System.out.print("O ");
                else
                    System.out.print(cell.getState() == OPEN ? "  " : "B ");
            }
            System.out.println();
        }
    }

    public String printMazeToString() {
        StringBuilder maze = new StringBuilder();

        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                if (cell.getRow() == goal.getY() && cell.getCol() == goal.getX())
                    maze.append("O ");
                else
                    maze.append(cell.getState() == OPEN ? "  " : "█ ");
            }
            maze.append("\n");
        }

        return maze.toString();
    }

    private boolean canBeSolved() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                solveVisited[row][col] = false;
                solvedPath[row][col] = false;
            }
        }

        return solve(1, 1);
    }

    /**
     * Credit:
     * https://en.wikipedia.org/wiki/Maze_solving_algorithm#Recursive_algorithm
     */
    private boolean solve(int row, int col) {
        if (row == goal.getY() && col == goal.getX())
            return true;
        if (grid[row][col].getState().equals(BLOCKED) || solveVisited[row][col])
            return false;

        solveVisited[row][col] = true;
        if (row != 0)
            if (solve(row-1, col)) {
                solvedPath[row][col] = true;
                return true;
            }
        if (row != WIDTH - 1)
            if (solve(row+1, col)) {
                solvedPath[row][col] = true;
                return true;
            }
        if (col != 0)
            if (solve(row, col-1)) {
                solvedPath[row][col] = true;
                return true;
            }
        if (col != HEIGHT - 1)
            if (solve(row, col+1)) {
                solvedPath[row][col] = true;
                return true;
            }
        return false;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public boolean[][] getSolvedPath() {
        return solvedPath;
    }
}