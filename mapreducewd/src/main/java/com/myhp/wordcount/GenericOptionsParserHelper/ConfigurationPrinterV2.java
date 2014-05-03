package com.myhp.wordcount.GenericOptionsParserHelper;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Map;
import java.util.Properties;

/**
 * Created on 5/2/14.
 */
public class ConfigurationPrinterV2 extends Configured implements Tool {
/*    static {
        Configuration.addDefaultResource("hdfs-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
        Configuration.addDefaultResource("mapred-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
    }*/
    // system property key: java.version, java.home, os.arch
    private final static String mJDKVersion = "java.version";
    private final static String mJDKHOME = "java.home";
    private final static String mOSARCH = "os.arch";
    private final static String mUserName = "user.name";
    @Override
    public int run(String[] args) throws Exception {
        //Configuration conf = getConf();
        JobConf conf = new JobConf(getConf(),getClass()); // if the "-D key=value" given in command line as program parameter, it will be passed to the JobConf object, who inherits them from Configuration.
        for(Map.Entry<String, String> entry: conf) {
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }
        // Map.Entry output contians:
        // hadoop.tmp.dir=/tmp/hadoop-${user.name}
        // The system setting "${user.name}" will be resolved when the get(key,) method is called.

        System.out.println("Conf:hadoop.tmp.dir,  expect =/tmp/hadoop-${user.name}, actual =" + conf.get("hadoop.tmp.dir", "key not exist"));
         // output:
         // Conf:hadoop.tmp.dir,  expect =/tmp/hadoop-${user.name}, actual =/tmp/hadoop-usergrid
        String jdkversion = System.getProperty(mJDKVersion);
        String jdkhome = System.getProperty(mJDKHOME);
        String osArch = System.getProperty(mOSARCH);
        String userName = System.getProperty(mUserName);

        System.out.println(jdkversion + "," + jdkhome + ", " + osArch + ", " + userName);

        return 0;
    }

    public static void main(String[] args) {
        int rc = 0;
        try {
            rc = ToolRunner.run(new ConfigurationPrinterV2(), args);
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
