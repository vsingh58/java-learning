package com.terminal.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/8/14
 * Time: 7:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordCountReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        int sum = 0;
        //for(IntWritable val : value)
        while (values.hasNext()) {
            int times = values.next().get();
            sum += times;
        }
        output.collect(key, new IntWritable(sum));
        System.out.print(" WordCountReducer  sys reducer Done");
    }
}
