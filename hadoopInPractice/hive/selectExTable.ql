--add jar $HIVE_HOME/bin/hive/lib/hive-contrib-0.12.0.jar; not work here.
add jar /home/zhishan/bin/hive/lib/hive-contrib-0.12.0.jar;
select host, request FROM logs_20120101 LIMIT 10;

-- run it, selected field divided by Tab
--0> hive -S -f selectExTable.ql 
--14/06/04 23:48:27 WARN conf.HiveConf: DEPRECATED: Configuration property hive.metastore.local no longer has any effect. Make sure to provide a valid value for hive.metastore.uris if you are connecting to a remote metastore.
--89.151.85.133	"GET /movie/127Hours HTTP/1.1"
--212.76.137.2	"GET /movie/BlackSwan HTTP/1.1"
--74.125.113.104	"GET /movie/TheFighter HTTP/1.1"
--212.76.137.2	"GET /movie/Inception HTTP/1.1"
--127.0.0.1	"GET /movie/TrueGrit HTTP/1.1"
--10.0.12.1	"GET /movie/WintersBone HTTP/1.1"

