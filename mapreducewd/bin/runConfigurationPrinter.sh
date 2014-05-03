# To print the default values 
hadoop jar ../target/wordcount-2.0.jar com.myhp.wordcount.GenericOptionsParserHelper.ConfigurationPrinter  -D color="yellow" | grep color
# To print the user defined key: color, size, weight in configuration-oneshot.xml
#hadoop jar ../target/wordcount-2.0.jar com.myhp.wordcount.GenericOptionsParserHelper.ConfigurationPrinter -conf ../conf/configuration-oneshot.xml
# Try command line seting
#$ hadoop jar ../target/wordcount-2.0.jar com.myhp.wordcount.GenericOptionsParserHelper.ConfigurationPrinter -D hello="aaa" | grep hello
#hello=aaa

