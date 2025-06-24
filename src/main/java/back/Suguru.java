package main.java.back;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Suguru {
    
    private int length;
    private int breadth;
    private Box[] boxes;
    private boolean completed;
    private Dictionary<Position, Square> squares;

    public Suguru(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
        squares = new Hashtable<>();
        completed = false;
    }

    public boolean addSquare(Square square) {
        if(squares.size() >= length*breadth) { return false; }
        if(doesSquareExist(square.getPosition())) { return false; }
        Position p = square.getPosition();
        squares.put(p, square);
        return true;
    }

    private boolean doesSquareExist(Position p) {
        if(squares.get(p) == null) { return false; }
        return true;
    }

    public void setBoxes(Box[] boxes) { this.boxes = boxes;}

    public Enumeration<Square> getSquares() { return squares.elements(); }

    public boolean isCompleted() { 
        updateCompleted();
        return completed;
    }

    public void updateCompleted() {
        completed = true;
        for(Box b : boxes) {
            b.updateCompleted();
            if(!b.isCompleted()) completed = false;
        }
    }

    public Square getSquare(Position p) {
        if(doesSquareExist(p)) { return squares.get(p); }
        //squares.ke
        return null;
    }

    public int getNumSquares() { return length*breadth; }

    public void fillSingleGaps() {
        for(Box b : boxes) {
            if(b.getNumEmpty() == 1) {
                b.fillSingleGap();
            }
        }
        updateCompleted();
    }

    public void fillByAdjacents() {
        Square s;
        Enumeration<Square> elems = squares.elements();
        Box box = null;
        while(elems.hasMoreElements()){
            s = (Square) elems.nextElement();
            if(!s.isEmpty()) continue;
            int boxNumber = s.getBoxNumber();
            box = null;
            for(Box b : boxes) {
                if(b.getBoxNumber() == boxNumber)  {
                    box = b; 
                    break;
                }
            }
            ArrayList<Integer> neededInBox = box.getMissingNums();
            int minCounter = neededInBox.size();
            for(int i : neededInBox) {
                if(s.isNumberinAdjacentSquare(i)) {
                    minCounter--;
                    if(minCounter == 1) {
                        s.setNumber(neededInBox.get(0));
                        box.addNumber(s);
                    }
                }
            }            
        }
    }

    public void setAdjacents() {
        Position tl = new Position(0, 0);
        Position bl = new Position(0, length-1);
        Position tr = new Position(breadth-1, 0);
        Position br = new Position(breadth-1, length-1);
        
        Square s;
        ArrayList<Square> adj;
        Enumeration<Square> elems = squares.elements();
        while(elems.hasMoreElements()){
            s = (Square) elems.nextElement();
            adj = new ArrayList<>();
            if(s.getPosition().equals(tl)) {
                s.setAdjacent(3);
                adj.add(squares.get(new Position(1,0)));
                adj.add(squares.get(new Position(0,1)));
                adj.add(squares.get(new Position(1,1)));
            }else if(s.getPosition().equals(bl)) {
                s.setAdjacent(3);
                adj.add(squares.get(new Position(0,length-2)));
                adj.add(squares.get(new Position(1,length-2)));
                adj.add(squares.get(new Position(1,length-1)));

            }else if(s.getPosition().equals(tr)) {
                s.setAdjacent(3);
                adj.add(squares.get(new Position(breadth-2,0)));
                adj.add(squares.get(new Position(breadth-2,1)));
                adj.add(squares.get(new Position(breadth-1,1)));

            }else if(s.getPosition().equals(br)) {
                s.setAdjacent(3);
                adj.add(squares.get(new Position(breadth-1,length-2)));
                adj.add(squares.get(new Position(breadth-2,length-2)));
                adj.add(squares.get(new Position(breadth-2,length-1)));

            }else if(s.getPosition().getX() == 0) {
                s.setAdjacent(5);
                int y = s.getPosition().getY();
                adj.add(squares.get(new Position(0, y-1)));
                adj.add(squares.get(new Position(0, y+1)));
                adj.add(squares.get(new Position(1, y-1)));
                adj.add(squares.get(new Position(1, y)));
                adj.add(squares.get(new Position(1, y-1)));
                
            }else if(s.getPosition().getX() == breadth-1) {
                s.setAdjacent(5);
                int y = s.getPosition().getY();
                adj.add(squares.get(new Position(breadth-1, y-1)));
                adj.add(squares.get(new Position(breadth-1, y+1)));
                adj.add(squares.get(new Position(breadth-2, y-1)));
                adj.add(squares.get(new Position(breadth-2, y)));
                adj.add(squares.get(new Position(breadth-2, y-1)));
            }else if(s.getPosition().getY() == 0) {
                s.setAdjacent(5);
                int x = s.getPosition().getX();
                adj.add(squares.get(new Position(x-1, 0)));
                adj.add(squares.get(new Position(x+1, 0)));
                adj.add(squares.get(new Position(x-1, 1)));
                adj.add(squares.get(new Position(x, 1)));
                adj.add(squares.get(new Position(x+1, 1)));
            }else if(s.getPosition().getY() == length-1) {
                s.setAdjacent(5);
                int x = s.getPosition().getX();
                adj.add(squares.get(new Position(x-1, length-1)));
                adj.add(squares.get(new Position(x+1, length-1)));
                adj.add(squares.get(new Position(x-1, length-2)));
                adj.add(squares.get(new Position(x, length-2)));
                adj.add(squares.get(new Position(x+1, length-2)));
            }
            else {
                s.setAdjacent(8);
                int x = s.getPosition().getX();
                int y = s.getPosition().getY();
                adj.add(squares.get(new Position(x-1, y-1)));
                adj.add(squares.get(new Position(x-1, y)));
                adj.add(squares.get(new Position(x-1, y+1)));
                adj.add(squares.get(new Position(x, y-1)));
                adj.add(squares.get(new Position(x, y+1)));
                adj.add(squares.get(new Position(x+1, y-1)));
                adj.add(squares.get(new Position(1+1, y)));
                adj.add(squares.get(new Position(x+1, y+1)));
            }
            for(Square sq : adj) { s.addAdjacentSquare(sq); }
        }
    }
}
