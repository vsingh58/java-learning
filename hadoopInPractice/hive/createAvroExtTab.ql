CREATE DATABASE test3;

CREATE EXTERNAL TABLE test3.QDB_UCENTER_USER
ROW FORMAT
SERDE 'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
STORED AS
INPUTFORMAT 'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
LOCATION '/avro/usermodelData/ODS.QDB_UCENTER_USER'
TBLPROPERTIES('avro.schema.url'='hdfs://localhost:9000/avro/usermodelData/schema/ODS.QDB_UCENTER_USER.avsc');

-- LOAD hdfs data
--LOAD DATA INPATH '/avro/usermodelData/ODS.QDB_UCENTER_USER' INTO TABLE test3.QDB_UCENTER_USER;
 
--To populate the table, we use a LOAD DATA statement as before:
--LOAD DATA LOCAL INPATH "input/ncdc/metadata/stations-fixed-width.txt"
--LOAD DATA INPATH "hdfs:path"
--INTO TABLE stations;

--If the LOCATION, where the dataset is stored on the HDFS, is not specified when EXTERNAL table is created, those files given in its following statement "LOAD DATA INPATH" will be moved to the Hive's warehouse. That is to say, the EXTERNAL table acts as an MANAGE TABLE.
--
--The dataset to be loaded into EXTERNAL TABLE should be given in "LOCATION" statment.
