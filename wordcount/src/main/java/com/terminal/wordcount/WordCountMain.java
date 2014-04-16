package com.terminal.wordcount;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/8/14
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordCountMain {

    public static void main(String [] args) throws Exception {
        if ( args.length != 2 ) {
            System.err.println("Usage: wordcount <in> <out>");
            //System.exit(-1);  oozie don't not need it.
            throw  new Exception("Usage: WordCountMain <int> <out>");
        }

        JobConf conf = new JobConf(WordCountMain.class);
        conf.setJobName("Word Count Test Job");

        FileInputFormat.addInputPath(conf,new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        conf.setMapperClass(WordCountMapper.class);
        // section 2 start
        conf.setNumReduceTasks(0);
        conf.setOutputFormat(WordNameMultipleTextOutputFormat.class);
        // section 2 end

// section 1 start
//        conf.setReducerClass(WordCountReducer.class);
//        conf.setOutputKeyClass(Text.class);
//        conf.setOutputValueClass(IntWritable.class);
        // section 1 end

        JobClient.runJob(conf);
        System.out.print(" Main  sys reducer Done");
    }
}
