package main.java.back;

import java.util.ArrayList;

public class Square {
    
    private int number;
    private boolean hasNumber;
    private Position position;
    private int boxNumber;
    private Box box;
    protected int adjacent;
    private ArrayList<Square> adjacentSquares;

    public Square(int boxNumber, Position position) {
        this.boxNumber = boxNumber;
        this.position = position;
        hasNumber = false;
        adjacentSquares = new ArrayList<>();
    }

    public Square(int number, int boxNumber, Position position) {
        this.boxNumber = boxNumber;
        this.position = position;
        this.number = number;
        hasNumber = true;
        adjacentSquares = new ArrayList<>();
    }

    public boolean isEmpty() { return !hasNumber; }
    public Position getPosition() { return position; }
    public int getNumber() { return number; }
    public int getBoxNumber() { return boxNumber; }

    public void setBox(Box box) { this.box = box; }

    public boolean setNumber(int num) {
        if(tryNumber(num)) {
            number = num;
            hasNumber = true;
            box.updateCompleted();
            return true;
        }
        else { return false; }
    }

    private boolean tryNumber(int num) {
        if(box.doesBoxHaveNumber(num)) { return false; }
        if(isNumberinAdjacentSquare(num)) { return false; }
        return true;
    }

    public boolean isNumberinAdjacentSquare(int num) {
        for(Square s : adjacentSquares) {
            if(!s.isEmpty()) {
                if(s.getNumber() == num) { return true; }
            }
        }
        return false;
    }

    public void addAdjacentSquare(Square s) { adjacentSquares.add(s); }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square s = (Square) o;
        return position.equals(s.getPosition());
    }

    public int getAdjacent() { return adjacent; }
    public void setAdjacent(int a) { this.adjacent = a; }
}
