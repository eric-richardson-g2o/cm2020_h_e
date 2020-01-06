package com.g2o.cm2020.hadoop.examples;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class ReduceByKeyExample {
  /**
   * <pre>
   *  #####
   * #     #  ####    ##   #        ##
   * #       #    #  #  #  #       #  #
   *  #####  #      #    # #      #    #
   *       # #      ###### #      ######
   * #     # #    # #    # #      #    #
   *  #####   ####  #    # ###### #    #
   * </pre>
   * <p>
   * <pre>
   * ######
   * #     # ######  ####  #    # # #####  ###### #####
   * #     # #      #    # #    # # #    # #      #    #
   * ######  #####  #    # #    # # #    # #####  #    #
   * #   #   #      #  # # #    # # #####  #      #    #
   * #    #  #      #   #  #    # # #   #  #      #    #
   * #     # ######  ### #  ####  # #    # ###### #####
   * </pre>
   *
   * <p>You must have scala 2.12 installed to run this example</p>
   *
   * @param args
   */
  public static void main(String[] args) {
    SparkConf conf = new SparkConf().setAppName("MapReduceExample").setMaster("local");
    JavaSparkContext sc = new JavaSparkContext(conf);
    String sentence = "to be or not to be";
    JavaRDD<String> words = sc.parallelize(Arrays.asList(sentence.split(" ")));
    JavaPairRDD<String, Integer> pairs = words.mapToPair(s -> new Tuple2<String,Integer>(s, 1));
    JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
    counts.take(10).forEach(System.out::println);
  }

}