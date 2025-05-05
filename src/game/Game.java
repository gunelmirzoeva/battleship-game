package game;

import cli.ConsoleUI;
import model.Coordinate;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;

public class Game {
    private final Player human;
    private final Player computer;
    private final ConsoleUI consoleUI;

    public Game() {
        this.consoleUI = new ConsoleUI();
        this.human = new HumanPlayer("Player", consoleUI);
        this.computer = new ComputerPlayer("Computer");
    }

    public void play() {
        consoleUI.displayMessage("Setting up boards...");
        human.placeShips();
        computer.placeShips();

        while(!human.hasLost() && !computer.hasLost()) {
            consoleUI.displayMessage("\n-------- Player's board --------");
            consoleUI.displayBoard(human.getBoard(), false);
            consoleUI.displayMessage("\n---------- Computer's Board ----------");
            consoleUI.displayBoard(computer.getBoard(), true);

            Coordinate humanShot = human.takeShot();
            boolean hit = computer.getBoard().receiveShot(humanShot);
            consoleUI.displayMessage("Player shots at " + formatCoordinate(humanShot) + ":" + (hit? "Hit!" : "Miss!"));


            if(computer.hasLost()) {
                break;
            }

            Coordinate computerShot = computer.takeShot();
            hit = human.getBoard().receiveShot(computerShot);
            consoleUI.displayMessage("Computer shots at " + formatCoordinate(computerShot) + ":" + (hit ? "Hit!" : "Miss!"));
        }
        consoleUI.displayMessage("\nFinal Board");
        consoleUI.displayMessage("\nPlayer's Board");
        consoleUI.displayBoard(human.getBoard(), false);
        consoleUI.displayMessage("\nComputer's Board:");
        consoleUI.displayBoard(computer.getBoard(), false);

        if(human.hasLost()) {
            consoleUI.displayMessage("Game Over! Computer wins!");
        } else {
            consoleUI.displayMessage("Congratulations! Player wins!");
        }

    }
    private String formatCoordinate(Coordinate coordinate) {
        return (char) ('A' + coordinate.getX()) + "" + (coordinate.getY() + 1);
    }

}
