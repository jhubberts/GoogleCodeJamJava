package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.*;

/**
 * http://code.google.com/codejam/contest/32016/dashboard
 *
 * @author jhubberts Apr 01, 2015.
 */
public class MinimumScalarProductProblem extends CodeJamProblem {

    public static void main( String[] args ) {
        new MinimumScalarProductProblem().run();
    }

    @Override
    public void solveCase( Scanner sc, PrintWriter pw ) throws Exception {
        int length = sc.nextInt();
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for( int i=0; i<length; i++ ) { x.add( sc.nextInt() ); }
        for( int i=0; i<length; i++ ) { y.add( sc.nextInt()); }
        Collections.sort(x);
        Collections.sort(y);
        long sum = 0;
        for( int i=0; i<length; i++ ) { sum += x.get( i ) * y.get( length - i - 1 ); }
        pw.write( String.valueOf( sum ) );
    }

}
