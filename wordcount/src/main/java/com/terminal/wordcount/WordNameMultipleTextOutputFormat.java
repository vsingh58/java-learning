package com.terminal.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;

/**
 * Created on 4/16/14.
 */
public class WordNameMultipleTextOutputFormat
        extends MultipleTextOutputFormat<Text, IntWritable> {
    @Override
    protected String generateFileNameForKeyValue(Text key, IntWritable value, String name) {
        //return super.generateFileNameForKeyValue(key, value, name);
        return key.toString();
    }
}
