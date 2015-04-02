package hubberts.codejam.problems.practice;

import hubberts.codejam.CodeJamProblem;
import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * http://code.google.com/codejam/contest/635101/dashboard
 *
 * Figure out how many MKDIR commands are required to make all of the specified directories
 *
 * @author jhubberts Apr 01, 2015.
 */
public class FileFixItProblem extends CodeJamProblem {

    public static class Node extends HashMap<String,Node> {
        boolean exists;
        Map<String,Node> children;

        public Node( boolean exists ) {
            this.children = new HashMap<>();
            this.exists = exists;
        }
    }

    @Override
    public void solveCase(Scanner sc, PrintWriter pw) throws Exception {
        int existing = sc.nextInt();
        int notExisting = sc.nextInt();
        sc.nextLine();

        Node root = new Node( true );

        for( int i=0; i<existing; i++ ) { addPath( sc.nextLine(), true, root ); }
        for( int i=0; i<notExisting; i++ ) { addPath( sc.nextLine(), false, root ); }

        pw.write( String.valueOf( computeMkdirs( root ) ) );
    }

    private void addPath( String absolutePath, boolean exists, Node root ) {
        String[] relativePaths = StringUtils.split(absolutePath, "/");
        Node currentNode = root;
        for( String path : relativePaths ) {
            if( !currentNode.containsKey( path ) ) { currentNode.put( path, new Node( exists ) ); }
            currentNode = currentNode.get( path );
        }
    }

    private int computeMkdirs( Node node ) {
        int mkdirs = node.exists ? 0 : 1;
        for( Node child : node.values() ) { mkdirs += computeMkdirs( child ); }
        return mkdirs;
    }

}
