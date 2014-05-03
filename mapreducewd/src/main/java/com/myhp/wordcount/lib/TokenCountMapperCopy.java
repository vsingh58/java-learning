package com.myhp.wordcount.lib;
//package org.apache.hadoop.mapred.lib;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.StringTokenizer;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


/** A {@link org.apache.hadoop.mapred.Mapper} that maps text values into <token,freq> pairs.  Uses
 * {@link java.util.StringTokenizer} to break text into tokens.
 *
 * It is a copy of the lib class TokenCountMapper
 */
public class TokenCountMapperCopy<K> extends MapReduceBase
        implements Mapper<K, Text, Text, LongWritable> {

    public void map(K key, Text value,
                    OutputCollector<Text, LongWritable> output,
                    Reporter reporter)
            throws IOException {
        // get input text
        String text = value.toString();       // value is line of text

        // tokenize the value
        StringTokenizer st = new StringTokenizer(text);
        while (st.hasMoreTokens()) {
            // output <token,1> pairs
            output.collect(new Text(st.nextToken()), new LongWritable(1));
        }
    }

}
