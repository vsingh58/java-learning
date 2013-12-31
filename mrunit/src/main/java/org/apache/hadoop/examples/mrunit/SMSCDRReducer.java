package org.apache.hadoop.examples.mrunit;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 9/12/13
 * Time: 6:22 AM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SMSCDRReducer extends
        Reducer<Text, IntWritable, Text, IntWritable> {

    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws java.io.IOException, InterruptedException {
        System.out.println("Reducer: " + key);
        int sum = 0;
        for (IntWritable value : values) {
            System.out.println("Mapper: " + value);
            sum += value.get();
        }
        context.write(key, new IntWritable(sum));
    }
}