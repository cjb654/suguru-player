package main.java.back;

import java.util.ArrayList;

public class Box {
    
    private int size;
    private boolean completed;
    private int boxNumber;
    private int numCompleted;
    private Square[] squares;
    private int numSquares;
    private ArrayList<Integer> missingNums; 

    public Box(int size, int boxNumber) {
        this.size = size;
        this.boxNumber = boxNumber;
        completed = false;
        numCompleted = 0;
        squares = new Square[size];
        numSquares = 0;
        missingNums = new ArrayList<>();
        for(int i=0;i<size;i++) {
            missingNums.add(i+1);
        }
    }

    public Box(int boxNumber) {
        this.boxNumber = boxNumber;
        completed = false;
        numCompleted = 0;
        squares = new Square[size];
        numSquares = 0;
        missingNums = new ArrayList<>();
    }

    public void setSize(int size) { 
        this.size = size; 
        squares = new Square[size];
        for(int i=0;i<size;i++) {
            missingNums.add(i+1);
        }
    }

    public int getBoxNumber() { return boxNumber; }
    public boolean isCompleted() { return completed; }
    public int getNumCompleted() { return numCompleted; }
    public int getSize() { return size; }
    public ArrayList<Integer> getMissingNums() { return missingNums; }

    public void updateCompleted() {
        for(Square s : squares) {
            if(s.isEmpty()) return;
        }
        completed = true;
    }

    public void addNumber(Square s) {
        numCompleted++;
        for(int i=0;i<missingNums.size();i++) {
            if(missingNums.get(i) == s.getNumber()) {
                missingNums.remove(i);
                break;
            }
        }
    }

    public boolean addSquare(Square square) {
        if(numSquares < size) {
            squares[numSquares++] = square;
            square.setBox(this);
            if(!square.isEmpty()) {
                addNumber(square);
            }
            return true;
        }
        else { return false; }
    }

    public void fillSingleGap() {
        if(size - numCompleted != 1) return;
        for(Square s : squares) {
            if(s.isEmpty()) {
                s.setNumber(missingNums.remove(0));
                numCompleted++;
                updateCompleted();
            }
        }
    }

    public int getNumEmpty() {
        return size - numCompleted;
    }

    public boolean doesBoxHaveNumber(int number) {
        for(Square s : squares) {
            if( s.getNumber() == number) { return true; }
        }
        return false;
    }
}
