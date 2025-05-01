package model;

public class Cell {
    private final Coordinate coordinate;
    private CellState state;

    public enum CellState {
        EMPTY,
        SHIP,
        HIT,
        MISS
    }
    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.state = CellState.EMPTY;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }
}
