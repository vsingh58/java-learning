CREATE EXTERNAL TABLE logs_20120101 (
host STRING,
identity STRING,
user STRING,
time STRING,
request STRING,
status STRING,
size STRING )
ROW FORMAT SERDE 'org.apache.hadoop.hive.contrib.serde2.RegexSerDe'
With SERDEPROPERTIES (
"input.regex" = 
"([^ ]*) ([^ ]*) ([^ ]*) (-|\\[[^\\]]*\\]) ([^ \"]*|\"[^\"]*\") (-|[0-9]*) (-|[0-9]*)",
"output.format.string"="%1$s %2$s %3$s %4$s %5$s %6$s %7$s"
)
STORED AS TEXTFILE LoCATion '/data/logs/20120101/';

--you can embed lines of comments that start with the string -- 
--data demo
--74.125.113.104 - - [23/Jun/2009:10:39:11 +0300] "GET /movie/TheFighter HTTP/1.1" 200 766
