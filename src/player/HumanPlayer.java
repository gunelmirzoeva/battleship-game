package player;

import cli.ConsoleUI;
import model.Coordinate;
import model.Ship;
import model.ShipType;
import util.GameConstants;

import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scanner;

    public HumanPlayer(String name, ConsoleUI consoleUI) {
        super(name);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void placeShips() {
        System.out.println(getName() + " place your ships...");
        for(int i = 0; i < GameConstants.SHIP_TYPES.length; i++) {
            ShipType type = GameConstants.SHIP_TYPES[i];
            int count = GameConstants.SHIP_COUNTS[i];
            for (int j = 0; j < count; j++) {
                Ship ship = new Ship(type);
                boolean placed = false;
                while(!placed) {
                    System.out.printf("Enter start coordinate for %s (size %d, e.g., B2): ", type, type.getSize());
                    String input = scanner.nextLine().trim().toUpperCase();
                    Coordinate start = parseCoordinate(input);
                    if(start == null) {
                        System.out.println("Invalid coordinate. Try again.");
                        continue;
                    }
                    System.out.println("Enter direction (h for horizontal, v for vertical): ");
                    String direction = scanner.nextLine().trim().toLowerCase();
                    placed = ship.setPosition(start, direction, GameConstants.BOARD_SIZE);
                    if(placed) {
                        placed = getBoard().placeShip(ship);
                        if (!placed) {
                            System.out.println("Ship overlaps or is out of bounds. Try again.");
                        } else {
                            getShips().add(ship);
                        }
                    } else {
                        System.out.println("Invalid placement. Try again.");
                    }
                }
            }

        }

    }

    @Override
    public Coordinate takeShot() {
        while(true) {
            System.out.println(getName() + ", enter shot coordinate (e.g., B2): ");
            String input = scanner.nextLine().trim().toUpperCase();
            Coordinate coordinate = parseCoordinate(input);
            if (coordinate != null) {
                return coordinate;
            }
            System.out.println("Invalid coordinate. Try again.");
        }
    }

    private Coordinate parseCoordinate(String input) {
        if(input.length() < 2) return null;
        char row = input.charAt(0);
        if(row < 'A' || row > 'J') return null;
        int x = row - 'A';
        try {
            int y = Integer.parseInt(input.substring(1)) - 1;
            if (y < 0 || y >= GameConstants.BOARD_SIZE) {
                return null;
            }
            return new Coordinate(x, y);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
