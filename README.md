# CodeMash 2020 Hadoop Essentials

This repository has the example code and hands-on project.

## Examples
There are examples for the four functions we talk about

### Map

`src/main/java/com/g2o/cm2020/hadoop/examples/MapExample.java`

Uses java.nio.streams.Stream class and Java functions to demonstrate how the map function works.

### Flat Map

`src/main/java/com/g2o/cm2020/hadoop/examples/FlatMapExample.java`

Uses java.nio.streams.Stream class and Java functions to demonstrate how the flat map function works.

### Reduce

`src/main/java/com/g2o/cm2020/hadoop/examples/ReduceExample.java`

Uses java.nio.streams.Stream class and Java functions to demonstrate how Reduce works and how one must be careful to make reduce functions associative.

### Reduce By Key

`src/main/java/com/g2o/cm2020/hadoop/examples/ReduceByKeyExample.java`

Uses Spark to demonstrate the reduce by key function.
To run that example, you must have Scala 2.12 installed.

## Word Count Tutorial

Designed to run on a Hadoop Sandbox.

### Instructions for Hortonworks HDP Sandbox v3.0.1

 1. Download and install sandbox according to instructions https://www.cloudera.com/downloads/hortonworks-sandbox.html
 1. Start the cluster
 1. Ensure you have Maven
 1. Login to the cluster manager
    
    http://127.0.0.1:8080
    user: raj_ops
    password: raj_ops

 1. Ensure the essential cluser components are running
    * HDFS
    * Yarn
    * ZooKeeper
    * MapReduce2
    
    Having other services running is OK, don't bother shutting down other services unless your device is severly resource constrained.
 1. Load the data into HDFS
    1. In cluster manager upper right click on the 9 cube and select File View
    1. Navigate to /tmp and Click Upload button (upper right)
    1. find `src/main/resources/magnacarta.txt` and upload it

 1. Complete the code in /src/main/java/com/g2o/cm2020/hadoop/WordCount.java
 1. Build the jar file
 
     `cd /...to where you downloaded the git repository...`
     
     `mvn clean package`
     
 1. Copy the jar to the cluster
 
    `scp -P 2222 ./target/cm2020_h_e-1.0-SNAPSHOT.jar raj_ops@sandbox-hdp.hortonworks.com:/home/raj_ops`
    
    or if you don't have the hostname mapping setup
    
    `scp -P 2222 ./target/cm2020_h_e-1.0-SNAPSHOT.jar raj_ops@127.0.0.1:/home/raj_ops`

    Password: raj_ops
    
 1. ssh into the cluster
 
    `ssh raj_ops@sandbox-hdp.hortonworks.com -p 2222`
    
    or if you don't have the hostname mapping setup
    
    `ssh raj_ops@127.0.0.1 -p 2222`
    
    Password: raj_ops
    
 1. Run the MapReduce job
  
     `hadoop jar cm2020_h_e-1.0-SNAPSHOT.jar com.g2o.cm2020.hadoop.mapreduce.WordCount /tmp/magnacarta.txt /tmp/output.0`
     
     Command reference can be found [here](https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/CommandsManual.html#jar)
     
     `<jar> = cm2020_h_e-1.0.SNAPSHOT.jar`
     
     `mainClass = com.g2o.cm2020.hadoop.mapreduce.WordCount`
     
     `arg0` (input file) `=/tmp/magnacarta.txt`
     
     `arg1` (output directory) `= /tmp/output.0`
     
 1. Check your output
  
     `hadoop fs -ls /tmp/output.0`
     
     Command refrence can be found [here](https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/CommandsManual.html#fs)
    
     `hadoop fs -cat /tmp/output.0/part-r-00000`
 1. If you need to rerun the application, the output directory is not allowed to exist, so you will need to remove the previous run
 
    `hadoop fs -rm -R -skipTrash /tmp/output.0`
    
    or change the output directory
    
    `hadoop jar cm2020_h_e-1.0-SNAPSHOT.jar com.g2o.cm2020.hadoop.mapreduce.WordCount /tmp/magnacarta.txt /tmp/output.1`
       
### Instructions for Cloudera CDH Sandbox

TBD