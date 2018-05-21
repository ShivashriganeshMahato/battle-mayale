package game.map;

/**
 * @author Shivashriganesh Mahato
 */

public class Cell {
    private int row;
    private int col;
    private CellState state = CellState.OPEN;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellState getState() {
        return this.state;
    }

    void setState(CellState state) {
        this.state = state;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isOpen() {
        return getState().equals(CellState.OPEN);
    }
}