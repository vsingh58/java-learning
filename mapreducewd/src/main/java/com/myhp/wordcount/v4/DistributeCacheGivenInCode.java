package com.myhp.wordcount.v4;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.filecache.TrackerDistributedCacheManager;
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

    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 3) {
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
        conf.setReducerClass(LongSumJSONReducerV4.class);

        // Configure the DistributedCache
        // DistributedFileSystem
        // args[2] can be cache.file or cache.file#cachefile.symbolic,
        // when it converted to the URI, by using URI.getFragment() method, the symbolic name can be got.
        // e.g ../conf/cache.file#cachefile.symbolic is given as the args[2] from commandline.
        DistributedCache.addCacheFile(new URI(args[2]), conf);
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


/**
 * Warning: {@link #CACHE_ARCHIVES} is not a *public* constant.
 **/
//public static final String CACHE_ARCHIVES = "mapred.cache.archives";

/**
 * Warning: {@link #CACHE_FILES} is not a *public* constant.
 **/
//public static final String CACHE_FILES = "mapred.cache.files";

    /**
     * Set the conf to contain the location for localized archives.  Used
     * by internal DistributedCache code.
     * @param conf The conf to modify to contain the localized caches
     * @param str a comma separated list of local archives
     */
//    public static void setLocalArchives(Configuration conf, String str) {
//        conf.set(CACHE_LOCALARCHIVES, str);
//    }

    /**
     * Set the conf to contain the location for localized files.  Used
     * by internal DistributedCache code.
     * @param conf The conf to modify to contain the localized caches
     * @param str a comma separated list of local files
     */
//    public static void setLocalFiles(Configuration conf, String str) {
//        conf.set(CACHE_LOCALFILES, str);
//    }

/*
* # Get ready of the cache files, only run once
#hadoop fs -put ../conf/cache.file /data/distCaches

#the symlink file could be given in the command line
distCacheFile="/data/distCaches/cache.file#cachefile.symbolic"
#distCacheFile="../conf/cache.file#cachefile.symbolic"
#local file will hit "java.io.FileNotFoundException: File does not exist", So the cache file should be uploaded before start the job.

hadoop jar ../target/wordcount-2.0.jar  com.myhp.wordcount.v4.DistributeCacheGivenInCode \
-libjars ../jars/json-20140107.jar \
-D mapred.reduce.tasks=2 \
-D keep.failed.task.files=true \
${input} $output $distCacheFile

Success!!!

1st, init the cache file
Get ready the cache file on hdfs. (hadoop fs -put localfile hdfsPath)

2nd, Adding the File to the DistributeCache
// Add a file to the cache. It must already exist on HDFS. The text
// after the hash is the link name.
DistributedCache.addCacheFile(
    new URI("hdfs://localhost:9000/foo/bar/baz.txt#baz.txt"), conf);
    // Create symlinks in the job's working directory using the link name
// provided below
DistributedCache.createSymlink(conf);

3rd, Access the file
Now that we’ve cached our file, let’s access it:
// Direct access by name
File baz = new File("baz.txt");
// prints "true" since the file was found in the working directory
System.out.println(baz.exists());


// We can also get a list of all cached files
Path[] cached = DistributedCache.getLocalCacheFiles(conf);
for (int i = 0; i < cached.length; i++) {
    Path path = cached[i];
    String filename = path.toString();
}

* */