--delete an table
DROP TABLE IF EXISTS employees_external;

-- create table;
CREATE EXTERNAL TABLE IF NOT EXISTS employees_external (
name STRING COMMENT 'Employee name',
salary FLOAT COMMENT 'Employee salary',
subordinates ARRAY<STRING> COMMENT 'NAME of subordinates',
deductions MAP<STRING, FLOAT> COMMENT 'Keys are deductions names, values are percentages',
address STRUCT<street:STRING, city:STRING, state:STRING, zip:INT> COMMENT 'Home Address'
) COMMENT 'Description of the table'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\001'
COLLECTION ITEMS TERMINATED BY '\002'
MAP KEYS TERMINATED BY '\003'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/data/external/employees'
TBLPROPERTIES ('creator' = 'zhishan', 'created_at' = '2014-07-09');
--TBLPROPERTIES:  should be as the last condition when table is craeted, otherwise, the error "ql.Driver: FAILED: ParseException line 10:0 missing EOF at 'ROW' near ')" hits. 

-- select
select name,  deductions['Insurance'], deductions['Federal Taxes'],address.street, address.zip,address.city from employees_external;
