package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * http://code.google.com/codejam/contest/351101/dashboard
 *
 * @author jhubberts Apr 01, 2015.
 */
public class ReverseWordsProblem extends CodeJamProblem {

    public static void main( String[] args ) {
        new ReverseWordsProblem().run();
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        List<String> words = Arrays.asList( sc.nextLine().split( " " ) );
        Collections.reverse( words );
        pw.write( StringUtils.join( words, " " ) );
    }
}
