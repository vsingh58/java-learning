package org.apache.hadoop.examples.mrunit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 9/12/13
 * Time: 6:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class SMSCDRMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text status = new Text();
    private final static IntWritable addOne = new IntWritable(1);

    /**
     * Returns the SMS status code and its count
     */
    protected void map(LongWritable key, Text value, Context context)
            throws java.io.IOException, InterruptedException {
          System.out.println("mapper: " + key);
          System.out.println("Mapper: " + value);
        //655209;1;796764372490213;804422938115889;6 is the Sample record format
        String[] line = value.toString().split(";");
        // If record is of SMS CDR
        if (Integer.parseInt(line[1]) == 1) {
            status.set(line[4]);
            context.write(status, addOne);
        }
    }
}
