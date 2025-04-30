package model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private ShipType shipType;
    private int shipSize;
    private List<Coordinate> coordinates;
    private String direction; // h for horizontal and v for vertical
    private List<Coordinate> hits;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.shipSize = shipType.getSize();
        this.coordinates = new ArrayList<>();
        this.hits = new ArrayList<>();
    }

    public boolean isSunk() {
        return hits.size() == shipSize;
    }
    public boolean takeHit(Coordinate coordinate) {
        if(coordinates.contains(coordinate) && !hits.contains(coordinate)) {
            hits.add(coordinate);
            return true; //Hit

        }
        return false; //Miss
    }

    public boolean setPosition(Coordinate startCoordinate, String direction, int boardSize) {
        String dir = direction.toLowerCase();
        if(!dir.equals("h") && !dir.equals("v")) {
            return false; // Invalid direction
        }
        List<Coordinate> newCoordinates = new ArrayList<>();
        int x  = startCoordinate.getX();
        int y = startCoordinate.getY();
        for (int i = 0; i < shipSize; i++) {
           if(dir.equals("h")) {
               x = startCoordinate.getX() + i;
           } else {
               y = startCoordinate.getY() + i;
           }

            if(x >= boardSize || y >= boardSize || x < 0 || y < 0) {
                return false;
            }
            newCoordinates.add(new Coordinate(x, y));
        }


        this.coordinates = newCoordinates;
        this.direction = dir;
        return true;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public int getShipSize() {
        return shipSize;
    }

    public List<Coordinate> getCoordinates() {
        return new ArrayList<>(coordinates);
    }

    public String getDirection() {
        return direction;
    }

    public List<Coordinate> getHits() {
        return new ArrayList<>(hits);
    }
}