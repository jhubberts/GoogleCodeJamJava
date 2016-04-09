package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.*;

/**
 * @author jhubberts Apr 11, 2015.
 */
public class InfiniteHouseOfPancakesProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new InfiniteHouseOfPancakesProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        int initialPlates = sc.nextInt();
        List<Integer> plates = new ArrayList<>();
        for( int i = 0; i < initialPlates; i++ ) {
            plates.add(sc.nextInt());
        }

        int splits = performSplits( plates );
        Collections.sort( plates, (x, y) -> Integer.compare( y, x ) );

        pw.write( String.valueOf( splits + plates.get( 0 ) ) );
    }

    // minimum optimal splits
    // greater of n/2 or next value is the max turns
    // once you hit optimal, return

    private int performSplits( List<Integer> plates ) {
        int splits = 0;

        Collections.sort( plates, ( x, y ) -> Integer.compare( y, x ) );
        int maxMoves =  plates.get( 0 );

        for( int i = 0; i < plates.size(); i++ ) {
            List<Integer> theoreticalPlates = new ArrayList<>();
            for( int j = 0; j < plates.size(); j++ ) {
                if( j < i ) {
                    int val = plates.get( j );
                    int high = val / 2 + val%2;
                    int low = val / 2;
                    theoreticalPlates.add( high );
                    theoreticalPlates.add( low );
                }

                Collections.sort( theoreticalPlates, (x, y) -> Integer.compare( y, x ) );
                if( theoreticalPlates.size() > 0 && theoreticalPlates.get( 0 ) + ++splits <= maxMoves ) {
                    splits = j;
                    maxMoves = theoreticalPlates.get(0) + splits;
                }
            }
        }

        return splits;
    }

}
