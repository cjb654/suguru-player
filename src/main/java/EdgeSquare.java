package main.java;

public class EdgeSquare extends Square {
    
    public EdgeSquare(int boxNumber, Position position) {
        super(boxNumber, position);
        this.adjacent = 5;
    }

    public EdgeSquare(int number, int boxNumber, Position position) {
        super(number, boxNumber, position);
        this.adjacent = 5;
    }
}
