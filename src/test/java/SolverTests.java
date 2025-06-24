package test.java;

import java.util.Enumeration;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.back.Position;
import main.java.back.Square;
import main.java.back.Suguru;
import main.java.back.SuguruBuilder;
import main.java.back.SuguruSolver;

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
