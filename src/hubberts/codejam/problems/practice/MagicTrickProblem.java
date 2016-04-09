package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.*;

/**
 * http://code.google.com/codejam/contest/2974486/dashboard
 * 14:46
 *
 * @author jhubberts Apr 06, 2015.
 */
public class MagicTrickProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new MagicTrickProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        int row1;
        int row2;

        int[][] grid1;
        int[][] grid2;

        row1 = sc.nextInt();
        grid1 = readGrid(sc);
        row2 = sc.nextInt();
        grid2 = readGrid( sc );

        Set<Integer> possibilities1 = new HashSet<>();
        Set<Integer> possibilities2 = new HashSet<>();
        for( int i = 0; i < 4; i++ ) { possibilities1.add( grid1[i][row1-1]); }
        for( int i = 0; i < 4; i++ ) { possibilities2.add( grid2[i][row2-1]); }

        Set<Integer> possibilities = new HashSet<>();
        for( Integer i : possibilities1 ) {
            if( possibilities2.contains( i ) ) { possibilities.add( i ); }
        }

        if( possibilities.size() == 0 ) {
            pw.write( "Volunteer cheated!" );
        } else if( possibilities.size() > 1 ) {
            pw.write("Bad magician!");
        } else {
            pw.write( String.valueOf( possibilities.toArray()[0] ) );
        }
    }

    private int[][] readGrid(Scanner sc) {
        int[][] retGrid = new int[4][4];

        for( int y=0; y<4; y++ ) {
            for (int x = 0; x < 4; x++) {
                retGrid[x][y] = sc.nextInt();
            }
        }

        return retGrid;
    }
}
