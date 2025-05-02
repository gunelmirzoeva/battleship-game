package util;

import model.ShipType;

public class GameConstants {
    public static final int BOARD_SIZE = 10;

    public static final ShipType[] SHIP_TYPES = {
            ShipType.CARRIER, ShipType.BATTLESHIP, ShipType.CRUISER,
            ShipType.SUBMARINE, ShipType.DESTROYER
    };
    public static final int[] SHIP_COUNTS = {1, 1, 1, 1, 1};
}
