package peersim.config;

import java.io.*;

/**
 * A subclass of PrintStream whose methods ignore the content 
 * being written. 
 *
 * PrintStream的子类
 *
 * @author Alberto Montresor
 * @version $Revision: 1.1 $
 */
public class NullPrintStream extends PrintStream {

    /**
     * Creates a null print stream that does not print anything.
     */
    public NullPrintStream() {
        super(System.out);
    }

    /**
     * This methods does not print anything.
     */
    public synchronized void write(byte[] b, int off, int len) {
    }

    /**
     * This methods does not print anything.
     */
    public synchronized void write(int b) {
    }

    /**
     * This methods does not print anything.
     */
    private void printLine() {
    }
}
