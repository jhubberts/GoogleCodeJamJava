package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author jhubberts Apr 06, 2015.
 */
public class MinesweeperMasterProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new MinesweeperMasterProblem().run();
    }

    class MinesweeperGame {
        private char[][] board;
        private int mines;
        private int rows;
        private int columns;

        private int blanksToGo;

        public MinesweeperGame( int rows, int columns, int mines ) {
            this.rows = rows;
            this.columns = columns;
            this.mines = mines;

            this.board = new char[columns][rows];
            for( char[] row : this.board ) { Arrays.fill( row, '*' ); }

            this.blanksToGo = rows * columns - mines - 1;
            board[columns/2][rows/2] = 'c';
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();

            for( int y = 0; y < rows; y++ ) {
                builder.append( '\n' );
                for( int x = 0; x < columns; x++ ) { builder.append( board[x][y] ); }
            }

            return builder.toString();
        }
    }

    @Override
    public void solveCase( Scanner sc, PrintWriter pw ) throws Exception {
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        int mines = sc.nextInt();

        pw.write( new MinesweeperGame( rows, columns, mines ).toString() );
    }
}
