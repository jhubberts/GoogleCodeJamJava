package hubberts.codejam;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jhubberts Mar 31, 2015.
 */
public class AllYourBase {

    public static void main( String[] args ) throws Exception {
        File inFile = new File( args[0] );
        File outFile = new File( args[1] );

        BufferedReader br = new BufferedReader( new FileReader( inFile ) );
        PrintWriter writer = new PrintWriter( new FileWriter( outFile ) );

        int size = Integer.parseInt( br.readLine() );
        System.out.println( size );
        for( int i=1; i <= size; i++ ){
            int minSeconds = stringToMinSeconds( br.readLine() );
            writer.write( String.format( "Case #%d: %d\n", i, minSeconds ) );
        }

        writer.close();
    }

    public static int stringToMinSeconds( String str ) {
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

        int sum = 0;
        int pow = 1;

        for( int i = reversedDigits.size() - 1; i >= 0; i-- ) {
            sum += reversedDigits.get( i ) * pow;
            pow *= base;
        }

        return sum;
    }

}
