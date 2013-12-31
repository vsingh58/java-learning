#!/bin/sh
hadoop fs -rmr  /user/zhishan/output

#submit the hadoop job
cd target && hadoop jar hadoop_examples-1.0-SNAPSHOT.jar  org.apache.hadoop.examples.WordCount /user/zhishan/input /user/zhishan/output
hadoop fs -lsr /user/zhishan/output
