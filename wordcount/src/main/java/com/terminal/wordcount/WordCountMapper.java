package com.terminal.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.StringTokenizer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/8/14
 * Time: 7:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordCountMapper extends MapReduceBase implements Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private Logger logger = Logger.getLogger(WordCountMapper.class.getName());

    @Override
    public void map(Object key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String where = "WordCountMapper: ";
        logger.log(Level.FINE,  where+ " Level.FINE Called.");
        logger.log(Level.SEVERE,  where+ "Level.SERVER Called.");
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            word.set(itr.nextToken());
            output.collect(word, one);
        }
        logger.log(Level.FINE,  where+ "One Mapper Done.");
        logger.log(Level.SEVERE,  where+ "One Mapper Done.");
        System.out.print(where + " sys Mapper Done");
    }
}

