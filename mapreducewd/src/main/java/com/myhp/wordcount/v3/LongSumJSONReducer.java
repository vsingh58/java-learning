package com.myhp.wordcount.v3;
//package org.apache.hadoop.mapred.lib;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/** A {@link org.apache.hadoop.mapred.Reducer} that sums long values.
 *
 * It is a copy of lib class LongSumReducer
 */

public class LongSumJSONReducer  extends MapReduceBase
        implements Reducer<Text, LongWritable, NullWritable, Text> {

    public void reduce(Text key, Iterator<LongWritable> values,
                       OutputCollector<NullWritable, Text> output,
                       Reporter reporter)
            throws IOException {

        // sum all values for this key
        long sum = 0;
        while (values.hasNext()) {
            sum += values.next().get();
        }

        // output sum
        JSONObject json = new JSONObject();
        json.put(String.valueOf(key), sum);
        output.collect(NullWritable.get() , new Text(json.toString()));
    }

}
