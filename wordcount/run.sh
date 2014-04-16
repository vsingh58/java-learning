#input=${1:=/user/zhishan/hadoop-book/ch02/input} run.sh: line 1: $1: cannot assign in this way
input=${1:-/wordcount/input/20140109}
output=${2:-/wordcount/output} # no assigned 
hadoop fs -rmr -skipTrash $output
#hadoop jar ./target/ch02-1.0-SNAPSHOT.jar com.myhp.ch02.MaxTemperatureMain $input $output 
hadoop jar ./target/WordCount-1.0.jar com.terminal.wordcount.WordCountMain  ${input} $output 
#mvn clean package -Dmaven.test.skip=true

#mapred.output.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/output
#mapred.input.dir	hdfs://localhost:9000/user/zhishan/hadoop-book/ch02/input
