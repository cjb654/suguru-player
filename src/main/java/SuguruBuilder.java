package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class SuguruBuilder {

    private Suguru suguru;
    
    private int length;
    private int breadth;

    public SuguruBuilder(String path) {

        JSONObject suguruJSON = readJSONFile(path);

        length = suguruJSON.getInt("length");
        breadth = suguruJSON.getInt("breadth");
        int size = length*breadth;

        suguru = new Suguru(length, breadth);

        Square[] squares;
        Box[] boxes;
        ArrayList<Integer> boxArray = new ArrayList<Integer>();
        squares = new Square[size];
        JSONArray squaresArray = suguruJSON.getJSONArray("squares");
        for(int i=0;i<size;i++) {
            JSONObject square = squaresArray.getJSONObject(i);

            int x = square.getInt("xPos");
            int y = square.getInt("yPos");
            Position p = new Position(x, y);

            int number = -1;
            try{ number = square.getInt("number"); } catch (Exception ex) {}

            int boxNumber = square.getInt("boxNumber");
            addBoxNum(boxArray, boxNumber);

            Square s;
            if(number != -1) { s = new Square(number, boxNumber, p); }
            else { s = new Square(boxNumber, p); }
            
            suguru.addSquare(s);
            squares[i] = s;
        }

        suguru.setAdjacents();

        boxes = new Box[(boxArray.size())];
        for(int i : boxArray) {
            boxes[i] = new Box(i);
        }

        for(Box b : boxes) {
            int num = getSquaresWithNum(squares, b.getBoxNumber());
            b.setSize(num);
        }

        for(Box b : boxes) {
            for(Square s : squares) {
                if(s.getBoxNumber() == b.getBoxNumber()) {
                    b.addSquare(s);
                }
            }
        }
        suguru.setBoxes(boxes);
        suguru.updateCompleted();
    }

    public void addBoxNum(ArrayList<Integer> boxes, int num) {
        if(boxes.contains(num)) return;
        boxes.add(num);
    }

    public int getSquaresWithNum(Square[] squares, int boxNum) {
        int total = 0;
        for(Square s : squares) {
            if(s.getBoxNumber() == boxNum) { total++; }
        }
        return total;
    }

    public Suguru getSuguru() { return suguru; }

    public JSONObject readJSONFile(String path) {

        try { 
            String jsonData = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject jsonObject = new JSONObject(jsonData);
            return jsonObject;

        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
