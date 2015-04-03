package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * http://code.google.com/codejam/contest/635101/dashboard
 *
 * @author jhubberts Apr 01, 2015.
 */
public class PickingUpChicksProblem extends CodeJamProblem {

    public static void main( String[] args ) {
        new PickingUpChicksProblem().run();
    }

    private class Chicken {
        public final int pos;
        public final int vel;
        public final boolean canFinish;

        public Chicken( int pos, int vel, int barnDistance, int finishTime ) {
            this.pos = pos;
            this.vel = vel;
            this.canFinish = ( barnDistance - pos ) / ( ( double ) finishTime ) <= vel;
        }
    }

    private List<Chicken> chickens;

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        int chickenCount = sc.nextInt();
        int minFinishers = sc.nextInt();
        int barnDistance = sc.nextInt();
        int finishTime = sc.nextInt();

        int[] initialPositions = new int[chickenCount];
        int[] velocities = new int[chickenCount];
        this.chickens = new ArrayList<>();

        for( int i=0; i<chickenCount; i++ ) { initialPositions[i] = sc.nextInt(); }
        for( int i=0; i<chickenCount; i++ ) { velocities[i] = sc.nextInt(); }
        for( int i=0; i<chickenCount; i++ ) { this.chickens.add( new Chicken( initialPositions[i], velocities[i], barnDistance, finishTime ) ); }

        Collections.sort( this.chickens, (Chicken c1, Chicken c2) -> Integer.compare( c2.pos, c1.pos ) );

        int finished = 0;
        int idx = 0;
        int moves = 0;
        int chickenToPass = 0;

        while( finished < minFinishers && idx < chickens.size() ) {
            if( chickens.get( idx++ ).canFinish ) {
                moves += chickenToPass;
                finished += 1;
            } else {
                chickenToPass += 1;
            }
        }

        pw.write( finished == minFinishers ? String.valueOf( moves ) : "IMPOSSIBLE" );
    }
}
