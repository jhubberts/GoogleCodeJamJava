package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.*;

/**
 * http://code.google.com/codejam/contest/351101/dashboard
 *
 * @author jhubberts Apr 01, 2015.
 */
public class StoreCreditProblem extends CodeJamProblem {

    public static void main( String[] args ) {
        new StoreCreditProblem().run();
    }

    @Override
    public String getProblemName() {
        return "store-credit";
    }

    @Override
    public void solveCase( Scanner sc, PrintWriter pw ) throws Exception {
        int credit = sc.nextInt();
        int numItems = sc.nextInt();
        List<Integer> items = new ArrayList<>();
        for( int i = 0; i < numItems; i++ ) { items.add( sc.nextInt() ); }
        Pair<Integer> indexes = getResult( credit, items );
        pw.write( indexes.x + " " + indexes.y );
    }

    protected static class Pair<T> {
        public T x;
        public T y;

        public Pair ( T x, T y ) {
            this.x = x;
            this.y = y;
        }
    }

    protected static Pair<Integer> getResult( int credit, List<Integer> items ) {
        Map<Integer,List<Integer>> indexMap = new HashMap<>();

        for( int idx=0; idx < items.size(); idx++ ) {
            Integer val = items.get( idx );
            if( !indexMap.containsKey( val ) ) { indexMap.put( val, new ArrayList<>() ); }
            indexMap.get( val ).add( idx + 1 );
        }

        for( int idx=0; idx < items.size(); idx++ ) {
            Integer val = items.get( idx );
            Integer matching = credit - val;

            if( indexMap.containsKey( matching ) ) {
                int idx1 = indexMap.get( val ).get( 0 );
                int idx2 = indexMap.get( matching ).get( 0 );

                if( idx1 == idx2 ) {
                    List<Integer> vals = indexMap.get( val );
                    if( vals.size() == 1 ) { continue; } else { idx2 = vals.get( 1 ); }
                }

                if( idx1 < idx2 ) {
                    return new Pair<>( idx1, idx2 );
                } else {
                    return new Pair<>( idx2, idx2 );
                }
            }
        }

        throw new IllegalArgumentException( "The input had no solution" );
    }

}
