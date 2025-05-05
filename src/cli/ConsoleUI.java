package cli;

import board.GameBoard;
import model.Cell;
import model.Coordinate;
import util.GameConstants;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public Coordinate getCoordinate(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() < 2) {
                System.out.println("Invalid coordinate. Try again.");
                continue;
            }
            char row = input.charAt(0);
            if (row < 'A' || row > 'J') {
                System.out.println("Invalid row. Use A-J. Try again.");
                continue;
            }
            int x = row - 'A';
            try {
                int y = Integer.parseInt(input.substring(1)) - 1;
                if (y < 0 || y >= GameConstants.BOARD_SIZE) {
                    System.out.println("Invalid column. Use 1-10. Try again.");
                    continue;
                }
                return new Coordinate(x, y);
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Use e.g., B2. Try again.");
            }
        }
    }

    public String getDirection(String prompt) {
        while (true) {
            System.out.println(prompt);
            String direction = scanner.nextLine().trim().toLowerCase();
            if (direction.equals("h") || direction.equals("v")) {
                return direction;
            }
            System.out.println("Invalid direction. Use 'h' or 'v'. Try again.");
        }
    }

    public void displayBoard(GameBoard gameBoard, boolean hideShips) {
        System.out.print("  ");
        for (int i = 1; i <= GameConstants.BOARD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int x = 0; x < GameConstants.BOARD_SIZE; x++) {
            System.out.print((char) ('A' + x) + " ");
            for (int y = 0; y < GameConstants.BOARD_SIZE; y++) {
                Cell cell = gameBoard.getCell(new Coordinate(x, y));
                char symbol;
                if (cell.getState() == Cell.CellState.HIT) {
                    symbol = 'X';
                } else if (cell.getState() == Cell.CellState.MISS) {
                    symbol = 'O';
                } else if (cell.getState() == Cell.CellState.SHIP && !hideShips) {
                    symbol = 'S';
                } else {
                    symbol = '~';
                }
                System.out.print(symbol + " ");
            }
            System.out.println();
        }

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
