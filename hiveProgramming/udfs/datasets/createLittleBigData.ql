CREATE TABLE IF NOT EXISTS littlebigdata (
name STRING,
email STRING,
bday STRING,
ip STRING,
gender STRING,
anum INT) 
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';

LOAD DATA local inpath '${env:HOME}/workspace/git/java-learning/hiveProgramming/udfs/datasets/littlebigdata.txt' into table littlebigdata;
