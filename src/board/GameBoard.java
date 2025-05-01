package board;

import model.Cell;
import model.Coordinate;
import model.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private final Cell[][] grid;
    private final List<Ship> ships;
    private static final int BOARD_SIZE = 10;


    public GameBoard() {
        grid = new Cell[BOARD_SIZE][BOARD_SIZE];
        ships = new ArrayList<>();
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                grid[x][y] = new Cell(new Coordinate(x, y));
            }
        }
    }

    public boolean placeShip(Ship ship) {
        List<Coordinate> coordinates = ship.getCoordinates();
        if (coordinates.isEmpty()) {
            return false;
        }
        for (Coordinate coordinate : coordinates) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            if (isOutOfBound(coordinate) || grid[x][y].getState() == Cell.CellState.SHIP) {
                return false;
            }
        }

        for (Coordinate coordinate : coordinates) {
            grid[coordinate.getX()][coordinate.getY()].setState(Cell.CellState.SHIP);
        }
        ships.add(ship);
        return true;
    }

    public boolean receiveShot(Coordinate coordinate) {
        if (coordinate == null || isOutOfBound(coordinate)) {
            return false;
        }
        Cell cell = grid[coordinate.getX()][coordinate.getY()];
        if (cell.getState() == Cell.CellState.HIT || cell.getState() == Cell.CellState.MISS) {
            return false;
        }
        if (cell.getState() == Cell.CellState.SHIP) {
            cell.setState(Cell.CellState.HIT);
            for (Ship ship : ships) {
                if (ship.takeHit(coordinate)) {
                    return true;
                }
            }
        }
        cell.setState(Cell.CellState.MISS);
        return false;
    }

    public boolean allShipsSunk() {
        for(Ship ship : ships) {
            if(!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public Cell getCell(Coordinate coordinate) {
        if(isOutOfBound(coordinate)) {
            return null;
        }
        return grid[coordinate.getX()][coordinate.getY()];
    }

    private boolean isOutOfBound(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return x >= BOARD_SIZE || y >= BOARD_SIZE || x < 0 || y < 0;
    }

    public Cell[][] getGrid() {
        Cell[][] copy = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int x = 0; x < BOARD_SIZE; x++) {
            System.arraycopy(grid[x], 0, copy[x], 0, BOARD_SIZE);
        }
        return copy;
    }

    public List<Ship> getShips() {
        return new ArrayList<>(ships);
    }

}
