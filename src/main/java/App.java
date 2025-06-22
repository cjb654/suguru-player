package main.java;

public class App {
    public static void main(String[] args) {
        SuguruBuilder builder = new SuguruBuilder("src/test/java/testSuguruGaps.json");
        Suguru suguru = builder.getSuguru();
        SuguruSolver solver = new SuguruSolver(suguru);
        solver.solveSuguru();
        System.out.println(suguru.isCompleted());
    }
}
