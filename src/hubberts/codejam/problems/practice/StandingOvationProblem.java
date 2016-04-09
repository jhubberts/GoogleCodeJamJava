package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author jhubberts Apr 11, 2015.
 */
public class StandingOvationProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new StandingOvationProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        int maxShyness = sc.nextInt();
        String shynessStr = sc.next();
        int[] shyness = new int[maxShyness + 1];

        int i = 0;
        for( char c : shynessStr.toCharArray() ) {
            shyness[i++] = Character.getNumericValue( c );
        }

        int friends = 0;
        int clapping = 0;

        i = 0;


        while( i <= maxShyness ) {
            if( clapping < i ) {
                friends += 1;
                clapping += 1;
            }

            clapping += shyness[i];

            i += 1;
        }

        pw.write( String.valueOf( friends ) );
    }
}
