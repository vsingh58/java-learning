-- Hadoop Definitive Guide Edition1, p439
--CREATE TABLE ...
--ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.columnar.ColumnarSerDe'
--STORED AS RCFILE;

CREATE EXTERNAL TABLE QDB_UCENTER_USER_rcfile (id string, create_by string, create_time bigint, mobile_num string,load_date bigint, birthday bigint)
ROW FORMAT
SERDE 'org.apache.hadoop.hive.serde2.columnar.ColumnarSerDe'
STORED AS RCFILE;
-- not work in here. TBLPROPERTIES('avro.schema.url'='hdfs://localhost:9000/avro/usermodelData/schema/ODS.QDB_UCENTER_USER.avsc');

--AS RCFILE has given INPUTFORMAT and OUTPUTFORMAT value.
--INPUTFORMAT 'org.apache.hadoop.hive.ql.io.RCFileInputFormat'
--OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.RCFileOutputFormat';

FROM QDB_UCENTER_USER a -- not work here,TBLPROPERTIES('avro.schema.url'='hdfs://localhost:9000/avro/usermodelData/schema/ODS.QDB_UCENTER_USER.avsc'), the WARN Still not fix.
INSERT OVERWRITE TABLE QDB_UCENTER_USER_rcfile SELECT a.id, a.create_by, a.create_time, a.mobile_num, a.load_date, a.birthday;

--LOAD hdfs data, until now i couldn't find out how to load a file into RCFile table.
--LOAD DATA INPATH '/avro/usermodelData/ODS.QDB_UCENTER_USER' INTO TABLE QDB_UCENTER_USER;

-- when TBLPROPERTIES('avro.schema.url'='hdfs://localhost:9000/avro/usermodelData/schema/ODS.QDB_UCENTER_USER.avsc'); is not specified in above CREATE statment.
--14/06/05 11:27:08 WARN avro.AvroSerdeUtils: Encountered AvroSerdeException determining schema. Returning signal schema to indicate problem
--org.apache.hadoop.hive.serde2.avro.AvroSerdeException: Neither avro.schema.literal nor avro.schema.url specified, can't determine table schema
--	at org.apache.hadoop.hive.serde2.avro.AvroSerdeUtils.determineSchemaOrThrowException(AvroSerdeUtils.java:66)
--	at org.apache.hadoop.hive.serde2.avro.AvroSerdeUtils.determineSchemaOrReturnErrorSchema(AvroSerdeUtils.java:87)
--	at org.apache.hadoop.hive.serde2.avro.AvroSerDe.initialize(AvroSerDe.java:60)
--	at org.apache.hadoop.hive.metastore.MetaStoreUtils.getDeserializer(MetaStoreUtils.java:254)
--	at org.apache.hadoop.hive.ql.metadata.Partition.getDeserializer(Partition.java:252)
--	at org.apache.hadoop.hive.ql.metadata.Partition.initialize(Partition.java:218)
--	at org.apache.hadoop.hive.ql.metadata.Partition.<init>(Partition.java:103)
--	at org.apache.hadoop.hive.ql.metadata.Hive.getAllPartitionsForPruner(Hive.java:1733)
--	at org.apache.hadoop.hive.ql.optimizer.ppr.PartitionPruner.getPartitionsFromServer(PartitionPruner.java:247)
--	at org.apache.hadoop.hive.ql.optimizer.ppr.PartitionPruner.prune(PartitionPruner.java:175)
--	at org.apache.hadoop.hive.ql.optimizer.ppr.PartitionPruner.prune(PartitionPruner.java:135)
--	at org.apache.hadoop.hive.ql.optimizer.GenMapRedUtils.setTaskPlan(GenMapRedUtils.java:459)
--	at org.apache.hadoop.hive.ql.optimizer.GenMapRedUtils.setTaskPlan(GenMapRedUtils.java:398)
--	at org.apache.hadoop.hive.ql.optimizer.GenMRFileSink1.processFS(GenMRFileSink1.java:715)
--	at org.apache.hadoop.hive.ql.optimizer.GenMRFileSink1.process(GenMRFileSink1.java:160)
--	at org.apache.hadoop.hive.ql.lib.DefaultRuleDispatcher.dispatch(DefaultRuleDispatcher.java:90)
--	at org.apache.hadoop.hive.ql.lib.DefaultGraphWalker.dispatchAndReturn(DefaultGraphWalker.java:94)
--	at org.apache.hadoop.hive.ql.parse.GenMapRedWalker.walk(GenMapRedWalker.java:54)
--	at org.apache.hadoop.hive.ql.parse.GenMapRedWalker.walk(GenMapRedWalker.java:65)
--	at org.apache.hadoop.hive.ql.parse.GenMapRedWalker.walk(GenMapRedWalker.java:65)
--	at org.apache.hadoop.hive.ql.lib.DefaultGraphWalker.startWalking(DefaultGraphWalker.java:109)
--	at org.apache.hadoop.hive.ql.parse.MapReduceCompiler.compile(MapReduceCompiler.java:267)
--	at org.apache.hadoop.hive.ql.parse.SemanticAnalyzer.analyzeInternal(SemanticAnalyzer.java:8410)
--	at org.apache.hadoop.hive.ql.parse.BaseSemanticAnalyzer.analyze(BaseSemanticAnalyzer.java:284)
--	at org.apache.hadoop.hive.ql.Driver.compile(Driver.java:441)
--	at org.apache.hadoop.hive.ql.Driver.compile(Driver.java:342)
--	at org.apache.hadoop.hive.ql.Driver.runInternal(Driver.java:977)
--	at org.apache.hadoop.hive.ql.Driver.run(Driver.java:888)
--	at org.apache.hadoop.hive.cli.CliDriver.processLocalCmd(CliDriver.java:259)
--	at org.apache.hadoop.hive.cli.CliDriver.processCmd(CliDriver.java:216)
--	at org.apache.hadoop.hive.cli.CliDriver.processLine(CliDriver.java:413)
--	at org.apache.hadoop.hive.cli.CliDriver.processLine(CliDriver.java:348)
--	at org.apache.hadoop.hive.cli.CliDriver.processReader(CliDriver.java:446)
--	at org.apache.hadoop.hive.cli.CliDriver.processFile(CliDriver.java:456)
--	at org.apache.hadoop.hive.cli.CliDriver.executeDriver(CliDriver.java:737)
--	at org.apache.hadoop.hive.cli.CliDriver.run(CliDriver.java:675)
--	at org.apache.hadoop.hive.cli.CliDriver.main(CliDriver.java:614)
--	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
--	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
--	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
--	at java.lang.reflect.Method.invoke(Method.java:606)
--	at org.apache.hadoop.util.RunJar.main(RunJar.java:160)
--
