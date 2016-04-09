package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

/**
 * @author jhubberts Apr 07, 2015.
 */
public class FairAndSquareProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new FairAndSquareProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        String minStr = sc.next();
        String maxStr = sc.next();

        int minDigits = minStr.length();
        int maxDigits = maxStr.length();

        int[] min = new int[minStr.length()];
        for( int i = 0; i < minStr.length(); i++ ) { min[i] = minStr.charAt( i ) - '0'; }

        int[] max = new int[maxStr.length()];
        for( int i = 0; i < maxStr.length(); i++ ) { max[i] = maxStr.charAt( i ) - '0'; }

        int minPartial = getPartial( min );
        int maxPartial = getPartial( max );

        pw.write( String.valueOf( getNDigitPalindromes( 6 ) ) );
    }

    public int getNDigitPalindromes( int n ) {
        int result = 9;
        for( int i=0; i < ( n - 1 ) / 2; i++ ) { result *= 10; }
        return result;
    }

    public int getPartial( int[] number ) {
        int digits = number.length;
        int pow = 1;
        int total = 0;

        for( int i = ( digits - 1 ) / 2; i >= 0; i-- ) {
            int high = number[i];
            int low = number[digits - i - 1];

            if( low < high && i != 0 ) {
                high += 1;
            }

            total += high * pow;
            pow *= 10;
        }

        return total;
    }

}
