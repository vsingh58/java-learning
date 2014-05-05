package com.myhp.wordcount.v4;
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

/**
 * A {@link org.apache.hadoop.mapred.Reducer} that sums long values.
 * <p/>
 * It is a copy of lib class LongSumReducer
 */

public class LongSumJSONReducerV4 extends MapReduceBase
        implements Reducer<Text, LongWritable, NullWritable, Text> {

    private static final Log LOG = LogFactory.getLog(LongSumJSONReducerV4.class);

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
            // symlinks are to be create for the localized cache files in the current working directory by default.
            LOG.info("whether symbolic created? :" + DistributedCache.getSymlink(job));

            // e.g ../conf/cache.file#cachefile.symbolic is given as the args[2] from commandline.
            cachedFileReader(new File("cachefile.symbolic"));


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
