package AdvancedMapReduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.LineRecordReader;
import org.apache.hadoop.mapred.RecordReader;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/8/14
 * Time: 5:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjPosRecordReader implements RecordReader<Text, Point3D> {

    private LineRecordReader lineReader;
    private LongWritable lineKey;
    private Text lineValue;

    public ObjPosRecordReader(JobConf job, FileSplit split) throws IOException {
        lineReader = new LineRecordReader(job, split);

        lineKey = lineReader.createKey();
        lineValue = lineReader.createValue();
    }

    public boolean next(Text key, Point3D value) throws IOException {
        // get the next line
        if (!lineReader.next(lineKey, lineValue)) {
            return false;
        }

        // parse the lineValue which is in the format:
        // objName, x, y, z
        String[] pieces = lineValue.toString().split(",");
        if (pieces.length != 4) {
            throw new IOException("Invalid record received");
        }

        // try to parse floating point components of value
        float fx, fy, fz;
        try {
            fx = Float.parseFloat(pieces[1].trim());
            fy = Float.parseFloat(pieces[2].trim());
            fz = Float.parseFloat(pieces[3].trim());
        } catch (NumberFormatException nfe) {
            throw new IOException("Error parsing floating point value in record");
        }

        // now that we know we'll succeed, overwrite the output objects

        key.set(pieces[0].trim()); // objName is the output key.

        value.x = fx;
        value.y = fy;
        value.z = fz;

        return true;
    }

    public Text createKey() {
        return new Text("");
    }

    public Point3D createValue() {
        return new Point3D();

    }

    public long getPos() throws IOException {
        return lineReader.getPos();
    }

    public void close() throws IOException {
        lineReader.close();
    }

    public float getProgress() throws IOException {
        return lineReader.getProgress();
    }
}
