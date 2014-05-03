package com.myhp.wordcount.GenericOptionsParserHelper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map;

/**
 * Created on 5/2/14.
 */
public class ConfigurationPrinter extends Configured implements Tool {
/*    static {
        Configuration.addDefaultResource("hdfs-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
        Configuration.addDefaultResource("mapred-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
    }*/

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        for(Map.Entry<String, String> entry: conf) {
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }
        return 0;
    }

    public static void main(String[] args) {
        int rc = 0;
        try {
            rc = ToolRunner.run(new ConfigurationPrinter(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(rc);
    }

    @Override
    public Configuration getConf() {
        System.out.println("ConfigurationPrinter getConf()");
        return super.getConf();
    }
}
