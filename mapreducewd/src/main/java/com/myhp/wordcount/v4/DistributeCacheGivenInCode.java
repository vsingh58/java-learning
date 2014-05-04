package com.myhp.wordcount.v4;

import com.myhp.wordcount.v3.LongSumJSONReducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.LongSumReducer;
import org.apache.hadoop.mapred.lib.TokenCountMapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.net.URI;

public class DistributeCacheGivenInCode extends Configured implements Tool {
    private static final Log LOG = LogFactory.getLog(DistributeCacheGivenInCode.class);

    private String get(Configuration conf, String key) {
        return conf.get(key);
    }

    private final static String mColorKey = "color";
    private final static String mSizeKey = "size";
    private final static String mWeightKey = "weight";

    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.printf("Usage: %s[generic options] <input> <output> <cache files>\n",
                    getClass().getSimpleName()
            );
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        JobConf conf = new JobConf(getConf(), DistributeCacheGivenInCode.class);
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(LongWritable.class);

        conf.setOutputKeyClass(NullWritable.class);
        conf.setOutputValueClass(Text.class);
        conf.setMapperClass(TokenCountMapper.class);
        conf.setCombinerClass(LongSumReducer.class);
        conf.setReducerClass(LongSumJSONReducer.class);

        // Add the user given files to the DistributedCache(by using -files on commandline)
        String localizedFile = conf.get("tmpfiles","not found");
        // a comma separated list of local files
        DistributedCache.addCacheFile(
                new URI(localizedFile + "#distributed.cachefile.symlink"),
                conf);
        DistributedCache.createSymlink(conf);


        try {
            JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DistributeCacheGivenInCode wordCount = new DistributeCacheGivenInCode();
        int rc = 0;
        try {
            rc = ToolRunner.run(wordCount, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(rc);


    }
}

/*
* run commandline in bin/runV3.sh

input=${1:-/data/in/20140501}
output=${2:-/data/out} # no assigned
hadoop fs -rmr -skipTrash $output
echo "=========== Try again =============="

hadoop jar ../target/wordcount-2.0.jar  com.myhp.wordcount.v3.WordCount \
-libjars ../jars/json-20140107.jar \
-D mapred.reduce.tasks=2 \
-conf ../conf/configuration-oneshot.xml \
-files ../conf/cache.file \
-archives ../conf/a.archive \
${input} $output

Reduce Task log:
2014-05-04 17:13:44,501 INFO org.apache.hadoop.util.NativeCodeLoader: Loaded the native-hadoop library
2014-05-04 17:13:45,049 INFO org.apache.hadoop.mapred.TaskRunner: Creating symlink: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/distcache/-7801810876208004212_221427785_1182961185/localhost/home/zhishan/HADOOP_DONTDELETE/mapred/staging/zhishan/.staging/job_201405041304_0009/archives/a.archive <- /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/attempt_201405041304_0009_r_000001_0/work/a.archive
2014-05-04 17:13:45,055 INFO org.apache.hadoop.mapred.TaskRunner: Creating symlink: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/distcache/5277546296166553574_-1641733485_1182961031/localhost/home/zhishan/HADOOP_DONTDELETE/mapred/staging/zhishan/.staging/job_201405041304_0009/files/cache.file <- /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/attempt_201405041304_0009_r_000001_0/work/cache.file
2014-05-04 17:13:45,062 INFO org.apache.hadoop.filecache.TrackerDistributedCacheManager: Creating symlink: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/jars/in <- /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/attempt_201405041304_0009_r_000001_0/work/in
2014-05-04 17:13:45,069 INFO org.apache.hadoop.filecache.TrackerDistributedCacheManager: Creating symlink: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/jars/META-INF <- /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/attempt_201405041304_0009_r_000001_0/work/META-INF
2014-05-04 17:13:45,074 INFO org.apache.hadoop.filecache.TrackerDistributedCacheManager: Creating symlink: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/jars/job.jar <- /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/attempt_201405041304_0009_r_000001_0/work/job.jar
2014-05-04 17:13:45,080 INFO org.apache.hadoop.filecache.TrackerDistributedCacheManager: Creating symlink: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/jars/com <- /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/attempt_201405041304_0009_r_000001_0/work/com
2014-05-04 17:13:45,090 INFO org.apache.hadoop.filecache.TrackerDistributedCacheManager: Creating symlink: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/jars/.job.jar.crc <- /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/jobcache/job_201405041304_0009/attempt_201405041304_0009_r_000001_0/work/.job.jar.crc
2014-05-04 17:13:45,427 WARN org.apache.hadoop.metrics2.impl.MetricsSystemImpl: Source name ugi already exists!

...

2014-05-04 17:13:52,023 INFO com.myhp.wordcount.v3.LongSumJSONReducer: Local Cache File:/home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/distcache/5277546296166553574_-1641733485_1182961031/localhost/home/zhishan/HADOOP_DONTDELETE/mapred/staging/zhishan/.staging/job_201405041304_0009/files/cache.file
2014-05-04 17:13:52,033 INFO com.myhp.wordcount.v3.LongSumJSONReducer: /home/zhishan/HADOOP_DONTDELETE/mapred/local/taskTracker/zhishan/distcache/5277546296166553574_-1641733485_1182961031/localhost/home/zhishan/HADOOP_DONTDELETE/mapred/staging/zhishan/.staging/job_201405041304_0009/files/cache.file]Distribute application-specific large, read-only files efficiently.
2014-05-04 17:13:52,033 INFO com.myhp.wordcount.v3.LongSumJSONReducer: DFS Cache File:hdfs://localhost:9000/home/zhishan/HADOOP_DONTDELETE/mapred/staging/zhishan/.staging/job_201405041304_0009/files/cache.file#cache.file
2014-05-04 17:13:52,033 INFO com.myhp.wordcount.v3.LongSumJSONReducer: whether symbolic created? :true

???? What does it intend to tell us???
1. "-files file1,file2,file3" given in commandline will be treated as "Distributed Cache file", Which has the same function/result as the Snippet Code:
        //String localizedFile = args[2];
        // a comma separated list of local files
      DistributedCache.addCacheFile(
                new URI(localizedFile + "#distributed.cachefile.symlink"),
                conf);
      DistributedCache.createSymlink(conf);

2. DistributedCache.getCacheFiles(job) and DistributedCache.getLocalCacheFiles(job) are different
former return hdfs:// file, but the other return a file with default local protocol. But both them could fetch the cache's content.
* */
