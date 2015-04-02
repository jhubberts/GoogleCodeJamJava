package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.*;
import java.util.*;

/**
 * http://code.google.com/codejam/contest/189252/dashboard
 *
 * @author jhubberts Mar 31, 2015.
 */
public class AllYourBaseProblem extends CodeJamProblem {

    public static void main( String[] args ) {
        new AllYourBaseProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        pw.write( String.valueOf( stringToMinSeconds( sc.next() ) ) );
    }

    public static long stringToMinSeconds( String str ) {
        Map<Character,Integer> digitMap = new HashMap<>();
        List<Integer> reversedDigits = new ArrayList<>();

        int base = 1;
        boolean zeroSeen = false;

        for( Character c : str.toCharArray() ) {
            if( !digitMap.containsKey( c ) ) {
                if( base == 2 && !zeroSeen ) {
                    digitMap.put( c, 0 );
                    zeroSeen = true;
                } else {
                    digitMap.put(c, base++);
                }
            }

            reversedDigits.add( digitMap.get( c ) );
        }

        long sum = 0;
        long pow = 1;

        for( int i = reversedDigits.size() - 1; i >= 0; i-- ) {
            sum += reversedDigits.get( i ) * pow;
            pow *= base;
        }

        return sum;
    }

}
