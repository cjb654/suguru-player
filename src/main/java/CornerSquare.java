package main.java;

public class CornerSquare extends Square{
    public CornerSquare(int boxNumber, Position position) {
        super(boxNumber, position);
        this.adjacent = 3;
    }

    public CornerSquare(int number, int boxNumber, Position position) {
        super(number, boxNumber, position);
        this.adjacent = 3;
    }
}
