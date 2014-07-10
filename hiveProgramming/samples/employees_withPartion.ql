--delete an table
DROP TABLE IF EXISTS employees_with_partition;

-- create table;
CREATE TABLE IF NOT EXISTS employees_with_partition (
name STRING COMMENT 'Employee name',
salary FLOAT COMMENT 'Employee salary',
subordinates ARRAY<STRING> COMMENT 'NAME of subordinates',
deductions MAP<STRING, FLOAT> COMMENT 'Keys are deductions names, values are percentages',
address STRUCT<street:STRING, city:STRING, state:STRING, zip:INT> COMMENT 'Home Address'
) 
COMMENT 'Description of the table'
PARTITIONED BY (country STRING, state STRING) 
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\001'
COLLECTION ITEMS TERMINATED BY '\002'
MAP KEYS TERMINATED BY '\003'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
TBLPROPERTIES ('creator' = 'zhishan', 'created_at' = '2014-07-09'); 
--TBLPROPERTIES:  should be as the last condition when table is craeted, otherwise, the error "ql.Driver: FAILED: ParseException line 10:0 missing EOF at 'ROW' near ')" hits. 
-- LOCATION 'hdfs://'

-- load partitioned_data: Chicago
LOAD DATA local INPATH '${env:HOME}/workspace/git/java-learning/hiveProgramming/samples/partitioned_data/Chicago' 
INTO TABLE employees_with_partition
PARTITION (country = 'US', state = 'Chicago');
-- load partitioned_data: OakPark 
LOAD DATA local INPATH '${env:HOME}/workspace/git/java-learning/hiveProgramming/samples/partitioned_data/OakPark' 
INTO TABLE employees_with_partition
PARTITION (country = 'US', state = 'OakPark');

-- load partitioned_data: Obscuria 
LOAD DATA local INPATH '${env:HOME}/workspace/git/java-learning/hiveProgramming/samples/partitioned_data/Obscuria' 
INTO TABLE employees_with_partition
PARTITION (country = 'CA', state = 'Obscuria');


-- select
--select name,  deductions['Insurance'], deductions['Federal Taxes'],address.street, address.zip,address.city from employees_with_partition;
