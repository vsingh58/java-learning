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

public class WordCount extends Configured implements Tool {
    private static final Log LOG = LogFactory.getLog(WordCount.class);

    private String get(Configuration conf, String key){
        return conf.get(key);
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

        JobClient client = new JobClient();
        JobConf conf = new JobConf(WordCount.class);  // it do not work when property given as command line parameter.
        LOG.info("run Configuration: (" + mColorKey + ", " + conf.get(mColorKey, "default value") + ")"); // expect "default value"
        LOG.info("run Configuration: (" + mColorKey + ", " + get(conf, mColorKey) + ")"); // expect null
        LOG.info("run Configuration: (" + mSizeKey + ", " + conf.get(mSizeKey, "0*0") + ")"); // expect 0*0
        LOG.info("run Configuration: (" + mWeightKey + ", " + conf.getInt(mWeightKey, 0) + ")"); // expect 0

        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(LongWritable.class);
        conf.setMapperClass(TokenCountMapper.class);
        conf.setCombinerClass(LongSumReducer.class);
        conf.setReducerClass(LongSumReducer.class);

        client.setConf(conf);
        // even they are setting in here
        LOG.info("run Configuration: (" + mColorKey + ", " + conf.get(mColorKey, "default value") + ")"); // expect "default value"
        LOG.info("run Configuration: (" + mColorKey + ", " + get(conf, mColorKey) + ")"); // expect null
        LOG.info("run Configuration: (" + mSizeKey + ", " + conf.get(mSizeKey, "0*0") + ")"); // expect 0*0
        LOG.info("run Configuration: (" + mWeightKey + ", " + conf.getInt(mWeightKey, 0) + ")"); // expect 0


        try {
            //JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        WordCount wordCount = new WordCount();
        int rc=0;
        try {
            rc = ToolRunner.run(wordCount, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(rc);


    }


}
