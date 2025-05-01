package model;

import java.util.Objects;

public class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new IllegalArgumentException("Coordinate out of bounds");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString() {
        char row = (char) ('A' + x);
        int col = y + 1;
        return row + Integer.toString(col);
    }
}
