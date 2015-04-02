package hubberts.codejam;

import java.util.*;
import java.io.*;

/**
 * @author jhubberts Apr 01, 2015.
 */
public abstract class CodeJamTemplate implements Runnable {

    private static final String PROBLEMS_DIRECTORY = "/home/jhubberts/IdeaProjects/JavaWorkshop/problems/"

    public abstract void solveCase( Scanner sc, PrintWriter pw );

    public abstract String getProblemName();

    public void run() {
        try {
            for( File inputFile : getInputFiles() ) {
                try( Scanner sc = new Scanner( new FileReader( inputFile ) );
                     PrintWriter pw = new PrintWriter(new FileWriter(getWorkingDirectory() + getOutputFileName()))) {

                    int caseCnt = sc.nextInt();

                    for( int caseNum = 1; caseNum <= caseCnt; caseNum++ ) {
                        System.out.println("Processing test case " + (caseNum + 1));
                        pw.print(String.format("Case #%d: %s", caseNum));
                        this.solveCase(sc, pw);
                    }
                }
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public File[] getInputFiles() {
        File problemDirectory = new File( PROBLEMS_DIRECTORY + getProblemName() + "/" );

        if( !problemDirectory.exists() ) {
            throw new IllegalArgumentException( "No directory exists at " + problemDirectory.getAbsolutePath() );
        }

        if( !problemDirectory.isDirectory() ) {
            throw new IllegalArgumentException( problemDirectory.getAbsolutePath() + " does not represent a directory" );
        }

        File[] inputFiles = problemDirectory.listFiles( pathname -> pathname.getName().endsWith( ".in" ) );

        if( inputFiles.length == 0 ) {
            throw new IllegalArgumentException( "No *.in files were found in " + problemDirectory.getAbsolutePath() );
        }

        return inputFiles;
    }

    public String getProblemDirectory() {
        return ;
    }

}


