package com.myhp.wordcount.v1;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.LongSumReducer;
import org.apache.hadoop.mapred.lib.TokenCountMapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
*  Configuration VS JobConf
*  new JobConf(getConf(), getclass()), inherit from the Configuration, and have the JobConf property too.
* */
public class WordCountV1 extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        JobConf conf = new JobConf(getConf(), getClass());

        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(LongWritable.class);
        conf.setMapperClass(TokenCountMapper.class);
        conf.setCombinerClass(LongSumReducer.class);
        conf.setReducerClass(LongSumReducer.class);

        JobClient.runJob(conf);

        return 0;
    }


    public static void main(String[] args) {
        int rc = 0;
        try {
            rc = ToolRunner.run(new WordCountV1(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(rc);

    }
}
