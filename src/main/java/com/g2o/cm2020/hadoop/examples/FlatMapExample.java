package com.g2o.cm2020.hadoop.examples;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapExample {
  public static void main(String[] args) throws Exception {
    numbersMap();
    numbersFlatMap();
    allTheWords();
  }

  private static void numbersMap() {
    Stream<Integer> numbers = Stream.of(-1, 0, 1, 2, 3, 4, 5);
    Function<Integer, Stream<Integer>> makeStream = i -> Stream.of(i - 1, i, i + 1);
    List<Stream<Integer>> mapResult = numbers.map(makeStream).collect(Collectors.toList());
    System.out.print("[");
    mapResult.forEach(x -> System.out.print(x.collect(Collectors.toList()) + ", "));
    System.out.println("]");
    //[[-2, -1, 0], [-1, 0, 1], [0, 1, 2], [1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5, 6], ]
  }

  private static void numbersFlatMap() {
    Stream<Integer> numbers = Stream.of(-1, 0, 1, 2, 3, 4, 5);
    Function<Integer, Stream<Integer>> makeStream = i -> Stream.of(i - 1, i, i + 1);
    List<Integer> flatMapResult = numbers.flatMap(makeStream).collect(Collectors.toList());
    System.out.println(flatMapResult);
    //[-2, -1, 0, -1, 0, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5, 6]
  }

  private static void allTheWords() throws Exception {
    String fileName = "magnacarta.txt";
    URI uri = MapExample.class.getClassLoader().getResource(fileName).toURI();
    Stream<String> lines = Files.lines(Paths.get(uri));
    Pattern space = Pattern.compile(" ");
    List<String> words = lines.flatMap(space::splitAsStream).collect(Collectors.toList());
    System.out.println(words);
  }
}
