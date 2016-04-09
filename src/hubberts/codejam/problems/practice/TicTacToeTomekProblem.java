package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author jhubberts Apr 02, 2015.
 */
public class TicTacToeTomekProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new TicTacToeTomekProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        char[][] board = new char[4][4];
        for( int i=0; i<4; i++ ) board[i] = sc.nextLine().toCharArray();
    }
}
