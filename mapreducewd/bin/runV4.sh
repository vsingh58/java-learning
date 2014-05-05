#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
input=${1:-/data/in/20140501}
output=${2:-/data/out} # no assigned 
hadoop fs -rmr -skipTrash $output
echo "=========== Try again =============="

# Get ready of the cache files, only run once
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


#mvn clean package -Dmaven.test.skip=true
#mvn clean package -DskipTests
#mapred.output.dir
#mapred.input.dir
