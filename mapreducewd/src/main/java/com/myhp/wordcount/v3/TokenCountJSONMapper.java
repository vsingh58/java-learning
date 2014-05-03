package com.myhp.wordcount.v3;
//package org.apache.hadoop.mapred.lib;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.json.JSONObject;

import java.io.IOException;
import java.util.StringTokenizer;


/** A {@link org.apache.hadoop.mapred.Mapper} that maps text values into <token,freq> pairs.  Uses
 * {@link java.util.StringTokenizer} to break text into tokens.
 *
 * It is a copy of the lib class TokenCountMapper
 */
public class TokenCountJSONMapper<K> extends MapReduceBase
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
            JSONObject json = new JSONObject();
            json.put("word", st.nextToken());

            // output <token,1> pairs
            output.collect(new Text(json.toString()), new LongWritable(1));
        }
    }

}
