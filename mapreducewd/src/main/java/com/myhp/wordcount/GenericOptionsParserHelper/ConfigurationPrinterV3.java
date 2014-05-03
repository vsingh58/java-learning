package com.myhp.wordcount.GenericOptionsParserHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/**
 * Created on 5/2/14.
 */
public class ConfigurationPrinterV3 extends Configured implements Tool {
    private static final Log LOG = LogFactory.getLog(ConfigurationPrinterV3.class);

    private final static String mColorKey = "color";
    private final static String mSizeKey = "size";
    private final static String mWeightKey = "weight";

    @Override
    public int run(String[] args) throws Exception {
        // case 1
        JobClient client = new JobClient();
        JobConf conf = new JobConf(ConfigurationPrinterV3.class);
        client.setConf(conf);

        // case 2
//        JobConf conf = new JobConf(getConf(), getClass());
        LOG.info("run Configuration: (" + mColorKey + ", " + conf.get(mColorKey, "default value") + ")");
        LOG.info("run Configuration: (" + mSizeKey + ", " + conf.get(mSizeKey, "0*0") + ")");
        LOG.info("run Configuration: (" + mWeightKey + ", " + conf.getInt(mWeightKey, 0) + ")");

        // the runJob Will start a MR with default settings(Mapper and Reducer)
        // JobClient.runJob(conf);
        return 0;
    }

    public static void main(String[] args) {
        int rc = 0;
        try {
            rc = ToolRunner.run(new ConfigurationPrinterV3(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(rc);
    }
}

/*
* Add the "-D color=yellow -D size=1096*1043 -D weight=80" in commline as program argument.
* When case 1 used here
*        JobClient client = new JobClient();
        JobConf conf = new JobConf(ConfigurationPrinterV3.class);
*  output:
*  14/05/03 18:55:31 INFO GenericOptionsParserHelper.ConfigurationPrinterV3: run Configuration: (color, default value)
14/05/03 18:55:31 INFO GenericOptionsParserHelper.ConfigurationPrinterV3: run Configuration: (size, 0*0)
14/05/03 18:55:31 INFO GenericOptionsParserHelper.ConfigurationPrinterV3: run Configuration: (weight, 0)
*
* case 2
* JobConf conf = new JobConf(getConf(), getClass());
* output
* 14/05/03 18:59:54 INFO GenericOptionsParserHelper.ConfigurationPrinterV3: run Configuration: (color, yellow)
14/05/03 18:59:54 INFO GenericOptionsParserHelper.ConfigurationPrinterV3: run Configuration: (size, 1096*1043)
14/05/03 18:59:54 INFO GenericOptionsParserHelper.ConfigurationPrinterV3: run Configuration: (weight, 80)
 *
  * */