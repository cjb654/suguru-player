package test.java;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.Square;
import main.java.Position;
import main.java.Suguru;
import main.java.SuguruBuilder;

public class BuilderTests {
    private static SuguruBuilder testBuilder;
    private static Suguru suguru;

    @BeforeClass
    public static void setupSuguruBuilder() {
        testBuilder = new SuguruBuilder("src/test/java/testSuguru.json");
        suguru = testBuilder.getSuguru();
    }


    @Test
    public void testSuguru() {
        suguru = testBuilder.getSuguru();
        Assert.assertEquals(suguru.getNumSquares(), 6);
    }

    @Test
    public void testCornerPiece() {
        Square s = suguru.getSquare(new Position(0, 0));
        Assert.assertEquals(3, s.getAdjacent());
    }

    @Test
    public void testEdgePiece() {
        Square s = suguru.getSquare(new Position(1, 0));
        Assert.assertEquals(s.getAdjacent(), 5);
    }

    /*
    @Test
    public void testCentrePiece() {
        Square s = suguru.getSquare(new Position(0, 0));
        Assert.assertEquals(s.getAdjacent(), 8);
    }
    */
}
