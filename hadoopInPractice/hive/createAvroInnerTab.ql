CREATE TABLE weather
ROW FORMAT
SERDE 'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
STORED AS
INPUTFORMAT 'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
TBLPROPERTIES('avro.schema.url'='hdfs://localhost:9000/tmp/output2/schema/WeatherRecord.avsc');

--To populate the table, we use a LOAD DATA statement as before:
--LOAD DATA LOCAL INPATH "input/ncdc/metadata/stations-fixed-width.txt"
--LOAD DATA INPATH "hdfs:path"
--INTO TABLE stations;
