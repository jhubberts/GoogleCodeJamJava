package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://code.google.com/codejam/contest/2974486/dashboard
 * 15:53 small dataset
 * 27:01 large dataset
 *
 * @author jhubberts Apr 06, 2015.
 */
public class CookieClickerAlphaProblem extends CodeJamProblem {

    public static void main(String[] args) {
        new CookieClickerAlphaProblem().run();
    }

    private final double INITIAL_RATE = 2.0d;

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        double farmCost = sc.nextDouble();
        double farmOutput = sc.nextDouble();
        double target = sc.nextDouble();

        pw.write( String.valueOf( getMinimumTime( farmCost, farmOutput, target ) ) );
    }

    double getMinimumTime( double farmCost, double farmOutput, double target ) {
        double rate = INITIAL_RATE;
        double time = 0;

        // Time it takes at current rate to hit the target
        double timeToTarget = time + ( target / rate );

        // Time it takes at current rate to buy a new farm
        double timeToFarm = time + ( farmCost / rate );

        // The best TOTAL time seen which allows the monetary target to be reached
        double bestTime = timeToTarget;

        // If it takes longer to buy a farm then to wait it out, it's not worth
        // buying a farm
        while( time + timeToFarm < bestTime ) {
            time += timeToFarm;
            rate += farmOutput;
            timeToTarget = ( target / rate );
            timeToFarm = ( farmCost / rate );
            bestTime = Math.min( time + timeToTarget, bestTime );
        }

        return bestTime;
    }
}
