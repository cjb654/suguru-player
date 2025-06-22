package main.java;

public class CentreSquare extends Square{
    public CentreSquare(int boxNumber, Position position) {
        super(boxNumber, position);
        this.adjacent = 8;
    }

    public CentreSquare(int number, int boxNumber, Position position) {
        super(number, boxNumber, position);
        this.adjacent = 8;
    }
}
