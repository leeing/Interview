package textfilter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class PrintUtil {

    public  static void updateTextArea(final JTextArea textArea,final String text) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                textArea.append(text);
            }
        });
    }

    public  static void redirectSystemStreams(final JTextArea textArea) {
        OutputStream out = new OutputStream() {

            @Override
            public void write(int b) throws IOException {
                updateTextArea(textArea ,String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(textArea,new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }
}
