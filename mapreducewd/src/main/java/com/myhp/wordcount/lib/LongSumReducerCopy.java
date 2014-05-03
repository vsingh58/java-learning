package com.myhp.wordcount.lib;
//package org.apache.hadoop.mapred.lib;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/** A {@link Reducer} that sums long values.
 *
 * It is a copy of lib class LongSumReducer
 */

public class LongSumReducerCopy<K> extends MapReduceBase
        implements Reducer<K, LongWritable, K, LongWritable> {

    public void reduce(K key, Iterator<LongWritable> values,
                       OutputCollector<K, LongWritable> output,
                       Reporter reporter)
            throws IOException {

        // sum all values for this key
        long sum = 0;
        while (values.hasNext()) {
            sum += values.next().get();
        }

        // output sum
        output.collect(key, new LongWritable(sum));
    }

}
