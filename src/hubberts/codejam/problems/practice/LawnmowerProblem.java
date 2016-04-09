package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://code.google.com/codejam/contest/2270488/dashboard
 * 10:15
 *
 * @author jhubberts Apr 06, 2015.
 */
public class LawnmowerProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new LawnmowerProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        int rows = sc.nextInt();
        int columns = sc.nextInt();

        int[][] lawn = new int[columns][rows];
        int[] maxRowHeight = new int[rows];
        int[] maxColumnHeight = new int[columns];

        for( int row = 0; row < rows; row++ ) {
            for( int column = 0; column < columns; column++ ) {
                lawn[column][row] = sc.nextInt();
                maxRowHeight[row] = Math.max( maxRowHeight[row], lawn[column][row] );
                maxColumnHeight[column] = Math.max( maxColumnHeight[column], lawn[column][row] );
            }
        }

        boolean solvable = true;

        for( int row = 0; row < rows; row++ ) {
            for( int column = 0; column < columns; column++ ) {
                solvable &= lawn[column][row]>= maxColumnHeight[column]
                    || lawn[column][row]>= maxRowHeight[row];
            }
        }

        pw.write( solvable ? "YES" : "NO" );
    }
}
