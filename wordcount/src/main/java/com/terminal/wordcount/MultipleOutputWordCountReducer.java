package com.terminal.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.lib.MultipleOutputs;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/8/14
 * Time: 7:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class MultipleOutputWordCountReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
    private MultipleOutputs multipleOutputs;

    @Override
    public void configure(JobConf job) {
        multipleOutputs = new MultipleOutputs(job);
    }

    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        //int sum = 0;
        OutputCollector collector = multipleOutputs.getCollector("station", key.toString().replace("-", ""), reporter);

        //for(IntWritable val : value)
        while (values.hasNext()) {
         //   int times = values.next().get();
           // sum += times;
            collector.collect(key, values);

        }
        System.out.print(" WordCountReducer  sys reducer Done");
    }
}
