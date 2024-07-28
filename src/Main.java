import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int N = 8;
    private static final int TRIALS = 15;

    public static void main(String[] args) {
        List<int[]> results = new ArrayList<>();
        for (int i = 0; i < TRIALS; i++) {
            EightQueen eq = new EightQueen();
            results.add(eq.solve());
        }
        printResults(results);
    }

    private static void printResults(List<int[]> results) {
        System.out.printf("%-10s %-10s %-10s %n", "Moves", "Restarts", "Time(ms)");
        for (int[] result : results) {
            System.out.printf("%-10d %-10d %-10d %n", result[0], result[1], result[2]);
}
}
}