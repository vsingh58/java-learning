package com.myhp.wordcount.v2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
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

public class WordCountV2 extends Configured implements Tool {
    private static final Log LOG = LogFactory.getLog(WordCountV2.class);

    private String get(Configuration conf, String key){
        return conf.get(key,"User Defined Value");
    }
    private final static String mColorKey = "color";
    private final static String mSizeKey = "size";
    private final static String mWeightKey = "weight";

    @Override
    public int run(String[] args) throws Exception {
        if( args.length != 2 ){
            System.err.printf("Usage: %s[generic options] <input> <output>\n",
                    getClass().getSimpleName()
            );
            ToolRunner.printGenericCommandUsage(System.err);
            return  -1;
        }

        JobConf conf = new JobConf(getConf(), getClass());

        LOG.info("run Configuration: (" + mColorKey + ", " + conf.get(mColorKey, "default value") + ")");
        LOG.info("run Configuration: (" + mSizeKey + ", " + conf.get(mSizeKey, "0*0") + ")");
        LOG.info("run Configuration: (" + mWeightKey + ", " + conf.getInt(mWeightKey, 0) + ")");

        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(LongWritable.class);
        conf.setMapperClass(TokenCountMapper.class);
        conf.setCombinerClass(LongSumReducer.class);
        conf.setReducerClass(LongSumReducer.class);

        try {
            //JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        WordCountV2 wordCount = new WordCountV2();
        int rc=0;
        try {
            rc = ToolRunner.run(wordCount, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(rc);


    }


}
