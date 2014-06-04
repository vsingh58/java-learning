# -S option: only the output of the Hive command was written to the console.
hive -S -f hive-script.pl
#-e option, lets you supply a Hive command as an argument.
hive -S -e "SHOW DATABASES"
# -hiveconf hive.root.logger=INFO,console, more detailed info will be outputed to console
hive -hiveconf hive.root.logger=INFO,console -S -e "SHOW DATABASES"
