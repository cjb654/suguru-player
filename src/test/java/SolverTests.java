package test.java;

import java.util.Enumeration;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.Position;
import main.java.Square;
import main.java.Suguru;
import main.java.SuguruBuilder;
import main.java.SuguruSolver;

public class SolverTests {
    private static SuguruBuilder unsolvedBuilder;
    private static SuguruBuilder solvedBuilder;
    private static Suguru unsolvedSuguru;    
    private static Suguru solvedSuguru;

    @BeforeClass
    public static void setupSuguruBuilder() {
        unsolvedBuilder = new SuguruBuilder("src/test/java/testUnsolvedSuguru.json");
        unsolvedSuguru = unsolvedBuilder.getSuguru();
        solvedBuilder = new SuguruBuilder("src/test/java/testsolvedSuguru.json");
        solvedSuguru = solvedBuilder.getSuguru();
    }

    @Test
    public void isSolvedSolved() {
        Assert.assertTrue(solvedSuguru.isCompleted());
    }

    @Test
    public void testSolving() {
        SuguruSolver solver = new SuguruSolver(unsolvedSuguru);
        solver.solveSuguru();
        Enumeration<Square> elems = unsolvedSuguru.getSquares();
        Square s;
        Square desiredSquare;
        while(elems.hasMoreElements()){
            s = (Square) elems.nextElement();
            Position desired = s.getPosition();
            desiredSquare = solvedSuguru.getSquare(desired);
            Assert.assertEquals(desiredSquare.getNumber(), s.getNumber());
        }
    }
}
