#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
input=${1:-/data/in/20140501}
output=${2:-/data/out} # no assigned 
hadoop fs -rmr -skipTrash $output
#hadoop jar ./target/wordcount-2.0.jar  com.myhp.wordcount.v1.WordCount ${input} $output
hadoop jar ../target/wordcount-2.0.jar  com.myhp.wordcount.v2.WordCountV2 \
-conf ../conf/configuration-oneshot.xml \
${input} $output

echo "=========== Try again =============="
hadoop jar ../target/wordcount-2.0.jar  com.myhp.wordcount.v2.WordCount \
-conf ../conf/configuration-oneshot.xml \
-D color=red \
${input} $output

#export HADOOP_CLASSPATH=/home/hadoop/zhishan/.; hadoop jar ./target/mobile_probe_data_convert-1.0.jar com.lakala.mobile_data.URLDecoderDriver ${input}/$file $output  -libjars json-20140107.jar 

#mvn clean package -Dmaven.test.skip=true
#mvn clean package -DskipTests

#mapred.output.dir
#mapred.input.dir

#cat <<_EOF
#0> sh runV2.sh 
#Deleted hdfs://localhost:9000/data/out
#Usage: WordCount[generic options] <input> <output>
#Generic options supported are
#-conf <configuration file>     specify an application configuration file
#-D <property=value>            use value for given property
#-fs <local|namenode:port>      specify a namenode
#-jt <local|jobtracker:port>    specify a job tracker
#-files <comma separated list of files>    specify comma separated files to be copied to the map reduce cluster
#-libjars <comma separated list of jars>    specify comma separated jar files to include in the classpath.
#-archives <comma separated list of archives>    specify comma separated archives to be unarchived on the compute machines.
#
#The general command line syntax is
#bin/hadoop command [genericOptions] [commandOptions]
#*/
#_EOF
