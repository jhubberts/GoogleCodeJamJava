package hubberts.codejam;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.io.*;

/**
 * Template for Google Code Jam problems which abstracts away a lot of the nonsense boilerplate required to
 * perform I/O in Java. Implementers will need to do all of their heavy lifting in the solveCase method, and
 * may specify the directory containing input files in the getProblemName method
 *
 * The easiest way to run this in an IDE is to have implementers define a main method that just calls run on an
 * instance of itself.
 *
 * @author jhubberts Apr 01, 2015.
 */
public abstract class CodeJamProblem implements Runnable {

    private static final String PROBLEMS_DIRECTORY = "/home/jhubberts/IdeaProjects/JavaWorkshop/problems/";

    /**
     * Abstract method which contains the entirety of the actual logic for this particular problem:
     * parsing the input for each case (via Scanner), performing some computations, and writing some
     * output to the provided PrintWriter.
     *
     * Implementers of this method do not need to write a newline to the printwriter unless the output
     * per case is multiple lines long.
     *
     * @param sc a scanner attached to the input file for a given problem
     * @param pw a printwriter printing to the output file for a given problem
     * @throws Exception
     */
    public abstract void solveCase( Scanner sc, PrintWriter pw ) throws Exception;

    /**
     * Returns the name of the directory containing the inputs for this problem. It is expected that this
     * directory A. exists, and B. is a sub-directory of PROBLEMS_DIRECTORY. Unless you explicitly override
     * this method, it will assume that your directory name is the spinal-cased version of your class name
     * minus the word "Problem". e.x. StoreCreditProblem -> store-credit
     *
     * @return the name of the directory containing the inputs for this problem
     */
    public String getProblemName() {
        String className = this.getClass().getSimpleName();

        // Trim off problem
        if( !className.endsWith( "Problem" ) ) {
            throw new IllegalArgumentException( "Class " + className + " does not follow the standard naming "
                    + "convention, you must override getProblem()." );
        }

        // Get each word of the camel cased title
        String[] words = StringUtils.splitByCharacterTypeCamelCase( className.substring( 0, className.length() - 7 ) );

        // Join the words together with dashes
        return StringUtils.join( words, "-" ).toLowerCase();
    }

    public void run() {
        try {
            for( File inFile : getInFiles() ) {
                File outFile = generateOutFile( inFile );
                System.out.println( String.format( "Reading input file %s, and outputting results to %s", inFile, outFile ) );

                try( Scanner sc = new Scanner( new FileReader( inFile ) );
                     PrintWriter pw = new PrintWriter( new FileWriter( outFile ) ) ) {

                    int caseCnt = sc.nextInt();

                    // If you don't do this, sc.nextLine() will return "" for the first line
                    sc.nextLine();

                    for( int caseNum = 1; caseNum <= caseCnt; caseNum++ ) {
                        System.out.println( "Processing test case " + caseNum );
                        pw.print( String.format( "Case #%d: ", caseNum ) );
                        this.solveCase( sc, pw );
                        pw.print( "\n" );
                    }
                }
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a list of input files from this problem's directory. The list will be ordered such that the
     * small file will always come before the large file
     *
     * @return a list of input files from this problem's directory
     */
    public List<File> getInFiles() {
        File problemDirectory = new File( PROBLEMS_DIRECTORY + getProblemName() + "/" );

        // Make sure that the file exists, and that the provided path represents a directory
        if( !problemDirectory.exists() ) {
            throw new IllegalArgumentException( "No directory exists at " + problemDirectory.getAbsolutePath() );
        }

        if( !problemDirectory.isDirectory() ) {
            throw new IllegalArgumentException( problemDirectory.getAbsolutePath() + " does not represent a directory" );
        }

        // Get the input files in the provided directory
        File[] inFiles = problemDirectory.listFiles( pathname -> pathname.getName().endsWith( ".in" ) );

        if( inFiles.length == 0 ) {
            throw new IllegalArgumentException( "No *.in files were found in " + problemDirectory.getAbsolutePath() );
        }

        // Convert the returned files to a list, and then sort that list so that the small file is run before
        // the large file
        List<File> inFilesList = Arrays.asList( inFiles );

        Collections.sort( inFilesList, (File x, File y) -> {
           if( x.getName().contains( "small" ) ) {
               return -1;
           } else if( y.getName().contains( "small" ) ) {
               return 1;
           } else {
               return 0;
           }
        } );

        return inFilesList;
    }

    /**
     * Returns a file pointing to a .out file whose prefix is identical to its corresponding
     * .in file
     *
     * @param inFile a google code jam input file
     * @return a google code jam output file corresponding to the provided input
     */
    public File generateOutFile( File inFile ) {
        String inFilePath = inFile.getAbsolutePath();

        if( !inFilePath.endsWith( ".in" ) ) {
            throw new IllegalArgumentException( "Can't generate an output file from input file " + inFilePath );
        }

        // Strip off the .in, and replace it with a .out
        return new File( inFilePath.substring( 0, inFilePath.length() - 3 ) + ".out" );
    }

}