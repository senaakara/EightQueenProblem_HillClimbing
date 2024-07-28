import java.util.Random;

public class EightQueen {

    private static final int N = 8; // Number of queens
    private int[] queens; // Queens' positions
    private final Random random = new Random();


    // Constructor
    public EightQueen() {
        this.queens = new int[N];
        randomizeQueens();
    }

    // Randomly places queens on the board
    private void randomizeQueens() {
        for (int i = 0; i < N; i++) {
            queens[i] = random.nextInt(N);
        }
    }

    // Calculates the heuristic value of the current state
    private int calculateHeuristic() {
        int heuristic = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (queens[i] == queens[j] || Math.abs(queens[i] - queens[j]) == Math.abs(i - j)) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }

    // Tries to find a solution using hill climbing
    //  algorithm to find a solution for the 8 queens problem
    public int[] solve() {
        int moves = 0;
        int restarts = 0;
        int currentHeuristic = calculateHeuristic();
        long startTime = System.currentTimeMillis();

        while (currentHeuristic > 0) {
            int bestHeuristic = currentHeuristic;
            int[] bestBoard = queens.clone();

            // Try moving each queen to reduce the number of attacks
            for (int row = 0; row < N; row++) {
                int initialPosition = queens[row];
                for (int col = 0; col < N; col++) {
                    if (col != initialPosition) {
                        // Move queen and test heuristic
                        queens[row] = col;
                        int newHeuristic = calculateHeuristic();
                        if (newHeuristic < bestHeuristic) {
                            bestHeuristic = newHeuristic;
                            bestBoard = queens.clone();
                        }
                    }
                }
                // Revert queen's position
                queens[row] = initialPosition;
            }

            // Compare the best found heuristic with the current one
            if (bestHeuristic < currentHeuristic) {
                currentHeuristic = bestHeuristic;
                queens = bestBoard;
                moves++;
            } else {
                // No better board was found, random restart
                randomizeQueens();
                currentHeuristic = calculateHeuristic();
                restarts++;
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        printBoard();
        System.out.println("Solution found with " + moves + " moves and " + restarts + " restarts in " + duration + " milliseconds.");
        return new int[]{moves, restarts, (int) duration};
    }


    // Prints the board
    private void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (queens[i] == j) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}