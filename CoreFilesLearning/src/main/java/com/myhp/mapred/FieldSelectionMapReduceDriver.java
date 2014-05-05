package com.myhp.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.FieldSelectionMapReduce;
import org.apache.hadoop.util.GenericOptionsParser;


import java.io.IOException;

//public class FieldSelectionMapReduceDriver extends Configured implements Tool {

public class FieldSelectionMapReduceDriver {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args)
                .getRemainingArgs();
        if (otherArgs.length != 4) {
            System.err
                    .println("Usage: FieldSelectionMapReduceDriver <input> <output>");
            System.exit(-1);
        }

        JobConf jobConf = new JobConf(conf, FieldSelectionMapReduceDriver.class);
        jobConf.setMapperClass(FieldSelectionMapReduce.class);
        jobConf.setReducerClass(FieldSelectionMapReduce.class);
        jobConf.setJobName("mapred: FieldSelectionMapReduce class demo");
        jobConf.setJarByClass(FieldSelectionMapReduceDriver.class);

        FileInputFormat.addInputPath(jobConf, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));
    }
}