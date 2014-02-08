package AdvancedMapReduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/8/14
 * Time: 5:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectPositionInputFormat extends FileInputFormat<Text, Point3D> {
    public RecordReader<Text, Point3D> getRecordReader(
            InputSplit input, JobConf job, Reporter reporter)
            throws IOException {

        reporter.setStatus(input.toString());
        return new ObjPosRecordReader(job, (FileSplit)input);
    }
}
