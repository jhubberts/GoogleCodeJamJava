package hubberts.codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * @author jhubberts Mar 31, 2015.
 */
public class StoreCredit {

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

    public static void main( String[] args ) throws Exception {

        try( BufferedReader br = new BufferedReader( new FileReader( args[0] ) );
             FileWriter writer = new FileWriter( args[1] ) ) {
            int numCases = Integer.parseInt(br.readLine());
            for( int caseNum = 1; caseNum <= numCases; caseNum++ ) {
                List<Integer> items = new ArrayList<>();
                int credit = Integer.parseInt( br.readLine() );
                br.readLine();
                for( String s : br.readLine().split(" ") ) { items.add( Integer.parseInt( s ) ); }
                Pair<Integer> indexes = getResult(credit, items);
                writer.write( String.format( "Case #%d: %d %d\n", caseNum, indexes.x, indexes.y ) );
            }
        }
    }

}
