package player;

import board.GameBoard;
import model.Coordinate;
import model.Ship;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final String name;
    private final List<Ship> ships;
    private final GameBoard board;

    public Player(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
        this.board = new GameBoard();
    }

    public abstract void placeShips();

    public abstract Coordinate takeShot();

    public boolean hasLost() {
        return board.allShipsSunk();
    }

    public String getName() {
        return name;
    }

    public GameBoard getBoard() {
        return board;
    }

    public List<Ship> getShips() {
        return new ArrayList<>(ships);
    }

}
