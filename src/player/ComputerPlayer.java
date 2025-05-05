package player;

import model.Coordinate;
import model.Ship;
import model.ShipType;
import util.GameConstants;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
    private final Random random;
    private final Set<Coordinate> shotsTaken = new HashSet<>();
    public ComputerPlayer(String name) {
        super(name);
        this.random = new Random();
    }

    @Override
    public void placeShips() {
        for (int i = 0; i < GameConstants.SHIP_TYPES.length; i++) {
            ShipType shipType = GameConstants.SHIP_TYPES[i];
            int count = GameConstants.SHIP_COUNTS[i];
            for (int j = 0; j < count; j++) {
                Ship ship = new Ship(shipType);
                boolean placed = false;
                while(!placed) {
                    int x = random.nextInt(GameConstants.BOARD_SIZE);
                    int y = random.nextInt(GameConstants.BOARD_SIZE);
                    Coordinate start = new Coordinate(x, y);
                    String direction = random.nextBoolean() ? "h" : "v";
                    placed = ship.setPosition(start, direction, GameConstants.BOARD_SIZE);
                    if (placed) {
                        placed = getBoard().placeShip(ship);
                        if(placed) {
                            getShips().add(ship);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Coordinate takeShot() {
        Coordinate coordinate;
        do {
            int x = random.nextInt(GameConstants.BOARD_SIZE);
            int y = random.nextInt(GameConstants.BOARD_SIZE);
            coordinate = new Coordinate(x, y);
        } while (shotsTaken.contains(coordinate));
        shotsTaken.add(coordinate);
        return coordinate;
    }
}
