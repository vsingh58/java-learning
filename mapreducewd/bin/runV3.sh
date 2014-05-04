#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
input=${1:-/data/in/20140501}
output=${2:-/data/out} # no assigned 
hadoop fs -rmr -skipTrash $output
echo "=========== Try again =============="

hadoop jar ../target/wordcount-2.0.jar  com.myhp.wordcount.v3.DistributeCacheGivenInCmdLine \
-libjars ../jars/json-20140107.jar \
-D mapred.reduce.tasks=2 \
-conf ../conf/configuration-oneshot.xml \
-files ../conf/cache.file#cachefile.symbolic \
-archives ../conf/a.archive \
${input} $output


#mvn clean package -Dmaven.test.skip=true
#mvn clean package -DskipTests
#mapred.output.dir
#mapred.input.dir

# -conf option output, not its default value any more
#(color, yellow)
#(color, yellow)
#(size, 1608*134)
#(weight, 80)

# other options ouptut
#-jt: localhost:9001
#-fs: hdfs://localhost:9000
#-libjars: file:/home/zhishan/workspace/git/java-learning/mapreducewd/jars/json-20140107.jar
#-files: file:/home/zhishan/workspace/git/java-learning/mapreducewd/conf/cache.file
#-archives: file:/home/zhishan/workspace/git/java-learning/mapreducewd/conf/a.archive
