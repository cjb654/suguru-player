package test.java;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.*;

public class SuguruTests {
    
    private static Suguru testSuguru;

    @BeforeClass
    public static void setupTestSuguru() {
        testSuguru = new Suguru(2, 3);
        
        Square s00 = new Square(2, 0, new Position(0, 0));
        Square s01 = new Square(3, 1, new Position(1, 0));
        Square s02 = new Square(1, 1, new Position(2, 0));
        Square s10 = new Square(1, 0, new Position(0, 1));
        Square s11 = new Square(4, 1, new Position(1, 1));
        Square s12 = new Square(2, 1, new Position(2, 1));

        testSuguru.addSquare(s00);
        testSuguru.addSquare(s01);
        testSuguru.addSquare(s02);
        testSuguru.addSquare(s10);
        testSuguru.addSquare(s11);
        testSuguru.addSquare(s12);

        Box b1 = new Box(2,0);
        Box b2 = new Box(4,1);
        Box[] boxes = new Box[2];
        boxes[0] = b1; boxes[1] = b2;

        testSuguru.setBoxes(boxes);
    }
    
    @Test
    public void testSquareXValue() {
        Square s = testSuguru.getSquare(new Position(0, 0));
        Assert.assertEquals(s.getPosition().getX(), 0);
    }

    @Test
    public void testSquareYValue() {
        Square s = testSuguru.getSquare(new Position(0, 0));
        Assert.assertEquals(s.getPosition().getY(), 0);
    }

    @Test
    public void testCompleted() {
        Assert.assertEquals(testSuguru.isCompleted(), false);
    }

    @Test
    public void testComparePositions() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(0, 0);
        Assert.assertEquals(p1, p2);
    }
}
