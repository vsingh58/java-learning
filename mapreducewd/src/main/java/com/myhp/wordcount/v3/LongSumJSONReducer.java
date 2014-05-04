package com.myhp.wordcount.v3;
//package org.apache.hadoop.mapred.lib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

/**
 * A {@link org.apache.hadoop.mapred.Reducer} that sums long values.
 * <p/>
 * It is a copy of lib class LongSumReducer
 */

public class LongSumJSONReducer extends MapReduceBase
        implements Reducer<Text, LongWritable, NullWritable, Text> {

    private static final Log LOG = LogFactory.getLog(LongSumJSONReducer.class);

    // initilize method
    @Override
    public void configure(JobConf job) {
        try {
            Path[] files = DistributedCache.getLocalCacheFiles(job);
            for (Path path : files) {
                LOG.info("Local Cache File:" + path.toString());
            }

            if (files == null || files.length == 0) {
                throw new RuntimeException(
                        "Fail to Try DistributedCache");
            }

            // Read all files in the DistributedCache
            for (Path p : files) {
                BufferedReader rdr = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(
                                        new File(p.toString()))));
                String line;
                while ((line = rdr.readLine()) != null) {
                    LOG.info(p + "]" + line);
                }
            }

            URI[] file1s = DistributedCache.getCacheFiles(job);
            for (URI uri : file1s) {
                LOG.info("DFS Cache File:" + uri.toString());
            }
            // symlinks are to be create for the localized cache files in the current working directory by default.
            LOG.info("whether symbolic created? :" + DistributedCache.getSymlink(job));

            // read a file
            LOG.info("=============== Reading File: \"-files ../conf/cache.file#cachefile.symbolic\"==============");
            //cachedFileReader(new File("files/cache.file")); //error: file not found
            //cachedFileReader(new File("files/cache.file")); //error: file not found
            /*java.io.FileNotFoundException: files/cache.file (No such file or directory*/
            LOG.info("=============== Reading Symbolic File With Relative Path ==============");
            cachedFileReader(new File("cachefile.symbolic"));

            LOG.info("conf.getLocalDirs: " + job.getLocalDirs());
            //LOG.info("conf.getLocalPath: " + job.getLocalPath("cache.file")); // it could not return what you want.
            LOG.info("conf.getJar: " + job.getJar());
            LOG.info("job.getJobLocalDir(): " + job.getJobLocalDir());
            LOG.info("conf.getJobEndNotificationURI: " + job.getJobEndNotificationURI());
            LOG.info("conf.getKeepFailedTaskFiles: " + job.getKeepFailedTaskFiles());

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("***Error: Exit with failure initialization the LongSumJSONReducer");
            System.exit(-1);
        }


    }

    private static void cachedFileReader(File file) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = in.readLine()) != null) {
                LOG.info("cacheFileReader: " + line);
            }
        } finally {
            IOUtils.closeStream(in);
        }

    }

    public void reduce(Text key, Iterator<LongWritable> values,
                       OutputCollector<NullWritable, Text> output,
                       Reporter reporter)
            throws IOException {

        // sum all values for this key
        long sum = 0;
        while (values.hasNext()) {
            sum += values.next().get();
        }

        // output sum
        JSONObject json = new JSONObject();
        json.put(String.valueOf(key), sum);
        output.collect(NullWritable.get(), new Text(json.toString()));
    }
}

    /**
     * Set whether the framework should keep the intermediate files for
     * failed tasks.
     *
     * @param keep <code>true</code> if framework should keep the intermediate files
     *             for failed tasks, <code>false</code> otherwise.
     *
     */
//    public void setKeepFailedTaskFiles(boolean keep) {
//        setBoolean("keep.failed.task.files", keep);
//    }

    /**
     * Constructs a local file name. Files are distributed among configured
     * local directories.
     */
//    public Path getLocalPath(String pathString) throws IOException {
//        return getLocalPath(MAPRED_LOCAL_DIR_PROPERTY, pathString);
//    }

/**
 * Property name for the configuration property mapred.local.dir
 */
//public static final String MAPRED_LOCAL_DIR_PROPERTY = "mapred.local.dir";

/*
* 3. mapred.max.tracker.failures, default value 4
* # of failed Reduce Tasks exceeded allowed limit. FailedCount: 1. LastFailedTask
* if there are 2 reduce task num setting, 2*4 tasks will be run.
* */