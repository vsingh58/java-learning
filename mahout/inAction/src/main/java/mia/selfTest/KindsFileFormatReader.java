package mia.selfTest;

import com.google.common.base.Charsets;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by zhishan on 9/23/14.
 */
public class KindsFileFormatReader {
    private final BufferedReader reader;

    /**
     * Creates a  over a given file, assuming a UTF-8 encoding.
     *
     * @throws java.io.FileNotFoundException
     *           if the file does not exist
     * @throws java.io.IOException
     *           if the file cannot be read
     */
    public KindsFileFormatReader(File file, boolean skipFirstLine) throws IOException {
        this(file, Charsets.UTF_8, skipFirstLine);
    }

    /**
     * Creates a  over a given file, using the given encoding.
     *
     * @throws java.io.FileNotFoundException
     *           if the file does not exist
     * @throws IOException
     *           if the file cannot be read
     */
    public KindsFileFormatReader(File file, Charset encoding, boolean skipFirstLine) throws IOException {
        this(getFileInputStream(file), encoding, skipFirstLine);
    }
    public KindsFileFormatReader(InputStream is, Charset encoding, boolean skipFirstLine) throws IOException {
        reader = new BufferedReader(new InputStreamReader(is, encoding));
        if (skipFirstLine) {
            reader.readLine();
        }
    }

    static InputStream getFileInputStream(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        String name = file.getName();
        if (name.endsWith(".gz")) {
            return new GZIPInputStream(is);
        } else if (name.endsWith(".zip")) {
            return new ZipInputStream(is);
        } else {
            return is;
        }
    }

    /*
     readeline() method
    * @return     A String containing the contents of the line, not including
     *             any line-termination characters, or null if the end of the
     *             stream has been reached
     *             */
    public String next(){
        String line;
        try {
            line = reader.readLine();
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
        return line == null ? null : line;
    }

    public static void main(String []args) {
        int count = 0;
        try {
            String line;
//            KindsFileFormatReader reader = new KindsFileFormatReader(new File("src/main/java/mia/selfTest/selfText.csv"), false);
//            KindsFileFormatReader reader = new KindsFileFormatReader(new File("src/main/java/mia/selfTest/selfText.csv"), true);
//            KindsFileFormatReader reader = new KindsFileFormatReader(new File("src/main/java/mia/selfTest/selfText.zip"), true);
            // zip file will output nothing.
            KindsFileFormatReader reader = new KindsFileFormatReader(new File("src/main/java/mia/selfTest/selfText.gz"), true);
            while ((line = reader.next()) != null) {
                System.out.println("[" + count++ + "]" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
