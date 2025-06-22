package main.java;

public class SuguruSolver {
    
    private Suguru suguru;

    public SuguruSolver(Suguru s) {
        suguru = s;
    }

    public Suguru getSuguru() { return suguru; }

    public void solveSuguru() {
        suguru.fillSingleGaps();
        suguru.fillByAdjacents();
    }
}
